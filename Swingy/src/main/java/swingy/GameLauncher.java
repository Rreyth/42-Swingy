package swingy;

import swingy.view.GameView;
import swingy.model.GameModel;
import swingy.controller.GameController;

import swingy.view.Print;

public class GameLauncher {
	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView(args[0]);
		GameController controller = new GameController(model, view);
		controller.startGame();

	}
}
