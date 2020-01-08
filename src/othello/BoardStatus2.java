package othello;

import java.util.ArrayList;
//Text file encoding must be changed into UTF-8
//Standard board is 8x8.

import othello.Square2.buttonValues;

public class BoardStatus2{
	
	private Square2[][] board;
	private buttonValues turn; 
	private ArrayList<Move2> availableMoves;
	
	public BoardStatus2 ()
	{
		this.board = new Square2[8][8];
		initializeBoard();
		this.turn = buttonValues.BLACK; //Black starts the game
		this.availableMoves = findAvailableMoves();
	}
	public Square2[][] getBoard() 
	{
		return board;
	}
	
	public buttonValues getTurn() 
	{
		return turn;
	}

	public ArrayList<Move2> getAvailableMoves() 
	{
		return availableMoves;
	}
	public void initializeBoard ()
	{
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if ((i==3 && j == 3) || (i==4 && j == 4))
				{
					board[i][j] = new Square2(j,i,buttonValues.WHITE);
				}
				else if ((i==3 && j == 4) || (i==4 && j == 3))
				{
					board[i][j] = new Square2(j,i,buttonValues.BLACK);
				}
				else
				{
					board[i][j] = new Square2(j,i,buttonValues.EMPTY);
				}
			}
		}
	}

	public ArrayList<Move2> findAvailableMoves()
	{
		ArrayList<Move2> availableMoves = new ArrayList<Move2>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				Square2 moveSquare = new Square2(j,i,turn);
				Move2 move = new Move2(moveSquare, board);
				if (move.getCaptureSquares().size() > 0)
				{
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
//		System.out.println(turn); //temp
		System.out.println("0 1 2 3 4 5 6 7");
		System.out.println("A B C D E F G H");
		for (int i = 0; i < board.length; i++) 
		{
//			System.out.print("  ");
//			System.out.print((i+1) + "\u2001"); // space in there appears with different width every single time
			for (int j = 0; j < board[i].length; j++) 
			{
				String squarePrint = "";
				switch(board[i][j].getButton())
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
			System.out.println(" " + i); //temp
		}

//		System.out.println("potential moves" + availableMoves); //temp
	}
	public void placeMove (Square2 square)
	{
//		System.out.println("placing move " + square); //temp
//		System.out.println("found square " + super.board[square.getLocationY()][square.getLocationX()]); //temp
		board[square.getLocationY()][square.getLocationX()].setButton(square.getButton());
		for (Move2 move : availableMoves)
		{
			if (move.getMoveSquare().getLocationX() == square.getLocationX() && move.getMoveSquare().getLocationY() == square.getLocationY())
			{
				for (Square2 captureSquare : move.getCaptureSquares())
				{
					board[captureSquare.getLocationY()][captureSquare.getLocationX()].setButton(square.getButton());
				}
			}
		}

		switchPlayer();
	}
	public void switchPlayer()
	{
		turn = (turn == buttonValues.BLACK)? buttonValues.WHITE : buttonValues.BLACK;
		findAvailableMoves();
	}
	public int countButtons (buttonValues referenceButton)
	{
		int count = 0;
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				if (board[j][i].getButton() == referenceButton)
				{
					count++;
				}
			}
		}
		return count;
	}
	
//	Using second constructor to make a deep copy of the board.
	public BoardStatus2 (BoardStatus2 boardStatus)
	{
		this.board = new Square2[8][8];
		this.turn = boardStatus.getTurn();
		for (int i = 0; i < boardStatus.getBoard().length; i++) 
		{
//			for (int j = 0; j < boardStatus.getBoard()[i].length; j++) 
			for (int j = 0; j < 8; j++) 
			{
//				System.out.println(boardStatus.getBoard()[i][j]);
//				System.out.println(this.board[i][j]);
				this.board[i][j] = new Square2(boardStatus.getBoard()[i][j]);
			}
		}
		this.availableMoves = findAvailableMoves();
	}

	

	

	

	

}
