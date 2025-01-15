package swingy.model.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import swingy.model.Artifact;

public class Player extends Entity
{

	@NotNull(message = "Hero class cannot be null")
	@Pattern(regexp = "^(Warrior|Mage|Monk)$", message = "Error: Hero class must be Warrior, Mage or Monk")
	private final String heroClass;

	private int	toNextLevel;
	private int	multAtk;
	private int	multDef;
	private int	multHp;

	private Player(Builder builder)
	{
		super(builder.name, "Hero", builder.level, builder.experience,
				builder.attack, builder.defense, builder.hitPoints,
				builder.weapon, builder.armor, builder.helm);
		this.heroClass = builder.heroClass;
		this.toNextLevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
		this.initStats();
	}

	private void	initStats()
	{
		int	startAtk = this.attack;
		int	startDef = this.defense;
		int	startHp = this.hitPoints;

		this.multAtk = 3;
		this.multDef = 4;
		this.multHp = 20;
		if (this.heroClass.equals("Warrior"))
		{
			this.multAtk = 5;
			this.multDef = 3;
			this.multHp = 15;
		}
		else if (this.heroClass.equals("Mage"))
		{
			this.multAtk = 7;
			this.multDef = 2;
			this.multHp = 10;
		}

		this.attack = startAtk + ((this.level - 1) * this.multAtk);
		this.defense = startDef + ((this.level - 1) * this.multDef);
		this.hitPoints = startHp + ((this.level - 1) * this.multHp);
		this.maxHitPoints = this.hitPoints;
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

	public void levelUp()
	{
		while (this.experience >= this.toNextLevel)
		{
			this.experience -= this.toNextLevel;
			this.level++;
			this.toNextLevel = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
			this.attack += this.multAtk;
			this.defense += this.multDef;
			this.maxHitPoints += this.multHp;
			this.hitPoints = this.maxHitPoints;
		}
	}

	public boolean	gainExperience(int gainedXp)
	{
		this.experience += gainedXp;

		if (this.experience >= this.toNextLevel)
		{
			this.levelUp();
			return (true);
		}
		return (false);
	}

	public void	equipLoot(Artifact loot)
	{
		switch (loot.getType()) {
			case "weapon":
				this.setWeapon(loot);
				break;
			case "helm":
				this.setHelm(loot);
				break;
			case "armor":
				this.setArmor(loot);
				break;
			default:
				break;
		}
	}

	public static class Builder
	{
		private String	name;
		private String	heroClass;
		private int		level;
		private int		experience;
		private Artifact	weapon;
		private Artifact	armor;
		private Artifact	helm;
		private int		attack;
		private int		defense;
		private int		hitPoints;


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
			this.attack = 9;
			this.defense = 11;
			this.hitPoints = 110;
			if (this.heroClass.equals("Warrior"))
			{
				this.attack = 12;
				this.defense = 10;
				this.hitPoints = 100;
			}
			else if (this.heroClass.equals("Mage"))
			{
				this.attack = 10;
				this.defense = 8;
				this.hitPoints = 90;
			}
			return (new Player(this));
        }
	}
}
