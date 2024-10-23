package swingy.model;


public class	SaveData
{
	private String name;
    private String heroClass;
    private int lvl;
    private int exp;
    private String weapon;
    private String armor;
    private String helm;

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
