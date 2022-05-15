package minesweeper.field;
import java.util.ArrayList;
import java.util.List;

public class MFUtil {
	/**
	 * Scans for a Tile's neighbors in a MineField from top-left to bottom-right
	 * @param mineField
	 * @param tile
	 * @return List of neighboring Tiles
	 */
	public static List<Tile> getNeighbors(MineField mineField, Tile tile) {
		List<Tile> neighbors = new ArrayList<Tile>(8);
		
		int xLower, xUpper, yLower, yUpper;
		
		// y bounds
		if(tile.y == 0) 
			yLower = 0;
		else
			yLower = -1;
		
		if(tile.y == mineField.height - 1) 
			yUpper = 0;
		else
			yUpper = 1;
		
		// x bounds
		if(tile.x == 0) 
			xLower = 0;
		else
			xLower = -1;
		
		if(tile.x == mineField.width - 1)
			xUpper = 0;
		else
			xUpper = 1;
		
		// scans for neighbors from top-left to bottom-right
		for(int i = yLower; i <= yUpper; ++i) {
			int y = tile.y + i;
			
			for(int j = xLower; j <= xUpper; ++j) {
				int x = tile.x + j;

				if(x == tile.x && y == tile.y) // don't add self
					continue;
				
				neighbors.add(mineField.get(x, y));
			}
		}
		
		return neighbors;
	}
	
	public static int countNearbyMines(MineField mineField, Tile tile) {
		int nearbyMines = 0;
		
		for(Tile neighborTile : MFUtil.getNeighbors(mineField, tile)) {
			if(neighborTile.value == Tile.MINE)
				++nearbyMines;
		}
		
		return nearbyMines;
	}
}
