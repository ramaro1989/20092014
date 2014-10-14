package motion;

import java.util.ArrayList;
import java.util.List;

import motion.Terrain.TerrainType;
import searchproblem.Arc;
import searchproblem.State;

public class RoverState extends State {

	private int x, y, height;
	private BitmapTerrain map;

	private enum RoverDirections {
		N, S, E, W, NE, NW, SE, SW;
	};

	public RoverState(int x, int y, BitmapTerrain map) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.height = map.getHeight(x, y);
	}

	public int getCoordX() {
		return x;
	}

	public int getCoordY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public List<Arc> successorFunction() {
		List<Arc> children = new ArrayList<Arc>(4);
		for (RoverDirections action : RoverDirections.values()) {
			if (applicableOperator(action))
				children.add(successorState(action));
		}
		return children;
	}

	private boolean applicableOperator(RoverDirections action) {
		boolean isApplicable = false;

		switch (action) {

		case N:

			if (y - 1 >= 0) {
				int h = map.getHeight(x, y - 1);

				double dh = Math.abs(h - height);

				if (dh <= 10) {
					isApplicable = true;
				}
			}
			break;

		case S:
			if (y + 1 < map.getVerticalSize()) {
				int h = map.getHeight(x, y + 1);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}
			}
			break;

		case E:
			if (x + 1 < map.getHorizontalSize()) {
				int h = map.getHeight(x + 1, y);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}
			break;

		case W:
			if (x - 1 >= 0) {
				int h = map.getHeight(x - 1, y);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}
			break;
		case NE:
			if (y - 1 >= 0 && x + 1 < map.getHorizontalSize()) {
				int h = map.getHeight(x + 1, y - 1);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}

			break;

		case NW:
			if (y - 1 >= 0 && x - 1 >= 0) {
				int h = map.getHeight(x - 1, y - 1);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}

			break;

		case SE:
			if (y + 1 < map.getVerticalSize()
					&& x + 1 < map.getHorizontalSize()) {
				int h = map.getHeight(x + 1, y + 1);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}

			break;

		case SW:
			if (y + 1 < map.getVerticalSize() && x - 1 >= 0) {
				int h = map.getHeight(x - 1, y + 1);
				double dh = Math.abs(h - height);
				if (dh <= 10) {
					isApplicable = true;
				}

			}

			break;
		}
		return isApplicable;
	}

	@Override
	public Arc successorState(Object op) {
		RoverState child = (RoverState) this.clone();

		return new Arc(this, child, op, child.applyOperator(op)); 
																	
	}

	@Override
	public double applyOperator(Object op) {
		double cost = 0;
		RoverDirections operator = (RoverDirections) op;

		switch (operator) {
		case N:
			cost = getMoveCost(x, y - 1);
			y--;
			break;
		case S:
			cost = getMoveCost(x, y + 1);
			y++;
			break;
		case E:
			cost = getMoveCost(x + 1, y);
			x++;
			break;
		case W:
			cost = getMoveCost(x - 1, y);
			x--;
			break;
		case NE:
			cost = getMoveCost(x + 1, y - 1);
			y--;
			x++;
			break;
		case NW:
			cost = getMoveCost(x - 1, y - 1);
			y--;
			x--;
			break;
		case SE:
			cost = getMoveCost(x + 1, y + 1);
			y++;
			x++;
			break;
		case SW:
			cost = getMoveCost(x - 1, y + 1);
			y++;
			x--;
			break;

		}

		return cost;
	}

	@Override
	public Object clone() {
		return new RoverState(this.getCoordX(), this.getCoordY(), this.map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoverState other = (RoverState) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	private double getMoveCost(int x, int y) {
		int h = map.getHeight(x, y);
		double dh = h - height;
		double dx = x - this.x;
		double dy = y - this.y;
		double distance = Math.sqrt(dx * dx + dy * dy + dh * dh);
		double dhcost = Math.exp(Math.abs(dh));
		double tType = 1.0;

		if (map.getTerrainType(this.x, this.y).equals(TerrainType.SAND))
			tType = 2.0;
		if (map.getTerrainType(this.x, this.y).equals(TerrainType.ROCK))
			tType = 3.0;

		return distance * dhcost * tType;
	}

}
