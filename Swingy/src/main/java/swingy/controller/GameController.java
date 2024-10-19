package swingy.controller;

import swingy.view.GameView;
import swingy.model.GameModel;
// import swingy.controller.PlayerManager;

import java.util.Arrays;

import swingy.view.Print;

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

	public void	startGame()  // fusion of startGame and gameLoop -> faire des sous fonctions (classes ?)
	{
		Print.print("Game is starting");
		// create map
		// int size = (model.getPlayer().getLevel() - 1) * 5 + 10 - (model.getPlayer().getLevel() % 2);
		// model.setMap(MapGenerator.getInstance().newMap(size));
		//
		// loop
		heroSelect();
		gameLoop();

		// end
		endGame();
	}

	private void	heroSelect()
	{
		this.view.heroSelect();
		
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
	}


	private void	gameLoop()
	{
		String input;
		while (this.isRunning) { // while player is alive or quit.
			input = view.getInput();
			inputHandler(input);
			//check player pos for encounter -> ded -> isRunning = false -> erase save
			// view.display();
			//display only if input ??
		}
	}


	private void	inputHandler(String input)
	{
		String lower = input.toLowerCase();
		Print.print("\nInput handler\n" + lower);

		if (lower.equals("quit")) {
			//save and quit
			Print.print("Quitting game");
			this.isRunning = false;
		}
		else if (lower.equals("switch"))
			view.switchMode();
		else {
			if (this.state == State.START)
				startStateInput(lower);
			else if (this.state == State.GAME)
				gameStateInput(lower);
		}
		//state = 'end'
			// nothing ?

	}


	private void	startStateInput(String input)
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
			Print.print("Unknown command");
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
				Print.print("Unknown command");
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
				Print.print("Unknown command");
				break;
		}
	}


	private void	endGame()
	{
		// verif if player

		// display end game
		// if dead -> delete save or go back and lose 1 lvl
		// if quit -> save
	}
}
