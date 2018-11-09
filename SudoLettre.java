import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * The Sudoku game. To solve the number puzzle, each row, each column, and each
 * of the nine 3×3 sub-grids shall contain all of the digits from 1 to 9
 */
public class SudoLettre extends JFrame {

	public static final int GRID_SIZE = 9; // grille
	public static final int SUBGRID_SIZE = 3; // sous grille

	public static final int CELL_SIZE = 60; // taille case en pixels
	public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

	public static final Color OPEN_CELL_BGCOLOR = Color.gray;
	public static final Color CLOSED_CELL_BGCOLOR = new Color(240, 240, 240); // RGB
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	// The game board composes of 9x9 JTextFields,
	// each containing String "1" to "9", or empty String
	private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];

	GrilleL grilles;
	int numG;
	private static int heure = 0, minute = 0, seconde = 0;
	int delais = 1000;
	Timer timer1 = new Timer(delais, null);

	public SudoLettre(String[][] puzzle, boolean[][] masks) {

		JFrame f = new JFrame("SudoLettre");
		Container cp = getContentPane();
		JPanel panel = new JPanel();
		cp.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE)); // 9x9 Grille
		timer1.start();
		ActionListener tache_timer;

		/* création des composants */

		final JLabel Label1 = new JLabel(heure + ":" + minute + ":"
				+ seconde); /* déclarer final car une classe interne va acceder à ce composant */
		JPanel Panel1 = new JPanel();
		BoxLayout b1 = new BoxLayout(Panel1, BoxLayout.LINE_AXIS);

		InputListener listener = new InputListener();
		tache_timer = new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				seconde++;
				if (seconde == 60) {
					seconde = 0;
					minute++;
				}
				if (minute == 60) {
					minute = 0;
					heure++;
				}
				Label1.setText(heure + ":" + minute + ":" + seconde);/* rafraichir le label */
			}
		};

		timer1.addActionListener(tache_timer);
		/* Ajout des composants aux conteneurs avec formatage */
		Panel1.setLayout(b1);
		Label1.setBorder(new EmptyBorder(10, 100, 10, 10));
		Panel1.add(Label1, "Center");
		f.getContentPane().add(Panel1);
		panel.add(cp);
		panel.add(Panel1);

		// creation du pan
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // comment ferme la fenetre

		setTitle("SudoLettre");
		setSize(800, 700);
		setVisible(true);
		setContentPane(panel);

	}

	public boolean gridCompleted(JTextField[][] tab) {
		boolean completed = true;
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (tab[row][col].getText().equals("")) {
					completed = false;
				}
			}
		}
		return completed;
	}

	private class InputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int rowSelected = -1;
			int colSelected = -1;

			// obtenir la source de l'evement e
			JTextField source = (JTextField) e.getSource();

			if (gridCompleted(tfCells)) { // essaye de verifier si toutes les cases ss valeur ont été remplis
				boolean success = true;
				for (int row = 0; row < GRID_SIZE; row++) {
					for (int col = 0; col < GRID_SIZE; col++) {
						String letter = tfCells[row][col].getText();
						if (letter.compareTo(
								(grilles.ListPuzzles.get(numG))[row][col]) == 0 /* && masks[row][col]== false */ ) { // si
																														// le
																														// nombre
																														// entré
																														// dans
																														// ler
																														// tfCells
																														// egal
																														// le
																														// nombre
																														// du
																														// puzzle
																														// case
																														// validée
							tfCells[row][col].setBackground(Color.GREEN);
						} else {
							tfCells[row][col].setBackground(Color.RED);
							success = false;
						}
					}
				}
				if (success) {
					timer1.stop();
					JOptionPane.showMessageDialog(null, "Bravo !");

				}

			}

		}
	}

	/** The entry main() entry method */
	public static void main(String[] args) {

		MenuL men = new MenuL();

	}
}
