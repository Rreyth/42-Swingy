package swingy.model.artifact;

public class Weapon extends Artifact
{
	public Weapon(String type, int attack, int quality)
	{
		super(type, attack, 0, 0, quality);
	}
}
