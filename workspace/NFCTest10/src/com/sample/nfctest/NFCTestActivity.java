package com.sample.nfctest;

import android.app.Activity;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.Toast;

public class NFCTestActivity extends Activity {

	EditText input;
	EditText output;
	NfcAdapter mAdapter;
	NdefMessage msgs[];
	NdefRecord msg;
	NfcAdapter nfcAdapter;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		input = (EditText) findViewById(R.id.editText1);
		output = (EditText) findViewById(R.id.editText2);
		nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null) 
		{
			Toast.makeText(this, "No NFC.  Sucks to suck.", Toast.LENGTH_LONG);
			System.out.println("No NFC.  Sucks to suck.");
			return;
		}
	}
	public void onResume() {
	    super.onResume();
	    msg = new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, "text/plain".getBytes(), 
	    		new byte[0], input.getText().toString().getBytes());
	    input.setText("");
	    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
	    	Intent intent = getIntent();
	        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	        if (rawMsgs != null) {
	            msgs = new NdefMessage[rawMsgs.length];
	            for (int i = 0; i < rawMsgs.length; i++) {
	                msgs[i] = (NdefMessage) rawMsgs[i];
	            }
	        }
	        for (int i = 0; i < rawMsgs.length; i++)
                output.append(msgs[i].toString());
	        
	    }
	    //process the msgs array
	    NdefRecord send[] = {msg};
	    try{
	    	nfcAdapter.setNdefPushMessage(new NdefMessage(send), this);
	    }catch(Exception e){}
	}
}