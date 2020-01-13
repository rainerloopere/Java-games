package othello;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import othello.Square.buttonValues;

//test comment
//Game is the main class to run the game, show messages and interact with the players. 
public class Game {
	
	public static Square askForMove (Board board)
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
				locationX = (int) inputLocation.toLowerCase().charAt(0) - 'a';
				locationY = (int) inputLocation.charAt(1) - '0' - 1;
				break;
			} 
			catch (Exception e) 
			{
				System.out.println("Wrong format for the input, try again.");
			} 
		}
		Square square = new Square (locationX, locationY, board.getTurn()); //button for available moves is always same based on who's turn it is
		if (board.isAvailableMove(square))
		{
			return square;
		}
		else
		{
			System.out.println("This is not an available move to play. Try again.");
			return askForMove(board);
		}
	}
	public static boolean hasAvailableMoves(Board board)
	{
		if (board.getAvailableMoves().size()==0)
		{
			System.out.println (board.getTurn() + " does not have any available moves and will have to skip.");
			board.switchPlayer();
			if (board.getAvailableMoves().size()==0)
			{
				System.out.println (board.getTurn() + " also does not have any available moves.");
				System.out.println("Game over!");
				return false;
			}
		}
		return true;
	}
	public static buttonValues findWinner(Board board)
	{
		int countWhite = board.countButtons(buttonValues.WHITE);
		int countBlack = board.countButtons(buttonValues.BLACK);
		
		System.out.println("Black has " + countBlack + " points.");
		System.out.println("White has " + countWhite + " points.");
		if (countBlack > countWhite)
		{
			System.out.println("Black wins!");
			return buttonValues.BLACK;
		}
		else if (countBlack == countWhite)
		{
			System.out.println("It's a tie!");
			return buttonValues.EMPTY;
		}
		else 
		{
			System.out.println("White wins!");
			return buttonValues.WHITE;
		}
	}
	
	public static void main(String[] args) throws InterruptedException 
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the option to play:");
		System.out.println("1 - two players");
		System.out.println("2 - play against computer");
		System.out.println("3 - computer against computer"); //to test different AI's
		int inputOption = Integer.parseInt(input.nextLine());
		Board board = new Board();

		switch (inputOption)
		{
		case 1:
			while (hasAvailableMoves(board))
			{
				System.out.println();
				System.out.println("It's " + board.getTurn() + "'s turn.");
				board.printBoard();
				board.placeMove(askForMove(board));
			}
			findWinner(board);
			break;
		case 2:
			System.out.println("User starts!");
			while (hasAvailableMoves(board))
			{
				System.out.println();
				System.out.println("It's " + board.getTurn() + "'s turn.");
				board.printBoard();
				
				if (board.getTurn() == buttonValues.BLACK)
				{
					board.placeMove(askForMove(board));
				}
				else
				{
					System.out.println("Computer is placing it's move...");
					TimeUnit.SECONDS.sleep(2);
					AI AI = new AI();
					AI.evaluateCapturesAndLocation2(board);
					board.placeMove(AI.getBestMove(board));
				}
			}
			findWinner(board);
			break;
		case 3:
			int whiteWins = 0;
			int blackWins = 0;

			for (int i = 0; i < 1000; i++) ///runs 1000 games
			{
				board = new Board();
				
				while (hasAvailableMoves(board))
				{
					System.out.println();
					System.out.println("It's " + board.getTurn() + "'s turn.");
					board.printBoard();
					
					if (board.getTurn() == buttonValues.BLACK)
					{
						AI randomAI = new AI();
						randomAI.evaluateRandom(board);
						board.placeMove(randomAI.getBestMove(board));
					}
					else
					{
						AI AI = new AI();
						
//						Choose one of following:
//						AI.evaluateRandom();
//						AI.evaluateCaptures(board); //AI that maximizes taking most pieces
//						AI.evaluateLocation(board); //AI that evaluates move by it's location on the board 
//						AI.evaluateLocation2(board); //AI that evaluates move by it's location on the board (different source for points)
//						AI.evaluateMobility(board); //AI that minimizes the number of opponents available moves
						AI.evaluateCapturesAndLocation2(board);
						
						board.placeMove(AI.getBestMove(board));
						
//						AI that evaluates the captured locations with a depth of 5
//						board.placeMove(AI.evaluateMiniMaxNode(board, board.getTurn(), 5, true,Integer.MIN_VALUE,Integer.MAX_VALUE).getMoveSquare());
					}
				}
				buttonValues winner = findWinner(board);
				if (winner == buttonValues.BLACK)
				{
					blackWins++;
				} 
				else if (winner == buttonValues.WHITE) 
				{
					whiteWins++;
				}
			}
			System.out.println("Black won " + blackWins + " times.");
			System.out.println("White won " + whiteWins + " times.");
			break;
			
		default:
			System.out.println("Something went wrong, try again.");
		}
}
}
