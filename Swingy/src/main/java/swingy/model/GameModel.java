package swingy.model;

import swingy.model.SaveData;
import swingy.model.map.GameMap;
import swingy.model.entity.Player;

// File and gson imports used for save
import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

// Validator imports
import java.util.Set;
import javax.validation.Validator;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;

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

	//SETTERS
	public void	setGameMap(GameMap newMap)
	{
		this.map = newMap;
	}

	public boolean	createPlayer(String name, String heroClass)
	{
		heroClass = heroClass.substring(0, 1).toUpperCase() + heroClass.substring(1);
		Player player = new Player.Builder()
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

		Set<ConstraintViolation<Player>> violations = validator.validate(player);

		if (!violations.isEmpty())
		{
			for (ConstraintViolation<Player> violation : violations)
				System.out.println(violation.getMessage());
			return (false);
		}
		this.player = player;
		return (true);
	}

	public boolean	loadPlayer(String name)
	{
		SaveData	playerData = this.saves.get(name);

		Player player = new Player.Builder() //TODO : add artifacts
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

		Set<ConstraintViolation<Player>> violations = validator.validate(player);

		if (!violations.isEmpty())
		{
			for (ConstraintViolation<Player> violation : violations)
				System.out.println(violation.getMessage());
			return (false);
		}
		this.player = player;
		return (true);
	}

	//METHODS
	public int[]	getPlayerStats() //TODO: add artifact stats
	{
		int	lvl = this.player.getLevel();

		int toNextLvl = this.player.getToNextLevel();
		int exp = this.player.getExperience();
		int	lvlPercentage = (int)(((double) exp / toNextLvl) * 100);

		int	atk = this.player.getAttack();
		int	def = this.player.getDefense();
		int	hp = this.player.getHitPoints();

		return (new int[]{lvl, lvlPercentage, atk, def, hp});
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
}
