package swingy.view;

import swingy.model.map.Tile;
import swingy.model.map.GameMap;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class GameView {
	private String	mode;
	private String	playerName;
	private String	playerClass;

	public	GameView(String p_mode)
	{
		this.mode = p_mode;
	}


	public void	initPlayerVisual(String name, String heroClass)
	{
		this.playerName = name;
		this.playerClass = heroClass;
	}


	public void	display(GameMap map) //TODO: MAKE FCT FOR CONSOLE AND GUI
	{
		// FOR NOW CONSOLE ONLY
		int		size;
		String	line;
		Tile	tile;

		size = map.getSize();
		Print.print("");
		for (int y = 0; y < size; y++)
		{
			line = "";
			for (int x = 0; x < size; x++)
			{
				tile = map.getTile(x, y);
				if (tile.hasPlayer())
					line += "[P]";
				else if (tile.isOccupied()) //TODO: add (&& tile.isVisited())
					line += "[O]";
				else if (tile.isVisited())
					line += "[X]";
				else
					line += "[ ]";
			}
			Print.print(line);
		}
	}


	public void	displayStats(int[] stats) //TODO: update with artifact
	{
		Print.print("");
		if (this.playerName.length() < 8)
			Print.print(this.playerName + "\t\t\t(" + this.playerClass + ")");
		else if (this.playerName.length() > 15)
			Print.print(this.playerName + "\t(" + this.playerClass + ")");
		else
			Print.print(this.playerName + "\t\t(" + this.playerClass + ")");
		Print.print("level:\t\t" + stats[0] + "\t(" + stats[1] + " %)");
		Print.print("Attack:\t\t" + stats[2] + "\t(base + artifact)");
		Print.print("Defense:\t" + stats[3] + "\t(base + artifact)");
		Print.print("Hitpoints:\t" + stats[4] + "\t(base + artifact)");
	}


	public String	getInput()
	{
		if (this.mode.equals("console")) {
			Print.print("\nEnter your command: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		}
		return "";
		// String[] inputs = {};
		// return inputs;
	}


	public void	switchMode()
	{
		if (this.mode.equals("console")) {
			this.mode = "gui";
			// create gui -> open window etc...
		}
		else {
			this.mode = "console";
			// kill gui
		}
	}


	public String	getMode()
	{
		return this.mode;
	}


	public void	setMode(String p_mode)
	{
		this.mode = p_mode;
	}


	public String[]	heroSelect()
	{
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nLoad or create a Hero?\t(load / create)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();
				if (input.equals("create"))
				{
					Print.print("\nPlease enter a hero name");
					input = scanner.nextLine();
					return (new String[]{"create", input});
				}
				else if (input.equals("load"))
				{
					Print.print("\nEnter the name of the hero you want to load");
					input = scanner.nextLine();
					return (new String[]{"load", input});
				}
				else if (input.equals("quit"))
					return (new String[]{input});
				else if (input.equals("switch"))
					this.switchMode();
				else
					Print.print("\nError: Unknown command\n");
			}
		}
	}


	public String classSelect()
	{
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nChoose a Hero Class\t(Warrior / Mage / Monk)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();

				if (input.equals("switch"))
					this.switchMode();
				else
					return (input);
			}
		}
	}
}
