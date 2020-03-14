package othello;

import java.util.ArrayList;

import othello.Square.buttonValues;

//AI class is gathering the different methods and options that can be used to evaluate moves for the computer player. 
public class AI{

//	This list corresponds to the availableMoves at Board class. The order of items in the list is same as availableMoves.
	private ArrayList<Integer> availableMovesEvaluation;
	
	public Square getBestMove (Board boardStatus)
	{
		int bestMoveIndex = 0;
		for (int evaluation : availableMovesEvaluation)
		{
			if (evaluation > availableMovesEvaluation.get(bestMoveIndex))
			{
				bestMoveIndex = availableMovesEvaluation.indexOf(evaluation);
			}
		}
		return boardStatus.getAvailableMoves().get(bestMoveIndex).getMoveSquare();
	}
	public ArrayList<Integer> evaluateRandom (Board boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (int i = 0 ; i < boardStatus.getAvailableMoves().size() ; i++)
		{
			evaluatedMoves.add((int)(Math.random()*100)); //scale of 0-100
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
//	This method values the move with highest captures.
	public ArrayList<Integer> evaluateCaptures (Board boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (Move move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(move.getCaptureSquares().size());
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
//	This method evaluates the current situation of the board based based on locations looking from the side of one player.
	public int evaluateBoardByLocation (Board boardStatus, buttonValues player)
	{
		int score = 0;
		
		int[][] values ={
			{ 99, -8,  8,  6,  6,  8, -8, 99},
			{ -8,-24, -4, -3, -3, -4,-24, -8},
			{  8, -4,  7,  4,  4,  7, -4,  8},
			{  6, -3,  4,  0,  0,  4, -3,  6},
			{  6, -3,  4,  0,  0,  4, -3,  6},
			{  8, -4,  7,  4,  4,  7, -4,  8},
			{ -8,-24, -4, -3, -3, -4,-24, -8},
			{ 99, -8,  8,  6,  6,  8, -8, 99}};
		
		for (int i = 0; i < boardStatus.getBoard().length; i++) 
		{
			for (int j = 0; j < boardStatus.getBoard()[i].length; j++) 
			{
				if (boardStatus.getBoard()[j][i].getButton() == player)
				{
					score += values[j][i];
				}
			}
		}
		return score;
	}
//	This method evaluates a move based on it's location on the board.
	public ArrayList<Integer> evaluateLocation (Board boardStatus)
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
		
		for (Move move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(values[move.getMoveSquare().getLocationY()][move.getMoveSquare().getLocationX()]);
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
//	This method evaluates a move based on it's location on the board (same logic, different source for values).
	public ArrayList<Integer> evaluateLocation2 (Board boardStatus)
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
		
		for (Move move : boardStatus.getAvailableMoves())
		{
			evaluatedMoves.add(values[move.getMoveSquare().getLocationY()][move.getMoveSquare().getLocationX()]);
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
//	This method values moves that are limiting opponents moves the next round.
	public ArrayList<Integer> evaluateMobility (Board boardStatus)
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();

		for (Move move : boardStatus.getAvailableMoves())
		{
			Board tempBoard = new Board(boardStatus);
			tempBoard.placeMove(move.getMoveSquare());
		
			// Multiplied with -1 because getBestMove works to find the highest evaluation. In this case, smallest number of opponents moves is best so evaluation is inverted.
			evaluatedMoves.add(tempBoard.getAvailableMoves().size() * -1); 
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	
//	Minimax algorithm with alpha-beta cutoffs. Uses locations to value a move at desired depth. Method is not tested and used in the final version of the Game
	public Move evaluateMiniMaxNode (Board boardStatus, buttonValues player, int depth, boolean maximize, int alpha, int beta)
	{
		Move bestMove;
		
//		If method has reached the desired depth (from n to 0) or there are no available moves, the evaluation is calculated.
		if (depth == 0 || !(Game.hasAvailableMoves(boardStatus)))
		{
			return new Move(evaluateBoardByLocation(boardStatus,player));
		}
		
//		This side makes sure that computer chooses a move that maximizes the value for himself.
		if (maximize)
		{
			bestMove = new Move(Integer.MIN_VALUE);
			
			for (Move move : boardStatus.getAvailableMoves())
			{
				Board tempBoard = new Board(boardStatus);
				tempBoard.placeMove(move.getMoveSquare());
				
				int score = evaluateMiniMaxNode(boardStatus, player, depth-1, false, alpha, beta).getEvaluation();
				if (score > bestMove.getEvaluation()) 
					{
					bestMove = new Move(move.getMoveSquare(),boardStatus.getBoard());
					bestMove.setEvaluation(score);
					}
				//alpha-beta cutoff
				if (score > alpha) alpha = score;
				if (beta <= alpha) break;
			}
		}
//		This side assumes that opponent of the player will be choosing the move that minimizes the value for original player.
		else
		{
			bestMove = new Move(Integer.MAX_VALUE);
			
			for (Move move : boardStatus.getAvailableMoves())
			{
				Board tempBoard = new Board(boardStatus);
				tempBoard.placeMove(move.getMoveSquare());
				
				int score = evaluateMiniMaxNode(boardStatus, player, depth-1, true, alpha, beta).getEvaluation();
				if (score < bestMove.getEvaluation()) 
				{
				bestMove = new Move(move.getMoveSquare(),boardStatus.getBoard());
				bestMove.setEvaluation(score);
				}
				//alpha-beta cutoff
				if (score < beta) beta = score;
				if (beta <= alpha) break;
			}
		}
		return bestMove;
	}
//	Hybrid of evaluateCaptures and evaluateLocation2
	public ArrayList<Integer> evaluateCapturesAndLocation2 (Board boardStatus)
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
		
		for (Move move : boardStatus.getAvailableMoves())
		{
			int locationEvaluation = values[move.getMoveSquare().getLocationY()][move.getMoveSquare().getLocationX()];
			int captureEvaluation = move.getCaptureSquares().size();
			evaluatedMoves.add(locationEvaluation + captureEvaluation);
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	
//	Evaluation methods were tested by running two computer player against each other for 1000 times.
//	evaluateRandom vs evaluateCaptures - 364 vs 607
//	evaluateRandom vs evaluateLocation - 202 vs 761
//	evaluateRandom vs evaluateLocation2 - 174 vs 781
//	evaluateRandom vs evaluateMobility - 314 vs 674
//	evaluateRandom vs evaluateMiniMaxNode & evaluateBoardByLocation - 41 vs 56 (100 times because it's slow)
//	evaluateRandom vs evaluateCapturesAndLocation2 - 184 vs 785
	
}
