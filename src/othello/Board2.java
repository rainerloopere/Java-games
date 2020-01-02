package othello;

import othello.Square2.buttonValues;

//Text file encoding must be changed into UTF-8

public class Board2 {

//	Standard board is 8x8.
//	think about making it private? has impact on subclasses
	 Square2[][] board;
	
	public Board2 ()
	{
		this.board = new Square2[8][8];
		initializeBoard();
	}
	
	public void initializeBoard ()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if ((i==3 && j == 3) || (i==4 && j == 4))
				{
					board[i][j] = new Square2(j,i,buttonValues.WHITE);
				}
				else if ((i==3 && j == 4) || (i==4 && j == 3))
				{
					board[i][j] = new Square2(j,i,buttonValues.BLACK);
				}
				else
				{
					board[i][j] = new Square2(j,i,buttonValues.EMPTY);
				}
			}
		}
	}
	
	public void printBoard ()
	{
		System.out.println("0 1 2 3 4 5 6 7");
		System.out.println("A B C D E F G H");
		for (int i = 0; i < board.length; i++) 
		{
//			System.out.print("  ");
//			System.out.print((i+1) + "\u2001"); // space in there appears with different width every single time
			for (int j = 0; j < board[i].length; j++) 
			{
				String squarePrint = "";
				switch(board[i][j].getButton())
				{
				case WHITE:
					squarePrint = "○";
					break;
				case BLACK:
					squarePrint = "●";
					break;
				case EMPTY:
					squarePrint = "☐";
					break;
				case ACTIVE:
					squarePrint = "☒";
					break;
				}
				System.out.print(squarePrint + "  ");
			}
			System.out.print((i+1));
			System.out.println(" " + i);
		}
	}
	
}
