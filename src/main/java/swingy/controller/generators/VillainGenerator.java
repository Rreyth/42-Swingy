package swingy.controller.generators;

import swingy.model.entity.Villain;

public class VillainGenerator
{
	private static VillainGenerator instance = null;

	private VillainGenerator() {}

	public static VillainGenerator getInstance()
	{
		if (instance == null)
		{
			instance = new VillainGenerator();
		}
		return instance;
	}

	private int[] genStats(int lvl, String type)
	{
		int[] stats = {0, 0, 0};

		switch (type)
		{
			case "Bandit":
				stats[0] = 6 + ((lvl - 1) * 3);
				stats[1] = 5 + ((lvl - 1) * 3);
				stats[2] = 80 + ((lvl - 1) * 10);
				break;
			case "Goblin":
				stats[0] = (int) Math.round(5 + ((lvl - 1) * 2.5));
				stats[1] = (int) Math.round(4 + ((lvl - 1) * 2.5));
				stats[2] = 65 + ((lvl - 1) * 7);
				break;
			case "Golem":
				stats[0] = (int) Math.round(7 + ((lvl - 1) * 3.5));
				stats[1] = (int) Math.round(6 + ((lvl - 1) * 3.5));
				stats[2] = 90 + ((lvl - 1) * 12);
				break;
		}

		return (stats);
	}

	public Villain	newVillain(int playerLevel)
	{
		int		lvl;
		int		typeRoll;
		int[]	stats;
		String	type;
		Villain	newVillain;

		lvl = RandGenerator.getInstance().randInt(playerLevel, playerLevel + 5);
		typeRoll = RandGenerator.getInstance().randInt(0, 101);

		if (typeRoll <= 55) // Bandit spawn rate = 55 %
			type = "Bandit";
		else if (typeRoll <= 90) // Goblin spawn rate = 35 %
			type = "Goblin";
		else // Golem spawn rate = 10 %
			type = "Golem";

		stats = this.genStats(lvl, type);
		newVillain = new Villain(type, lvl, 0, stats[0], stats[1], stats[2]);
		return (newVillain);
	}
}
