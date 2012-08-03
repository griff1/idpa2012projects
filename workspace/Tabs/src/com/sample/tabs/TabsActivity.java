package com.sample.tabs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

public class TabsActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    EditText box; 
    EditText box2;
	double total = 0;
	String next = "";
	int nextOp = 0;		//0 - none, 1 - plus, 2 - minus, 3 - multiplication, 4 - division
	Button buttons[] = new Button[18];
	Button b1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TabHost tabs = (TabHost)findViewById(R.id.tabhost1);
        tabs.setup();
        
        TabHost.TabSpec spec1 = tabs.newTabSpec("tab1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Tab 1");
        tabs.addTab(spec1);
        
        TabHost.TabSpec spec2 = tabs.newTabSpec("tab2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator("Tab 2");
        tabs.addTab(spec2);
        
        TabHost.TabSpec spec3 = tabs.newTabSpec("tab3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator("Tab 3");
        tabs.addTab(spec3);
        

        box = (EditText) findViewById(R.id.editText1);
        b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(this);
        
        
        box2 = (EditText) findViewById(R.id.editText2);
	    buttons[0] = (Button) findViewById(R.id.button11);		//1
	    buttons[1] = (Button) findViewById(R.id.button12);		//2
	    buttons[2] = (Button) findViewById(R.id.button13);		//3
	    buttons[3] = (Button) findViewById(R.id.button8);		//4
	    buttons[4] = (Button) findViewById(R.id.button9);		//5
	    buttons[5] = (Button) findViewById(R.id.button10);		//6
	    buttons[6] = (Button) findViewById(R.id.button5);		//7
	    buttons[7] = (Button) findViewById(R.id.button6);		//8
	    buttons[8] = (Button) findViewById(R.id.button7);		//9
	    buttons[9] = (Button) findViewById(R.id.button2);		//0
	    buttons[10] = (Button) findViewById(R.id.button3);		//neg
	    buttons[11] = (Button) findViewById(R.id.button19);		//.
	    buttons[12] = (Button) findViewById(R.id.button16);		//+
	    buttons[13] = (Button) findViewById(R.id.button15);		//-
	    buttons[14] = (Button) findViewById(R.id.button14);		//*
	    buttons[15] = (Button) findViewById(R.id.button4);		///
	    buttons[16] = (Button) findViewById(R.id.button17);		//=
	    buttons[17] = (Button) findViewById(R.id.button18);		//C
	    
	    box.setText("0");
	    for(int i = 0; i < 18; i++)
	    {
	    	buttons[i].setOnClickListener(this);
	    }
    }
	public void onClick(View v) 
	{
		if(v.equals(b1))
		{
			String s1 = box.getText() + "";
			box.setText("");
			Toast.makeText(this, s1, Toast.LENGTH_LONG).show();
		}
		else
		{
			if(v.equals(buttons[0]))
			{
				next += "1";
				box.setText(next);
			}
			if(v.equals(buttons[1]))
			{
				next += "2";
				box.setText(next);
			}	
			if(v.equals(buttons[2]))
			{
				next += "3";
				box.setText(next);
			}	
			if(v.equals(buttons[3]))
			{
				next += "4";
				box.setText(next);
			}	
			if(v.equals(buttons[4]))
			{
				next += "5";
				box.setText(next);
			}	
			if(v.equals(buttons[5]))
			{
				next += "6";
				box.setText(next);
			}	
			if(v.equals(buttons[6]))
			{
				next += "7";
				box.setText(next);
			}	
			if(v.equals(buttons[7]))
			{
				next += "8";
				box.setText(next);
			}	
			if(v.equals(buttons[8]))
			{
				next += "9";
				box.setText(next);
			}	
			if(v.equals(buttons[9]))
			{
				next += "0";
				box.setText(next);
			}	
			if(v.equals(buttons[10]))
			{
				if(!next.contains("-"))
					next = "-" + next;
				else
					next.replace("-", "");
				box.setText(next);
			}	
			if(v.equals(buttons[11]))
			{
				if(!next.contains("."))
					next += ".";
				box.setText(next);
			}	
			if(v.equals(buttons[12]))
			{
				if(nextOp == 0)
					total = Double.parseDouble(next);
				if(nextOp == 1)
					total += Double.parseDouble(next);
				if(nextOp == 2)
					total -= Double.parseDouble(next);
				if(nextOp == 3)
					total *= Double.parseDouble(next);
				if(nextOp == 4)
					total /= Double.parseDouble(next);
				if(total != 0)
					box.setText(total + "");
				else
					box.setText(next);
				next = "";
				nextOp = 1;
			}	
			if(v.equals(buttons[13]))
			{
				if(nextOp == 0)
					total = Double.parseDouble(next);
				if(nextOp == 1)
					total += Double.parseDouble(next);
				if(nextOp == 2)
					total -= Double.parseDouble(next);
				if(nextOp == 3)
					total *= Double.parseDouble(next);
				if(nextOp == 4)
					total /= Double.parseDouble(next);
				if(total != 0)
					box.setText(total + "");
				else
					box.setText(next);
				next = "";
				nextOp = 2;
			}	
			if(v.equals(buttons[14]))
			{
				if(nextOp == 0)
					total = Double.parseDouble(next);
				if(nextOp == 1)
					total += Double.parseDouble(next);
				if(nextOp == 2)
					total -= Double.parseDouble(next);
				if(nextOp == 3)
					total *= Double.parseDouble(next);
				if(nextOp == 4)
					total /= Double.parseDouble(next);
				if(total != 0)
					box.setText(total + "");
				else
					box.setText(next);			
				next = "";
				nextOp = 3;
			}	
			if(v.equals(buttons[15]))
			{
				if(nextOp == 0)
					total = Double.parseDouble(next);
				if(nextOp == 1)
					total += Double.parseDouble(next);
				if(nextOp == 2)
					total -= Double.parseDouble(next);
				if(nextOp == 3)
					total *= Double.parseDouble(next);
				if(nextOp == 4)
					total /= Double.parseDouble(next);
				if(total != 0)
					box.setText(total + "");
				else
					box.setText(next);			
				next = "";
				nextOp = 4;
			}	
			if(v.equals(buttons[16]))
			{
				if(nextOp == 0)
					total = Double.parseDouble(next);
				if(nextOp == 1)
					total += Double.parseDouble(next);
				if(nextOp == 2)
					total -= Double.parseDouble(next);
				if(nextOp == 3)
					total *= Double.parseDouble(next);
				if(nextOp == 4)
					total /= Double.parseDouble(next);
				next = "";
				nextOp = 0;
				box.setText(total + "");
			}
			if(v.equals(buttons[17]))
			{
				next = "";
				total = 0;
				nextOp = 5;
				box.setText("0");
			}
		}
	}
}