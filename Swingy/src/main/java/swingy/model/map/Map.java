package swingy.model.map;

import swingy.model.map.Tile;

public class Map {
	private Tile[][] map;
	private int size;

	public Map(int size) {
		this.size = size;
		this.map = new Tile[size][size];
	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

	public void setTile(int x, int y, Tile tile) {
		map[x][y] = tile;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}