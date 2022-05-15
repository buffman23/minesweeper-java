package minesweeper.field;
import java.util.List;
/**
 * A mine field data structure used to play MineSweeper
 * @author Ryan Coughlin
 *
 */
public abstract class MineField {
	/**
	 * Dimensions of MineField.
	 */
	protected int width;
	protected int height;
	
	/**
	 * Number of mines in field.
	 */
	protected int mineCount;
	
	/**
	 * Creates MineField with given dimensions. No tile values are initialized.
	 * @param width
	 * @param height
	 */
	public MineField(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	/**
	 * populates MineField with mines and sets the value of all tiles.
	 * @param mineCount Number of mines in MineField.
	 * @param randomSeed 
	 * @param safeTiles Tiles that 100% will not spawn a mine.
	 */
	public abstract void populateField(int mineCount, long randomSeed, List<Tile> safeTiles);
	
	/**
	 * Sweeps a tile.
	 * <p>
	 * If this given tile location is empty, All neighboring tiles will be swept as well.
	 * @param x
	 * @param y
	 * @return A list of Tiles whose values were updated during to sweeping.
	 */
	public abstract List<Tile> sweep(int x, int y);
	
	/**
	 * Flags a tile.
	 * @param x
	 * @param y
	 */
	public abstract void flag(int x, int y);
	
	public abstract Tile get(int x, int y);
	
	public abstract void set(int x, int y, int value);
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getMineCount() {
		return mineCount;
	}
}
