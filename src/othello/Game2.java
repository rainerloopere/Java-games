package othello;

import java.util.ArrayList;
import java.util.Scanner;

import othello.Square2.buttonValues;

public class Game2 {
	
	public static Square2 askForMove (BoardStatus2 boardStatus)
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
		Square2 square = new Square2 (locationX, locationY, boardStatus.getTurn()); //button for available moves is always same based on who's turn it is
//		if (isAvailableMove(locationX,locationY,availableMoves))
		if (boardStatus.isAvailableMove(square))
		{
			return square;
		}
		else
		{
			System.out.println("This is not an available move to play. Try again.");
			return askForMove(boardStatus);
		}
	}
	public static boolean hasAvailableMoves(BoardStatus2 boardStatus)
	{
		if (boardStatus.getAvailableMoves().size()==0)
		{
			System.out.println (boardStatus.getTurn() + " does not have any available moves and will have to skip.");
			boardStatus.switchPlayer();
			if (boardStatus.getAvailableMoves().size()==0)
			{
				System.out.println (boardStatus.getTurn() + " also does not have any available moves.");
				System.out.println("Game over!");
				return false;
			}
		}
		return true;
	}
	public static buttonValues findWinner(Board2 board)
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
	
	public static void main(String[] args) 
	{

		//		Dark moves first

		//		board.checkAllDirections(5,4,"b");
		//		board.getButton(3, 4);
		//		board.checkAllSquares("w");

		//		board.getSquareList(3, 2, "b", 0, 0);

		Board2 board = new Board2();
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the option to play:");
		System.out.println("1 - two players");
		System.out.println("2 - play against computer");
		System.out.println("3 - computer against computer");
		int inputOption = Integer.parseInt(input.nextLine());
		BoardStatus2 boardStatus = new BoardStatus2();
//
		switch (inputOption)
		{
		case 1:
			while (hasAvailableMoves(boardStatus))
//			int counter = 0;
//			while (counter < 2)
			{
				System.out.println();
				System.out.println("It's " + boardStatus.getTurn() + "'s turn.");
				boardStatus.printBoard();
				boardStatus.placeMove(askForMove(boardStatus));
//				counter++;
			}
			findWinner(board);
			break;
		case 2:
			System.out.println("User starts!");
//			Randomize who starts
			while (hasAvailableMoves(boardStatus))
			{
				System.out.println();
				System.out.println("It's " + boardStatus.getTurn() + "'s turn.");
				boardStatus.printBoard();
				
				if (boardStatus.getTurn() == buttonValues.BLACK)
				{
					boardStatus.placeMove(askForMove(boardStatus));
				}
				else
				{
					AI2.evaluateRandom();
					boardStatus.placeMove(AI2.getBestMove());
				}
			}
			findWinner(board);

			break;
		case 3:
			int whiteWins = 0;
			int blackWins = 0;

			for (int i = 0; i < 2; i++) 
			{
				boardStatus = new BoardStatus2();
				
				while (hasAvailableMoves(boardStatus))
				{
					System.out.println();
					System.out.println("It's " + boardStatus.getTurn() + "'s turn.");
					boardStatus.printBoard();
					
					if (boardStatus.getTurn() == buttonValues.BLACK)
					{
						AI2.evaluateRandom();
						boardStatus.placeMove(AI2.getBestMove());
					}
					else
					{
						AI2.evaluateRandom();
						boardStatus.placeMove(AI2.getBestMove());
					}
				}
				buttonValues winner = findWinner(board);
				if (findWinner(board) == buttonValues.BLACK)
				{
					blackWins++;
				} 
				else if (findWinner(board) == buttonValues.WHITE) 
				{
					whiteWins++;
				}
			}
			System.out.println("Black won " + blackWins + " times.");
			System.out.println("White won " + whiteWins + " times.");
			break;
			
//			counting does not work because it is running on board class. board class is useless and needs to be refactored.
		default:
			System.out.println("Something went wrong, try again.");
		}


}
}
