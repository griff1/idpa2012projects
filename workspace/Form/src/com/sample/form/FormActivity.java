package com.sample.form;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FormActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button submit = (Button) findViewById(R.id.button1);
        submit.setOnClickListener(this);
    }

	public void onClick(View v) 
	{
		EditText 
		String name = 
	}
}