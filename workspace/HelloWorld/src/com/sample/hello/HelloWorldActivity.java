package com.sample.hello;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        ToggleButton y = (ToggleButton) findViewById(R.id.toggleButton1);
        
        CheckBox z = (CheckBox) findViewById(R.id.checkBox1);
        
		z.setOnClickListener(new OnClickListener()
		{
			TextView x = (TextView) findViewById(R.id.textView1);
	        int counter = 0;
			public void onClick(View view){
				if(counter % 2 == 0)
					x.setText("WOOT");
				else
					x.setText("Hi");
				counter++;
		    }
		});		
		y.setOnClickListener(new OnClickListener()
		{
			EditText x = (EditText) findViewById(R.id.editText1); 
	        int counter = 0;
			public void onClick(View view){
				if(counter % 2 == 0)
					x.setText("WOOT");
				else
					x.setText("Hi");
				counter++;
		    }
		});
    }
}