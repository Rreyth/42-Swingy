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

	public Artifact newArtifact(String heroClass, int levelDiff, int playerLevel)
	{
		Artifact loot;
		int rand;
		int stat;
		double qualityBonus;

		qualityBonus = 1 + (levelDiff * 0.1);

		rand = RandGenerator.getInstance().randInt(0, 3);

		// TODO: add names
		// TODO: name depends on class

		switch (rand)
		{
			case 0: // weapon
				stat = (int)((7 + 3 * playerLevel) * qualityBonus);
				loot = new Artifact("weapon", stat, 0, 0, levelDiff);
				break;

			case 1: // armor
				stat = (int)((6 + 4 * playerLevel) * qualityBonus);
				loot = new Artifact("armor", 0, stat, 0, levelDiff);
				break;

			default: //case 2 -> helm
				stat = (int)((15 + 5 * playerLevel) * qualityBonus);
				loot = new Artifact("helm", 0, 0, stat, levelDiff);
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
