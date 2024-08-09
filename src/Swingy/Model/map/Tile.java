package Swingy.Model.map;

public class Tile {
	private int x;
	private int y;
	private boolean occupied;
	private boolean visited;

	// gen mob before or when player visits tile

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.occupied = false;
		this.visited = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
}