package com.sample.getfilenames;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GetFileNamesActivity extends Activity 
{
	ArrayList<String> songs = new ArrayList<String>();
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override 
    public void onStart()
    {
    	super.onStart();
    	
    	Toast.makeText(this, "Hello", Toast.LENGTH_LONG).show();
        
        File file = new File("/storage/sdcard0/Music");
        String names[] = file.list();
        if(names == null)
        {
        	Toast.makeText(this, "You have no songs.", Toast.LENGTH_LONG).show();
        	return;
        }
        else
        {
            for(int i = 0; i < names.length; i++)
            {
            	Toast.makeText(this, names[i], Toast.LENGTH_LONG).show();
            }
        }
        listAll("/storage/sdcard0/Music", names);
        bubbleSort();
        
        for(int i = 0; i < songs.size(); i++)
        {
        	Button b = new Button(this);
        	b.setId(i+1);
        	b.setText(songs.get(i));
        	LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout1);
        	l.addView(b);
        }
    }
    public void bubbleSort()
    {
    	boolean sorted = false;
    	while(!sorted)
    	{
    		for(int i = 0; i < songs.size()-1; i++)
    		{
    			if(songs.get(i).compareTo(songs.get(i+1)) > 0)
    			{
    				String temp = songs.get(i);
    				songs.set(i, songs.get(i+1));
    				songs.set(i+1, temp);
    			}
    		}
    	}
    }
    public void listAll(String path, String[] names)
    {
        for(int i = 0; i < names.length; i++)
        {
        	File file2 = new File(path + "/" + names[i]);
        	String newNames[] = file2.list();
        	if(newNames != null || newNames.length == 0)
        	{
        		names[i] = null;
        		listAll(path + "/" + names[i], newNames);
        	}
        	if(names[i] != null)
        	{
        		songs.add(names[i]);
        		
        	}
        }
    }
}