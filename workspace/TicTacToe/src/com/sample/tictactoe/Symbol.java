package com.sample.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Symbol 
{
	private int x, y;
	Bitmap img;
	String sym;
	public Symbol(Context context, int tempx, int tempy, char symbol)
	{
		x = tempx;
		y = tempy;
		sym = symbol + "";
		if(symbol == 'X')
			img = BitmapFactory.decodeResource(context.getResources(), R.drawable.x);
		else 
			img = BitmapFactory.decodeResource(context.getResources(), R.drawable.o);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Bitmap getImg()
	{
		return img;
	}
	public void setX(int tempx)
	{
		x = tempx;
	}
	public void setY(int tempy)
	{
		y = tempy;
	}
	public String toString()
	{
		return sym;
	}
}
