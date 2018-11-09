import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MenuL extends JFrame implements ActionListener {

	JMenu menu;
	JMenuItem i1, i2, i3, i4;
	JComboBox combo;

	MenuL() {
		GrilleL grillesL = new GrilleL();
		String[] tab = { "", "Easy", "Medium", "Hard", "Expert" };
		combo = new JComboBox(tab);

		JFrame f = new JFrame("SudoLettre Menu");
		ImageIcon img = new ImageIcon("WORDOKU.jpg");

		Image myNewImage = img.getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(myNewImage);
		JLabel pic = new JLabel(img2);
		JPanel pan = new JPanel();
		combo.setPreferredSize(new Dimension(100, 20));
		pan.add(combo);
		combo.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent event) {
				switch (event.getStateChange()) {
				case ItemEvent.SELECTED: {

					if (combo.getSelectedIndex() == 1) {
						SudoLettre sudoL = new SudoLettre(grillesL.ListPuzzles.get(0), grillesL.ListMasks.get(0));
						sudoL.numG = 0;
						sudoL.grilles = grillesL;
					}
					if (combo.getSelectedIndex() == 2) {
						SudoLettre sudoL = new SudoLettre(grillesL.ListPuzzles.get(1), grillesL.ListMasks.get(1));
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
		// f.setLayout(null);
		f.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
