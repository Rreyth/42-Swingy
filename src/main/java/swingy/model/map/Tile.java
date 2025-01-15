package swingy.model.map;

import swingy.model.entity.Villain;

public class Tile
{
	private final int		x;
	private final int		y;
	private boolean	occupied;
	private boolean	visited;
	private boolean	playerHere;
	private	Villain	villain = null;

	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.occupied = false;
		this.visited = false;
		this.playerHere = false;
	}

	public int	getX()
	{
		return (this.x);
	}

	public int	getY()
	{
		return (this.y);
	}

	public boolean	isOccupied()
	{
		return (this.occupied);
	}

	public boolean	isVisited()
	{
		return (this.visited);
	}

	public boolean	hasPlayer()
	{
		return (this.playerHere);
	}

	public Villain	getVillain()
	{
		return (this.villain);
	}

	public void	setOccupied(boolean occupied)
	{
		this.occupied = occupied;
	}

	public void	setVisited(boolean visited)
	{
		this.visited = visited;
	}

	public void	setPlayerHere(boolean here)
	{
		this.playerHere = here;
	}

	public void	setVillain(Villain villain)
	{
		this.villain = villain;
	}
}
