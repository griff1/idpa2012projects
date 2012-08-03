package com.sample.tictactoe;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;

public class MyView extends View
{
	Context context;
	private Paint paint;
	static ArrayList<Symbol> symbols = new ArrayList<Symbol>();
	public MyView(Context context) 
	{
		super(context);
		this.context = context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	public void onDraw(Canvas c) 
	{ 
		System.out.println(symbols);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.rgb(0, 0, 0));
		paint.setStrokeWidth(2);
		c.drawRect(75, 75, 75, 300, paint);
		c.drawRect(150, 75, 150, 300, paint);
		c.drawRect(25, 150, 200, 150, paint);
		c.drawRect(25, 225, 200, 225, paint);
				
		for(Symbol s : symbols)
			c.drawBitmap(s.getImg(), s.getY(), s.getX(), null);
		
		invalidate();
	}
	public Symbol resetCoords(Symbol s)
	{
			if(s.getX() == 0)
				s.setX(150);
			if(s.getY() == 0)
				s.setY(60);
			if(s.getX() == 1)
				s.setX(270);
			if(s.getY() == 1)
				s.setY(180);
			if(s.getX() == 2)
				s.setX(390);
			if(s.getY() == 2)
				s.setY(300);
			return s;
	}
	public void addSymbol(Symbol temp)
	{
		symbols.add(resetCoords(temp));
	}
}