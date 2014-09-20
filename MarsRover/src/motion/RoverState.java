package motion;

import java.util.ArrayList;
import java.util.List;

import motion.Terrain.TerrainType;
import static java.lang.Math.*;
import searchproblem.*;


public class RoverState extends State {

	private int x,y,height;
	private BitmapTerrain map;
	private enum RoverDirections {
		N, S, E, W, NE, NW, SE, SW;
	};
	
	public RoverState(int x, int y, BitmapTerrain map) {
		this.x=x;
		this.y=y;
		this.map=map;
	}
	public int getCoordX() {
		return x;
	}
	public int getCoordY() {
		return y;
	}
	
	public int getheight() {
		return height;
	}
	
	
	@Override
	public List<Arc> successorFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arc successorState(Object op) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double applyOperator(Object op) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	private double getMoveCost(int x, int y){
		int h=map.getHeight(x, y);
		double dh = h-height;
		double dx = x - this.x * 10;
		double dy = y - this.y * 10;
		double distance = Math.sqrt(dx * dx + dy * dy + h * h);
		double dhcost = Math.exp(Math.abs(dh));
		double tType= 1.0;
		
		if(map.getTerrainType(x, y).equals(TerrainType.SAND))
			tType=2.0;
		if(map.getTerrainType(x, y).equals(TerrainType.ROCK))
			tType=3.0;
		
		return distance * dhcost * tType; 
	}
}

