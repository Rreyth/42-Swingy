package swingy.controller;

import swingy.view.GameView;
import swingy.model.GameModel;
import swingy.controller.PlayerManager;

import swingy.view.Print;

public class GameController {
	private GameModel model;
	private GameView view;

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	public void startGame() {
		Print.print("Game is starting");
		// load or create player
		// create map
		// villains gen
		// loop
		// end
	}

	public void gameLoop() {
		// while player is alive or quit
			// input
			// move
			// encounter
			// display
	}

	public void endGame() {
		// display end game
		// if dead -> delete save
		// if quit -> save
	}
}
