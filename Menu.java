package sudoku;

import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Menu extends JFrame implements ActionListener {
	
	
	 	int numeroGrille ; 
        JMenu menu;  
        JMenuItem i1, i2, i3, i4;  
        JComboBox combo ; 
        Menu(){  
        	Grille grilles = new Grille();
        	String[] tab = {"","Easy", "Medium", "Hard", "Expert"};
        	combo = new JComboBox(tab);
           
            
        JFrame f= new JFrame("Sudoku Menu");
        ImageIcon img= new ImageIcon("imgMenu.png");
        
        Image myNewImage = img.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT); 
        ImageIcon img2= new ImageIcon(myNewImage);
        JLabel pic = new JLabel(img2);
        JPanel pan = new JPanel();
        combo.setPreferredSize(new Dimension(100, 20));
        pan.add(combo);
        combo.addItemListener(new ItemListener(){
      	  
        	public void itemStateChanged(ItemEvent event) {
      		    switch (event.getStateChange()) {  
      		      case ItemEvent.SELECTED: { 
      		    	
      		    	  if(combo.getSelectedIndex()==1 ) {Sudoku sudo = new Sudoku(grilles.ListPuzzles.get(0),grilles.ListMasks.get(0));
      		    	  sudo.numG = 0 ;sudo.grilles= grilles ;  }
      		    	  if(combo.getSelectedIndex()==2 ) {Sudoku sudo = new Sudoku(grilles.ListPuzzles.get(1),grilles.ListMasks.get(1)); 
      		    	   }
      		      }
      		     
      		      break;
      		      case ItemEvent.DESELECTED: 
      		      default: { 
      		    	  
      		        
      		      }
      		    }
      		  }
       
        	
      		});

    
        f.add(pic) ;

        f.add(pan, BorderLayout.NORTH);
        
        
        f.setSize(500,500);  
        //f.setLayout(null);  
        f.setVisible(true);  
        
}  
        
 
	public void actionPerformed (ActionEvent e){
			  
	     
	         
	      }
 	public int getNum() {
    	return numeroGrille ; 
    	}
}

