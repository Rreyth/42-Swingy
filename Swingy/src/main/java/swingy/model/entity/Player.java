package swingy.model.entity;

import swingy.model.entity.Entity;
import swingy.model.artifact.Artifact;
import javax.validation.constraints.*;

public class Player extends Entity {

	@NotNull(message = "Hero class cannot be null")
	@Pattern(regexp = "^(Warrior|Mage|Monk)$", message = "Error: Hero class must be Warrior, Mage or Monk")
	private String heroClass;

	private int toNextLevel;

	private Player(Builder builder)
	{
		super(builder.name, "Hero", builder.level, builder.experience,
				builder.attack, builder.defense, builder.hitPoints,
				builder.weapon, builder.armor, builder.helm);
		this.heroClass = builder.heroClass;
		// TODO: set toNextLevel
	}

	public String getHeroClass()
	{
		return heroClass;
	}

	public int getToNextLevel()
	{
		return toNextLevel;
	}

	public void setToNextLevel(int p_toNextLevel)
	{
		this.toNextLevel = p_toNextLevel;
	}

	public void levelUp()  // if toNextLevel - experience < 0 -> level up
	{
		this.experience -= this.toNextLevel;
		this.level++;
		this.toNextLevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
		// this.attack += 5;
		// this.defense += 5;
		// this.hitPoints += 50;
	}

	public static class Builder
	{
		private String		name;
		private String		heroClass;
		private int			level;
		private int			experience;
		private Artifact	weapon;
		private Artifact	armor;
		private Artifact	helm;
		private int			attack;
		private int			defense;
		private int			hitPoints;


		public Builder	setName(String name)
		{
			this.name = name;
			return (this);
		}

		public Builder	setHeroClass(String heroClass)
		{
			this.heroClass = heroClass;
			return (this);
		}

		public Builder	setLevel(int level)
		{
			this.level = level;
			return (this);
		}

		public Builder	setExperience(int experience)
		{
			this.experience = experience;
			return (this);
		}

		public Builder	setWeapon(Artifact weapon)
		{
			this.weapon = weapon;
			return (this);
		}

		public Builder	setArmor(Artifact armor)
		{
			this.armor = armor;
			return (this);
		}

		public Builder	setHelm(Artifact helm)
		{
			this.helm = helm;
			return (this);
		}

		public Player	build()
		{
			//TODO: calcul stats player with level
			this.attack = 10;
			this.defense = 10;
			this.hitPoints = 50;
            return (new Player(this));
        }
	}
}
