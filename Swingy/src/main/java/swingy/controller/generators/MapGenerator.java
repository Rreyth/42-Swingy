package swingy.controller.generators;

import swingy.model.map.GameMap;
import swingy.model.map.Tile;

public class MapGenerator
{
	private static MapGenerator	instance = null;

	private MapGenerator() {}

	public static MapGenerator	getInstance()
	{
		if (instance == null)
		{
			instance = new MapGenerator();
		}
		return instance;
	}

	public GameMap	newMap(int playerLevel)
	{
		int	size = this.computeSize(playerLevel);
		GameMap	map = new GameMap(size);
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				map.setTile(j, i, new Tile(j, i));
			}
		}
		this.populateMap(map);
		return (map);
	}

	private int	computeSize(int playerLevel)
	{
		return ((playerLevel - 1) * 5 + 10 - (playerLevel % 2));
	}

	private void	populateMap(GameMap	map)
	{
		//TODO : add mob gen + floor artifact gen
		// When a map is generated, villains of varying power will be spread randomly over the map
		int	size;
		size = map.getSize();
		map.getTile(size / 2, size / 2).setPlayerHere(true);
	}
}
