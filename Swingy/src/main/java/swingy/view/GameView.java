package swingy.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class GameView {
	private String	mode;

	public	GameView(String p_mode)
	{
		this.mode = p_mode;
	}


	public void	displayGame()
	{
		System.out.println("Game is displayed");
	}


	public String	getInput()
	{
		if (this.mode.equals("console")) {
			System.out.println("\nEnter your command: ");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			return input;
		}
		return "";
		// String[] inputs = {};
		// return inputs;
	}


	public void	switchMode()
	{
		if (this.mode.equals("console")) {
			this.mode = "gui";
			// create gui -> open window etc...
		}
		else {
			this.mode = "console";
			// kill gui
		}
	}


	public String	getMode()
	{
		return this.mode;
	}


	public void	setMode(String p_mode)
	{
		this.mode = p_mode;
	}


	public String[]	heroSelect()
	{
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nLoad or create a Hero?\t(load / create)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();
				if (input.equals("create"))
				{
					Print.print("\nPlease enter a hero name");
					input = scanner.nextLine();
					return (new String[]{"create", input});
				}
				else if (input.equals("load"))
				{
					Print.print("\nEnter the name of the hero you want to load");
					input = scanner.nextLine();
					return (new String[]{"load", input});
				}
				else if (input.equals("quit"))
					return (new String[]{input});
				else if (input.equals("switch"))
					this.switchMode();
				else
					Print.print("\nError: Unknown command\n");
			}
		}
	}


	public String classSelect()
	{
		while (true)
		{
			if (mode.equals("console"))
			{
				Print.print("\nChoose a Hero Class\t(Warrior / Mage / Monk)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();

				if (input.equals("switch"))
					this.switchMode();
				else
					return (input);
			}
		}
	}
}
