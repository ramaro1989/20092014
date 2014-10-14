package motion;

import searchalgorithm.HeuristicFunction;
import searchalgorithm.Node;
import searchproblem.InformedSearchProblem;

public class RoverProblem extends InformedSearchProblem implements
		HeuristicFunction {

	private RoverState goalState;
	private final double MIN_COST = 1;
	private final double DIAGONAL_MIN_COST = Math.sqrt(2) * MIN_COST;

	private int dx, dy, dz;

	public RoverProblem(RoverState initial, RoverState goal) {
		super(initial, goal);

		this.goalState = goal;

	}

	@Override
	public double heuristic(Node n) {
		RoverState node = (RoverState) n.getState();

		dx = node.getCoordX() - goalState.getCoordX();
		dy = node.getCoordY() - goalState.getCoordY();
		dz = node.getHeight() - goalState.getHeight();

		return euclideanDistance(node,goalState);
	}

	private double diagonal() {
//		double diagonal = Math.max(Math.abs(dx), Math.abs(dy));
//		double hStraight = manhattan();
//		double hDiagonal = 1; // valor aldrabado
//		double finalCost = DIAGONAL_MIN_COST * hDiagonal + MIN_COST
//				* (hStraight - 2 * hDiagonal);
//		return finalCost;
		
		
		 double diagonal = Math.max(Math.abs(dx), Math.abs(dy));
		  double hStraight = manhattan();
		  double hDiagonal = dz; // valor aldrabado
		  double finalCost = DIAGONAL_MIN_COST * diagonal + MIN_COST * (hStraight - 2 * hDiagonal);
		  return  finalCost;

	}

	private double manhattan() {
		return Math.abs(dx) + Math.abs(dy);
	}

	
	// {Node Expansions=541993, Nodes Generated=4330615}
	// 153988 
	private double euclideanDistance(RoverState current, RoverState goal) {
		double dh = current.getHeight() - goal.getHeight();
		double dx = current.getCoordX() - goal.getCoordX();
		double dy = current.getCoordY() - goal.getCoordY();
		double distance = Math.sqrt(dx * dx + dy * dy + dh * dh);
		

		return distance;
	}
	
	
	
}