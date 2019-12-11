package othello;


import java.util.ArrayList;
import java.util.List;


//Text file encoding must be changed into UTF-8
public class Board {
	
//	Standard board is 8x8. Using List of lists
	private List<ArrayList<String>> board;
	
	public Board ()
	{
		this.board = new ArrayList<ArrayList<String>>();
		initializeBoard();
	}
	
	public void initializeBoard ()
	{
		for (int i = 0; i < 8; i++) 
		{
			ArrayList<String> temprow = new ArrayList<String> ();
			for (int j = 0; j < 8; j++) 
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
					square = "○"; // 
				}
				else if (board.get(i).get(j).equals("b"))
				{
					square = "●";
				}
				else if (board.get(i).get(j).equals(""))
				{
					square = "☐";
				}
				else if (board.get(i).get(j).equals("x"))
				{
					square = "☒";
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
	public boolean isMatchingButton(int blocationX, int blocationY, String referenceButton)
	{
		String asquareString = getButton(blocationX,blocationY);
		if (asquareString.equals(referenceButton))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
//	for sure needs to be refactored
	public boolean isValidDirection (ArrayList<String> oneDirection)
	{
		boolean isValidMove = false;
		if (oneDirection.contains("") || oneDirection.contains("x") ||oneDirection.size()<=2)
		{
			isValidMove = false;
		}
		else if (oneDirection.get(0) == oneDirection.get(oneDirection.size()-1))
		{
			isValidMove = true;
		}
		return isValidMove;
	}
	
	public ArrayList<String> checkOneDirection (int locationX, int locationY, String referenceButton, int xIncrement, int yIncrement)
	{
		ArrayList<String> oneDirection = new ArrayList<String> (); 
		int tempLocationX = locationX;
		int tempLocationY = locationY;
		oneDirection.add(referenceButton);
		while (!(isMatchingButton(tempLocationX, tempLocationY, referenceButton)) && tempLocationX < 7 && tempLocationY < 7 && tempLocationX > 0 && tempLocationY > 0) 
		{
			tempLocationX += xIncrement;
			tempLocationY += yIncrement;
			oneDirection.add(getButton(tempLocationX, tempLocationY));
		}
		return oneDirection;
	}
	
	public boolean checkAllDirections(int alocationX, int alocationY, String referenceButton)
	{
		ArrayList<String> oneDirection;
//		go right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,1);
		if (isValidDirection(oneDirection))
			{
			return true;
			}
//		go up and right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,1);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go up
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,0);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go up and left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,-1);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,-1);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go down and left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,-1);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go down
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,0);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
//		go down and right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,1);
		if (isValidDirection(oneDirection))
		{
		return true;
		}
		return false;
	}
	
	public void checkAllSquares(String referenceButton)
	{
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				if (board.get(i).get(j) == "" && checkAllDirections(i, j, referenceButton))
				{
					board.get(i).set(j, "x");
				}
			}
		}
		printBoard();
	}
	public void clearMoves()
	{
		for (int i = 0; i < 8; i++) 
		{
			for (int j = 0; j < 8; j++) 
			{
				if (board.get(i).get(j) == "x")
				{
					board.get(i).set(j, "");
				}
			}
		}
	}
	
	public void placeOneDirection (int locationX, int locationY, ArrayList<String> oneDirection, int xIncrement, int yIncrement)
	{
		for (int i = 0 ; i < oneDirection.size() ; i++)
		{
			int tempLocationX = locationX + (xIncrement * i);
			int tempLocationY = locationY + (yIncrement * i);
			if (oneDirection.get(i) != oneDirection.get(0) && oneDirection.get(i) == "w")
			{
				board.get(tempLocationX).set(tempLocationY, "b");
			}
			else if (oneDirection.get(i) != oneDirection.get(0) && oneDirection.get(i) == "b")
			{
				board.get(tempLocationX).set(tempLocationY, "w");
			}
		}
	}
	
	public boolean placeAllDirections(int alocationX, int alocationY, String referenceButton)
	{
		ArrayList<String> oneDirection;
//		go right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,0,1);
		}
//		go up and right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,-1,1);
		}
//		go up
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,0);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,-1,0);
		}
//		go up and left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,-1,-1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,-1,-1);
		}
//		go left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,0,-1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,0,-1);
		}
//		go down and left
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,-1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,1,-1);
		}
//		go down
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,0);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,1,0);
		}
//		go down and right
		oneDirection = checkOneDirection (alocationX,alocationY,referenceButton,1,1);
		if (isValidDirection(oneDirection))
		{
			placeOneDirection(alocationX,alocationY,oneDirection,1,1);
		}
		return false;
	}


}
