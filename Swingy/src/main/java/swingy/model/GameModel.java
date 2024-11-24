package swingy.model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import swingy.model.entity.Player;
import swingy.model.map.GameMap;

public class GameModel
{
	Player					player;
	Map<String, SaveData>	saves;
	GameMap					map;


	public GameModel()
	{
		this.saves = new TreeMap<>();
		String	path = "Swingy/src/main/java/swingy/model/save/saveFile";

		Gson	gson = new GsonBuilder().create();

		try (FileReader reader = new FileReader(path))
		{
			Type	savesType = new TypeToken<Map<String, SaveData>>() {}.getType();

			this.saves = gson.fromJson(reader, savesType);
		}
		catch (IOException e)
		{
		}
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
		return (this.saves.containsKey(name));
	}

	public List<Player>	getSavedHeroes()
	{
		List<Player>	savedHeroes = new ArrayList<>();
		Player			tmpHero;

		for (SaveData saved : this.saves.values()) {
			tmpHero = new Player.Builder() //TODO : add artifacts
						.setName(saved.getName())
						.setHeroClass(saved.getHeroClass())
						.setLevel(saved.getLvl())
						.setExperience(saved.getExp())
						.setWeapon(null)
						.setArmor(null)
						.setHelm(null)
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

	public boolean	createPlayer(String name, String heroClass)
	{
		heroClass = heroClass.substring(0, 1).toUpperCase() + heroClass.substring(1);
		Player tmpPlayer = new Player.Builder()
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

	public boolean	loadPlayer(String name)
	{
		SaveData	playerData = this.saves.get(name);

		Player tmpPlayer = new Player.Builder() //TODO : add artifacts
						.setName(playerData.getName())
						.setHeroClass(playerData.getHeroClass())
						.setLevel(playerData.getLvl())
						.setExperience(playerData.getExp())
						.setWeapon(null)
						.setArmor(null)
						.setHelm(null)
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
	public void	save()
	{
		SaveData	playerData = new SaveData();

		playerData.setName(this.player.getName());
		playerData.setHeroClass(this.player.getHeroClass());
		playerData.setLvl(this.player.getLevel());
		playerData.setExp(this.player.getExperience());
		playerData.setWeapon("none");
		playerData.setArmor("none");
		playerData.setHelm("none");
		//TODO : add artifacts saving
		// playerData.setWeapon(this.player.getWeapon());
		// playerData.setArmor(this.player.getArmor());
		// playerData.setHelm(this.player.getHelm());

		this.saves.put(this.player.getName(), playerData);
	}

	private void	saveToFile()
	{
		String	path = "Swingy/src/main/java/swingy/model/save/saveFile";
		Gson	gson = new GsonBuilder().setPrettyPrinting().create();

		File saveFile = new File(path);
		saveFile.delete();
		try
		{
			saveFile.createNewFile();
		}
		catch (IOException e)
		{
			System.err.println("Error while saving : " + e.getMessage());
			return ;
		}

		try (FileWriter writer = new FileWriter(path))
		{
			gson.toJson(this.saves, writer);
		}
		catch (IOException e)
		{
			System.err.println("Error while saving : " + e.getMessage());
		}
	}

	public void	quitGame()
	{
		this.save();
		this.saveToFile();
	}

	public void	loseQuit()
	{
		this.saves.remove(player.getName());
		this.saveToFile();
	}
}
