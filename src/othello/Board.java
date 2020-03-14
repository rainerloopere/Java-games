package othello;

import java.util.ArrayList;
import othello.Square.buttonValues;

//Text file encoding must be changed into UTF-8
//Standard board is 8x8.
//Board class is used to keep track of the current status of the board.
public class Board{
	
	private Square[][] board;
	private buttonValues turn; 
	private ArrayList<Move> availableMoves;
	
	public Board ()
	{
		this.board = new Square[8][8];
		initializeBoard();
		this.turn = buttonValues.BLACK; //Black starts the game
		this.availableMoves = findAvailableMoves();
	}
//	Using second constructor to create deep copies of Board without dependencies.
	public Board (Board boardStatus)
	{
		this.board = new Square[8][8];
		this.turn = boardStatus.getTurn();
		for (int i = 0; i < boardStatus.getBoard().length; i++) 
		{
			for (int j = 0; j < boardStatus.getBoard()[i].length; j++)
			{
				this.board[i][j] = new Square(boardStatus.getBoard()[i][j]);
			}
		}
		this.availableMoves = findAvailableMoves();
	}
	public Square[][] getBoard() 
	{
		return board;
	}
	public buttonValues getTurn() 
	{
		return turn;
	}
	public ArrayList<Move> getAvailableMoves() 
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
					board[i][j] = new Square(j,i,buttonValues.WHITE);
				}
				else if ((i==3 && j == 4) || (i==4 && j == 3))
				{
					board[i][j] = new Square(j,i,buttonValues.BLACK);
				}
				else
				{
					board[i][j] = new Square(j,i,buttonValues.EMPTY);
				}
			}
		}
	}
//	This method goes through all the Squares on the board and analyses if the Square would be a legitimate move. All potential moves are added to a list.
	public ArrayList<Move> findAvailableMoves()
	{
		ArrayList<Move> availableMoves = new ArrayList<Move>();
		for (int i = 0; i < board.length; i++) 
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				Square moveSquare = new Square(j,i,turn);
				Move move = new Move(moveSquare, board);
				if (move.getCaptureSquares().size() > 0)
				{
					availableMoves.add(move);
				}
			}
			
		}
		this.availableMoves = availableMoves;
		return availableMoves;
	}
//	This method analyses if a Square in question is in the list of available moves.
	public boolean isAvailableMove(Square square)
	{
		for (Move move : availableMoves)
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
		System.out.println("A B C D E F G H");
		for (int i = 0; i < board.length; i++) 
		{
//			System.out.println("\n");
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
				}
				if (isAvailableMove(new Square(j,i,turn)))
				{
					squarePrint = "☒";
				}
				System.out.print(squarePrint + "  ");
			}
			System.out.print((i+1 + "\n"));
		}
	}
	public void placeMove (Square square)
	{
		board[square.getLocationY()][square.getLocationX()].setButton(square.getButton());
		for (Move move : availableMoves)
		{
			if (move.getMoveSquare().getLocationX() == square.getLocationX() && move.getMoveSquare().getLocationY() == square.getLocationY())
			{
				for (Square captureSquare : move.getCaptureSquares())
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
	

	

	

	

	

}
