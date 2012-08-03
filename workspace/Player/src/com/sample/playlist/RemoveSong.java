package com.sample.playlist;

import java.io.File;
import java.util.ArrayList;

import com.sample.blank.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RemoveSong extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
	ArrayList<String> songs = new ArrayList<String>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	LinearLayout l;
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);
        
        l = (LinearLayout) findViewById(R.id.linearLayout1);
        Bundle bundle = getIntent().getExtras();
        songs = bundle.getStringArrayList("songs");
        
    	buttons = new ArrayList<Button>();
    	buttons.trimToSize();
        for(int i = 0; i < songs.size(); i++)
        {
        	Button b = new Button(this);
        	b.setId(i);
        	b.setText(songs.get(i));
        	b.setWidth(300);
        	b.setHeight(100);
        	b.setOnClickListener(this);
        	l = (LinearLayout) findViewById(R.id.linearLayout1);
        	l.addView(b);
        	buttons.add(b);
        }    
    }
	public void onClick(View v) 
	{
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("index", v.getId());
		l.removeView(v);
    	buttons.trimToSize();
		this.startActivity(intent);
	}
}