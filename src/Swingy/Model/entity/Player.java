package Swingy.Model.entity;

import Swingy.Model.entity.Entity;
import Swingy.Model.artifact.Artifact;
import javax.validation.constraints.*;

public class Player extends Entity { //add builder pattern

	@NotNull(message = "Hero class cannot be null")
	@Pattern(regexp = "^(Warrior|Mage|Monk)$", message = "Hero class must be Warrior, Mage or Monk")
	private String heroClass; //replace with class ?

	private int toNextLevel;

	public Player(String name, String heroClass, int level, int experience, int attack, int defense, int hitPoints, Artifact weapon, Artifact armor, Artifact helm) {
		super(name, "Hero", level, experience, attack, defense, hitPoints, weapon, armor, helm);
		this.heroClass = heroClass;
	}

	public String getHeroClass() {
		return heroClass;
	}

	public int getToNextLevel() {
		return toNextLevel;
	}

	public void setToNextLevel(int p_toNextLevel) {
		this.toNextLevel = p_toNextLevel;
	}

	public void levelUp() { // if toNextLevel - experience < 0 -> level up
		this.experience -= this.toNextLevel;
		this.level++;
		this.toNextLevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
		// this.attack += 5;
		// this.defense += 5;
		// this.hitPoints += 50;
	}

	// public static class PlayerBuilder {
	// }
}