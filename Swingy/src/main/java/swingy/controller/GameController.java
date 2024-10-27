package swingy.controller;

import swingy.view.Print;
import swingy.view.GameView;
import swingy.model.GameModel;
import swingy.model.map.GameMap;
import swingy.model.entity.Player;
import swingy.controller.generators.MapGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameController
{
	private GameModel		model;
	private GameView		view;
	private boolean			isRunning;
	private boolean			isFighting;
	private List<String>	moveList;

	public GameController(GameModel model, GameView view)
	{
		this.model = model;
		this.view = view;
		this.isRunning = true;
		this.isFighting = false;
		this.moveList = new ArrayList<>();
		this.moveList.add("north");
		this.moveList.add("south");
		this.moveList.add("east");
		this.moveList.add("west");
	}

	public void	startGame()
	{
		heroSelect();
		if (!this.isRunning)
			return;

		Player	player;
		player = this.model.getPlayer();

		this.view.initPlayerVisual(player.getName(),
									player.getHeroClass());

		int		playerLevel;
		GameMap	map;

		playerLevel = player.getLevel();
		map = MapGenerator.getInstance().newMap(playerLevel);
		this.model.setGameMap(map);

		Print.print("\nGame starts");
		gameLoop();
	}

	private void	heroSelect()
	{
		String[] res;
		res = this.view.heroSelect();

		if (res[0].equals("quit"))
			quitGame();
		else if (res[0].equals("create"))
		{
			if (this.model.alreadyExist(res[1]))
			{
				Print.print("\nError: Name already exist");
				Print.print("Do you want to load Hero " + res[1] + "?\t(yes/no)");
				String input = this.view.getInput().toLowerCase();
				if (!input.equals("yes") || !this.model.loadPlayer(res[1]))
					this.heroSelect();
			}
			else
			{
				String heroClass = this.view.classSelect();
				if (heroClass.equals("quit"))
					quitGame();
				else if (!this.model.createPlayer(res[1], heroClass))
					this.heroSelect();
			}
		}
		else if (res[0].equals("load"))
		{
			if (!this.model.alreadyExist(res[1]))
			{
				Print.print("\nError: Hero " + res[1] + " does not exist");
				Print.print("Do you want to create it?\t(yes/no)");
				String input = this.view.getInput().toLowerCase();
				if (!input.equals("yes"))
					this.heroSelect();
				else
				{
					String heroClass = this.view.classSelect();
					if (heroClass.equals("quit"))
						quitGame();
					else if (!this.model.createPlayer(res[1], heroClass))
						this.heroSelect();
				}
			}
			else if (!this.model.loadPlayer(res[1]))
				this.heroSelect();
		}
	}


	private void	gameLoop()
	{
		while (this.isRunning) { // while player is alive or quit.
			this.view.display(this.model.getGameMap());
			Print.print("\nWhere do you want to go?\t(North/South/East/West)(stats/save/switch/quit)");
			inputHandler(this.view.getInput().toLowerCase());
			//TODO: check player pos for encounter -> ded -> isRunning = false -> erase save

			if (this.model.getGameMap().isFinished())
			{
				this.view.display(this.model.getGameMap());
				Print.print("\nYou have successfully completed the current map!");
				Print.print("Get ready for the next one!");
				int playerLevel = this.model.getPlayer().getLevel();
				GameMap map = MapGenerator.getInstance().newMap(playerLevel);
				this.model.setGameMap(map);
			}
		}
	}


	private void	inputHandler(String input)
	{
		Print.print("\nInput handler = " + input); //TODO: RM

		if (input.equals("quit"))
			quitGame();
		else if (input.equals("switch"))
			view.switchMode();
		else if (input.equals("save"))
			this.model.save();
		else if (input.equals("stats"))
			this.view.displayStats(this.model.getPlayerStats());
		else if (this.moveList.contains(input))
			this.model.getGameMap().movePlayer(input);
		else if (isFighting) // TODO
		{
			if (input.equals("fight"))
				Print.print("fight"); //TODO
			else if (input.equals("run"))
				Print.print("run"); //TODO : 50% chance
		}
		else
		{
			Print.print("\nError: Unknown command");
		}
	}


	private	void	quitGame()
	{
		Print.print("\nQuitting game");
		this.model.quitGame();
		this.isRunning = false;
	}
}
