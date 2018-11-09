import java.util.ArrayList;

public class GrilleL {
	static String[][] puzzle1 = { 
			{ "e", "o", "s", "a", "f", "m", "i", "r", "b" },
			{ "i", "r", "m", "o", "s", "b", "a", "f", "e" }, 
			{ "f", "a", "b", "e", "r", "i", "s", "m", "o" },
			{ "s", "m", "o", "i", "a", "r", "b", "e", "f" }, 
			{ "a", "f", "e", "b", "o", "s", "m", "i", "r" },
			{ "b", "i", "r", "m", "e", "f", "o", "a", "s" }, 
			{ "m", "e", "a", "r", "b", "o", "f", "s", "i" },
			{ "o", "s", "i", "f", "m", "e", "r", "b", "a" }, 
			{ "r", "b", "f", "s", "i", "a", "e", "o", "m" } };
	
	boolean[][] mask1 = { 
			{ true, true, false, true, true, true, true, false, true },
			{ true, false, true, true, false, false, true, true, true },
			{ true, true, true, true, true, true, false, false, true },
			{ true, false, true, true, false, true, true, false, true },
			{ true, true, false, true, true, false, true, true, true },
			{ false, true, false, true, true, false, false, true, false },
			{ false, true, true, true, true, true, true, true, false },
			{ false, true, true, true, false, true, true, true, true },
			{ true, false, true, false, true, true, true, true, true } };

	public ArrayList<String[][]> ListPuzzles = new ArrayList<String[][]>();
	public ArrayList<boolean[][]> ListMasks = new ArrayList<boolean[][]>();

	public GrilleL() {
		String[][] puzzle = { 
				{ "i", "e", "o", "y", "m", "q", "u", "p", "l" },
				{ "m", "u", "q", "p", "l", "o", "i", "e", "y" }, 
				{ "p", "l", "y", "e", "u", "i", "m", "o", "q" },
				{ "e", "m", "p", "u", "q", "l", "o", "y", "i" }, 
				{ "q", "y", "l", "o", "i", "p", "e", "u", "m" },
				{ "o", "i", "u", "m", "y", "e", "q", "l", "p" }, 
				{ "y", "q", "i", "l", "o", "u", "p", "m", "e" },
				{ "u", "p", "m", "q", "e", "y", "l", "i", "o" }, 
				{ "l", "o", "e", "i", "p", "m", "y", "q", "u" } };
		this.ListPuzzles.add(puzzle);

		boolean[][] mask = { 
				{ false, false, false, false, false, true, false, false, false },
				{ false, false, false, false, false, false, false, false, true },
				{ false, false, false, false, false, false, false, false, false },
				{ false, true, false, false, false, false, false, false, false },
				{ false, false, false, true, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, false, false },
				{ false, false, false, false, false, false, false, true, false } };
		this.ListMasks.add(mask);
		this.ListPuzzles.add(puzzle1);
		this.ListMasks.add(mask1);

	}

}
