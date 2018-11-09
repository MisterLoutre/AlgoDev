package sudoku;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {

	int numeroGrille;
	JMenu menu;
	

	
	Menu() {
		Grille grilles = new Grille();
		String[] tab = { "", "Easy", "Medium", "Hard", "Expert" };
		JComboBox combo = new JComboBox(tab);

		JFrame f = new JFrame("Sudoku Menu");
		ImageIcon img = new ImageIcon("imgMenu.png");

		Image myNewImage = img.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(myNewImage);
		JLabel pic = new JLabel(img2);
		JPanel pan = new JPanel();
		combo.setPreferredSize(new Dimension(100, 20));
		pan.add(combo);
		combo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED: {
				
					int randomInt ;
					if (combo.getSelectedIndex() == 1) {
						randomInt = 0 + (int)(Math.random() * ((0) + 1));
						
						Sudoku sudo = new Sudoku(grilles.ListPuzzles.get(randomInt), grilles.ListMasks.get(randomInt));
						
						sudo.numGrille = randomInt;
						sudo.grilles = grilles;
					
						
					}
					if (combo.getSelectedIndex() == 2) {
						Sudoku sudo = new Sudoku(grilles.ListPuzzles.get(1), grilles.ListMasks.get(1));
						sudo.numGrille = 0;
						sudo.grilles = grilles;
					}
				}

					break;
				case ItemEvent.DESELECTED:
				default: {

				}
				}
			}

		});

		f.add(pic);
		f.add(pan, BorderLayout.NORTH);
		f.setSize(500, 500);
		
		f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {

	}

}
