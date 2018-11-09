package motmele;

import java.awt.*;
import java.lang.*;
import java.awt.List;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.*;

import javax.swing.*;

import javafx.scene.input.MouseButton;

import java.util.*;

public class MotMele extends JFrame  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int GRID_SIZE = 10; // grille

	public static final int CELL_SIZE = 60; // taille case en pixels
	public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

	public static Color SELECTED_CELL = Color.green;
	public static Color CLOSED_CELL_BGCOLOR = Color.white; 
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	JPanel contentPanel = new JPanel();
	JPanel panGrille;
	JTextField Valide = new JTextField("Validation du mot clique droit \n selection du mot clique gauche");
	ArrayList <String> mot = new ArrayList<String>();
	//ArrayList<String> motsADeviner= new ArrayList<String>();
	
	int positionXPred = -1 ; 
	int positionYPred = -1 ;
	int orientationX = -2;
	int orientationY = -2;
	
	int nbMotsValides = 0 ; 
	
	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

	GrilleMotMele grilles ;
	int numGrille ; 
	
	public MotMele(String[][]puzzle1 , ArrayList<String>motsADeviner) {	
		
		setSize(new Dimension(CANVAS_WIDTH+100,CANVAS_HEIGHT+100));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		
		
		panGrille = new JPanel();
		panGrille.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		panGrille.setLayout( new GridLayout(GRID_SIZE, GRID_SIZE,2,2));
		
		/* mouse listener*/ 
		MouseListener m = new MouseAdapter() {
				
			@Override
			public void mousePressed (MouseEvent e) {
				int n = 0 ;
				int x = e.getComponent().getX();
				int y = e.getComponent().getY();
				int col = x / CELL_SIZE;
				int row = y / CELL_SIZE;
				
			
				/**
				 * clique droit de la souris pour verifier le mot selectionner 
				 */
				
				if(e.getButton() == MouseEvent.BUTTON3 ) {
					while(n<motsADeviner.size()) {
					if(compareMot(motsADeviner.get(n),mot)) {
						System.out.println("mot : "+motsADeviner.get(n)+" validé");
						nbMotsValides ++ ; 
						System.out.println(nbMotsValides);
						for(int i =0 ; i<GRID_SIZE;i++) {
							for(int j=0; j< GRID_SIZE ; j++  ) {
								if(tfCells[i][j].getBackground()==SELECTED_CELL) {
									tfCells[i][j].setBackground(Color.LIGHT_GRAY) ; 
								}
							}
						}
						
					}else if(n==motsADeviner.size()-1) {
						for(int i =0 ; i<GRID_SIZE;i++) {
							for(int j=0; j< GRID_SIZE; j++  ) {
								if(tfCells[i][j].getBackground()==SELECTED_CELL) {
									tfCells[i][j].setBackground(null) ; 
								}
							}
						}
						
					}
					n++;
					
				}	
					mot.clear() ;			//
					n=0;					// REMISE A ZERO DU MOT 
					positionXPred = -1 ;	//
					positionYPred = -1 ; 	//
					orientationX = -2 ;
					orientationY = -2 ;
					/**
					 * clique gauche de la souris pour selectionner les lettres 
					 */
					
				}else if(e.getButton()==MouseEvent.BUTTON1) {
					if(nbMotsValides<motsADeviner.size()) {
					if(positionXPred>= 0 && positionYPred >= 0) {
						if(orientationX ==-2 && orientationY == -2) {
						/*deuxieme lettre du mot donc choix tout autour de la premiere lettre */
							if((col == positionXPred || col==positionXPred+1 || col == positionXPred-1 )&&(row == positionYPred || row == positionYPred+1 || row == positionYPred-1 )) {
								/* on recupère la "l'orientation du mot"  valeur possible pour x et y -1 , 0 et 1 */
								orientationX =  col - positionXPred ;
								orientationY =  row - positionYPred ; 
								positionXPred = col ; 
								positionYPred = row ;
								tfCells[row][col].setBackground(SELECTED_CELL);
								mot.add(tfCells[row][col].getText());						
							}
						}/*(else du if sur l'orientation ) autres lettres du mot donc 1 seul choix déterminé par la direction (orientaition) */
						else if(col == positionXPred+orientationX && row == positionYPred+orientationY) {
							positionXPred = col ; 
							positionYPred = row ;
							tfCells[row][col].setBackground(SELECTED_CELL);
							mot.add(tfCells[row][col].getText());		
						}
						
						
						/* (else du if sur la positionPred ) premiere lettre du mot donc choix sur toute la grille */
					}else {
						positionXPred = col ; 
						positionYPred = row ;
						tfCells[row][col].setBackground(SELECTED_CELL);
						mot.add(tfCells[row][col].getText());
					}
				}
					else if(nbMotsValides == motsADeviner.size()) {
						JOptionPane.showMessageDialog(null, "Congrat' you just have completed the MOTS MELES ! ");
						setVisible(false);
					}
				}
			}
		};
	
		/*initialisation du panel contenant la grille a jouer */ 
		
		for (int row = 0; row < GRID_SIZE; ++row) {
			for (int col = 0; col < GRID_SIZE; ++col) {
				
				tfCells[row][col] = new JTextField();
				panGrille.add(tfCells[row][col]);
				
				
				tfCells[row][col].setText(puzzle1[row][col]);
				tfCells[row][col].setEditable(false);
				tfCells[row][col].setFocusable(false);
				tfCells[row][col].setBackground(null);
				tfCells[row][col].setForeground(CLOSED_CELL_TEXT);

				tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
				tfCells[row][col].setFont(FONT_NUMBERS);
				
				tfCells[row][col].addMouseListener(m);
				
			}

		}
		
		contentPanel.add(panGrille);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		
		setTitle("Mots Mêlés");
		setVisible(true);

	}
	public boolean compareMot(String motATrouver,ArrayList <String> listLettres) {
		int i=1 ; 
		boolean resultat = false ; 
		String mot=listLettres.get(0) ;
		String motEnvers = listLettres.get(0);
		
		if(motATrouver.length() == listLettres.size()) {
		while( i< listLettres.size()) { 
				mot =mot.concat(listLettres.get(i));
				motEnvers = listLettres.get(i).concat(motEnvers); // cas ou le mot est saisie a l'envers dans la grille par l'utilisateur
				i++ ;
			}
		
		}
		if(mot.equalsIgnoreCase(motATrouver)|| (motEnvers.equalsIgnoreCase(motATrouver))) {// saisie a l'endroit ou a l'envers 
			resultat =true ; 
		}
		return resultat ; 
		
	}
	
	
	

	/** The entry main() entry method */
	public static void main(String[] args) {
		MenuMotMele menuMotsMeles = new MenuMotMele();
	

	}

}
