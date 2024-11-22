package swingy.controller.generators;

import java.util.random.RandomGenerator;

public class RandGenerator
{
	private static	RandGenerator instance = null;
	private final	RandomGenerator randomGen = RandomGenerator.of("Random");

	private RandGenerator() {}

	public static RandGenerator getInstance()
	{
		if (instance == null)
		{
			instance = new RandGenerator();
		}
		return instance;
	}

	public int randInt(int start, int end)
	{
		return (this.randomGen.nextInt(start, end));
	}
}
