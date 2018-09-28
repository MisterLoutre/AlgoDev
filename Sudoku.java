package sudoku;

import java.awt.*;        // Uses AWT's Layout Managers
import java.awt.event.*;  // Uses AWT's Event Handlers
import javax.swing.*;     // Uses Swing's Container/Components
 import java.util.*;
/**
 * The Sudoku game.
 * To solve the number puzzle, each row, each column, and each of the
 * nine 3�3 sub-grids shall contain all of the digits from 1 to 9
 */
public class Sudoku extends JFrame {
 
   public static final int GRID_SIZE = 9;    // grille
   public static final int SUBGRID_SIZE = 3; // sous grille 
 
   public static final int CELL_SIZE = 60;   // taille case en pixels
   public static final int CANVAS_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
                                            
   public static final Color OPEN_CELL_BGCOLOR = Color.gray;
   public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
   public static final Color CLOSED_CELL_TEXT = Color.BLACK;
   public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
 
   
   // The game board composes of 9x9 JTextFields,
   // each containing String "1" to "9", or empty String
   private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
   
  /*
  private int[][] puzzle =
      {{5, 3, 4, 6, 7, 8, 9, 1, 2},
       {6, 7, 2, 1, 9, 5, 3, 4, 8},
       {1, 9, 8, 3, 4, 2, 5, 6, 7},
       {8, 5, 9, 7, 6, 1, 4, 2, 3},
       {4, 2, 6, 8, 5, 3, 7, 9, 1},
       {7, 1, 3, 9, 2, 4, 8, 5, 6},
       {9, 6, 1, 5, 3, 7, 2, 8, 4},
       {2, 8, 7, 4, 1, 9, 6, 3, 5},
       {3, 4, 5, 2, 8, 6, 1, 7, 9}};

   private boolean[][] masks =
      {{false, false, false, false, false, true, false, false, false},
       {false, false, false, false, false, false, false, false, true},
       {false, false, false, false, false, false, false, false, false},
       {false, true, false, false, false, false, false, false, false},
       {false, false, false, true, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, true, false}};
 
*/	Grille grilles ;
   int numG ; 
	

   public Sudoku(int [][]puzzle,boolean [][] masks ) {
	   
	  JFrame f= new JFrame("Sudoku");
      Container cp = getContentPane();
      cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // 9x9 Grille
    
      InputListener listener = new InputListener();
 
      //creation du pan
      for (int row = 0; row < GRID_SIZE; ++row) {
         for (int col = 0; col < GRID_SIZE; ++col) {
            tfCells[row][col] = new JTextField(); 
            cp.add(tfCells[row][col]);            
            if (masks[row][col]) {
               tfCells[row][col].setText("");     
               tfCells[row][col].setEditable(true);
               tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
  
               tfCells[row][col].addActionListener(listener);
               
            } else {
               tfCells[row][col].setText(puzzle[row][col] + "");
               tfCells[row][col].setEditable(false);
               tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
               tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
            }
            
            tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
            tfCells[row][col].setFont(FONT_NUMBERS);
         }
      }
 
      
      cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      pack();
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // comment ferme la fenetre
    
  
      
      setTitle("Sudoku");
      setVisible(true);
      
   }
		public boolean gridCompleted(JTextField [][] tab ) {
   			boolean completed = true ; 
   			for( int row = 0 ; row< GRID_SIZE; row++) {
           	 for ( int col = 0; col < GRID_SIZE ;  col++) {
           		 	if(tab[row][col].getText().equals("") ) {
           		 		completed = false ; 
           		 	}
           	 	}
           	 }
   			return completed ; 
   		}
 
   private class InputListener implements ActionListener {
	   
      @Override
      public void actionPerformed(ActionEvent e ) {
  
         int rowSelected = -1;
         int colSelected = -1;
 
         // obtenir la source de l'evement e
         JTextField source = (JTextField)e.getSource();
         // scanner tout le tableau et verifie si la case match avec la source 
        // boolean found = false;
        /* for (int row = 0; row < GRID_SIZE  ; row++) {
            for (int col = 0; col < GRID_SIZE ; col++) {
               if (tfCells[row][col] == source) {
                  rowSelected = row;
                  colSelected = col;
                 // masks[row][col]= false ;
                 // masks[rowSelected][colSelected]= false ; 
               }
              
            }
         }*/
         
       
        if(gridCompleted(tfCells)) { //essaye de verifier si toutes les cases ss valeur ont �t� remplis 
        	 boolean success = true ;
         for( int row = 0 ; row< GRID_SIZE; row++) {
        	 for ( int col = 0; col < GRID_SIZE ; col++) {
        		 int number = Integer.parseInt( tfCells[row][col].getText());
        		 if(number == (grilles.ListPuzzles.get(numG))[row][col] /*&& masks[row][col]== false*/ ) { // si le nombre entr� dans ler tfCells egal le nombre du puzzle case valid�e 
        			 tfCells[row][col].setBackground(Color.GREEN);
        		 }else  { tfCells[row][col].setBackground(Color.RED);
        		 			success =false ;}
        	 }	 
        	 }
         if (success) {
        	 JOptionPane.showMessageDialog(null, "Bravo mon con t'as reussi un sudoku niveau grosse merde");
        	 
         }
         
        }
    
         
      }
   }
   /** The entry main() entry method */
   public static void main(String[] args) {
      
	Menu men = new Menu();
	//Sudoku sudo = new Sudoku();
	
   }
}
