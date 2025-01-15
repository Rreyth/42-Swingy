package swingy.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import swingy.model.entity.Player;
import swingy.model.map.GameMap;
import swingy.model.save.HibernateDB;
import swingy.model.save.SaveData;

public class GameModel
{
	Player			player;
	List<SaveData>	saves;
	GameMap			map;
	HibernateDB		SaveDB = new HibernateDB();

	public GameModel()
	{
		this.saves = this.SaveDB.loadFromDB();
	}

	//GETTERS
	public GameMap	getGameMap()
	{
		return (this.map);
	}

	public Player	getPlayer()
	{
		return (this.player);
	}

	public boolean	alreadyExist(String name)
	{
		return (this.saves.stream().anyMatch(obj -> obj.getName().equals(name)));
	}

	public List<Player>	getSavedHeroes()
	{
		List<Player>	savedHeroes = new ArrayList<>();
		Player			tmpHero;

		for (SaveData saved : this.saves)
		{
			tmpHero = new Player.Builder()
						.setName(saved.getName())
						.setHeroClass(saved.getHeroClass())
						.setLevel(saved.getLvl())
						.setExperience(saved.getExp())
						.setWeapon(saved.getWeapon())
						.setArmor(saved.getArmor())
						.setHelm(saved.getHelm())
						.build();

			savedHeroes.add(tmpHero);
		}

		return (savedHeroes);
	}

	//SETTERS
	public void	setGameMap(GameMap newMap)
	{
		this.map = newMap;
	}

	public void erasePlayer()
	{
		this.player = null;
	}

	public Set<ConstraintViolation<Player>>	createPlayer(String name, String heroClass)
	{
		if (!heroClass.isBlank())
			heroClass = heroClass.substring(0, 1).toUpperCase() + heroClass.substring(1);
		this.player = new Player.Builder()
						.setName(name)
						.setHeroClass(heroClass)
						.setLevel(1)
						.setExperience(0)
						.setWeapon(null)
						.setArmor(null)
						.setHelm(null)
						.build();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		return (validator.validate(this.player));
	}

	public boolean	loadPlayer(String name)
	{
		SaveData	playerData = this.saves.stream()
							.filter(obj -> obj.getName().equals(name))
							.findFirst()
							.orElse(null);

		Player tmpPlayer = new Player.Builder()
						.setName(playerData.getName())
						.setHeroClass(playerData.getHeroClass())
						.setLevel(playerData.getLvl())
						.setExperience(playerData.getExp())
						.setWeapon(playerData.getWeapon())
						.setArmor(playerData.getArmor())
						.setHelm(playerData.getHelm())
						.build();

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Player>> violations = validator.validate(tmpPlayer);

		if (!violations.isEmpty())
		{
			for (ConstraintViolation<Player> violation : violations)
				System.out.println(violation.getMessage());
			return (false);
		}
		this.player = tmpPlayer;
		return (true);
	}

	//METHODS
	public void updateSave()
	{
		for (int i = 0; i < this.saves.size(); i++)
		{
			if (this.saves.get(i).getName().equals(this.player.getName()))
			{
				this.saves.get(i).setLvl(this.player.getLevel());
				this.saves.get(i).setExp(this.player.getExperience());
				this.saves.get(i).setWeapon(this.player.getWeapon());
				this.saves.get(i).setArmor(this.player.getArmor());
				this.saves.get(i).setHelm(this.player.getHelm());
				break;
			}
		}
	}

	public void	save()
	{
		if (this.player == null)
			return;
		if (!this.alreadyExist(this.player.getName()))
		{
			SaveData	playerData = new SaveData();

			playerData.setName(this.player.getName());
			playerData.setHeroClass(this.player.getHeroClass());
			playerData.setLvl(this.player.getLevel());
			playerData.setExp(this.player.getExperience());
			playerData.setWeapon(this.player.getWeapon());
			playerData.setArmor(this.player.getArmor());
			playerData.setHelm(this.player.getHelm());

			this.saves.add(playerData);
		}
		else
		{
			this.updateSave();
		}
	}

	public void	quitGame()
	{
		this.save();
		this.SaveDB.saveToDB(this.saves);
	}

	public void	loseQuit()
	{
		SaveData	removed = new SaveData();
		for (int i = 0; i < this.saves.size(); i++)
		{
			if (this.saves.get(i).getName().equals(this.player.getName()))
			{
				removed = this.saves.remove(i);
				break;
			}
		}
		this.SaveDB.eraseFromDB(removed);
	}
}
