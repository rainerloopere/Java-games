package othello;

import java.util.ArrayList;

import othello.Square2.buttonValues;

public class Move2 extends Square2 {
	
	private ArrayList<Square2> captureSquares;
	
	public Move2(int locationX, int locationY, buttonValues button)
	{
		super(locationX, locationY, button);
		getcaptureSquares();
	}
	
//	add here getcapturesquare
//	then change the boardstatus to use a list of moves

}
