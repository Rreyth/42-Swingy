package swingy.controller;

import swingy.view.Print;
import swingy.view.GameView;
import swingy.model.GameModel;

import java.util.Arrays;

//TODO: RM
enum State
{
	NONE,
	START,
	NEW,
	LOAD,
	GAME,
	FIGHT;
}


public class GameController
{
	private GameModel	model;
	private GameView	view;

	private boolean		isRunning = true;

	private State		state = State.GAME; //TODO: RM
	private State		subState = State.NONE;

	public GameController(GameModel model, GameView view)
	{
		this.model = model;
		this.view = view;
	}

	public void	startGame()
	{
		heroSelect();
		Print.print("\nGame starts");

		// create map
		// int size = (model.getPlayer().getLevel() - 1) * 5 + 10 - (model.getPlayer().getLevel() % 2);
		// model.setMap(MapGenerator.getInstance().newMap(size));
		//

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
				{
					quitGame();
					return;
				}
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
					{
						quitGame();
						return;
					}
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
		String input;
		while (this.isRunning) { // while player is alive or quit.
			// view.display();
			input = this.view.getInput();
			inputHandler(input);
			//check player pos for encounter -> ded -> isRunning = false -> erase save
		}
	}


	private void	inputHandler(String input)
	{
		String lower = input.toLowerCase();
		Print.print("\nInput handler\n" + lower); //TODO: RM

		if (lower.equals("quit"))
			quitGame();
		else if (lower.equals("switch"))
			view.switchMode();
		else
		{
			gameStateInput(lower);
		}
		//state = 'end'
			// nothing ?

	}

	private void	gameStateInput(String input) // TODO: RM ?
	{
		//state = 'game'
		// if res[0] == 'move' -> movePlayer(res[1])
		// if res[0] == 'save' -> savePlayer()
		// if res[0] == 'stats' -> displayStats()
		// if res[0] == switch -> change between GUI and console
		// else -> error

		if (this.subState == State.FIGHT) {
			if (input.equals("fight"))
				Print.print("fight"); //TODO
			else if (input.equals("run"))
				Print.print("run"); //TODO : 50% chance ?
			else
				Print.print("Error: Unknown command");
			return;
		}

		switch (input) {
			case "save":
				break;
			case "stats":
				break;
			case "north":
				break;
			case "south":
				break;
			case "east":
				break;
			case "west":
				break;
			default:
				Print.print("Error: Unknown command");
				break;
		}
	}


	private	void	quitGame()
	{
		//save and quit
		Print.print("Quitting game");
		this.isRunning = false;
	}
}
