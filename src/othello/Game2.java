package othello;

import java.util.ArrayList;
import java.util.Scanner;

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

//			try 
//			{
				locationX = (int) inputLocation.toLowerCase().charAt(0) - 'a';
				locationY = (int) inputLocation.charAt(1) - '0' - 1;
				break;
//			} 
//			catch (Exception e) 
//			{
//				System.out.println("Wrong format for the input, try again.");
//			} 
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
			while (boardStatus.getAvailableMoves().size() > 0)
			{
//				System.out.println();
				board.printBoard();
				System.out.println("It's " + boardStatus.getTurn() + "'s turn.");
				boardStatus.printBoard();
				boardStatus.placeMove(askForMove(boardStatus));
			}
//			findWinner(board);
			break;
//
//		case 2:
//			//			Randomize who starts
//			while (hasAvailableMoves(board))
//			{
//				System.out.println();
//				System.out.println("It's " + turn + "'s turn.");
//
//				if (turn.equals("white"))
//				{
////					board.placeMove(getBestMove(board,evaluateRandom(board,turn.substring(0,1)),turn.substring(0,1)));
//					board.placeMove(getBestMove(board,evaluateCaptures(board,turn.substring(0,1)),turn.substring(0,1)));
//				}
//				else
//				{
//					ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
//					board.placeMove(askForMove(availableMoves));
//				}
//				switchPlayer();
//			}
//			findWinner(board);
//
//			break;
//
//		case 3:
//			int whiteWins = 0;
//			int blackWins = 0;
//			
//			for (int i = 0; i < 1000; i++) 
//			{
//				board = new Board();
//				
//				while (hasAvailableMoves(board)) {
//					System.out.println();
//					System.out.println("It's " + turn + "'s turn.");
//
//					if (turn.equals("white")) 
//					{
//						board.placeMove(getBestMove(board, evaluateMobility(board, turn.substring(0, 1)),turn.substring(0, 1)));
//					} 
//					else 
//					{
//						board.placeMove(getBestMove(board, evaluateRandom(board, turn.substring(0, 1)), turn.substring(0, 1)));
//					}
//					switchPlayer();
//				}
//				String winner = findWinner(board);
//				if (winner.equals("b"))
//				{
//					blackWins++;
//				} 
//				else if (winner.equals("w")) 
//				{
//					whiteWins++;
//				} 
//			}
//			System.out.println("Black won " + blackWins + " times.");
//			System.out.println("White won " + whiteWins + " times.");
//			break;
//		default:
//			System.out.println("Something went wrong, try again.");
		}


}
}
