package swingy.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import swingy.controller.generators.ArtifactGenerator;
import swingy.controller.generators.MapGenerator;
import swingy.controller.generators.RandGenerator;
import swingy.model.Artifact;
import swingy.model.GameModel;
import swingy.model.entity.Player;
import swingy.model.entity.Villain;
import swingy.model.map.GameMap;
import swingy.view.GameView;

public class GameController
{
	private final GameModel		model;
	private final GameView		view;
	private final List<String>	moveList;
	private boolean				isRunning;
	private boolean				isFighting;
	private boolean 			isLooting;
	private boolean 			waitLoot;
	private boolean				guiPrinted = false;
	private boolean				switched = false;
	private boolean 			death = false;

	public GameController(GameModel model, GameView view)
	{
		this.model = model;
		this.view = view;
		this.isRunning = true;
		this.isFighting = false;
		this.moveList = new ArrayList<>();
		this.moveList.add("north");
		this.moveList.add("south");
		this.moveList.add("east");
		this.moveList.add("west");
	}

	public void	startGame()
	{
		heroSelect();
		if (!this.isRunning)
			return;

		Player	player;
		player = this.model.getPlayer();

		int		playerLevel;
		GameMap	map;

		playerLevel = player.getLevel();
		map = MapGenerator.getInstance().newMap(playerLevel);
		this.model.setGameMap(map);

		if (this.view.getMode().equals("console"))
			this.view.displayText("\nGame starts");
		this.view.displayStats(this.model.getPlayer());

		gameLoop();
	}

	private void	heroSelect()
	{
		String[] res;
		res = this.view.heroSelect(this.model.getSavedHeroes());

		switch (res[0])
		{
			case "quit":
				quitGame();
				break;
			case "create":
				if (this.model.alreadyExist(res[1]))
				{
					this.view.displayText("\nError: Name already exist");
					this.view.displayText("Do you want to load Hero " + res[1] + "?\t(yes/no)");
					String input = this.view.getInput().toLowerCase().trim();
					if (!input.equals("yes") || !this.model.loadPlayer(res[1]))
						this.heroSelect();
				}
				else
				{
					String heroClass = this.view.classSelect();
					if (heroClass.equals("quit"))
						quitGame();
					Set<ConstraintViolation<Player>> violations;
					violations = this.model.createPlayer(res[1], heroClass);
					if (!violations.isEmpty())
					{
						for (ConstraintViolation<Player> violation : violations)
							this.view.displayText(violation.getMessage());
						this.model.erasePlayer();
						this.heroSelect();
					}
				}
				break;
			case "load":
				if (!this.model.alreadyExist(res[1]))
				{
					this.view.displayText("\nError: Hero " + res[1] + " does not exist");
					this.view.displayText("Do you want to create it?\t(yes/no)");
					String input = this.view.getInput().toLowerCase().trim();
					if (!input.equals("yes"))
						this.heroSelect();
					else
					{
						String heroClass = this.view.classSelect();
						if (heroClass.equals("quit"))
							quitGame();
						Set<ConstraintViolation<Player>> violations;
						violations = this.model.createPlayer(res[1], heroClass);
						if (!violations.isEmpty())
						{
							for (ConstraintViolation<Player> violation : violations)
								this.view.displayText(violation.getMessage());
							this.model.erasePlayer();
							this.heroSelect();
						}
					}
				}
				else if (!this.model.loadPlayer(res[1]))
					this.heroSelect();
				break;
			case "fullCreate":
				if (this.model.alreadyExist(res[1]))
				{
					this.view.displayGuiError("Error: Name already exist");
					this.heroSelect();
				}
				else
				{
					Set<ConstraintViolation<Player>> violations;
					violations = this.model.createPlayer(res[1], res[2]);
					if (!violations.isEmpty())
					{
						for (ConstraintViolation<Player> violation : violations)
							this.view.displayGuiError(violation.getMessage());
						this.model.erasePlayer();
						this.heroSelect();
					}
					else
						this.view.displayGuiGame();
				}
				break;
			default:
				this.heroSelect();
				break;
		}
	}

	private void	gameLoop()
	{
		Villain villain;

		while (this.isRunning)
		{
			this.view.displayMap(this.model.getGameMap());
			if (this.view.getMode().equals("console"))
				this.view.displayText("\nWhere do you want to go?\t(North/South/East/West)(stats/save/switch/quit)");
			inputHandler(this.view.getInput().toLowerCase().trim(), this.model.getPlayer());
			if (this.switched)
				this.switched = false;
			if (!this.isRunning)
				break;
			villain = this.model.getGameMap().villainEncounter();
			if (villain != null)
				this.fightLoop(this.model.getPlayer(), villain);
			if (this.model.getGameMap().isFinished() && this.isRunning)
			{
				this.view.displayMap(this.model.getGameMap());
				this.view.displayText("\nYou have successfully completed the current map!");
				this.view.displayText("Get ready for the next one!");
				int playerLevel = this.model.getPlayer().getLevel();
				GameMap map = MapGenerator.getInstance().newMap(playerLevel);
				this.model.setGameMap(map);
			}
		}
	}

	private void	fightLoop(Player player, Villain villain)
	{
		this.view.displayText("\nYou encounter a level " + villain.getLevel() + " " + villain.getName() + ".");
		this.isFighting = true;
		this.view.changeButtonState(true, "fight");

		while (this.isFighting && this.isRunning)
		{
			String viewMode = this.view.getMode();
			if (viewMode.equals("console") || !this.guiPrinted)
			{
				this.view.displayText("\nWhat do you want to do?\t(Fight/Run)");
				if (viewMode.equals("gui"))
					this.guiPrinted = true;
			}
			this.inputHandler(this.view.getInput().toLowerCase().trim(), player);
			if (this.switched)
			{
				this.view.displayText("\nYou encounter a level " + villain.getLevel() + " " + villain.getName() + ".");
				this.view.changeButtonState(true, "fight");
				this.guiPrinted = false;
				this.switched = false;
			}
		}
		this.guiPrinted = false;
	}

	private void 	lootLoop(Artifact loot, Player player)
	{
		this.waitLoot = true;
		this.guiPrinted = false;
		this.view.changeButtonState(true, "loot");
		while (this.waitLoot && this.isRunning)
		{
			String viewMode = this.view.getMode();
			if (viewMode.equals("console") || !this.guiPrinted)
			{
				this.view.displayText("\nDo you want to keep or leave it?\t(Keep/Leave)");
				if (viewMode.equals("gui"))
					this.guiPrinted = true;
			}
			this.inputHandler(this.view.getInput().toLowerCase().trim(), player);
			if (this.switched)
			{
				this.view.displayLoot(loot, player);
				this.view.changeButtonState(true, "loot");
				this.guiPrinted = false;
				this.switched = false;
			}
		}
		this.guiPrinted = false;
		if (!this.isRunning)
			return;
		this.view.changeButtonState(false, "loot");
		if (this.isLooting)
		{
			this.view.displayText("You decided to keep it");
			player.equipLoot(loot);
		}
		else
			this.view.displayText("You decided to leave it");
	}

	private void	fightWin(Villain villain, Player player)
	{
		int	villainLevel = villain.getLevel();
		int playerLevel = player.getLevel();
		int	gainedXp;

		player.setHitPoints(player.getMaxHitPoints());
		this.view.displayText("\nYou have won the battle against this level " + villainLevel + " " + villain.getName() + ".\nCongratulations!");
		this.model.getGameMap().villainDefeat();

		gainedXp = villainLevel * 750 + (villainLevel - 1) * (villainLevel - 1) * 400;
		this.view.displayText("You have gained " + gainedXp + " experience points.");
		if (player.gainExperience(gainedXp))
			this.view.displayText("LEVEL UP! You are now level " + player.getLevel() + ".");
		this.view.updateGuiStats(player);

		int rand = RandGenerator.getInstance().randInt(0, 5);
		int diff = Math.max((villainLevel - playerLevel), 0);

		if (rand <= diff)
		{
			Artifact loot = ArtifactGenerator.getInstance().newArtifact(player.getHeroClass(), diff, player.getLevel());
			this.view.displayLoot(loot, player);
			this.lootLoop(loot, player);
			if (!this.isRunning)
				return;
			this.view.updateGuiStats(player);
		}
	}

	private void	fightHandler()
	{
		Villain	villain;
		Player	player;

		villain = this.model.getGameMap().villainEncounter();
		player = this.model.getPlayer();

		while (player.getHitPoints() > 0 && villain.getHitPoints() > 0)
		{
			// player attack
			player.attack(villain);

			if (villain.getHitPoints() <= 0)
				break;

			// villain attack
			villain.attack(player);
		}

		this.isFighting = false;
		this.view.changeButtonState(false, "fight");

		if (villain.getHitPoints() <= 0)
			this.fightWin(villain, player);
		else if (player.getHitPoints() <= 0)
		{
			this.death = true;
			this.view.displayText("You have lost the battle against this level " + villain.getLevel() + " " + villain.getName() + ".\nThis is the end of the game for this hero. Better luck next time.");
			this.loseQuit(player, villain);
		}
	}

	private void	inputHandler(String input, Player player)
	{
		if (input.isBlank())
			return;
		if (this.death && input.equals("quit"))
		{
			this.view.killGui();
			this.isRunning = false;
		}
		else if (input.equals("quit"))
			quitGame();
		else if (input.equals("switch"))
		{
			this.switched = true;
			this.view.switchMode();
			this.view.updateGuiStats(player);
			if (this.view.getMode().equals("gui") || this.isFighting)
				this.view.displayMap(this.model.getGameMap());
		}
		else if (input.equals("save"))
			this.model.save();
		else if (this.isFighting)
		{
			switch (input)
			{
				case "fight":
					this.view.displayText("You decide to fight.");
					this.fightHandler();
					break;
				case "run":
					if (RandGenerator.getInstance().randInt(0, 2) == 0) // Run success
					{
						this.view.displayText("Your escape from the villain was successful");
						this.isFighting = false;
						this.model.getGameMap().undoLastMove();
					}
					else // Run failure
					{
						this.view.displayText("You failed to escape");
						this.fightHandler();
					}
					break;
				default:
					this.view.displayText("\nError: Only fight and run are available during an encounter");
					break;
			}
		}
		else if (this.waitLoot)
		{
			switch (input)
			{
				case "keep":
					this.waitLoot = false;
					this.isLooting = true;
					break;
				case "leave":
					this.waitLoot = false;
					this.isLooting = false;
					break;
				default:
					this.view.displayText("\nError: You have to chose if you keep or leave the artifact");
					break;
			}
		}
		else if (input.equals("stats"))
			this.view.displayStats(this.model.getPlayer());
		else if (this.moveList.contains(input))
			this.model.getGameMap().movePlayer(input);
		else
			this.view.displayText("\nError: Unknown command");
	}

	private	void	quitGame()
	{
		this.view.displayText("\nQuitting game");
		this.model.quitGame();
		this.view.killGui();
		this.isRunning = false;
	}

	private void	loseQuit(Player player, Villain villain)
	{
		this.view.displayText("\nDeleting hero save");
		this.model.loseQuit();
		this.view.displayText("\nQuitting game");
		if (this.view.getMode().equals("gui"))
			this.view.guiDeathScreen(player, villain);
		else
			this.isRunning = false;
	}
}
