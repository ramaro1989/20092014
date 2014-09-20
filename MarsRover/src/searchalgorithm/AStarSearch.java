package searchalgorithm;

import java.util.*;

import searchproblem.InformedSearchProblem;

public class AStarSearch implements SearchAlgorithm {
	private GraphSearch graph;

	public AStarSearch(final InformedSearchProblem p) {
		Queue<Node> pqueue = new PriorityQueue<Node>(11,
				new Comparator<Node>() {

					public int compare(Node o1, Node o2) {

						double cost1 = o1.getPathCost() + p.heuristic(o1);
						double cost2 = o2.getPathCost() + p.heuristic(o2);

						if (cost1 > cost2)
							return 1;
						else if (cost1 < cost2)
							return -1;
						else
							return 0;
					}
				});
		graph = new GraphSearch(p, pqueue);

	}

	@Override
	public Node searchSolution() {
		// TODO Auto-generated method stub
		return graph.searchSolution();
	}

	@Override
	public Map<String, Number> getMetrics() {
		// TODO Auto-generated method stub
		return graph.getMetrics();
	}
}