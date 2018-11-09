package sudoku;
import java.util.* ; 

public class Grille {
	public  ArrayList<int [][]> ListPuzzles = new ArrayList<int [][]>() ; 
	public  ArrayList<boolean [][]> ListMasks = new ArrayList<boolean [][]>(); 
	
	
	public Grille(){
		 int [][] puzzle1 =
				{{9,4,6,1,5,2,7,8,3},
				{7,3,5,4,9,8,2,6,1},
				{8,1,2,7,6,3,4,5,9},
				{6,2,1,8,3,5,9,4,7},
				{3,5,7,6,4,9,8,1,2},
				{4,8,9,2,7,1,5,3,6},
				{1,9,3,5,2,4,6,7,8},
				{5,7,8,9,1,6,3,2,4},
				{2,6,4,3,8,7,1,9,5}};
		 boolean [][] mask1 =
			 {{true, true, false, true, true, true, true, false, true},
					 {true, false, true, true, false, false, true, true, true},
					 {true, true, true, true,  true, true, false, false, true},
					 {true, false, true, true, false, true, true, false, true},
					 {true, true, false, true, true, false, true, true, true},
					 {false, true, false, true, true, false, false, true, false},
					 {false, true, true, true, true, true, true, true, false},
					 {false, true, true, true, false, true, true, true, true},
					 {true, false, true, false, true, true, true, true, true}};

		 int[][] puzzle =
		      {{5, 3, 4, 6, 7, 8, 9, 1, 2},
		       {6, 7, 2, 1, 9, 5, 3, 4, 8},
		       {1, 9, 8, 3, 4, 2, 5, 6, 7},
		       {8, 5, 9, 7, 6, 1, 4, 2, 3},
		       {4, 2, 6, 8, 5, 3, 7, 9, 1},
		       {7, 1, 3, 9, 2, 4, 8, 5, 6},
		       {9, 6, 1, 5, 3, 7, 2, 8, 4},
		       {2, 8, 7, 4, 1, 9, 6, 3, 5},
		       {3, 4, 5, 2, 8, 6, 1, 7, 9}};
		this.ListPuzzles.add(puzzle);
		
		boolean [][] mask =
				   {{false, false, false, false, false, true, false, false, false},
			       {false, false, false, false, false, false, false, false, true},
			       {false, false, false, false, false, false, false, false, false},
			       {false, true, false, false, false, false, false, false, false},
			       {false, false, false, true, false, false, false, false, false},
			       {false, false, false, false, false, false, false, false, false},
			       {false, false, false, false, false, false, false, false, false},
			       {false, false, false, false, false, false, false, false, false},
			       {false, false, false, false, false, false, false, true, false}};
		this.ListMasks.add(mask);
		this.ListPuzzles.add(puzzle1);
		this.ListMasks.add(mask1);
		
		
	}
}
