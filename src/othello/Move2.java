package othello;

import java.util.ArrayList;

import othello.Square2.buttonValues;

public class Move2{
	
	Square2 moveSquare;
	private ArrayList<Square2> captureSquares;
	
	public Move2(Square2 square, Square2[][] board)
	{
		this.moveSquare = square;
		this.captureSquares = findCaptureSquares(board);
	}
	
public Square2 getMoveSquare() 
{
		return moveSquare;
	}

	public ArrayList<Square2> getCaptureSquares() 
	{
		return captureSquares;
	}

	//	list of opposite colour buttons that one particular move will capture
	public ArrayList<Square2> findCaptureSquares(Square2[][] board)
	{
		ArrayList<Square2> captureSquares = new ArrayList<Square2>();
		if (!board[moveSquare.getLocationY()][moveSquare.getLocationX()].isEmpty()) //if the square under question is not empty, a button cannot be placed there so nothing can be captured
		{
//			System.out.println("occupied square"); //temp
			return captureSquares;
			
		}
		for (int i = -1 ; i <= 1;i++)
		{
			for (int j = -1 ; j <= 1;j++)
			{
				ArrayList<Square2> squareList = getOneDirection(board,i,j);
//				System.out.println("checking direction " + i + " " + j + " with list of " + squareList); //temp
				if (isValidDirection(squareList))
				{
//					System.out.println("confirmed direction " + i + " " + j + " with list of " + squareList); //temp

						for (Square2 captureSquare : squareList)
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
	public ArrayList<Square2> getOneDirection (Square2[][] board, int xIncrement, int yIncrement)
	{
		ArrayList<Square2> squareList = new ArrayList<Square2> ();
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
				squareList.add(new Square2(tempLocationX,tempLocationY,board[tempLocationY][tempLocationX].getButton())); 
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
	
//	public void placeMove ()
//	{
////		System.out.println(move);
////		ArrayList<Square> captureSquares = getCaptureSquares(move.getLocationX(),move.getLocationY(), move.getButton());
////		System.out.println(captureSquares);
//		super.board[moveSquare.getLocationY()][moveSquare.getLocationX()].setButton(moveSquare.getButton());
//		for (Square2 square : captureSquares)
//		{
//			super.board[square.getLocationY()][square.getLocationX()].setButton(moveSquare.getButton());
//		}
//	}
	
	public String toString() //used for testing only
	{
		return "move " + moveSquare + " captures " + captureSquares;
	}
}
