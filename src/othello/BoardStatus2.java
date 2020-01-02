package othello;

import java.util.ArrayList;

import othello.Square2.buttonValues;

public class BoardStatus2 extends Board2{
	private buttonValues turn; 
	private ArrayList<Square2> availableMoves;
	
	public BoardStatus2 (buttonValues turn)
	{
		this.turn = turn;
	}
	
	public ArrayList<Square2> getAvailableMoves()
	{
		ArrayList<Square2> availableMoves = new ArrayList<Square2>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				Square2 move = new Square2(j,i,turn);
				if (getCaptureSquares(move).size() > 0)
				{
//					board[i][j] = "x";
					availableMoves.add(move);
				}
			}
			
		}
//		printBoard();
//		clearMoves();
		this.availableMoves = availableMoves;
		return availableMoves;
	}
	
//	list of opposite colour buttons that one particular move will capture
//	possible to add this as an variable to Square and refactor the placing part
	public ArrayList<Square2> getCaptureSquares(Square2 square)
	{
		ArrayList<Square2> captureSquares = new ArrayList<Square2>();
		if (!board[square.getLocationY()][square.getLocationX()].isEmpty())
		{
			return captureSquares;
		}
//		ArrayList<Square2> squareList;
		for (int i = -1 ; i <= 1;i++)
		{
			for (int j = -1 ; j <= 1;j++)
			{
				ArrayList<Square2> squareList = getOneDirection(square,i,j);
				if (isValidDirection(squareList))
				{
						for (Square2 captureSquare : squareList)
						{
							if (captureSquare.getButton() != square.getButton())
							{
								captureSquares.add(captureSquare);
							}
						}
				}
			}
		}
		return captureSquares;
	}
	
	public ArrayList<Square2> getOneDirection (Square2 square, int xIncrement, int yIncrement)
	{
		ArrayList<Square2> squareList = new ArrayList<Square2> ();
		int tempLocationX = square.getLocationX();
		int tempLocationY = square.getLocationY();
		squareList.add(square);
		if (xIncrement == 0 && yIncrement == 0)
		{
			return squareList;
		}
		while (tempLocationY <= 7 && tempLocationX <= 7 && tempLocationY >= 0 && tempLocationX >= 0) 
		{
			if (!(tempLocationY == square.getLocationY() && tempLocationX == square.getLocationX()))
			{
				squareList.add(new Square2(tempLocationX,tempLocationY,board[tempLocationY][tempLocationX].getButton()));
			}
			if (board[tempLocationY][tempLocationX].getButton() == square.getButton())
			{
				break;
			}
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
		}
		return squareList;
	}
	
	public boolean isValidDirection (ArrayList<Square2> squareList)
	{
		for (Square2 square : squareList)
		{
			if (square.isEmpty() || square.isActive() || squareList.size()<=2)
			{
				return false;
			}
		}
		if (squareList.get(0).getButton() == squareList.get(squareList.size()-1).getButton() ) // making sure that the list starts and ends with same button
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
