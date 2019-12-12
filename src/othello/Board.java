package othello;


import java.util.ArrayList;
import java.util.List;


//Text file encoding must be changed into UTF-8

//refactoring
//add a point/square class
//change to arrays?
//loop -1 to 1 for directions


public class Board {
	
//	Standard board is 8x8. Using List of lists
	private final String[][] board;
	
	public Board ()
	{
		this.board = new String[8][8];
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

//	Take b, w and empty as input and present based on turn
	public void printBoard ()
	{
		System.out.println("0 1 2 3 4 5 6 7");
		System.out.println("a b c d e f g h");
		for (int i = 0; i < board.length; i++) 
		{
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
			System.out.println(i);
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
//		if (squareList.get(0).getButton() == squareList.get(squareList.size()-1).getButton()) // first if filters everything out and no need for others
//		{
			return true;
//		}
//		else
//		{
//			return false;
//		}
	}
	
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
		while (!(board[tempLocationX][tempLocationY].equals(referenceButton)) && tempLocationX < 7 && tempLocationY < 7 && tempLocationX > 0 && tempLocationY > 0) 
		{
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
			squareList.add(new Square(tempLocationX,tempLocationY,board[tempLocationX][tempLocationY]));
		}
		return squareList;
	}
	
	public boolean isValidMove(int locationX, int locationY, String referenceButton)
	{
		if (board[locationX][locationY].equals(""))
		{
			return false;
		}
		ArrayList<Square> squareList;
		for (int i = -1 ; i <= 1;i++)
		{
			for (int j = -1 ; j <= 1;j++)
			{
				squareList = getSquareList(locationX,locationY,referenceButton,i,j);
				if (isValidDirection(squareList))
				{
					return true;
				}
			}
		}
		return false;
		
////		go right
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,1);
//		if (isValidDirection(oneDirection))
//			{
//			return true;
//			}
////		go up and right
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,1);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go up
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,0);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go up and left
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,-1);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go left
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,-1);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go down and left
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,-1);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go down
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,0);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
////		go down and right
//		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,1);
//		if (isValidDirection(oneDirection))
//		{
//		return true;
//		}
//		return false;
	}
	
	public ArrayList<Square> getAvailableMoves(String referenceButton)
	{
		ArrayList<Square> availableMoves = new ArrayList<Square>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (isValidMove(i,j,referenceButton))
				{
					board[i][j] = "x";
					availableMoves.add(new Square(i,j,referenceButton));
				}
			}
			
		}
		printBoard();
		clearMoves();
		return availableMoves;
	}
	public void clearMoves()
	{
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				if (board[i][j] == "x")
				{
					board[i][j] = "";
				}
			}
		}
	}
	
	public void placeMove (ArrayList<Square> availableMoves)
	{
		for (Square square : availableMoves)
		{
			board[square.getLocationX()][square.getLocationY()] = square.getButton();
		}
	}

}
