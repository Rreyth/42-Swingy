package swingy.model.map;

public class Tile
{
	private int		x;
	private int		y;
	private boolean	occupied;
	private boolean	visited;
	private boolean	playerHere;

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
}
