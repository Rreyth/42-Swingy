package swingy.controller.generators;

import swingy.model.Artifact;

public class ArtifactGenerator
{
	private static ArtifactGenerator instance = null;

	private ArtifactGenerator() {}

	public static ArtifactGenerator getInstance()
	{
		if (instance == null)
		{
			instance = new ArtifactGenerator();
		}
		return instance;
	}

	private String[] genNames(String heroClass)
	{
		String[] names = {"", "", ""};

		switch (heroClass)
		{
			case "Warrior":
				names[0] = "sword";
				names[1] = "plate armor";
				names[2] = "helmet";
				break;
			case "Mage":
				names[0] = "staff";
				names[1] = "robe";
				names[2] = "hat";
				break;
			default:
				names[0] = "knuckles";
				names[1] = "tunic";
				names[2] = "headband";
				break;
		}
		return (names);
	}

	public Artifact newArtifact(String heroClass, int levelDiff, int playerLevel)
	{
		int rand;
		int stat;
		double qualityBonus;
		String[] names;
		Artifact loot;

		qualityBonus = 1 + (levelDiff * 0.1);
		rand = RandGenerator.getInstance().randInt(0, 3);
		names = this.genNames(heroClass);

		switch (rand)
		{
			case 0: // weapon
				stat = (int)((7 + 3 * playerLevel) * qualityBonus);
				loot = new Artifact("weapon", names[0], stat, 0, 0, levelDiff);
				break;

			case 1: // armor
				stat = (int)((6 + 4 * playerLevel) * qualityBonus);
				loot = new Artifact("armor", names[1], 0, stat, 0, levelDiff);
				break;

			default: //case 2 -> helm
				stat = (int)((15 + 5 * playerLevel) * qualityBonus);
				loot = new Artifact("helm", names[2], 0, 0, stat, levelDiff);
		}

		return (loot);
	}
}

// quality:
// 0 -> Weathered
// 1 -> Fragile
// 2 -> Acceptable
// 3 -> Excellent
// 4 -> Pristine
