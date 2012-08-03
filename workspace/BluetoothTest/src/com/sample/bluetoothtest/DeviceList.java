package com.sample.bluetoothtest;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class DeviceList extends Activity implements OnClickListener
{
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<BluetoothDevice> devices = new ArrayList<BluetoothDevice>();
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		System.out.println("So far, so good - 3");
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(receiver, filter); 		// Don't forget to unregister during onDestroy
        for(int i = 0; i < list.size(); i++)
        {
        	Button b = new Button(this);
        	b.setId(i);
        	b.setText(list.get(i));
        	b.setWidth(200);
        	b.setHeight(100);
        	b.setOnClickListener(this);
        	LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout1);
        	l.addView(b);
        	buttons.add(b);
        }
	}
	private final BroadcastReceiver receiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	        String action = intent.getAction();
	        // When discovery finds a device
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
	            // Get the BluetoothDevice object from the Intent
	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            // Add the name and address to an array adapter to show in a ListView
	            list.add(device.getName() + "\n" + device.getAddress());
	            devices.add(device);
	        }
	    }
	};
	public void onClick(View v) 
	{
		BluetoothDevice device = devices.get(v.getId());
		Intent intent = new Intent(this, BluetoothTestActivity.class);
		intent.putExtra("device", device);
		this.startActivity(intent);
	}
	public void onDestroy()
	{
		unregisterReceiver(receiver);
	}
}
