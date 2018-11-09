package sudoku;

import java.awt.*;        
import java.awt.event.*;  
import javax.swing.*; 
import java.util.*;

public class Sudoku extends JFrame {

	public static final int GRID_SIZE = 9;    // grille
	public static final int SUBGRID_SIZE = 3; // sous grille 

	public static final int CELL_SIZE = 60;   // taille case en pixels
	public static final int CANVAS_WIDTH  = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

	public static Color OPEN_CELL_BGCOLOR = Color.pink;
	public static Color CLOSED_CELL_BGCOLOR = Color.GRAY; // RGB
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);


	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
	Grille grilles ;
	int numGrille ; 
	boolean success ;
	double start ; 
	double end ; 
	


	public Sudoku(int [][]puzzle,boolean [][] masks ) {
		
		Container cp = getContentPane();
		cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));  // 9x9 Grille
		InputListener listener = new InputListener();
	
		//creation du pan
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {	
			if((row<3 || row>5 )&& (col <3 || col>5)||((row >2 && row <6)&&(col>2 && col<6))) {
				OPEN_CELL_BGCOLOR = Color.LIGHT_GRAY;
				CLOSED_CELL_BGCOLOR= Color.LIGHT_GRAY;
				
			}
			else {
				OPEN_CELL_BGCOLOR = Color.white;
				CLOSED_CELL_BGCOLOR= Color.white;
				
			}
				tfCells[row][col] = new JTextField(); 
				cp.add(tfCells[row][col]);    
				tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,Color.black));
				if (masks[row][col]) { // si le mask est a vrai alors on veut que l'utilisateur puisse entrer une valeur (case vide) 
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
				if(col == 3 || col ==6) {
					tfCells[row][col].setBorder(BorderFactory.createMatteBorder(1, 3, 1, 1,Color.black));}
				if(row == 3|| row==6) {
					tfCells[row][col].setBorder(BorderFactory.createMatteBorder(3, 1, 1, 1,Color.black));
					}
				if((row == 3|| row==6)&&(col == 3 || col ==6)) {
						tfCells[row][col].setBorder(BorderFactory.createMatteBorder(3, 3, 1, 1,Color.black));
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
			for ( int col = 0; col < GRID_SIZE;  col++) {
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

			// obtenir la source de l'evement e
			JTextField source = (JTextField)e.getSource();



			if(gridCompleted(tfCells)) { //essaye de verifier si toutes les cases ss valeur ont été remplies 
				success = true ;
				
				for( int row = 0 ; row< GRID_SIZE; row++) {
					for ( int col = 0; col < GRID_SIZE ; col++) {
						if( tfCells[row][col].getText().matches("[1-9]" )){ //verifie que le resultat du JtextField est un entier compris entre 1 et 9 evite au programme de planter en cas d'entrée de lettres ou autres caracteres 
							int number = Integer.parseInt( tfCells[row][col].getText());
							if(number == (grilles.ListPuzzles.get(numGrille))[row][col]  ) { // si le nombre entré dans ler tfCells egal le nombre du puzzle case validée 
								tfCells[row][col].setBackground(Color.GREEN);// le (grilles.ListPuzzles.get(numG)) permet de recuperer la grille solution de la grille jouée par l'utilisateur
							}else {
								tfCells[row][col].setBackground(Color.RED);
								success = false; }
						}else  { tfCells[row][col].setBackground(Color.RED);
						success =false ;}
					}	 
				}
				if (success) {
					

					JOptionPane.showMessageDialog(null, "Congrat' you just have completed the sudoku ! ");
					setVisible(false);
					
				}

			}


		}

	}
	/** The entry main() entry method */
	public static void main(String[] args) {


		Menu men = new Menu();
		
	}
}
