package othello;

import java.util.ArrayList;

//Move class is grouping a Square, the potential other Squares this move will capture and the evaluation score of that move.
//Most methods are used to analyse the Board, moving directions and potential captures.
public class Move{
	
	private Square moveSquare;
	private ArrayList<Square> captureSquares;
	private int evaluation;
	
	public Move(Square square, Square[][] board)
	{
		this.moveSquare = square;
		this.captureSquares = findCaptureSquares(board);
	}
	public Move(int evaluation)
	{
		this.evaluation = evaluation;
	}
	public Square getMoveSquare() 
	{
		return moveSquare;
	}
	public ArrayList<Square> getCaptureSquares() 
	{
		return captureSquares;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	//	list of opposite colour buttons that would be captured if moveSquare would be placed on the board.
	public ArrayList<Square> findCaptureSquares(Square[][] board)
	{
		ArrayList<Square> captureSquares = new ArrayList<Square>();
		if (!board[moveSquare.getLocationY()][moveSquare.getLocationX()].isEmpty()) //if the square under question is not empty, a button cannot be placed there so nothing can be captured
		{
			return captureSquares;
		}
//		Two loops to go through all directions
		for (int i = -1 ; i <= 1;i++)
		{
			for (int j = -1 ; j <= 1;j++)
			{
				ArrayList<Square> squareList = getOneDirection(board,i,j);
				if (isValidDirection(squareList))
				{
						for (Square captureSquare : squareList)
						{
							if (captureSquare.getButton() != moveSquare.getButton())
							{
								captureSquares.add(captureSquare);
							}
						}
				}
			}
		}
		return captureSquares;
	}
//	Creates a list of Squares that are in line in one direction. Starts with the location of and colour of the moveSquare and ends at either
//	1) when side of board has reached or 2) a second same coloured button is reached
	public ArrayList<Square> getOneDirection (Square[][] board, int xIncrement, int yIncrement)
	{
		ArrayList<Square> squareList = new ArrayList<Square> ();
		int tempLocationX = moveSquare.getLocationX();
		int tempLocationY = moveSquare.getLocationY();
		squareList.add(moveSquare);
		if (xIncrement == 0 && yIncrement == 0)
		{
			return squareList;
		}
		while (tempLocationY <= 7 && tempLocationX <= 7 && tempLocationY >= 0 && tempLocationX >= 0) 
		{
			if (!(tempLocationY == moveSquare.getLocationY() && tempLocationX == moveSquare.getLocationX()))
			{
				squareList.add(new Square(tempLocationX,tempLocationY,board[tempLocationY][tempLocationX].getButton())); 
			}
			if (board[tempLocationY][tempLocationX].getButton() == moveSquare.getButton())
			{
				break;
			}
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
		}
		return squareList;
	}
	
	public boolean isValidDirection (ArrayList<Square> squareList)
	{
		for (Square square : squareList)
		{
			if (square.isEmpty() || squareList.size()<=2)
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
//	toString is only used for testing
	public String toString()
	{
		return "move " + moveSquare + " captures " + captureSquares;
	}
}
