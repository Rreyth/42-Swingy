package Swingy.Model.entity;

import Swingy.Model.entity.Entity;
import Swingy.Model.artifact.Artifact;

public class Villain extends Entity {
	public Villain(String name, int level, int experience, int attack, int defense, int hitPoints, Artifact weapon, Artifact armor, Artifact helm) {
		super(name, "Villain", level, experience, attack, defense, hitPoints, weapon, armor, helm);
	}

	//drop method to drop artifact ? -> gen quality(mob level) -> gen type -> drop chance (mob level + quality) -> drop

}