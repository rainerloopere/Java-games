package othello;

import java.util.ArrayList;

import othello.Square2.buttonValues;

public class BoardStatus2 extends Board2{
	private buttonValues turn; 
	private ArrayList<Move2> availableMoves;
	
	public BoardStatus2 ()
	{
		this.turn = buttonValues.BLACK; //Black starts the game
		this.availableMoves = findAvailableMoves();
	}
	
	public buttonValues getTurn() 
	{
		return turn;
	}

	public ArrayList<Move2> getAvailableMoves() 
	{
		return availableMoves;
	}

	public ArrayList<Move2> findAvailableMoves()
	{
		ArrayList<Move2> availableMoves = new ArrayList<Move2>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				Square2 moveSquare = new Square2(j,i,turn);
				Move2 move = new Move2(moveSquare);
				if (move.getCaptureSquares().size() > 0)
				{
//					board[i][j] = "x";
					availableMoves.add(move);
				}
			}
			
		}
		this.availableMoves = availableMoves;
		return availableMoves;
	}
	public boolean isAvailableMove(Square2 square)
	{
		for (Move2 move : availableMoves)
		{
			if (move.getMoveSquare().getLocationX() == square.getLocationX() && move.getMoveSquare().getLocationY() == square.getLocationY())
			{
				return true;
			}
		}
		return false;
	}
	
	public void printBoard ()
	{
		System.out.println("0 1 2 3 4 5 6 7");
		System.out.println("A B C D E F G H");
		for (int i = 0; i < board.length; i++) 
		{
//			System.out.print("  ");
//			System.out.print((i+1) + "\u2001"); // space in there appears with different width every single time
			for (int j = 0; j < board[i].length; j++) 
			{
				String squarePrint = "";
				switch(board[j][i].getButton())
				{
				case WHITE:
					squarePrint = "○";
					break;
				case BLACK:
					squarePrint = "●";
					break;
				case EMPTY:
					squarePrint = "☐";
					break;
//				case ACTIVE:
//					squarePrint = "☒";
//					break;
				}
				if (isAvailableMove(new Square2(j,i,turn)))
				{
					squarePrint = "☒";
				}
				System.out.print(squarePrint + "  ");
			}
			System.out.print((i+1));
			System.out.println(" " + i);
		}
	}
	public void placeMove (Square2 square)
	{
		super.board[square.getLocationX()][square.getLocationY()].setButton(square.getButton());
		for (Move2 move : availableMoves)
		{
			if (move.getMoveSquare().getLocationX() == square.getLocationX() && move.getMoveSquare().getLocationY() == square.getLocationY())
			{
				for (Square2 captureSquare : move.getCaptureSquares())
				{
					super.board[captureSquare.getLocationY()][captureSquare.getLocationX()].setButton(square.getButton());
				}
			}
		}
//		Switch player
		turn = (turn == buttonValues.BLACK)? buttonValues.WHITE : buttonValues.BLACK;
		findAvailableMoves();

	}
	

	

	

	

}
