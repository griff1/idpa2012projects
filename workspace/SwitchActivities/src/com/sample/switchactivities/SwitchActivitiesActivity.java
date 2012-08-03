package com.sample.switchactivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SwitchActivitiesActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
    }

	public void onClick(View v) 
	{
		Intent intent = new Intent(this, CalculatorActivity.class);
		this.startActivity(intent);
	}
}