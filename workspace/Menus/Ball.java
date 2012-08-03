package com.dky.bouncy;

import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ball {
	private int[] coords = new int[2];
	
	private int radius;

	private int color;
	
	private Random generator = new Random();
	
	private int xVel = generator.nextInt(20) - 10;
	private int yVel = generator.nextInt(20) - 10;
	
	private final int SCREENRIGHT = 480;
	private final int SCREENBOTTOM = 700;
	
	private Bitmap img;
	
	private Rect rect;
	
	public Ball(Context context, int x, int y, int radius, int blue) {
		//Log.d("myStuff", Integer.toString(x));
		coords[0] = x;
		coords[1] = y;
		rect = new Rect(x, y, radius);
		
		this.radius = radius;
		this.color = blue;
		
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(), R.drawable.meteoroid);
	}
	
	public int[] getCoords() {
		return coords;
	}

	public int getRadius() {
		return radius;
	}
	
	public int getColor() {
		return color;
	}
	
	public int[] getVelocities() {
		int[] retVal = {xVel, yVel}; 
		return retVal;
	}
	
	public Bitmap getImage() {
		return img;
	}
	
	public Rect getRect() {
		return rect;
	}
	
	public void update() {
		if (rect.right >= SCREENRIGHT || 
				rect.left <= 0) {
			xVel *= -1;
		}
		
		if (rect.bottom >= SCREENBOTTOM || 
				rect.top <= 0) {
			yVel *= -1;
		}
		
		coords[0] += xVel;
		coords[1] += yVel;
		
		rect.set(coords[0], coords[1]);
	}

	public void collide(Ball otherBall) {
		/* Right */
		if ((this.rect.right >= otherBall.getRect().left &&
				this.rect.left < otherBall.getRect().left) &&
					(this.rect.bottom >= otherBall.getRect().top &&
							this.rect.top <= otherBall.getRect().bottom))
			if (xVel > 0) xVel *= -1;
		
		/* Left */
		if ((this.rect.left <= otherBall.getRect().right &&
				this.rect.right > otherBall.getRect().right) &&
					(this.rect.bottom >= otherBall.getRect().top &&
							this.rect.top <= otherBall.getRect().bottom))
			if (xVel < 0) xVel *= -1;

		/* Bottom */
		if ((this.rect.bottom >= otherBall.getRect().top &&
				this.rect.top < otherBall.getRect().top) &&
					(this.rect.right >= otherBall.getRect().left &&
							this.rect.left < otherBall.getRect().right))
			if (yVel > 0) yVel *= -1;

		/* Top */
		if ((this.rect.top <= otherBall.getRect().bottom &&
				this.rect.bottom > otherBall.getRect().bottom) &&
					(this.rect.right >= otherBall.getRect().left &&
							this.rect.left < otherBall.getRect().right))
			if (yVel < 0) yVel *= -1;
	}
	
	public void setCoords(int x, int y) {
		coords[0] = x;
		coords[1] = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setColor(int color) {
		this.color = color;
	}
	
	public void setVelocity(boolean isX, int vel) {
		if (isX) xVel = vel;
		else yVel = vel;
	}
	
	private class Rect {
		private int radius;

		public int left;
		public int right;
		public int top;
		public int bottom;
		
		public Rect(int x, int y, int radius) {
			this.radius = radius;
			set(x, y);
		}
		
		public void set(int x, int y) {
			left = x - radius;
			right = x + radius;
			top = y - radius;
			bottom = y + radius;
		}
	}
}
