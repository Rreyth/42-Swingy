package swingy.controller;

import java.util.ArrayList;
import java.util.List;

import swingy.controller.generators.ArtifactGenerator;
import swingy.controller.generators.MapGenerator;
import swingy.controller.generators.RandGenerator;
import swingy.model.GameModel;
import swingy.model.artifact.Artifact;
import swingy.model.entity.Player;
import swingy.model.entity.Villain;
import swingy.model.map.GameMap;
import swingy.view.GameView;
import swingy.view.Print;

public class GameController
{
	private final GameModel		model;
	private final GameView		view;
	private final List<String>	moveList;
	private boolean				isRunning;
	private boolean				isFighting;
	private boolean 			isLooting;
	private boolean 			waitLoot;

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

		this.view.initPlayerVisual(player.getName(),
									player.getHeroClass());

		int		playerLevel;
		GameMap	map;

		playerLevel = player.getLevel();
		map = MapGenerator.getInstance().newMap(playerLevel);
		this.model.setGameMap(map);

		Print.print("\nGame starts");
		gameLoop();
	}

	private void	heroSelect()
	{
		String[] res;
		res = this.view.heroSelect();

		switch (res[0])
		{
			case "quit":
				quitGame();
				break;
			case "create":
				if (this.model.alreadyExist(res[1]))
				{
					Print.print("\nError: Name already exist");
					Print.print("Do you want to load Hero " + res[1] + "?\t(yes/no)");
					String input = this.view.getInput().toLowerCase();
					if (!input.equals("yes") || !this.model.loadPlayer(res[1]))
						this.heroSelect();
				}
				else
				{
					String heroClass = this.view.classSelect();
					if (heroClass.equals("quit"))
						quitGame();
					else if (!this.model.createPlayer(res[1], heroClass))
						this.heroSelect();
				}
				break;
			case "load":
				if (!this.model.alreadyExist(res[1]))
				{
					Print.print("\nError: Hero " + res[1] + " does not exist");
					Print.print("Do you want to create it?\t(yes/no)");
					String input = this.view.getInput().toLowerCase();
					if (!input.equals("yes"))
						this.heroSelect();
					else
					{
						String heroClass = this.view.classSelect();
						if (heroClass.equals("quit"))
							quitGame();
						else if (!this.model.createPlayer(res[1], heroClass))
							this.heroSelect();
					}
				}
				else if (!this.model.loadPlayer(res[1]))
					this.heroSelect();
				break;
			default:
				break;
		}
	}

	private void	gameLoop() //TODO: replace every print with a call to view
	{
		Villain villain;

		while (this.isRunning)
		{
			this.view.display(this.model.getGameMap());
			Print.print("\nWhere do you want to go?\t(North/South/East/West)(stats/save/switch/quit)");
			inputHandler(this.view.getInput().toLowerCase());
			if (!this.isRunning)
				break;
			villain = this.model.getGameMap().villainEncounter();
			if (villain != null)
				this.fightLoop(villain);
			if (this.model.getGameMap().isFinished() && this.isRunning)
			{
				this.view.display(this.model.getGameMap());
				Print.print("\nYou have successfully completed the current map!");
				Print.print("Get ready for the next one!");
				int playerLevel = this.model.getPlayer().getLevel();
				GameMap map = MapGenerator.getInstance().newMap(playerLevel);
				this.model.setGameMap(map);
			}
		}
	}

	private void	fightLoop(Villain villain)
	{
		Print.print("You encounter a level " + villain.getLevel() + " " + villain.getName() + ".");
		this.isFighting = true;
		while (this.isFighting && this.isRunning)
		{
			Print.print("\nWhat do you want to do?\t(Fight/Run)");
			this.inputHandler(this.view.getInput().toLowerCase());
		}
	}

	private void 	lootLoop(Artifact loot, Player player)
	{
		this.waitLoot = true;
		while (this.waitLoot)
		{
			Print.print("\nDo you want to keep or leave it?\t(Keep/Leave)");
			this.inputHandler(this.view.getInput().toLowerCase());
		}
		if (this.isLooting)
		{
			Print.print("You decided to keep it");
			player.equipLoot(loot);
			// TODO : display new stats ?
		}
		else
			Print.print("You decided to leave it");
	}

	private void	fightWin(Villain villain, Player player)
	{
		int	villainLevel = villain.getLevel();
		int playerLevel = player.getLevel();
		int	gainedXp;

		player.setHitPoints(player.getMaxHitPoints());
		Print.print("\nYou have won the battle against this level " + villainLevel + " " + villain.getName() + ".\nCongratulations!");
		this.model.getGameMap().villainDefeat();

		gainedXp = villainLevel * 750 + (villainLevel - 1) * (villainLevel - 1) * 400;
		Print.print("You have gained " + gainedXp + " experience points.");
		if (player.gainExperience(gainedXp))
			Print.print("LEVEL UP! You are now level " + player.getLevel() + ".");

		int rand = RandGenerator.getInstance().randInt(0, 5);
		int diff = (villainLevel - playerLevel);

		if (rand <= diff)
		{
			Artifact loot = ArtifactGenerator.getInstance().newArtifact(player.getHeroClass(), diff, player.getLevel());
			this.view.displayLoot(loot);
			this.lootLoop(loot, player);
		}
	}

	private void	fightHandler()
	{
		Villain	villain;
		Player	player;
		int		atk;
		int		def;
		int		hp;

		villain = this.model.getGameMap().villainEncounter();
		player = this.model.getPlayer();

		while (player.getHitPoints() > 0 && villain.getHitPoints() > 0)
		{
			// player attack
			atk = player.getFullAttack();
			def = villain.getFullDefense();
			hp = villain.getFullHitPoints();


			hp -= Math.max(1, ((atk * atk) / (atk + def)));
			villain.setHitPoints(hp);
			if (hp <= 0)
				break;

			// villain attack
			atk = villain.getFullAttack();
			def = player.getFullDefense();
			hp = player.getFullHitPoints();

			hp -= Math.max(1, ((atk * atk) / (atk + def)));
			player.setHitPoints(hp);
		}

		this.isFighting = false;

		if (villain.getHitPoints() <= 0)
			this.fightWin(villain, player);
		else if (player.getHitPoints() <= 0)
		{
			Print.print("You have lost the battle against this level " + villain.getLevel() + " " + villain.getName() + ".\nThis is the end of the game for this hero. Better luck next time.");
			this.loseQuit();
		}
	}

	private void	inputHandler(String input)
	{
		if (this.isFighting)
		{
			switch (input)
			{
				case "fight":
					Print.print("You decide to fight.");
					this.fightHandler();
					break;
				case "run":
					if (RandGenerator.getInstance().randInt(0, 2) == 0) // Run success
					{
						Print.print("Your escape from the villain was successful");
						this.isFighting = false;
						this.model.getGameMap().undoLastMove();
					}
					else // Run failure
					{
						Print.print("You failed to escape");
						this.fightHandler();
					}
					break;
				default:
					Print.print("\nError: Only fight and run are available during an encounter");
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
					Print.print("\nError: You have to chose if you keep or leave the artifact");
					break;
			}
		}
		else if (input.equals("quit"))
			quitGame();
		else if (input.equals("switch"))
			view.switchMode();
		else if (input.equals("save"))
			this.model.save();
		else if (input.equals("stats"))
			this.view.displayStats(this.model.getPlayer());
		else if (this.moveList.contains(input))
			this.model.getGameMap().movePlayer(input);
		else
			Print.print("\nError: Unknown command");
	}

	private	void	quitGame()
	{
		Print.print("\nQuitting game");
		this.model.quitGame();
		this.isRunning = false;
	}

	private void	loseQuit()
	{
		Print.print("\nDeleting hero save");
		// this.model.loseQuit(); //TODO: commented for testing
		Print.print("\nQuitting game");
		this.isRunning = false;
	}
}
