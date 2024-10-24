package swingy.model;

import swingy.model.SaveData;
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
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class GameModel
{
	// 1 player, 1 map, 1 array of mobs ?
	Map<String, SaveData>	saves = new TreeMap<>();

	public GameModel()
	{
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


	public boolean	alreadyExist(String name)
	{
		return (this.saves.containsKey(name));
	}


	public	 void	createPlayer(String name, String heroClass) //TODO
	{
		System.out.println("create name: " + name + " - class: " + heroClass);
		Player player = new Player.Builder()
						.setName(name)
						.setHeroClass(heroClass)
						.setLevel(1)
						.setExperience(0)
						.setWeapon(null)
						.setArmor(null)
						.setHelm(null)
						.build();

		System.out.println("created");
		System.out.println("validator test --------");


		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Player>> violations = validator.validate(player);

		if (!violations.isEmpty())
		{
			for (ConstraintViolation<Player> violation : violations)
			{
		        System.out.println(violation.getMessage());
		    }
		}
		// else
		// {
		// 		all good
		// }
		System.out.println("validator tested --------");

	}


	public	 void	loadPlayer(String name)
	{
		//TODO
	}


	private void saveToFile()
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
