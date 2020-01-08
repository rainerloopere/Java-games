package othello;

import java.util.ArrayList;


public class AI2{

//	also possible to add each evaluation to move, but seems useless?
	private ArrayList<Integer> availableMovesEvaluation;
	
	
	public Square2 getBestMove (BoardStatus2 boardStatus)
	{
		int bestMoveIndex = 0;
		for (int evaluation : availableMovesEvaluation)
		{
			if (evaluation > availableMovesEvaluation.get(bestMoveIndex))
			{
//				System.out.println("evaluation " + evaluation + " max " + availableMovesEvaluation.get(bestMoveIndex)); //temp
				bestMoveIndex = availableMovesEvaluation.indexOf(evaluation);
			}
		}
//		System.out.println(boardStatus.getAvailableMoves()); //temp
		System.out.println("best move is " + boardStatus.getAvailableMoves().get(bestMoveIndex).getMoveSquare());
		return boardStatus.getAvailableMoves().get(bestMoveIndex).getMoveSquare();
	}
	public ArrayList<Integer> evaluateRandom (BoardStatus2 boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (int i = 0 ; i < boardStatus.getAvailableMoves().size() ; i++)
		{
			evaluatedMoves.add((int)(Math.random()*100)); //scale of 0-100
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	public ArrayList<Integer> evaluateCaptures (BoardStatus2 boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (Move2 move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(move.getCaptureSquares().size());
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	public ArrayList<Integer> evaluateLocation (BoardStatus2 boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		
		int[][] values ={
			{ 99, -8,  8,  6,  6,  8, -8, 99},
			{ -8,-24, -4, -3, -3, -4,-24, -8},
			{  8, -4,  7,  4,  4,  7, -4,  8},
			{  6, -3,  4,  0,  0,  4, -3,  6},
			{  6, -3,  4,  0,  0,  4, -3,  6},
			{  8, -4,  7,  4,  4,  7, -4,  8},
			{ -8,-24, -4, -3, -3, -4,-24, -8},
			{ 99, -8,  8,  6,  6,  8, -8, 99}};
		
		for (Move2 move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(values[move.getMoveSquare().getLocationY()][move.getMoveSquare().getLocationX()]);
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	public ArrayList<Integer> evaluateLocation2 (BoardStatus2 boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		
		int[][] values ={
			{200,-100, 100, 50, 50,100,-100,200},
			{-100,-200,-50,-50,-50,-50,-200,-100},
			{100,-50,100,  0,  0,100,-50,100},
			{50, -50,  0,  0,  0,  0,-50, 50},
			{50, -50,  0,  0,  0,  0,-50, 50},
			{100,-50,100,  0,  0,100,-50,100},
			{-100,-200,-50,-50,-50,-50,-200,-100},
			{200,-100, 100, 50, 50,100,-100,200}};
		
		for (Move2 move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(values[move.getMoveSquare().getLocationY()][move.getMoveSquare().getLocationX()]);
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
//	evaluate each move by choosing the move that limits opponents next round moves
	public ArrayList<Integer> evaluateMobility (BoardStatus2 boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();

		for (Move2 move : boardStatus.getAvailableMoves())
		{
//			System.out.println(move.getMoveSquare()); //temp
			BoardStatus2 tempBoard = new BoardStatus2(boardStatus);
//			System.out.println("copy of the board"); //temp
//			tempBoard.printBoard(); //temp
			tempBoard.placeMove(move.getMoveSquare());
//			System.out.println("evaluating a potential move:"); //temp
//			tempBoard.printBoard(); //temp
		
			// Multiplied with -1 because getBestMove works to find the highest evaluation. In this case, smallest number of opponents moves is best so evaluation is inverted.
			evaluatedMoves.add(tempBoard.getAvailableMoves().size() * -1); 
		}
//		System.out.println(boardStatus.getAvailableMoves()); //temp
//		System.out.println(evaluatedMoves); //temp
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	
}
