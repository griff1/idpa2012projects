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

public class AddSong extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
	ArrayList<String> songs = new ArrayList<String>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	ArrayList<String> paths = new ArrayList<String>();
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        File file = new File("/storage/sdcard0/Music");
        String names[] = file.list();
        if(names == null)
        {
            Toast.makeText(this, "No songs.", Toast.LENGTH_LONG).show();
    		Intent intent = new Intent(this, MainActivity.class);
    		this.startActivity(intent);
        }
        else
        {        
        	listAllSongs("/storage/sdcard0/Music", names);
        	bubbleSort();
            for(int i = 0; i < songs.size(); i++)
            {
            	Button b = new Button(this);
            	b.setId(i);
            	b.setText(songs.get(i));
            	b.setWidth(300);
            	b.setHeight(100);
            	b.setOnClickListener(this);
            	LinearLayout l = (LinearLayout) findViewById(R.id.linearLayout1);
            	l.addView(b);
            	buttons.add(b);
            }
        }
    }
    public void bubbleSort()
    {
    	boolean sorted = false;
    	while(!sorted)
    	{
    		sorted = true;
    		for(int i = 0; i < songs.size()-1; i++)
    		{
    			if(songs.get(i).compareTo(songs.get(i+1)) > 0)
    			{
    				sorted = false;
    				String temp = songs.get(i);
    				songs.set(i, songs.get(i+1));
    				songs.set(i+1, temp);
    				
    				String temp2 = paths.get(i);
    				paths.set(i, paths.get(i+1));
    				paths.set(i+1, temp2);
    			}
    		}
    	}
    }
    public void listAllSongs(String path, String[] names)
    {
        for(int i = 0; i < names.length; i++)
        {
        	File file2 = new File(path + "/" + names[i]);
        	String[] newNames = file2.list();
        	if(newNames != null)
        	{
        		listAllSongs(path + "/" + names[i], newNames);
        		names[i] = "";
        	}
        	if(!names[i].equals(""))
        	{
        		String s1 = ""; 		
        		for(int q = names[i].length()-1; q > names[i].length() - 4; q--)
        			s1 = names[i].charAt(q) + s1;
        		if(s1.equals("mp3") || s1.equals("mp4") || s1.equals("aac") || s1.equals("wav"))
        		{
        			paths.add(path + "/" + names[i]);
        			songs.add(names[i]);
        		}
        	}
        }
    }
	public void onClick(View v) 
	{
		String path = paths.get(v.getId());
		String name = songs.get(v.getId());
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("path", path);
		intent.putExtra("name", name);
		this.startActivity(intent);
	}
}