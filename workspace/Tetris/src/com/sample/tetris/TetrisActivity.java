package com.sample.tetris;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.*;

public class TetrisActivity extends Activity
{
    /** Called when the activity is first created. */
	MyView view;
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        view = new MyView(this);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout2);
        layout1.addView(view);
    }
}