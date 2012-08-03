package com.sample.tictactoe;

import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TicTacToeActivity extends Activity implements OnClickListener
{
    Button buttons[] = new Button[9];
    int count = 0;
	static int board[][] = new int[3][3];		//0 - empty; 1 - X; 2 - O
	MyView view;
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
     
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        buttons[8] = (Button) findViewById(R.id.button9);
        for(int i = 0; i < 9; i++)
        {
        	//buttons[i].setVisibility(View.INVISIBLE);
        	buttons[i].setOnClickListener(this);
        }

        view = new MyView(this);
        RelativeLayout layout1 = (RelativeLayout) findViewById(R.id.layout2);
        layout1.addView(view);
	}
	public static int checkForWinner()
	{
		if(board[0][0] == board[0][1] && board[0][1] == board[0][2])
		{
			if(board[0][0] == 1)
				return 1;
			if(board[0][0] == 2)
				return 2;
		}
		if(board[1][0] == board[1][1] && board[1][1] == board[1][2])
		{
			if(board[1][0] == 1)
				return 1; 
			if(board[1][0] == 2)
				return 2;
		}
		if(board[2][0] == board[2][1] && board[2][1] == board[2][2])
		{
			if(board[2][0] == 1)
				return 1; 
			if(board[2][0] == 2)
				return 2;
		}
		
		if(board[0][0] == board[1][0] && board[1][0] == board[2][0])
		{
			if(board[0][0] == 1)
				return 1; 
			if(board[0][0] == 2)
				return 2;
		}
		if(board[0][1] == board[1][1] && board[1][1] == board[2][1])
		{
			if(board[0][1] == 1)
				return 1; 
			if(board[0][1] == 2)
				return 2;
		}
		if(board[0][2] == board[1][2] && board[1][2] == board[2][2])
		{
			if(board[0][2] == 1)
				return 1; 
			if(board[0][2] == 2)
				return 2;
		}
		return 0;
	}
	public void onClick(View v) 
	{
		if(v.equals(buttons[0]) && board[0][0] == 0)
		{
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 0, 0, 'X'));
				board[0][0] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 0, 0, 'O'));
				board[0][0] = 1;
			}
			count++;
		}
		if(v.equals(buttons[1]) && board[0][1] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 0, 1, 'X'));
				board[0][1] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 0, 1, 'O'));
				board[0][1] = 2;
			}
		}
		if(v.equals(buttons[2]) && board[0][2] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 0, 2, 'X'));
				board[0][2] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 0, 2, 'O'));
				board[0][2] = 2;
			}
		}
		if(v.equals(buttons[3]) && board[1][0] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 1, 0, 'X'));
				board[1][0] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 1, 0, 'O'));
				board[1][0] = 2;
			}
		}
		if(v.equals(buttons[4]) && board[1][1] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 1, 1, 'X'));
				board[1][1] = 1;	
			}
			else
			{
				view.addSymbol(new Symbol(this, 1, 1, 'O'));
				board[1][1] = 2;
			}
		}
		if(v.equals(buttons[5]) && board[1][2] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 1, 2, 'X'));
				board[1][2] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 1, 2, 'O'));
				board[1][2] = 2;
			}
		}
		if(v.equals(buttons[6]) && board[2][0] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 2, 0, 'X'));
				board[2][0] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 2, 0, 'O'));
				board[2][0] = 2;
			}
		}
		if(v.equals(buttons[7]) && board[2][1] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 2, 1, 'X'));
				board[2][1] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 2, 1, 'O'));
				board[2][1] = 2;
			}
		}
		if(v.equals(buttons[8]) && board[2][2] == 0)
		{
			count++;
			if(count % 2 == 0)
			{
				view.addSymbol(new Symbol(this, 2, 2, 'X'));
				board[2][2] = 1;
			}
			else
			{
				view.addSymbol(new Symbol(this, 2, 2, 'O'));
				board[2][2] = 2;
			}
		}
		int winner = checkForWinner();
		if(winner == 1)
		{
			Toast.makeText(this, "X's win!!!", Toast.LENGTH_LONG).show();
			System.exit(0);
		}
		if(winner == 2)
		{
			Toast.makeText(this, "O's win!!!", Toast.LENGTH_LONG).show();
			System.exit(0);
		}
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 3; j++)
				System.out.println();
	}
}