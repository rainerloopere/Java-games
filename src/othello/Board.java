package othello;


import java.util.ArrayList;
import java.util.List;


//Text file encoding must be changed into UTF-8



public class Board {
	
//	Standard board is 8x8. Using List of lists
	String[][] board;
	
	public Board ()
	{
		this.board = new String[8][8];
		initializeBoard();
	}
//	public Board getBoard ()
//	{
//		return this.getBoard();
//	}
	
	public void initializeBoard ()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if ((i==3 && j == 3) || (i==4 && j == 4))
				{
					board[i][j] = "w";
				}
				else if ((i==3 && j == 4) || (i==4 && j == 3))
				{
					board[i][j] = "b";
				}
				else
				{
					board[i][j] = "";
				}
			}
		}
	}
	
//	for testing only
	public void setSquare (int locationX, int locationY, String referenceButton)
	{
		board[locationY][locationX] = referenceButton;
	}

//	Take b, w and empty as input and present based on turn
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
				String squarePrint;
				if (board[i][j].equals("w"))
				{
					squarePrint = "○"; // 
				}
				else if (board[i][j].equals("b"))
				{
					squarePrint = "●";
				}
				else if (board[i][j].equals(""))
				{
					squarePrint = "☐";
				}
				else if (board[i][j].equals("x"))
				{
					squarePrint = "☒";
				}
				else
				{
					squarePrint = board[i][j];
				}
				System.out.print(squarePrint + "  ");
			}
			System.out.print((i+1));
			System.out.println(" " + i);
		}
	}

	public boolean isValidDirection (ArrayList<Square> squareList)
	{
		for (Square square : squareList)
		{
			if (square.getButton().equals("") || square.getButton().equals("x") || squareList.size()<=2)
			{
				return false;
			}
		}
		if (squareList.get(0).getButton().equals(squareList.get(squareList.size()-1).getButton())) // making sure that the list starts and ends with same button
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
//	does not properly handle sides of board
	public ArrayList<Square> getSquareList (int locationX, int locationY, String referenceButton, int xIncrement, int yIncrement)
	{
		ArrayList<Square> squareList = new ArrayList<Square> ();
		int tempLocationX = locationX;
		int tempLocationY = locationY;
		squareList.add(new Square(locationX,locationY,referenceButton));
		if (xIncrement == 0 && yIncrement == 0)
		{
			return squareList;
		}
		while (tempLocationY <= 7 && tempLocationX <= 7 && tempLocationY >= 0 && tempLocationX >= 0) 
		{
			if (!(tempLocationY == locationY && tempLocationX == locationX))
			{
				squareList.add(new Square(tempLocationX,tempLocationY,board[tempLocationY][tempLocationX]));
			}
			if (board[tempLocationY][tempLocationX].equals(referenceButton))
			{
				break;
			}
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
		}
		return squareList;
	}
	
//	possible to add this as an variable to Square and refactor the placing part
	public ArrayList<Square> getCaptureSquares(int locationX, int locationY, String referenceButton)
	{
		ArrayList<Square> captureSquares = new ArrayList<Square>();
		if (!board[locationY][locationX].equals(""))
		{
			return captureSquares;
		}
		ArrayList<Square> squareList;
		for (int i = -1 ; i <= 1;i++)
		{
			for (int j = -1 ; j <= 1;j++)
			{
				squareList = getSquareList(locationX,locationY,referenceButton,i,j);
				if (isValidDirection(squareList))
				{
						for (Square square : squareList)
						{
							if (!square.getButton().equals(referenceButton))
							{
								captureSquares.add(square);
							}
						}
				}
			}
		}
		return captureSquares;
	}
	
	public ArrayList<Square> getAvailableMoves(String referenceButton)
	{
		ArrayList<Square> availableMoves = new ArrayList<Square>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (getCaptureSquares(j,i,referenceButton).size() > 0)
				{
					board[i][j] = "x";
					availableMoves.add(new Square(j,i,referenceButton));
				}
			}
			
		}
		printBoard();
		clearMoves();
		return availableMoves;
	}
	public void clearMoves()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (board[i][j] == "x")
				{
					board[i][j] = "";
				}
			}
		}
	}
	
	public void placeMove (Square move)
	{
		System.out.println(move);
		ArrayList<Square> captureSquares = getCaptureSquares(move.getLocationX(),move.getLocationY(), move.getButton());
		System.out.println(captureSquares);
		board[move.getLocationY()][move.getLocationX()] = move.getButton();
		for (Square square : captureSquares)
		{
			board[square.getLocationY()][square.getLocationX()] = move.getButton();
		}
	}
	public int countButtons (String referenceButton)
	{
		int count = 0;
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (board[j][i].equals(referenceButton))
				{
					count++;
				}
			}
		}
		return count;
	}

}
