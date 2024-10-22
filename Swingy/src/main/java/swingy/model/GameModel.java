package swingy.model;

import swingy.model.entity.Player;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


public class GameModel
{
	// 1 player, 1 map, 1 array de saved players, 1 array of mobs ?
	List<Player> savedPlayers = new ArrayList<>(); //TODO : replace with gamesave class and use gson to get saves

	public GameModel()
	{
		String path = "Swingy/src/main/java/swingy/model/save/saveFile";
		
		try
		{
			List<String> save = Files.readAllLines(Paths.get(path));
			this.saveParser(save);
		}
		catch (IOException error)
		{
			savedPlayers.clear();
		}
	}

	
	private	void	saveParser(List<String> save)
	{
		for (String line : save)
		{
			System.out.println(line);
		}
	}


	public boolean	alreadyExist(String name)
	{
		if (savedPlayers.size() == 0)
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


	private void saveToFile() //TODO : use gson
	{
		String path = "Swingy/src/main/java/swingy/model/save/saveFile";

		File saveFile = new File(path);
		saveFile.delete();
		try
		{
			saveFile.createNewFile();
		}
		catch (IOException e)
		{
			System.err.println("Error while saving : " + e.getMessage());
		}

		String content = "{\n\t\"save3\" : {\n\t\t\"name\" : \"YEET\"\n\t}\n}\n"; //TODO : save formater

 		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path)))
		{
            writer.write(content);
        }
		catch (IOException e)
		{
            System.err.println("Error while saving : " + e.getMessage());
        }
	}
}

