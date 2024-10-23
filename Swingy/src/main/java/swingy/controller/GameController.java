package swingy.controller;

import swingy.view.Print;
import swingy.view.GameView;
import swingy.model.GameModel;

import java.util.Arrays;


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

	private State		state = State.START;
	private State		subState = State.NONE;

	public GameController(GameModel model, GameView view)
	{
		this.model = model;
		this.view = view;
	}

	public void	startGame()
	{
		Print.print("Game is starting");

		heroSelect();

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
				Print.print("ALREADY EXIST");
				// TODO: ask if want to load or choose another name
			}
			else
			{
				String heroClass = this.view.classSelect();
				if (heroClass.equals("quit"))
					quitGame();
				else
					this.model.createPlayer(res[1], heroClass);
			}
			quitGame(); //TODO: rm
		}
		else if (res[0].equals("load"))
		{
			Print.print(res[0] + " name: " + res[1]);
			if (!this.model.alreadyExist(res[1]))
			{
				Print.print("Error: Name does not exist");
				this.heroSelect();
			}
			else
				this.model.loadPlayer(res[1]);
			quitGame(); //TODO: rm
		}
	}


	private void	gameLoop()
	{
		String input;
		while (this.isRunning) { // while player is alive or quit.
			// view.display();
			input = view.getInput();
			inputHandler(input);
			//check player pos for encounter -> ded -> isRunning = false -> erase save
		}
	}


	private void	inputHandler(String input)
	{
		String lower = input.toLowerCase();
		Print.print("\nInput handler\n" + lower);

		if (lower.equals("quit"))
			quitGame();
		else if (lower.equals("switch"))
			view.switchMode();
		else
		{
			if (this.state == State.START)
				startStateInput(lower);
			else if (this.state == State.GAME)
				gameStateInput(lower);
		}
		//state = 'end'
			// nothing ?

	}


	private void	startStateInput(String input) // TODO: remove ?
	{
		//state = 'start'
		// if res[0] == 'load' -> loadPlayer(res[1])
		// if res[0] == 'new' -> createPlayer(res[1])
		//sub state for each ?

		// res = view.loadOrNewPlayer();
		// if (res[0] == "load") {
		// 	model.loadPlayer(res[1]);
		// }
		// else if (res[0] == "new") {
		// 	model.createPlayer(res[1]);
		// }

		if (input.equals("load") && this.subState == State.NONE)
			this.subState = State.LOAD;
		else if (input.equals("new") && this.subState == State.NONE)
			this.subState = State.NEW;
		else
			Print.print("Error: Unknown command");
	}


	private void	gameStateInput(String input)
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
			else if (input.equals("fight"))
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
