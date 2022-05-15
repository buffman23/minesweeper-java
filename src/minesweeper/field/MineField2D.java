package minesweeper.field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MineField2D extends MineField {
	
	
	private Tile[][] field;
	
	public MineField2D(int width, int height)
	{
		super(width, height);
		
		field = new Tile[width][];
		
		for(int i = 0; i < width; ++i) {
			Tile[] column = new Tile[height];
			
			for(int j = 0; j < width; ++j) {
				column[j] = new Tile(i, j, Tile.EMPTY);
			}
			field[i] = column;
		}
	}
	
	public void populateField(int mineCount, long randomSeed, List<Tile> safeTiles)
	{
		this.mineCount = mineCount;
		
		// list of Tiles that will not be bombs
		// initialized as containing all tiles in field
		List<Tile> noMineTiles = new ArrayList<Tile>(width * height);
		
		//
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				noMineTiles.add(this.get(i, j));
			}
		}
		
		if(safeTiles != null)
			noMineTiles.removeAll(safeTiles);
		
		Random rand = new Random(randomSeed);
		for(int i = 0; i < mineCount && noMineTiles.size() > 0; ++i) {
			int randomIdx = rand.nextInt(noMineTiles.size());
			Tile randomTile = noMineTiles.remove(randomIdx);
			
			randomTile.value = Tile.MINE;
		}
		
		for(Tile tile : noMineTiles) {
			tile.value = MFUtil.countNearbyMines(this, tile);
		}
	}
	
	public Iterator<Tile> iterator()
	{
		return new FieldIterator();
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder(width * height);
		
		for(int x = 0; x < (width) * 2 + 1; ++x) {
			sb.append('-');
		}
		sb.append('\n');
		
		for(int y = 0; y < height; ++y) {
			sb.append('|');
			for(int x = 0; x < width; ++x) {
				Tile tile = field[x][y];
				
				if(tile.value == Tile.EMPTY) { // empty
					sb.append(' ');
				}else if(tile.value < Tile.MINE) { // number
					sb.append(tile.value);
				}else if(tile.value >= Tile.FLAGGED) { // flagged
					sb.append('>');
				}else if(tile.value >= Tile.TILE_UP) { // tile_up
					sb.append('o');
				}else { // MINE
					sb.append('x');
				}
				
				if(x != width - 1)
					sb.append(' ');
			}
			sb.append("|\n");
		}
		
		for(int x = 0; x < (width) * 2 + 1; ++x) {
			sb.append('-');
		}
		
		return sb.toString();
	}

	@Override
	public List<Tile> sweep(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flag(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tile get(int x, int y) {
		return field[x][y];
	}

	@Override
	public void set(int x, int y, int value) {
		// TODO Auto-generated method stub
		field[x][y].value = value ;
	}

	private class FieldIterator implements Iterator<Tile>
	{
		private int x = 0;
		private int y = 0;
		
		@Override
		public boolean hasNext() {
			return x < MineField2D.this.width &&
					y < MineField2D.this.height;
		}

		@Override
		public Tile next() {
			Tile tile = field[x][y];
			
			++x;
			if(x == width) {
				x = 0;
				++y;
			}

			return tile;
		}
		
	}
}
