package swingy.controller;

import swingy.view.GameView;
import swingy.model.GameModel;
// import swingy.controller.PlayerManager;

import java.util.Arrays;

import swingy.view.Print;

public class GameController {
	private GameModel model;
	private GameView view;

	private boolean isRunning = true;

	private String state = "start";

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	public void startGame() { // fusion of startGame and gameLoop -> faire des sous fonctions (classes ?)
		Print.print("Game is starting");
		// load or create player // fct, stuck till you have a player
		// res = view.loadOrNewPlayer();
		// if (res[0] == "load") {
		// 	model.loadPlayer(res[1]);
		// }
		// else if (res[0] == "new") {
		// 	model.createPlayer(res[1]);
		// }
		//
		// create map
		// int size = (model.getPlayer().getLevel() - 1) * 5 + 10 - (model.getPlayer().getLevel() % 2);
		// model.setMap(MapGenerator.getInstance().newMap(size));
		//
		// loop
		state = "game";
		gameLoop();

		// end
		endGame();
	}

	public void gameLoop() {
		String[] inputs;
		while (isRunning) { // while player is alive or quit
			inputs = view.getInputs();
			inputHandler(Arrays.toString(inputs));
			//check player pos for encounter -> ded -> isRunning = false -> erase save
			// view.display();
		}
	}

	public void inputHandler(String[] inputs) {
		Print.print("Input handler\n" + inputs);
		//state = 'start'
			// if res[0] == 'load' -> loadPlayer(res[1])
			// if res[0] == 'new' -> createPlayer(res[1])
			// if res[0] == 'quit' -> isRunning = false
			// if res[0] == switch -> change between GUI and console
			// else -> error
		//state = 'game'
			// if res[0] == 'move' -> movePlayer(res[1])
			// if res[0] == 'fight' -> fight()
			// if res[0] == 'run' -> run()
			// if res[0] == 'quit' -> isRunning = false
			// if res[0] == 'save' -> savePlayer()
			// if res[0] == 'stats' -> displayStats()
			// if res[0] == switch -> change between GUI and console
			// else -> error
		//state = 'end'
			// nothing ?

	}

	public void endGame() {
		// verif if player

		// display end game
		// if dead -> delete save
		// if quit -> save
	}
}
