package swingy.view;

import java.util.Scanner;

import swingy.model.artifact.Artifact;
import swingy.model.map.GameMap;
import swingy.model.map.Tile;

public class GameView
{
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

	public void	displayStats(int[] stats) //TODO: update with artifact // TODO: reiceive Player ?
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

	public void	displayLoot(Artifact loot)
	{
		String[] quality = {"weathered", "fragile", "acceptable", "excellent", "pristine"};
		Print.print("\nYou dropped a " + quality[loot.getQuality()] + " " + loot.getType()+ "!");
		Print.print("Stats:");
		Print.print("Attack:\t\t" + loot.getAttack());
		Print.print("Defense:\t" + loot.getDefense());
		Print.print("Hit points:\t" + loot.getHitPoints());
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
				switch (input)
				{
					case "create":
						Print.print("\nPlease enter a hero name");
						input = scanner.nextLine();
						return (new String[]{"create", input});
					case "load":
						Print.print("\nEnter the name of the hero you want to load");
						input = scanner.nextLine();
						return (new String[]{"load", input});
					case "quit":
						return (new String[]{input});
					case "switch":
						this.switchMode();
						break;
					default:
						Print.print("\nError: Unknown command\n");
						break;
				}
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
