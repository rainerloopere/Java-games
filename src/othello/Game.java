package othello;


import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
//	private static Board board;
	
//	public Game()
//	{
//		board = new Board();
//	}
//	testing
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
			System.out.println("It's " + turn + "'s turn.");
//			something fails with substring
			ArrayList<Square> availableMoves = board.getAvailableMoves(turn.substring(0,1));
			System.out.println("Enter the move you want to play (e.g. A1):");
//			input is not readed
			String inputLocation = input.nextLine();
			int locationY = (int)inputLocation.toLowerCase().charAt(2) - 'a'; //I'm standardazing the character into scale 0-7
			int locationX = (int)inputLocation.charAt(3) - '0'- 1;
			System.out.println(locationX);
			System.out.println(locationY);
			break;
		case 2:
			
			break;
		default:
			System.out.println("Something went wrong, try again.");
		}
		
	}

}
