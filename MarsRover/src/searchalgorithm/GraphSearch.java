package searchalgorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import motion.AnimatedSearch;
import motion.RoverState;
import searchproblem.SearchProblem;

public class GraphSearch implements SearchAlgorithm {
	private boolean done = false;
	private SearchProblem problem;
	private Node goal;
	private Queue<Node> frontier;
	private int expansions;
	private int generated;
	private HashMap<Node, Node> explored;
	private long timeElapsed;

	public GraphSearch(SearchProblem p, Queue<Node> q) {

		problem = p;
		goal = null;
		frontier = q;
		expansions = 0;
		generated = 0;

	}

	public Node searchSolution() {
		if (!done) {
			timeElapsed = System.currentTimeMillis();
			goal = search();
			done = true;
			frontier = null;
			problem = null;
		}
		timeElapsed = System.currentTimeMillis() - timeElapsed;
		return goal;
	}

	private Node search() {
		frontier.clear();
		frontier.add(new Node(problem.getInitial()));
		generated++;
		explored = new HashMap<Node, Node>();
		for (;;) {
			if (frontier.isEmpty()) {
				return null;
			}
			Node n = frontier.remove();
			RoverState rover = (RoverState) n.getState();
			AnimatedSearch.draw(rover.getCoordX(), rover.getCoordY());
			if (problem.goalTest(n.getState())) {
				return n;
			}
			if (!explored.containsKey(n)) {
				explored.put(n, n);
				expansions++;
				List<Node> children = n.Expand();
				generated += children.size();
				frontier.addAll(children);
			} else {

				// optimização caso encontre nós já explorados com um valor da
				// função de avaliação menor
				Node old = explored.get(n);
				if (old.getPathCost() > n.getPathCost()) {
					explored.put(n, n);
					expansions++;
					List<Node> children = n.Expand();
					generated += children.size();
					frontier.addAll(children);
				}
			}
		}
	}

	public Map<String, Number> getMetrics() {
		Map<String, Number> metrics = new LinkedHashMap<String, Number>();

		metrics.put("Node Expansions", expansions);
		metrics.put("Nodes Generated", generated);
		metrics.put("Time Elapsed (ms)", timeElapsed);
		return metrics;
	}
}
