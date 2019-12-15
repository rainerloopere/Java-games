package othello;

//refactoring idea: method declarations with XY pairs to use squares?
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	public static boolean isAvailableMove (int locationX, int locationY, ArrayList<Square> availableMoves)
	{
		for (Square square : availableMoves)
		{
			if (square.getLocationX() == locationX && square.getLocationY() == locationY)
			{
				return true;
			}
		}
		return false;
	}
	public static Square askForMove (ArrayList<Square> availableMoves)
	{
		int locationX = 0;
		int locationY = 0;
		
		while(true) 
		{
			System.out.println("Enter the move you want to play (e.g. A1):");
			Scanner input = new Scanner(System.in);
			String inputLocation = input.nextLine();
			
				try 
				{
					locationX = (int) inputLocation.toLowerCase().charAt(1) - 'a';
					locationY = (int) inputLocation.charAt(2) - '0' - 1;
					break;
				} 
				catch (Exception e) 
				{
					System.out.println("Wrong format for the input, try again.");
				} 
		}
		Square move = new Square (locationX, locationY, availableMoves.get(0).getButton()); //button for available moves is always same based on who's turn it is
		if (isAvailableMove(locationX,locationY,availableMoves))
		{
			return move;
		}
		else
		{
			System.out.println("This is not an available move to play. Try again.");
			return askForMove(availableMoves);
		}
	}

	public static void main(String[] args) 
	{
//		Board board = new Board();
//		board.printBoard();
//		Dark moves first
		
//		board.checkAllDirections(5,4,"b");
//		board.getButton(3, 4);
//		board.checkAllSquares("w");
		
//		board.getSquareList(3, 2, "b", 0, 0);
		
		Board board = new Board();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the option to play:");
		System.out.println("1 - two players");
		System.out.println("2 - play against computer");
		int inputOption = Integer.parseInt(input.nextLine());
		
		switch (inputOption)
		{
		case 1:
			String turn = "black";
			while (true)
			{
				System.out.println();
				ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
				if (availableMoves.size()==0)
				{
					System.out.println (turn + " does not have any available moves and will have to skip.");
					turn = (turn == "black") ? "white" : "black";
					availableMoves = board.getAvailableMoves(turn.substring(0,1));
					if (availableMoves.size()==0)
					{
						System.out.println("Game over!");
						break;
					}
				}
				System.out.println("It's " + turn + "'s turn.");

				board.placeMove(askForMove(availableMoves));
				turn = (turn == "black") ? "white" : "black";
			}
			break;
			
		case 2:
			break;
			
		case 3:

			break;
		default:
			System.out.println("Something went wrong, try again.");
		}
		
	}

}
