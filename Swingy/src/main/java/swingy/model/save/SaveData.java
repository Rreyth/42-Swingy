package swingy.model.save;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table( name="heroes" )
public class	SaveData
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="heroClass")
	private String heroClass;

	@Column(name="level")
	private int lvl;

	@Column(name="experience")
	private int exp;

	private String weapon;
	private String armor;
	private String helm;

	public Integer getId()
	{
		return (this.id);
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return (this.name);
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getHeroClass()
	{
		return (this.heroClass);
	}

	public void setHeroClass(String heroClass)
	{
		this.heroClass = heroClass;
	}

	public int getLvl()
	{
		return (this.lvl);
	}

	public void setLvl(int lvl)
	{
		this.lvl = lvl;
	}

	public int getExp()
	{
		return (this.exp);
	}

	public void setExp(int exp)
	{
		this.exp = exp;
	}

	public String getWeapon()
	{
		return (this.weapon);
	}

	public void setWeapon(String weapon)
	{
		this.weapon = weapon;
	}

	public String getArmor()
	{
		return (this.armor);
	}

	public void setArmor(String armor)
	{
		this.armor = armor;
	}

	public String getHelm()
	{
		return (this.helm);
	}

	public void setHelm(String helm)
	{
		this.helm = helm;
	}
}
