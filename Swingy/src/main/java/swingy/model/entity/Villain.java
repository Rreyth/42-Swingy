package swingy.model.entity;

public class Villain extends Entity
{
	public Villain(String name, int level, int experience, int attack, int defense, int hitPoints)
	{
		super(name, "Villain", level, experience, attack, defense, hitPoints, null, null, null);
	}

	//drop method to drop artifact ? -> gen quality(mob level) -> gen type -> drop chance (mob level + quality) -> drop

}
