package othello;

//refactoring idea: method declarations with XY pairs to use squares?
// attach evaluation to a move with a class
// don't get best moves every single time (player class?)

//Add winner
import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	static String turn;

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
	public static void switchPlayer()
	{
		turn = (turn == "black") ? "white" : "black";
	}
	public static boolean hasAvailableMoves(Board board)
	{
		ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
		if (availableMoves.size()==0)
		{
			System.out.println (turn + " does not have any available moves and will have to skip.");
			switchPlayer();
			availableMoves = board.getAvailableMoves(turn.substring(0,1));
			if (availableMoves.size()==0)
			{
				System.out.println (turn + " also does not have any available moves.");
				System.out.println("Game over!");
				int countWhite = board.countButtons("w");
				int countBlack = board.countButtons("b");
				
				System.out.println("Black has " + countBlack + " points.");
				System.out.println("White has " + countWhite + " points.");
				if (countBlack > countWhite)
				{
					System.out.println("Black wins!");
				}
				else if (countBlack == countWhite)
				{
					System.out.println("It's a tie!");
				}
				else 
				{
					System.out.println("White wins!");
				}
				return false;
			}
		}
		return true;
	}
	
	public static ArrayList<Integer> evaluateRandom (Board board, String referenceButton)
	{
		ArrayList<Square> availableMoves = board.getAvailableMoves(referenceButton);
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (int i = 0 ; i < availableMoves.size() ; i++)
		{
			evaluatedMoves.add((int)(Math.random()*100)); //scale of 0-100
		}
		return evaluatedMoves;
	}
	
	public static Square getBestMove (Board board, ArrayList<Integer> evaluatedMoves, String referenceButton)
	{
		
		ArrayList<Square> availableMoves = board.getAvailableMoves(referenceButton);
		System.out.println(availableMoves);
		System.out.println(evaluatedMoves);
		int bestMoveIndex = 0;
		for (int evaluation : evaluatedMoves)
		{
			if (evaluation > evaluatedMoves.get(bestMoveIndex))
			{
				System.out.println("evaluation " + evaluation + " max " + evaluatedMoves.get(bestMoveIndex));
				bestMoveIndex = evaluatedMoves.indexOf(evaluation);
			}
		}
		return availableMoves.get(bestMoveIndex);
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
		turn = "black";

		switch (inputOption)
		{
		case 1:
			
			while (hasAvailableMoves(board))
			{
				System.out.println();
				board.printBoard();
				System.out.println("It's " + turn + "'s turn.");
				ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));

				board.placeMove(askForMove(availableMoves));
				switchPlayer();
			}
			break;

		case 2:
			//			Randomize who starts
			while (hasAvailableMoves(board))
			{
				System.out.println();
				System.out.println("It's " + turn + "'s turn.");

				if (turn.equals("black"))
				{
					board.placeMove(getBestMove(board,evaluateRandom(board,turn.substring(0,1)),turn.substring(0,1)));
				}
				else
				{
					ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
					board.placeMove(askForMove(availableMoves));
				}
				switchPlayer();
			}

			break;

		case 3:

			break;
		default:
			System.out.println("Something went wrong, try again.");
		}

	}

}
