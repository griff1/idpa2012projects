package com.sample.threading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class ThreadingActivity extends Activity implements OnClickListener
{
	Button b1;
	Button b2;
	ProgressBar bar;
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(isRunning)
			{
				bar.incrementProgressBy(5);
			}
		}
	};
	boolean isRunning = false;
	Thread back = new Thread(new Runnable(){
		public void run(){
			try{
				while(bar.getProgress() < 100){
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage());
				}
			}
			catch(Throwable e){
			}
		}
	});
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bar = (ProgressBar) findViewById(R.id.progressBar1);
		
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}
	public void onStart()
	{
		super.onStart();
		bar.setProgress(0);
		isRunning = true;
		back.start();
	}
	public void onStop()
	{
		super.onStop();
		isRunning = false;
	}
	public void onClick(View v) 
	{
		if(v.equals((Button) findViewById(R.id.button1)))
		{	
			isRunning = true;
		}
		else
		{
			isRunning = false;
		}
	}
}