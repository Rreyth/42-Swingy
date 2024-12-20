package swingy.view;

import swingy.model.Artifact;
import swingy.model.entity.Player;
import swingy.model.map.GameMap;
import swingy.model.map.Tile;

public class ConsoleView
{
	public ConsoleView() {}

	public void	displayMap(GameMap map)
	{
		int		size;
		String	line;
		Tile	tile;

		size = map.getSize();
		Print.print("");
		for (int y = 0; y < size; y++)
		{
			line = "";
			for (int x = 0; x < size; x++)
			{
				tile = map.getTile(x, y);
				if (tile.hasPlayer())
					line += "[P]";
				else if (tile.isOccupied() && tile.isVisited())
					line += "[O]";
				else if (tile.isVisited())
					line += "[X]";
				else
					line += "[ ]";
			}
			Print.print(line);
		}
	}

	public void displayText(Object text)
	{
		Print.print(text);
	}

	public void	displayStats(Player player)
	{
		Print.print("");
		String name = player.getName();
		if (name.length() < 8)
			Print.print(name + "\t\t\t(" + player.getHeroClass() + ")");
		else if (name.length() > 15)
			Print.print(name + "\t(" + player.getHeroClass() + ")");
		else
			Print.print(name + "\t\t(" + player.getHeroClass() + ")");

		int	lvl = player.getLevel();

		int toNextLvl = player.getToNextLevel();
		int exp = player.getExperience();
		int	lvlPercentage = (int)(((double) exp / toNextLvl) * 100);
		Print.print("level:\t\t" + lvl + "\t(" + lvlPercentage + " %)");

		int	stat = player.getFullAttack();
		int baseStat = player.getAttack();
		int artifactStat = stat - baseStat;
		Print.print("Attack:\t\t" + stat + "\t(" + baseStat + " + " + artifactStat + ")");

		stat = player.getFullDefense();
		baseStat = player.getDefense();
		artifactStat = stat - baseStat;
		Print.print("Defense:\t" + stat + "\t(" + baseStat + " + " + artifactStat + ")");

		stat = player.getFullHitPoints();
		baseStat = player.getHitPoints();
		artifactStat = stat - baseStat;
		Print.print("Hitpoints:\t" + stat + "\t(" + baseStat + " + " + artifactStat + ")");
	}

	public void	displayLoot(Artifact loot, Player player)
	{
		Artifact oldArtifact = player.getArtifactByType(loot.getType());
		String[] quality = {"weathered", "fragile", "acceptable", "excellent", "pristine"};
		int diffAtk = loot.getAttack();
		int diffDef = loot.getDefense();
		int diffHp = loot.getHitPoints();

		if (oldArtifact != null)
		{
			diffAtk -= oldArtifact.getAttack();
			diffDef -= oldArtifact.getDefense();
			diffHp -= oldArtifact.getHitPoints();
		}

		Print.print("\nYou dropped a " + quality[loot.getQuality()] + " " + loot.getName()+ "!");
		Print.print("Stats:");
		Print.print("Attack:\t\t" + loot.getAttack() + "\t(" + ((diffAtk > 0) ? "+" : "") + diffAtk + ")");
		Print.print("Defense:\t" + loot.getDefense() + "\t(" + ((diffDef > 0) ? "+" : "") + diffDef + ")");
		Print.print("Hit points:\t" + loot.getHitPoints() + "\t(" + ((diffHp > 0) ? "+" : "") + diffHp + ")");
	}
}
