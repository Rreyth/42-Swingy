package swingy.view;

import java.util.Scanner;

public class GameView {
	private String[] inputs;
	private String mode;

	public GameView(String p_mode) {
		this.mode = p_mode;
	}

	public void displayGame() {
		System.out.println("Game is displayed");
	}

	public String getInput() {
		if (mode.equals("console")) {
			System.out.println("Enter your command: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		}
		return "";
		// String[] inputs = {};
		// return inputs;
	}


	public void switchMode() {
		if (mode == "console") {
			mode = "gui";
			// create gui -> open window etc...
		}
		else {
			mode = "console";
			// kill gui
		}
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String p_mode) {
		this.mode = p_mode;
	}
}