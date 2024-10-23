package swingy.model;

import swingy.model.SaveData;
import swingy.model.entity.Player;

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
		if (this.saves.size() == 0)
			return (false);

		//TODO
		//true if name already exist
		//else false

		return (false);
	}


	public	 void	createPlayer(String name, String heroClass)
	{
		//TODO
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
