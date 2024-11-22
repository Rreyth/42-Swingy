package swingy.model.entity;

public class Bandit extends Villain
{  //not usefull ? contains stats ? better drop rate for certain types artifact?
	// public Villain(String name, int level, int experience, int attack, int defense, int hitPoints, Artifact weapon, Artifact armor, Artifact helm)

	public Bandit(String name, int level, int experience, int attack, int defense, int hitPoints)
	{ //TODO
		// calculate stats
		super(name, level, experience, attack, defense, hitPoints);
	}
}
