package motmele;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class MenuMotMele extends JFrame implements ActionListener {
	

		int numeroGrille;
		JMenu menu;
	
		
		MenuMotMele() {
			GrilleMotMele grilles = new GrilleMotMele();
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
							
						//	MotMele motmele = new MotMele (grilles.listGrilles.get(randomInt), grilles.listMots.get(randomInt));
							
							//motmele.numGrille = randomInt;
						//	motmele.grilles = grilles;
						
							
						}
						if (combo.getSelectedIndex() == 2) {
						//	MotMele motmele = new MotMele(grilles.listGrilles.get(1), grilles.listMots.get(1));
							//motmele.numGrille = 0;
							//motmele.grilles = grilles;
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

