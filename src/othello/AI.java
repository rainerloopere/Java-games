package othello;

import java.util.ArrayList;


public class AI extends Board{
	
	public ArrayList<Integer> evaluateRandom (String referenceButton)
	{
		ArrayList<Square> availableMoves = getAvailableMoves(referenceButton);
		ArrayList<Integer> evaluatedMoves = new ArrayList<Integer>();
		for (int i = 0 ; i < availableMoves.size() ; i++)
		{
			evaluatedMoves.add((int)(Math.random()*100)); //scale of 0-100
		}
		return evaluatedMoves;
	}
	
	public Square getBestMove (ArrayList<Integer> evaluatedMoves, String referenceButton)
	{
		ArrayList<Square> availableMoves = getAvailableMoves(referenceButton);
		int bestMoveIdex = 0;
		for (int evaluation : evaluatedMoves)
		{
			if (evaluation > evaluatedMoves.get(bestMoveIdex))
			{
				bestMoveIdex = availableMoves.indexOf(evaluation);
			}
		}
		return availableMoves.get(bestMoveIdex);
	}
	

}
