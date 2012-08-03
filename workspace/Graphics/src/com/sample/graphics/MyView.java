package com.sample.graphics;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyView extends View implements OnTouchListener {
	private final int[] colors = {Color.RED, Color.GREEN, Color.BLUE,
								  Color.CYAN, Color.MAGENTA, Color.YELLOW};
	
	private final int numBalls = 3;
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private Paint paint;
	
	private Random generator = new Random();
	
	private Context context;
	
	public MyView(Context context) {
		super(context);
		this.context = context;
		setFocusable(true);
		for (int i=0; i<numBalls; i++) {
			Ball newBall = new Ball(context, generator.nextInt(400) + 20,
					generator.nextInt(600) + 20, 15, colors[generator.nextInt(6)]); 
			balls.add(newBall);
		}
		setFocusableInTouchMode(true);
		
		this.setOnTouchListener(this);
	}
	
	public void onDraw(Canvas c) {
		paint = new Paint();
		paint.setAntiAlias(true);
		
		for (Ball b : balls) {
			b.update();
			
			for (Ball b2 : balls) {
				if (b != b2) b.collide(b2);
			}
			
			paint.setColor(b.getColor());
			//c.drawCircle(b.getCoords()[0], b.getCoords()[1], b.getRadius(), paint);
			c.drawBitmap(b.getImage(), b.getCoords()[0], b.getCoords()[1], null);
		}
		
		invalidate();
	}

	public boolean onTouch(View v, MotionEvent event) {
		Ball newBall = new Ball(context, (int) event.getX(), (int) event.getY(),
				15, colors[generator.nextInt(6)]);
		balls.add(newBall);
		return true;
	}
}
