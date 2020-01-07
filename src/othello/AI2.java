package othello;

import java.util.ArrayList;


public class AI2 extends BoardStatus2{

//	also possible to add each evaluation to move, but seems useless?
	private static ArrayList<Integer> availableMovesEvaluation;
	
	public static Square2 getBestMove ()
	{
		int bestMoveIndex = 0;
		for (int evaluation : availableMovesEvaluation)
		{
			if (evaluation > availableMovesEvaluation.get(bestMoveIndex))
			{
//				System.out.println("evaluation " + evaluation + " max " + availableMovesEvaluation.get(bestMoveIndex));
				bestMoveIndex = availableMovesEvaluation.indexOf(evaluation);
			}
		}
		System.out.println("best move is " + getAvailableMoves().get(bestMoveIndex).getMoveSquare());
		return getAvailableMoves().get(bestMoveIndex).getMoveSquare();
	}
	public static ArrayList<Integer> evaluateRandom ()
	{
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (int i = 0 ; i < getAvailableMoves().size() ; i++)
		{
			evaluatedMoves.add((int)(Math.random()*100)); //scale of 0-100
		}
		availableMovesEvaluation = evaluatedMoves;
		return evaluatedMoves;
	}
	
}
