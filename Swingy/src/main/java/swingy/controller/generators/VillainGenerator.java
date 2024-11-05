package swingy.controller.generators;

import swingy.model.entity.Villain;
import swingy.model.entity.Bandit;

import java.util.random.RandomGenerator;


public class VillainGenerator {
	private static VillainGenerator instance = null;
	private	RandomGenerator randomGen = RandomGenerator.of("Random");

	private VillainGenerator() {}

	public static VillainGenerator getInstance()
	{
		if (instance == null)
		{
			instance = new VillainGenerator();
		}
		return instance;
	}

	public Villain	newVillain(int playerLevel) // When a map is generated, villains of varying power will be spread randomly over the map
	{
		int		lvl;
		int		atk;
		int		def;
		int		hp;
		Villain newVillain;

		lvl = this.randomGen.nextInt(playerLevel, playerLevel + 5);
		//TODO: chose Villain between multiple possibilities
		//TODO: stats computing in Villain class
		atk = 8 + ((lvl - 1) * 4);
		def = 7 + ((lvl - 1) * 3);
		hp = 80 + ((lvl - 1) * 10);
		newVillain = new Bandit("Bandit", lvl, 0, atk, def, hp);
		return (newVillain);
	}
}

// bandit stats
// Atk : 8 + (lvl * 4)
// Def : 7 + (lvl * 3)
// Hp : 80 + (lvl * 10)
// goblins are weaker
// need smth stronger too -> werewolf ?
// boss each x lvls ? -> dragon ? Minotaur ?
