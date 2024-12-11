package swingy.model.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import swingy.model.Artifact;
import swingy.model.validator.ValidName;


public class Entity
{
	@ValidName
	protected String name;

	@NotNull(message = "Type cannot be null")
	@Pattern(regexp = "^(Hero|Villain)$", message = "Type must be Hero or Villain")
	protected String type;

	@Min(value = 1, message = "Level cannot be less than 1")
	protected int level;

	@Min(value = 0, message = "Experience cannot be less than 0")
	protected int experience;

	@Min(value = 1, message = "Attack cannot be less than 1")
	protected int attack;

	@Min(value = 1, message = "Defense cannot be less than 1")
	protected int defense;

	@Min(value = 1, message = "Hit Points cannot be less than 1")
	protected int hitPoints;
	protected int maxHitPoints;

	protected Artifact weapon;
	protected Artifact armor;
	protected Artifact helm;

	public Entity(String p_name, String p_type, int p_level, int p_experience, int p_attack, int p_defense, int p_hitPoints, Artifact p_weapon, Artifact p_armor, Artifact p_helm)
	{
		this.name = p_name;
		this.type = p_type;
		this.level = p_level;
		this.experience = p_experience;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hitPoints = p_hitPoints;
		this.maxHitPoints = p_hitPoints;
		this.weapon = p_weapon;
		this.armor = p_armor;
		this.helm = p_helm;
	}

	//GETTERS
	public String getName()
	{
		return (this.name);
	}

	public String getType()
	{
		return (this.type);
	}

	public int getLevel()
	{
		return (this.level);
	}

	public int getExperience()
	{
		return (this.experience);
	}

	public int getAttack()
	{
		return (this.attack);
	}

	public int getFullAttack()
	{
		if (this.weapon == null)
			return (this.attack);
		return (this.attack + this.weapon.getAttack());
	}

	public int getDefense()
	{
		return (this.defense);
	}

	public int getFullDefense()
	{
		if (this.armor == null)
			return (this.defense);
		return (this.defense + this.armor.getDefense());
	}

	public int getHitPoints()
	{
		return (this.hitPoints);
	}

	public int getFullHitPoints()
	{
		if (this.helm == null)
			return (this.hitPoints);
		return (this.hitPoints + this.helm.getHitPoints());
	}

	public int getMaxHitPoints()
	{
		return (this.maxHitPoints);
	}

	public Artifact getWeapon()
	{
		return (this.weapon);
	}

	public Artifact getArmor()
	{
		return (this.armor);
	}

	public Artifact getHelm()
	{
		return (this.helm);
	}

	public Artifact getArtifactByType(String type)
	{
		switch (type)
		{
			case "weapon":
				return (this.weapon);
			case "armor":
				return (this.armor);
			case "helm":
				return (this.helm);
			default:
				return (null);
		}
	}

	//SETTERS
	public void setName(String p_name)
	{
		this.name = p_name;
	}

	public void setType(String p_type)
	{
		this.type = p_type;
	}

	public void setLevel(int p_level)
	{
		this.level = p_level;
	}

	public void setExperience(int p_experience)
	{
		this.experience = p_experience;
	}

	public void setAttack(int p_attack)
	{
		this.attack = p_attack;
	}

	public void setDefense(int p_defense)
	{
		this.defense = p_defense;
	}

	public void setHitPoints(int p_hitPoints)
	{
		this.hitPoints = p_hitPoints;
	}

	public void setWeapon(Artifact p_weapon)
	{
		this.weapon = p_weapon;
	}

	public void setArmor(Artifact p_armor)
	{
		this.armor = p_armor;
	}

	public void setHelm(Artifact p_helm)
	{
		this.helm = p_helm;
	}
}
