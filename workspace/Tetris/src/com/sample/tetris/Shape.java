package com.sample.tetris;

public class Shape 
{
	int r1;
	int c1;
	int r2;
	int c2;
	int r3;
	int c3;
	int r4;
	int c4;
	String shape;
	boolean locked = false;
	public Shape(String shape)
	{
		this.shape = shape;
		if(shape.equals("straight"))
		{
			r1 = 0;
			r2 = 0;
			r3 = 0;
			r4 = 0;
			c1 = 2;
			c2 = 3;
			c3 = 4;
			c4 = 5;
		}
		if(shape.equals("center"))
		{
			r1 = 1;
			r2 = 1;
			r3 = 1;
			r4 = 0;
			c1 = 3;
			c2 = 4;
			c3 = 5;
			c4 = 4;
		}
		if(shape.equals("square"))
		{
			r1 = 0;
			r2 = 0;
			r3 = 1;
			r4 = 1;
			c1 = 3;
			c2 = 4;
			c3 = 3;
			c4 = 4;
		}
		if(shape.equals("stacked"))
		{
			r1 = 1;
			r2 = 1;
			r3 = 0;
			r4 = 0;
			c1 = 3;
			c2 = 4;
			c3 = 4;
			c4 = 5;
		}
		if(shape.equals("l-shape"))
		{
			r1 = 0;
			r2 = 1;
			r3 = 1;
			r4 = 1;
			c1 = 3;
			c2 = 4;
			c3 = 5;
			c4 = 5;
		}
		if(shape.equals("l-shape2"))
		{
			r1 = 1;
			r2 = 1;
			r3 = 1;
			r4 = 0;
			c1 = 3;
			c2 = 4;
			c3 = 5;
			c4 = 5;
		}
		if(shape.equals("stacked2"))
		{
			r1 = 0;
			r2 = 0;
			r3 = 1;
			r4 = 1;
			c1 = 3;
			c2 = 4;
			c3 = 4;
			c4 = 5;
		}
	}
	public void swap()
	{
		//TODO
	}
	public void moveDown()
	{
		r1++;
		r2++;
		r3++;
		r4++;
	}
	public void moveLeft()
	{
		c1--;
		c2--;
		c3--;
		c4--;
	}
	public void moveRight()
	{
		c1++;
		c2++;
		c3++;
		c4++;
	}
	public int[] getRows()
	{
		int array[] = {r1, r2, r3, r4};
		return array;
	}
	public int[] getCols()
	{
		int array[] = {c1, c2, c3, c4};
		return array;
	}
}
