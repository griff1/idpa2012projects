package com.sample.toastprac;

import com.sample.toastprac.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ToastPracActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    EditText box; 

    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        box = (EditText) findViewById(R.id.editText1);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
    }
    
	public void onClick(View v) 
	{
		String s1 = box.getText() + "";
		box.setText("");
		Toast.makeText(this, s1, Toast.LENGTH_LONG).show();
	}
}