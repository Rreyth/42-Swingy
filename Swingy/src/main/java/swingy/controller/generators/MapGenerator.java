package swingy.controller.generators;

import java.util.ArrayList;
import java.util.List;

import swingy.model.entity.Villain;
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
		this.populateMap(map, playerLevel);
		return (map);
	}

	private int	computeSize(int playerLevel)
	{
		return ((playerLevel - 1) * 5 + 10 - (playerLevel % 2));
	}

	private void	populateMap(GameMap	map, int playerLevel)
	{
		int			size;
		int[]		newPos;
		List<int[]> takenPos;
		int			totalSize;
		int			nbVillain;
		Villain		newVillain;

		size = map.getSize();
		totalSize = size * size;
		nbVillain = (int)((double) totalSize * 0.20);
		takenPos = new ArrayList<>();

		takenPos.add(new int[]{size / 2, size / 2});
		map.getTile(size / 2, size / 2).setPlayerHere(true);
		map.setPlayerPos(size / 2, size / 2);

		for (int i = 0; i < nbVillain; i++)
		{
			newPos = posGenerator(size);
			while (alreadyTaken(takenPos, newPos))
				newPos = posGenerator(size);
			takenPos.add(newPos);
			newVillain = VillainGenerator.getInstance().newVillain(playerLevel);
			map.getTile(newPos[0], newPos[1]).setOccupied(true);
			map.getTile(newPos[0], newPos[1]).setVillain(newVillain);
		}

		//TODO : floor artifact gen ?
	}

	private boolean	alreadyTaken(List<int[]> takenPos, int[] newPos)
	{
		for (int[] pos : takenPos)
		{
			if (pos[0] == newPos[0] && pos[1] == newPos[1])
				return (true);
		}
		return (false);
	}

	private int[]	posGenerator(int max)
	{
		int	x;
		int	y;

		x = RandGenerator.getInstance().randInt(0, max);
		y = RandGenerator.getInstance().randInt(0, max);

		return (new int[]{x, y});
	}
}
