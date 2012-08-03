import java.util.Scanner;

public class HelloWorld 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		int board[][] = new int[3][3];		//0 - empty; 1 - X; 2 - O
		boolean xWin = false;
		boolean oWin = false;
		while(!xWin && !oWin)
		{
			printBoard(board);
			int winner = checkForWinner(board);
			if(winner == 1)
			{
				System.out.println("X wins!");
				xWin = true;
				break;
			}
			if(winner == 2)
			{
				System.out.println("O wins!");
				oWin = true;
				break;
			}
			boolean successfulMove = false;
			while(!successfulMove)
			{
				System.out.print("X - Enter a move (x-coord): ");
				int ycoord = scan.nextInt() - 1;
				System.out.print("X - Enter a move (y-coord): ");
				int xcoord = scan.nextInt() - 1;
				if(xcoord < 3 && xcoord >= 0 && ycoord < 3 && ycoord >= 0)
				{
					if(board[xcoord][ycoord] == 0)
					{
						board[xcoord][ycoord] = 1;
						successfulMove = true;
					}
					else
						System.out.println("Error: Invalid move.");
				}
				else
					System.out.println("Error: Invalid move.");
			}
			printBoard(board);
			winner = checkForWinner(board);
			if(winner == 1)
			{
				System.out.println("X wins!");
				xWin = true;
				break;
			}
			if(winner == 2)
			{
				System.out.println("O wins!");
				oWin = true;
				break;
			}
			boolean successfulMove2 = false;
			while(!successfulMove2)
			{
				System.out.print("O - Enter a move (x-coord): ");
				int ycoord = scan.nextInt() - 1;
				System.out.print("O - Enter a move (y-coord): ");
				int xcoord = scan.nextInt() - 1;
				if(xcoord < 3 && xcoord >= 0 && ycoord < 3 && ycoord >= 0)
				{
					if(board[xcoord][ycoord] == 0)
					{
						board[xcoord][ycoord] = 2;
						successfulMove2 = true;
					}
					else
						System.out.println("Error: Invalid move.");
				}
				else
					System.out.println("Error: Invalid move.");
			}
		}
	}
	public static int checkForWinner(int[][] board)
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
	public static void printBoard(int[][] board)
	{
		char array[][] = new char[3][3];
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(board[i][j] == 0)
					array[i][j] = '_';
				if(board[i][j] == 1)
					array[i][j] = 'X';
				if(board[i][j] == 2)
					array[i][j] = 'O';
			}
		}
		System.out.println(array[0][0] + " " + array[0][1] + " " + array[0][2]);
		System.out.println(array[1][0] + " " + array[1][1] + " " + array[1][2]);
		System.out.println(array[2][0] + " " + array[2][1] + " " + array[2][2]);
	}
}
