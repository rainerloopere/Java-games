package november30;

import java.util.ArrayList;

public class Game {
	
//	private static Board board;
	
//	public Game()
//	{
//		board = new Board();
//	}
//	testing
	public static void main(String[] args) 
	{
		Board board = new Board();
		board.printBoard();
		
//		Dark moves first
		
//		* user chooses the location
// 		1) go in all directions
//		2) create a list in all directions
//		3) go through the list, identify if applicable
//		4) if available move, use same direction pattern to change colours within list
		
		board.checkAllDirections(3,1,"b");
//		board.getButton(3, 4);
		
	}

}
