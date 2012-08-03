package com.sample.bluetoothtest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BluetoothTestActivity extends Activity {
	/** Called when the activity is first created. */
	EditText t1;
	EditText t2;
	final int REQUEST_ENABLE_BT = 1;
	BluetoothAdapter mBluetoothAdapter;
	BluetoothDevice device;
	String message;
	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			t2.setText(msg.toString());
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		t1 = (EditText) findViewById(R.id.editText1);
		t2 = (EditText) findViewById(R.id.editText2);

		
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		System.out.println("So far, so good - 1");
		Toast.makeText(this, "So far so good - 1", Toast.LENGTH_LONG).show();
		if (mBluetoothAdapter == null) 
		{
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("No Bluetooth.");
			alert.setMessage("This application requires Bluetooth and your device does not support it.  " +
					"The application will now close.");
			alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int arg1) {

				}
			});
			alert.show();
			finish();
		}
		else if (!mBluetoothAdapter.isEnabled()) 
		{
			Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		//Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		// If there are paired devices
		//if (pairedDevices.size() > 0) {
			// Loop through paired devices
			//for (BluetoothDevice device : pairedDevices) {
				// Add the name and address to an array adapter to show in a ListView
			//	list.add(device.getName() + "\n" + device.getAddress());
			//}
		//}
		
		// Create a BroadcastReceiver for ACTION_FOUND
		// Register the BroadcastReceiver

		System.out.println("So far, so good - 2");
		Toast.makeText(this, "So far so good - 2", Toast.LENGTH_LONG).show();

		Intent intent = new Intent(this, DeviceList.class);
		this.startActivity(intent);
	}
	public void onResume()
	{
		mBluetoothAdapter.cancelDiscovery();
		Bundle bundle = getIntent().getExtras();
		device = (BluetoothDevice)bundle.getParcelable("device");
		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		startActivity(discoverableIntent);
		
		message = t1.getText().toString();
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle("Host or client");
		alert.setMessage("Would you like to host this connection, or act as the client?");
		alert.setPositiveButton("Host", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1) 
			{
				AcceptThread accept = new AcceptThread();
				accept.start();
			}
		});
		alert.setPositiveButton("Client", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int arg1)
			{
				ConnectThread connect = new ConnectThread(device);
				connect.start();
			}
		});
		alert.show();
		
		//AcceptThread accept = new AcceptThread();
		//accept.start();
		//ConnectThread connect = new ConnectThread(device);
		//connect.start();
	}
    private class AcceptThread extends Thread 
    {
        private final BluetoothServerSocket mmServerSocket;
     
        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord("Griff", 
                		UUID.fromString("bc2a6490-dc1e-11e1-9b23-0800200c9a66"));
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }
     
        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    ConnectedThread thread = new ConnectedThread(socket);
                    thread.start();
                    thread.write(message.getBytes());
                    try {
						mmServerSocket.close();
					} catch (IOException e) {}
                    break;
                }
            }
        }
     
        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }
    private class ConnectThread extends Thread 
    {
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;
     
        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket,
            // because mmSocket is final
            BluetoothSocket tmp = null;
            mmDevice = device;
     
            // Get a BluetoothSocket to connect with the given BluetoothDevice
            try {
                // MY_UUID is the app's UUID string, also used by the server code
                tmp = device.createRfcommSocketToServiceRecord(
                		UUID.fromString("bc2a6490-dc1e-11e1-9b23-0800200c9a66"));
            } catch (IOException e) { }
            mmSocket = tmp;
        }
     
        public void run() {
            // Cancel discovery because it will slow down the connection
            mBluetoothAdapter.cancelDiscovery();
     
            try {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
            } catch (IOException connectException) {
                // Unable to connect; close the socket and get out
                try {
                    mmSocket.close();
                } catch (IOException closeException) { }
                return;
            }
     
            // Do work to manage the connection (in a separate thread)
            ConnectedThread thread = new ConnectedThread(mmSocket);
            thread.start();
            thread.write(message.getBytes());
        }
     
        /** Will cancel an in-progress connection, and close the socket */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
     
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
     
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
     
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
     
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
     
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                    // Send the obtained bytes to the UI activity
                    mHandler.obtainMessage(1, bytes, -1, buffer)
                            .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
     
        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }
     
        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }
} 