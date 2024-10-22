package swingy.view;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class GameView {
	private String[] inputs;
	private String mode;

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
			System.out.println("Enter your command: ");
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
		boolean	selected = false;
		String[] res = {"quit"};
		//System.out.println("Enter your command: ");
		while (!selected)
		{
			if (mode.equals("console"))
			{
				Print.print("Load or create a Hero?\t(load / create)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();
				if (input.equals("create"))
				{
					Print.print("Please enter a Hero name");
					input = scanner.nextLine();
					res = new String[]{"create", input};
					selected = true;
				}
				else if (input.equals("load"))
				{

				}
				else if (input.equals("quit"))
					return (new String[]{input});
				else if (input.equals("switch"))
					this.switchMode();
				else
					Print.print("Error: Unknown command\n");
			}
		}
		return (res);
	}


	public String classSelect()
	{
		boolean	selected = false;
		String	heroClass = "";
		List<String> classList = new ArrayList<>();

		classList.add("warrior");
		classList.add("mage");
		classList.add("monk");
		classList.add("quit");
		while (!selected)
		{
			if (mode.equals("console"))
			{
				Print.print("Choose a Hero Class\t(Warrior / Mage / Monk)");
				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine().toLowerCase();

				if (classList.contains(input))
				{
					heroClass = input;
					selected = true;
				}
				else if (input.equals("switch"))
					this.switchMode();
				else
					Print.print("Error: Unknown command\n");
			}
		}
		return (heroClass);
	}
}
