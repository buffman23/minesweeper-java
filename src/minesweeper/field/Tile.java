package minesweeper.field;

public class Tile implements Comparable<Tile> {
	/**
	 * States of a tile.
	 */
	public static final int EMPTY = 0, MINE = 9, TILE_UP = 10, FLAGGED = 20;
	
	public static char[] cSymbols = {' ', '1', '2', '3', '4', '5', '6', '7', '8' , 'x', 'o'};
	public int x, y;
	public int value;
	
	public Tile(int x, int y)
	{
		this(x, y, 0);
	}
	
	public Tile(int x, int y, int value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public void toggleFlag() {
		if(this.isUp()) {
			if(this.isFlagged())
				this.value -= 10;
			else
				this.value += 10;
		}
	}
	
	public boolean isFlagged() {
		return this.value >= Tile.FLAGGED;
	}
	
	public boolean isUp() {
		return this.value >= Tile.TILE_UP;
	}

	@Override
	/**
	 * top-left cells > bottom right cells
	 */
	public int compareTo(Tile other) {
		if(this.x == other.x) {
			return this.y - other.y;
		}
		
		return this.x - other.x;
	}
	
	
	public boolean equals(Tile other) {
		return this.x == other.x && this.y == other.y;
	}
}
