package swingy;

import swingy.view.GameView;
import swingy.model.GameModel;
import swingy.controller.GameController;

public class GameLauncher {
	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameView view = new GameView();
		GameController controller = new GameController(model, view);
		controller.startGame();

	}
}
