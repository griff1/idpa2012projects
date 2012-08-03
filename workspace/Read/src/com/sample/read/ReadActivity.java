package com.sample.read;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.sample.io.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReadActivity extends Activity implements OnClickListener{
	/** Called when the activity is first created. */
	Button b1;
	EditText box;
	EditText box2;
	Button b2;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		box = (EditText) findViewById(R.id.editText1);
		box2 = (EditText) findViewById(R.id.editText2);
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
	}
	public void onClick(View v) 
	{ 
		if(v.equals(b1))
		{
			String s1 = box.getText().toString();
			String s2 = box2.getText().toString();
			OutputStreamWriter out;
			try {
				out = new OutputStreamWriter(openFileOutput(s1, Context.MODE_WORLD_READABLE));
				out.write(s2);
				out.close();
				//Toast.makeText(this, "File created", Toast.LENGTH_LONG).show();
			} catch (Exception e) {} 
			box.setText("");
			box2.setText("");
		}
		else
		{
			String s1 = box.getText().toString();
			InputStream in;
			try { 
				in = openFileInput(s1);
				String str;
				if(in != null)
				{
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					while((str = reader.readLine()) != null)
					{
						Toast.makeText(this, str, Toast.LENGTH_LONG).show();
					}
				}
				in.close();
			} catch (Exception e) {}
		}
	}
}