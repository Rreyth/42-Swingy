package swingy.model.save;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import swingy.model.Artifact;

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

	@Embedded
	@AttributeOverrides
	({
		@AttributeOverride(name = "attack", column = @Column(name = "weapon_attack")),
		@AttributeOverride(name = "defense", column = @Column(name = "weapon_defense")),
		@AttributeOverride(name = "hitPoints", column = @Column(name = "weapon_hitPoints")),
		@AttributeOverride(name = "type", column = @Column(name = "weapon_type")),
		@AttributeOverride(name = "quality", column = @Column(name = "weapon_quality")),
		@AttributeOverride(name = "name", column = @Column(name = "weapon_name"))
	})
	private Artifact weapon;

	@Embedded
	@AttributeOverrides
	({
		@AttributeOverride(name = "attack", column = @Column(name = "armor_attack")),
		@AttributeOverride(name = "defense", column = @Column(name = "armor_defense")),
		@AttributeOverride(name = "hitPoints", column = @Column(name = "armor_hitPoints")),
		@AttributeOverride(name = "type", column = @Column(name = "armor_type")),
		@AttributeOverride(name = "quality", column = @Column(name = "armor_quality")),
		@AttributeOverride(name = "name", column = @Column(name = "armor_name"))
	})
	private Artifact armor;

	@Embedded
	@AttributeOverrides
	({
		@AttributeOverride(name = "attack", column = @Column(name = "helm_attack")),
		@AttributeOverride(name = "defense", column = @Column(name = "helm_defense")),
		@AttributeOverride(name = "hitPoints", column = @Column(name = "helm_hitPoints")),
		@AttributeOverride(name = "type", column = @Column(name = "helm_type")),
		@AttributeOverride(name = "quality", column = @Column(name = "helm_quality")),
		@AttributeOverride(name = "name", column = @Column(name = "helm_name"))
	})
	private Artifact helm;

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

	public Artifact getWeapon()
	{
		return (this.weapon);
	}

	public void setWeapon(Artifact weapon)
	{
		this.weapon = weapon;
	}

	public Artifact getArmor()
	{
		return (this.armor);
	}

	public void setArmor(Artifact armor)
	{
		this.armor = armor;
	}

	public Artifact getHelm()
	{
		return (this.helm);
	}

	public void setHelm(Artifact helm)
	{
		this.helm = helm;
	}
}
