package swingy.model.map;

import swingy.model.map.Tile;
import swingy.model.entity.Villain;

public class GameMap
{
	private Tile[][]	map;
	private int			size;
	private int			playerX;
	private int			playerY;
	private boolean		isFinished;
	private	String		lastMove;

	public GameMap(int size)
	{
		this.size = size;
		this.map = new Tile[size][size];
		this.playerX = 0;
		this.playerY = 0;
		this.isFinished = false;
		this.lastMove = "";
	}

	public Tile	getTile(int x, int y)
	{
		return (map[x][y]);
	}

	public void	setTile(int x, int y, Tile tile)
	{
		map[x][y] = tile;
	}

	public int	getSize()
	{
		return (size);
	}

	public void	setSize(int size)
	{
		this.size = size;
	}

	public int	getPlayerX()
	{
		return (this.playerX);
	}

	public int	getPlayerY()
	{
		return (this.playerY);
	}

	public boolean	isFinished()
	{
		return (this.isFinished);
	}

	public void	setPlayerX(int x)
	{
		this.playerX = x;
	}

	public void	setPlayerY(int y)
	{
		this.playerY = y;
	}

	public void	setPlayerPos(int x, int y)
	{
		this.playerX = x;
		this.playerY = y;
	}

	public void	movePlayer(String dir)
	{
		this.lastMove = dir;
		this.map[this.playerX][this.playerY].setPlayerHere(false);
		this.map[this.playerX][this.playerY].setVisited(true);
		switch (dir)
		{
			case "north":
				if (this.playerY > 0)
					this.playerY -= 1;
				break;
			case "south":
				if (this.playerY < this.size - 1)
					this.playerY += 1;
				break;
			case "east":
				if (this.playerX < this.size - 1)
					this.playerX += 1;
				break;
			default: // west
				if (this.playerX > 0)
					this.playerX -= 1;
				break;
		}
		this.map[this.playerX][this.playerY].setPlayerHere(true);
		if (this.playerX == 0 || this.playerX == this.size - 1
				|| this.playerY == 0 || this.playerY == this.size - 1)
			this.isFinished = true;
	}

	public void	undoLastMove()
	{
		if (this.lastMove.equals("north"))
			this.movePlayer("south");
		else if (this.lastMove.equals("south"))
			this.movePlayer("north");
		else if (this.lastMove.equals("east"))
			this.movePlayer("west");
		else
			this.movePlayer("east");
	}

	public Villain	villainEncounter()
	{
		return (this.map[this.playerX][this.playerY].getVillain());
	}
}
