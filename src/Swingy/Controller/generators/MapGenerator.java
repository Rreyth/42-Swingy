package Swingy.Controller.generators;

import Swingy.Model.map.Map;
import Swingy.Model.map.Tile;

public class MapGenerator {
	private static MapGenerator instance = null;

	private MapGenerator() {}

	public static MapGenerator getInstance() {
		if (instance == null) {
			instance = new MapGenerator();
		}
		return instance;
	}

	public Map newMap(int size) { // add event gen ?
		Map map = new Map(size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				map.setTile(i, j, new Tile(i, j));
			}
		}
		return map;
	}
}