package november30;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
//	Standard board is 8x8. Using List of lists
	private List<ArrayList<String>> board;
	
	public Board ()
	{
		this.board = new ArrayList<ArrayList<String>>();
		initializeBoard(8);
	}
	
	public void initializeBoard (int size)
	{
		for (int i = 0; i < size; i++) 
		{
			ArrayList<String> temprow = new ArrayList<String> ();
			for (int j = 0; j < size; j++) 
			{
				if ((i==3 && j == 3) || (i==4 && j == 4))
				{
					temprow.add("w");
				}
				else if ((i==3 && j == 4) || (i==4 && j == 3))
				{
					temprow.add("b");
				}
				else
				{
				temprow.add("");
				}
			}
			board.add(temprow);
		}
	}

//	Take b, w and empty as input and present based on turn
	public void printBoard ()
	{
//		System.out.println("a b c d e f g h");
		System.out.println("0 1 2 3 4 5 6 7");
		for (int i = 0; i < board.size(); i++) 
		{
//			System.out.print((i+1) + "\u2001"); // space in there appears with different width every single time
			for (int j = 0; j < board.size(); j++) 
			{
				String square;
				if (board.get(i).get(j).equals("w"))
				{
					square = "○";
				}
				else if (board.get(i).get(j).equals("b"))
				{
					square = "●";
				}
				else if (board.get(i).get(j).equals(""))
				{
					square = "☐";
				}
				else
				{
					square = board.get(i).get(j);
				}
				System.out.print(square + "  ");
			}
			System.out.println(i);
//			System.out.println((i+1));
		}
	}
	
	
//	this was added later. check if other functions are optimizing usage of this. for example above
	public String getButton(int clocationX, int clocationY)
	{
		String bsquareString = board.get(clocationX).get(clocationY);
		return bsquareString;
	}
	public void setButton(int locationX, int locationY, String newButton)
	{
		board.get(locationX).set(locationY, newButton);
	}
	
//	doesn't work
//	public boolean isOppositeButton(int locationX, int locationY, String referenceButton)
//	{
//		String squareString = getButton(locationX,locationX);
//
//		if (!(squareString.equals(referenceButton)) && !(squareString.equals("")))
//		{
//			System.out.println(squareString + " is opposite to " + referenceButton);
//			return true;
//		}
//		else
//		{
//			System.out.println(squareString + " is not opposite to " + referenceButton);
//			return false;
//		}
//	}
	public boolean isMatchingButton(int blocationX, int blocationY, String referenceButton)
	{
		String asquareString = getButton(blocationX,blocationY);
		if (asquareString.equals(referenceButton))
		{
			System.out.println(asquareString + " does match " + referenceButton);
			return true;
		}
		else 
		{
			System.out.println(asquareString + " does not match " + referenceButton);
			return false;
		}
	}
//	for sure needs to be refactored
	public boolean isValidDirection (ArrayList<String> oneDirection)
	{
		boolean isValidMove = false;
		if (oneDirection.contains("") || oneDirection.size()==2)
		{
			isValidMove = false;
		}
		else if (oneDirection.get(0) == oneDirection.get(oneDirection.size()-1))
		{
			isValidMove = true;
		}
		System.out.println("isValidMove = " + isValidMove);
		return isValidMove;
	}
	
	public ArrayList<String> checkOneDirection (int locationX, int locationY, String referenceButton, int xIncrement, int yIncrement)
	{
		ArrayList<String> oneDirection = new ArrayList<String> (); 
		int tempLocationX = locationX;
		int tempLocationY = locationY;
		oneDirection.add(referenceButton);
		System.out.println("start button " + referenceButton + " " + locationX + locationY);
		while (!(isMatchingButton(tempLocationX, tempLocationY, referenceButton)) && tempLocationX < 7 && tempLocationY < 7) 
		{
			System.out.println("start round");
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
//			setButton(tempLocationX,tempLocationY,"x");
			System.out.println("next button " + getButton(tempLocationX, tempLocationY) + " " + tempLocationX + tempLocationY);
			oneDirection.add(getButton(tempLocationX, tempLocationY));
		}
		return oneDirection;
	}
	
	public void checkAllDirections(int alocationX, int alocationY, String referenceButton)
	{
//		setButton(locationX,locationY,"x");
//		printBoard();
//		String buttonString = getButton(locationX, locationY); not needed any more
//		go right
		ArrayList<String> oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,1);
		
		
		for (String square:oneDirection)
		{
			System.out.print(square + "-");
		}
		isValidDirection(oneDirection);
		
		
	}


}
