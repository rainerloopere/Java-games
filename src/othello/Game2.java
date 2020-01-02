package othello;

import java.util.ArrayList;
import java.util.Scanner;

public class Game2 {
	public static void main(String[] args) 
	{
//				Board2 board = new Board2();
//				board.printBoard();
		//		Dark moves first

		//		board.checkAllDirections(5,4,"b");
		//		board.getButton(3, 4);
		//		board.checkAllSquares("w");

		//		board.getSquareList(3, 2, "b", 0, 0);

//		Board2 board = new Board2();
//		Scanner input = new Scanner(System.in);
//
//		System.out.println("Enter the option to play:");
//		System.out.println("1 - two players");
//		System.out.println("2 - play against computer");
//		System.out.println("3 - computer against computer");
//		int inputOption = Integer.parseInt(input.nextLine());
//		turn = "black";
//
//		switch (inputOption)
//		{
//		case 1:
//			
//			while (hasAvailableMoves(board))
//			{
//				System.out.println();
//				board.printBoard();
//				System.out.println("It's " + turn + "'s turn.");
//				ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
//
//				board.placeMove(askForMove(availableMoves));
//				switchPlayer();
//			}
//			findWinner(board);
//			break;
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
//		}


}
}
