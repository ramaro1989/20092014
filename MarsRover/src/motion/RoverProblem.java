package motion;

import searchalgorithm.HeuristicFunction;
import searchalgorithm.Node;
import searchproblem.InformedSearchProblem;
import searchproblem.State;

public class RoverProblem extends InformedSearchProblem implements
		HeuristicFunction {

	private RoverState initialState, goalState;
	private final double MIN_COST = 1;
	private final double DIAGONAL_MIN_COST = Math.sqrt(2) * MIN_COST;

	private int dx, dy, dz;

	public RoverProblem(RoverState initial, RoverState goal) {
		super(initial, goal);

		this.initialState = initial;
		this.goalState = goal;

		dx = initial.getCoordX() - goal.getCoordX();
		dy = initial.getCoordY() - goal.getCoordY();
		dz = initial.getHeight() - goal.getHeight();
	}

	@Override
	public double heuristic(Node n) {
		// TODO Auto-generated method stub
		return 0;
	}

	private double diagonal() {
		double diagonal = Math.max(Math.abs(dx), Math.abs(dy));
		double hStraight = manhattan();
		double finalCost = diagonalMinCost * hDiagonal + minCost * (hStraight - 2 * hDiagonal);
		return  
		
	}

	private double manhattan() {
		return Math.abs(dx) + Math.abs(dy);
	}
}
