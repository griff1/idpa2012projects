/*
 * Copyright (c) 2007-2010 Inside Secure, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*******************************************************************************

Implementation of the standard CCClient trace client.

*******************************************************************************/

#include "ccclient.h"
#include "ccclient_md.h"

#include <linux/mutex.h>
#include <net/sock.h>
#include <linux/tcp.h>

static unsigned long nInitialised = 0;

bool_t CCInterlockedEnter()
{
   int nValue= test_and_set_bit(0, &nInitialised);

   return (nValue == 0) ? W_FALSE : W_TRUE;
}

typedef struct __OSCriticalSection
{
   struct mutex mutex;

} OSCriticalSection;

void CCCreateCriticalSection(
      CCCriticalSection* pCS)
{
   mutex_init( & ((OSCriticalSection*)pCS)->mutex);
}

void CCDestroyCriticalSection(
      CCCriticalSection* pCS)
{
   mutex_destroy(& ((OSCriticalSection*)pCS)->mutex);
}

void CCEnterCriticalSection(
      CCCriticalSection* pCS)
{
   mutex_lock(& ((OSCriticalSection*)pCS)->mutex);
}

void CCLeaveCriticalSection(
      CCCriticalSection* pCS)
{
   mutex_unlock(& ((OSCriticalSection*)pCS)->mutex);
}

void CCInitializeTime(void)
{
}

void CCGetAbsoluteRawTime(
               char* pBuffer,
               uint32_t nMaxLength)
{
   snprintf(pBuffer, nMaxLength, "%ld", (long) (jiffies * 1000 / HZ));
}

void CCGetRelativeTime(
               char* pBuffer,
               uint32_t nMaxLength)
{
   snprintf(pBuffer, nMaxLength, "%ld", (long) (jiffies * 1000 / HZ));
}

void CCPrintError(
            const char* pString)
{
   printk(pString);
}

void CCDefaultPrintf(
               uint32_t nTraceLevel,
               const char* pString)
{
   printk(pString);
}

uint32_t CCStrLen(
               const char* pString,
               uint32_t nMaxLength)
{
   return (uint32_t)strnlen(pString, nMaxLength);
}


void CCStrCat(
               char* pString,
               uint32_t nMaxLength,
               const char* pAppend)
{
   strncat(pString, pAppend, nMaxLength);
}

uint32_t CCVSPrintf(
               char* pString,
               uint32_t nMaxLength,
               const char* pFormat,
               va_list list)
{
   return (uint32_t)vsnprintf( pString, nMaxLength, pFormat, list);
}

typedef struct __OSSocket
{
   struct socket * socket;

} OSSocket;


uint32_t CCSocketCreate(
               const char* pAddress,
               CCSocket* pSocket)
{
   struct socket * socket;
   struct sockaddr_in sockaddr;
   int bTCPNoDelay = W_TRUE;
   int error;
   char * end;
   uint32_t nInetAddr;
   uint32_t nError = CC_SUCCESS;

   /* create the socket */
   error = sock_create(PF_INET, SOCK_STREAM, IPPROTO_TCP, &socket);

   if (error < 0) {

      printk(KERN_ERR "static_CCClientCreateSocket : sock_create() failed %d\n", error);
      nError = CC_ERROR_SOCKET_OPERATION;

      goto return_error;
   }

   /* set TCP_NODELAY option */
   error = kernel_setsockopt(socket, SOL_TCP, TCP_NODELAY, (char *)&bTCPNoDelay, sizeof(bTCPNoDelay));

   if (error < 0) {

      printk(KERN_ERR "static_CCClientCreateSocket / kernel_setsockopt() failed %d ", error);
      nError = CC_ERROR_SOCKET_OPERATION;

      goto return_error;
   }

   /* establish TCP connection with the connection center */

   nInetAddr = (uint32_t) simple_strtol(pAddress, &end, 10);

   sockaddr.sin_family = AF_INET;
   sockaddr.sin_addr.s_addr = nInetAddr;
   sockaddr.sin_port = htons(14443);

   error = kernel_connect(socket, (struct sockaddr *)&sockaddr, sizeof(sockaddr), 0);

   if (error < 0) {
      printk(KERN_ERR "static_CCClientCreateSocket / kernel_connect() failed %d ", error);

      nError = CC_ERROR_CONNECTION_FAILURE;
      goto return_error;
   }

   nError = CC_SUCCESS;


return_error:

   if(nError != CC_SUCCESS) {

      if (socket != NULL) {
         sock_release(socket);
         socket = NULL;
      }
   }

   ((OSSocket*)pSocket)->socket = socket;

    return nError;
}

void CCSocketShutdownClose(
               CCSocket* pSocket)
{
   if(((OSSocket*)pSocket)->socket != NULL)
   {
      kernel_sock_shutdown(((OSSocket*)pSocket)->socket, SHUT_RDWR);
      sock_release(((OSSocket*)pSocket)->socket);
      ((OSSocket*)pSocket)->socket = NULL;
   }
}

int32_t CCSocketReceive(
            CCSocket* pSocket,
            uint8_t* pBuffer,
            uint32_t nLength,
            bool_t bWait)
{
   int32_t nOffset = 0;
   int32_t res;
   struct socket * socket = ((OSSocket*)pSocket)->socket;
   int nFlags = 0;

   if (bWait == W_FALSE)
   {
      nFlags = MSG_DONTWAIT;
   }

   while (nLength != 0)
   {
      struct msghdr msg;
      struct kvec   iov;

      msg.msg_name = 0;
      msg.msg_namelen = 0;
      msg.msg_control = NULL;
      msg.msg_controllen = 0;
      msg.msg_flags = nFlags;

      iov.iov_base = (void *)  (pBuffer + nOffset);
      iov.iov_len = (size_t)   nLength;

      res = kernel_recvmsg(socket, &msg, &iov, 1,  nLength, nFlags);

      if(res < 0)
      {
         if ((res == -EWOULDBLOCK) || (res == -EAGAIN))
         {
            res = 0;
         }
         else
         {
            return res;
         }
      }
      else if(res == 0)
      {
         /* Socket gracefully shutdown */
         return -1;
      }

      if(res == 0)
      {
         if((nOffset == 0) && (bWait == W_FALSE))
         {
            return 0;
         }

         nFlags = 0;
      }
      else
      {
         nOffset += res;
         nLength -= res;
      }
   }

   return 1;
}

bool_t CCSocketSend(
            CCSocket* pSocket,
            const uint8_t* pBuffer,
            uint32_t nLength)
{
   int32_t res;
   int32_t nOffset = 0;
   struct socket * socket = ((OSSocket*)pSocket)->socket;
   int flags = MSG_DONTWAIT;

   while(nLength != 0)
   {
      struct msghdr msg;
      struct kvec   iov;

      msg.msg_name = 0;
      msg.msg_namelen = 0;
      msg.msg_control = NULL;
      msg.msg_controllen = 0;
      msg.msg_flags = flags;

      iov.iov_base = (void *)  (pBuffer + nOffset);
      iov.iov_len = (size_t)   nLength;

      res =  kernel_sendmsg(socket, & msg, &iov, 1, nLength);

      /* first write operation is always done non blocking,
        but we want to send the whole message if we've been able to send the first part of it */

      flags = 0;

      if(res < 0)
      {
        return W_FALSE;
      }

      nOffset += res;
      nLength -= res;
   }

   return W_TRUE;
}

void* CCSocketGetReceptionEvent(
            CCSocket* pSocket)
{
   printk(__FUNCTION__ ); printk("\n");

   return (void *) (uintptr_t) ((OSSocket*)pSocket)->socket;
}

void CCSocketSignalReceptionEvent(
            CCSocket* pSocket)
{
   printk(__FUNCTION__ ); printk("\n");

   /* FIXME */
}

void CCGetApplicationName(
            char16_t* pBuffer)
{
   char16_t   aName[] = { 'o','n','f','c','-', 'l','i','n','u','x','-','d','r','i','v','e','r', 0 };

   memcpy(pBuffer, aName, sizeof(aName));
}

void CCGetApplicationIdentifier(
            char16_t* pBuffer)
{
   char16_t   aName[] = { '0', 0 };

   memcpy(pBuffer, aName, sizeof(aName));
}

void * CCMalloc(uint32_t nSize)
{
   return kmalloc(nSize, GFP_KERNEL);
}

/**
 * Frees a memory area previously allocated by CCMalloc()
 *
 * @param[in] pMemory  The address of the memory area to be freed
 */
void  CCFree(void * pMemory)
{
   kfree(pMemory);
}

void CCMemcpy(void * pDest, const void * pSrc, uint32_t nLength)
{
   memcpy(pDest, pSrc, nLength);
}
