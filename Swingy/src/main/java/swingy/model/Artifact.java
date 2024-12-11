package swingy.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Artifact
{
	private int attack;
	private int defense;
	private int hitPoints;

	private String type;
	private String name;
	private int quality;

	public Artifact()
	{
	}

	//TODO: add name
	public Artifact(String p_type, String p_name, int p_attack, int p_defense, int p_hitPoints, int quality)
	{
		this.type = p_type;
		this.name = p_name;
		this.attack = p_attack;
		this.defense = p_defense;
		this.hitPoints = p_hitPoints;
		this.quality = quality;
	}

	//GETTERS
	public String getType()
	{
		return (this.type);
	}

	public String getName()
	{
		return (this.name);
	}

	public int getAttack()
	{
		return (this.attack);
	}

	public int getDefense()
	{
		return (this.defense);
	}

	public int getHitPoints()
	{
		return (this.hitPoints);
	}

	public int	getQuality()
	{
		return (this.quality);
	}

	//SETTERS
	public void setType(String p_type)
	{
		this.type = p_type;
	}

	public void setName(String p_name)
	{
		this.name = p_name;
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

	public void	setQuality(int p_quality)
	{
		this.quality = p_quality;
	}
}
