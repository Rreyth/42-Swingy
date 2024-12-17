package swingy.view;

import java.util.List;
import java.util.Scanner;

import swingy.model.Artifact;
import swingy.model.entity.Player;
import swingy.model.map.GameMap;

public class GameView
{
	private String	mode;
	private final ConsoleView consoleView = new ConsoleView();
	private final GuiView guiView = new GuiView();

	public	GameView(String p_mode)
	{
		this.mode = p_mode;

		if (this.mode.equals("gui"))
			this.guiView.createWindow();
	}

	public void	displayMap(GameMap map)
	{
		if (this.mode.equals("console"))
			this.consoleView.displayMap(map);
		else
			this.guiView.displayMap(map);
	}

	public void	displayText(Object text)
	{
		if (this.mode.equals("console"))
			this.consoleView.displayText(text);
		else
			this.guiView.displayText(text);
	}

	public void displayGuiError(String text)
	{
		this.guiView.displayError(text);
	}

	public void displayGuiGame()
	{
		this.guiView.displayGame();
	}

	public void	displayStats(Player player)
	{
		if (this.mode.equals("console"))
			this.consoleView.displayStats(player);
		else
			this.guiView.displayStats(player);
	}

	public void	displayLoot(Artifact loot, Player player)
	{
		if (this.mode.equals("console"))
			this.consoleView.displayLoot(loot, player);
		else
			this.guiView.displayLoot(loot, player);
	}

	public String	getInput()
	{
		if (this.mode.equals("console"))
		{
			Print.print("\nEnter your command: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return (input);
		}
		else
			return (this.guiView.getInput());
	}

	public void	switchMode()
	{
		if (this.mode.equals("console"))
		{
			this.mode = "gui";
			this.guiView.createWindow();
		}
		else
		{
			this.mode = "console";
			this.guiView.killWindow();
		}
	}

	public String	getMode()
	{
		return (this.mode);
	}

	public void	setMode(String p_mode)
	{
		this.mode = p_mode;
	}

	public String[]	heroSelect(List<Player> savedHeroes)
	{
		//TODO: separate gui vers
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nLoad or create a Hero?\t(load / create)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase().trim();
				switch (input)
				{
					case "create":
						Print.print("\nPlease enter a hero name");
						input = scanner.nextLine();
						return (new String[]{"create", input});
					case "load":
						Print.print("\nLoadable Heroes:");
						if (savedHeroes.isEmpty())
						{
							Print.print("No hero saved.");
							break;
						}
						for (Player hero : savedHeroes)
							displayStats(hero);
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
			else
			{
				String[] res = this.guiView.heroSelect(savedHeroes);

				if (res[0].equals("switch"))
					this.switchMode();
				else if (!res[0].isBlank())
					return (res);
			}
		}
	}

	public String classSelect()
	{
		//TODO: separate gui vers
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nChoose a Hero Class\t(Warrior / Mage / Monk)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase().trim();

				if (input.equals("switch"))
					this.switchMode();
				else
					return (input);
			}
		}
	}
}
