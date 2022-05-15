import minesweeper.field.MineField;
import minesweeper.field.MineField2D;

public class Main {
	public static void main(String args[])
	{
		MineField minefield = new MineField2D(25, 25);
		minefield.populateField(50, 0, null);
		
		System.out.println(minefield);
	}
}
