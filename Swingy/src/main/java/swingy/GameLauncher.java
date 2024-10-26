package swingy;

import swingy.view.GameView;
import swingy.model.GameModel;
import swingy.controller.GameController;

import swingy.view.Print;

public class GameLauncher {
	public static void main(String[] args) {
		if (args.length == 0 || (!args[0].equals("console") && !args[0].equals("gui")))
		{
			Print.printErr("Error: wrong argument");
			Print.printErr("Usage: java -jar Swingy/target/Swingy-1-jar-with-dependencies.jar <gui/console>");
			return;
		}
		GameModel model = new GameModel();
		GameView view = new GameView(args[0]);
		GameController controller = new GameController(model, view);
		controller.startGame();
	}
}
