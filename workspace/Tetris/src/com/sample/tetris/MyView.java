package com.sample.tetris;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MyView extends View implements OnClickListener
{
	Button buttons[] = new Button[7];
	Paint paint;
	boolean locked[][] = new boolean[12][8];
	Context context;
	Shape shape;
	Random rand;
	boolean currentShape = false;
	public MyView(Context context)
	{
		super(context);
		this.context = context;
        buttons[0] = (Button) findViewById(R.id.button1);		//Turn
        buttons[1] = (Button) findViewById(R.id.button2);		//Speed up
        buttons[2] = (Button) findViewById(R.id.button3);		//Left
        buttons[3] = (Button) findViewById(R.id.button4);		//Right
        
        for(int i = 0; i < 4; i++)
        {
        	buttons[i].setOnClickListener(this);
        }
        rand = new Random();
	}
	public void onDraw(Canvas c)
	{
		paint = new Paint();
		paint.setAntiAlias(true);
	
		if(!currentShape)
		{
			int temp = rand.nextInt(7);
			if(temp == 0)
				shape = new Shape("center");
			if(temp == 1)
				shape = new Shape("l-shape");
			if(temp == 2)
				shape = new Shape("l-shape2");
			if(temp == 3)
				shape = new Shape("square");
			if(temp == 4)
				shape = new Shape("stacked");
			if(temp == 5)
				shape = new Shape("stacked2");
			if(temp == 6)
				shape = new Shape("straight");
		}
	}
	public void onClick(View v) 
	{
		if(v.equals(buttons[0]))
		{
			
		}
		if(v.equals(buttons[1]))
		{
			
		}
		if(v.equals(buttons[2]))
		{
			
		}
		if(v.equals(buttons[3]))
		{
			
		}
	}
}
