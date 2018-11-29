package finalAlgoDev;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Pendu extends JFrame implements ActionListener {

	public static final int GRID_SIZE = 20; //grille
	public static final int CELL_SIZE = 40;
	public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
	public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
	public static final Color CLOSED_CELL_TEXT = Color.BLACK;
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
	
	JPanel contentPanel = new JPanel(); //Concerne le panneau principal
	JPanel panGrille;
	JPanel panDeSaisie; 
	JTextField [] tfCells = new JTextField [GRID_SIZE];
	JTextField caseSaisie = new JTextField ();
	
	ImageIcon img = new ImageIcon("erreur0.png");
	JLabel panSchema = new JLabel (img);

	List <String> mot = new ArrayList <String>();
	List <String> listeMUtil = new ArrayList <String>();
	List <String> listeMDico = new ArrayList <String>();
	
	Boolean valideLettre = false;
	public static int tentativesMax=10; //nombre d'erreurs maxi
	int score = 0;
	
	
	/* Ci dessous, constructeur du Pendu
	 * Ce qu'on veut à l'affichage 
	 *  dès le lancement de ce jeu
	 */
	
	Pendu (){
		listeMotsDico();
		listeMotsUtil();
		afficheMot();
		affichage();
		
		setSize(new Dimension(CANVAS_WIDTH+100,CANVAS_HEIGHT+100));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        
        panDeSaisie.add(caseSaisie);
        contentPanel.add(panGrille);
        contentPanel.add(panDeSaisie);
        contentPanel.add(panSchema);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        getContentPane().add(panSchema, BorderLayout.SOUTH);
        getContentPane().setBackground(Color.WHITE);

        setTitle("Pendu");
        this.setSize(800, 4000);
        setVisible(true);
	}
	
	
	
	//------------------------------------------
	//----AFFICHAGE DES ELEMENTS DANS LA FENETRE
	//------------------------------------------
	
public void affichage() {
		
	 panGrille = new JPanel();
	 panDeSaisie= new JPanel();
	 panGrille.setPreferredSize(new Dimension(800, 100));
	 panGrille.setLayout( new GridLayout(1, GRID_SIZE,2,2));      panDeSaisie.setLayout(new GridLayout(1,1));       panDeSaisie.setPreferredSize(new Dimension(200, 40));
	       
	 panSchema = new JLabel (img);
	 panSchema.repaint();
	      
	 caseSaisie = new JTextField();
	 caseSaisie.setText(null);
	 caseSaisie.setEditable(true); //utilisateur peut rentrer qqc dedans
	 caseSaisie.setSize(200,40);
	 caseSaisie.setHorizontalAlignment(JTextField.CENTER);
	 caseSaisie.setFont(FONT_NUMBERS);
	 
	 caseSaisie.addKeyListener(new KeyAdapter()
	 {
		@Override
		public void keyPressed(KeyEvent arg0) {
				if(KeyEvent.VK_ENTER==arg0.getKeyCode()) valideLettre = true ; 	
			}});
	       
	        caseSaisie.addActionListener(this);
	
	        for (int col = 0; col < tabMotAChercher.length; ++col) {	
	                
	                tfCells[col] = new JTextField();
	                panGrille.add(tfCells[col]);
	                
	                tfCells[col].setText(tabMotAChercher[col]+"");
	                tfCells[col].setEditable(false);
	                tfCells[col].setFocusable(false);
	                tfCells[col].setBackground(null);
	                tfCells[col].setForeground(CLOSED_CELL_TEXT);

	                tfCells[col].setHorizontalAlignment(JTextField.CENTER); 
	                tfCells[col].setFont(FONT_NUMBERS);
      
	            }
	}

	//------------------------------------------
	//----CHERCHER UN MOT DANS LE DICO ---------
	//------------------------------------------
	        
	public String motInitial;
	public int longueurMotAChercher;
	public char tabMotAChercher [];
	Scanner sc = new Scanner(System.in);
	
	
	public void listeMotsDico ()
	{
		String pathFichier = "/home/tim/Bureau/dico.txt";
		
		BufferedReader fluxEntree = null;
		String line;
		
		try {
			//Creation du flux vers le fichier texte utilise
			fluxEntree = new BufferedReader (new FileReader (pathFichier));
			
			//Lecture d'une ligne aleatoire
			while (( line = fluxEntree.readLine()) != null)
			{
				String ligneLue = fluxEntree.readLine();	
				ligneLue = fluxEntree.readLine();
				listeMDico.add(ligneLue);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				if (fluxEntree != null)
				{
					fluxEntree.close();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

public void listeMotsUtil () {
	
	Random r = new Random ();
	int ligne = r.nextInt(listeMDico.size()) +1 ;
	
	motInitial = listeMDico.get(ligne);
	longueurMotAChercher = motInitial.length();
	tabMotAChercher = new char [longueurMotAChercher];
			
	for (int i=0; i<longueurMotAChercher; i++)	tabMotAChercher[i] = motInitial.charAt(i);
	System.out.println(tabMotAChercher);
			
	}


	//------------------------------------------
	//----AFFICHER LE MOT CACHE  ---------------
	//------------------------------------------


public void afficheMot() {
	
	int k=0;
	listeMotsDico();listeMotsUtil();
	tabMotAChercher = new char [longueurMotAChercher];

System.out.print("Mot à deviner: ");

for (k=0;k<longueurMotAChercher;k++){

	if (k==0){ //Affichage de la première lettre
		tabMotAChercher[k]=motInitial.charAt(k);
	}
	else if (k==tabMotAChercher.length-1){ //Affichage de la dernière lettre
		tabMotAChercher[k]=motInitial.charAt(k);	
	}
	else{
		tabMotAChercher[k]='_'; //On cache le reste par des tirets
	}
	
}
for (k=0;k<tabMotAChercher.length;k++){
System.out.print(tabMotAChercher[k]); //Affichage du mot à deviner
}
}


    //------------------------------------------
	//----SAISIR UNE LETTRE - -------  ---------
	//------------------------------------------

public void SaisirLettre () {
	
	int i;
	int nbLettresBonnes=0;
	int erreur=0; 
	
	char lettreSaisie; 
	char tabErreurs[] = new char [tentativesMax];
	
	boolean motTrouve=false; 
	boolean bonneLettre=false; //Lettre saisie dans le mot initial ou pas
	
	boolean bonneLettreDejaDite=false; // Donc déjà affichée dans le mot
	boolean difference; // Différence avec le mot initial
	boolean mauvaiseLettreDejaDite=false; // Sert à ne pas compter faux si on redit la lettre
	
	
	
	while ((motTrouve==false) && (erreur!=tentativesMax)){ //Tant que le mot n'a pas été trouvé et qu'on a pas 10 erreurs
			
		nbLettresBonnes=0; //Au début, on a pas encore trouvé de lettres
		bonneLettre=false; //La lettre n'a pas encore été saisie donc false
			
	
				do{ 
		/* Cas où la lettre à déjà été dite
		 * 	qu'elle soit fausse
		 * ou déjà affichée à l'écran 		
		 */
					if ((bonneLettreDejaDite!=false) || (mauvaiseLettreDejaDite!=false)){	
				
						System.err.println(" deja dans le mot ou déjà dit");
						erreur = erreur --;				

					}
						
					String r1 = caseSaisie.getText();
					lettreSaisie= r1.toUpperCase().charAt(0); // Eviter le conflit des minuscules et majuscules
					caseSaisie.setText(null);
								
					
					/* 1er cas
					 * La lettre n'est pas dans le mot initial (donc fausse) et n'a pas encore était dite
					 */
					difference=true; 
					bonneLettreDejaDite=false;
					mauvaiseLettreDejaDite=false;
					
					for (i=0;i<tentativesMax;i++){ 
					
						if (tabErreurs[i]==lettreSaisie){
							
							mauvaiseLettreDejaDite=true;
							i = i+1;
							erreur++;							
						}
					}
					
					
					/* 2eme cas
					 * 	La lettre fait parti du mot initial ou a dejà été dite
					 */
						for (i=1;i<tabMotAChercher.length-1;i++){//Si l'utilisateur a saisie une lettre qui est contenu dans le mot ou si la lettre saisie  se trouve dans le tableau des lettres fausses déjà saisie, alors difference devient faux. L'utilisateur a donc juste.
						
							if ((lettreSaisie==motInitial.charAt(i)) || (mauvaiseLettreDejaDite==true)){
								
								difference=false;
								score++;							
							}
						}

						
					
					for (i=1;i<tabMotAChercher.length-1;i++){
					
						if (lettreSaisie==tabMotAChercher[i]){
						
							bonneLettreDejaDite=true;
							score = score++;
						}
					
					}
						
					
				}	
			
			while((bonneLettreDejaDite!=false) || (mauvaiseLettreDejaDite!=false));
			
	
				
			//La boucle ci dessous vérifie si 
			//Une bonne lettre est saisie alors 
			//On enleve le tiret et on remplace par la lettre saisie 
						
			for (i=1;i<tabMotAChercher.length-1;i++){
				
				if (lettreSaisie==motInitial.charAt(i)){	
										
					tabMotAChercher[i]=lettreSaisie;//S'il ya correspondance
					tfCells[i].setText(lettreSaisie+"");
					bonneLettre=true;//Cela est donc une bonne lettre
					score = score--;
				}
				
					
			}
			
			if (bonneLettre!=true)	erreur=erreur+1;
			
			
			
			/* On va utiliser le switch pour l'affichage des image
			 * a chaque erreur
			 */
			
			if (erreur!=tentativesMax) {
	
				switch (erreur) {
				
				case 0 :  continue;

				case 1: img= new ImageIcon("erreur1.png");
						panSchema.setIcon(img);
						contentPanel.setVisible(true);
				    break;

				case 2: img= new ImageIcon("erreur2.png");
					  panSchema.setIcon(img);
					  contentPanel.setVisible(true);
					break;
					
		   	    case 3: img= new ImageIcon("erreur3.png");
					  panSchema.setIcon(img);
					  contentPanel.setVisible(true);
					break;
					
				  case 4:  img= new ImageIcon("erreur4.png");
					  panSchema.setIcon(img);
					  contentPanel.setVisible(true);
					break;
					
				  case 5: img= new ImageIcon("erreur5.png");
					   panSchema.setIcon(img);
					  contentPanel.setVisible(true);
					break;
					
				  case 6: img= new ImageIcon("erreur6.png");
					   panSchema.setIcon(img);
					  contentPanel.setVisible(true);
				    break;
				    
				  case 7: img= new ImageIcon("erreur7.png");
					   panSchema.setIcon(img);
					  contentPanel.setVisible(true);
					  break;
					  
				  case 8: img= new ImageIcon("erreur8.png");
					   panSchema.setIcon(img);
					   contentPanel.setVisible(true);
					   break;
					   
				  case 9: img= new ImageIcon("erreur9.png");
					   panSchema.setIcon(img);
					   contentPanel.setVisible(true);
					   break;

				  default:  JOptionPane.showMessageDialog(null, "ERREUR !");

				}

			
			//--------------------------------------
			//-------SAVOIR SI ON A GAGNER ---------
			// -------------------------------------
				
				
				for (i=1;i<tabMotAChercher.length-1;i++){

					if (tabMotAChercher[i]==motInitial.charAt(i)){ //On comptabilise le nombre de bonnes lettre trouvé.
						
						nbLettresBonnes=nbLettresBonnes+1;			
					}	
				
					if (nbLettresBonnes==motInitial.length()-2){ //lenght -2 car dejà 2 lettres affichées
						
						motTrouve=true;
						score = score * longueurMotAChercher;
						JOptionPane.showMessageDialog(null, "Felicitations ! Votre score est de " + score);
					}
				}

		
				if (motTrouve!=true){
					System.out.println("Vous avez trouve en tout "+nbLettresBonnes+" lettres."); //Si le mot n'est pas trouvé alors on affiche le nombre de lettre trouvé en tout
					
					//On reaffiche le nouveau mot
					
					for (i=0;i<tabMotAChercher.length;i++) System.out.print(tabMotAChercher[i]);

				}
			}
		}
		
		//PERDU -->	
		if (erreur==tentativesMax){ 
			img= new ImageIcon("pendu.png");
			panSchema.setIcon(img);
			contentPanel.setVisible(true);
			JOptionPane.showMessageDialog(null, "Perdu ! Le mot était : " +motInitial + ". Votre score est de " + score);
		
		}
		
	}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
	if(!caseSaisie.getText().equals(" ")&&!caseSaisie.getText().equals("")) {
	    
        SaisirLettre();
        caseSaisie.setText(" ");
    } 
	
	}

}
	

/*
public void init ()
{
	try {
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		connexion = DriverManager.getConnection("jdbc:hsqldb:file:AlgoDev", "sa", "");
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	Statement statement;
	
	// ---- ATTENTION ----
	/* La partie ci-dessous représente la création de BDD
	 * ETANT DEJA EXISTANTE ELLE RESTE EN COMMENTAIRE 
	 * Sinon il y aura des problèmes affichés dans le MAIN
	 */
	
	/*
	
	try {
		statement = connexion.createStatement();
		statement.executeUpdate("CREATE TABLE joueurs (nom VARCHAR(30), id INT, inscription BOOLEAN, nbparties INT)");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	shutdown();
	//getPlayer();
	
}

public void execute (String arret)
{
	try {
		Statement statement = connexion.createStatement();
		statement.executeQuery(arret);
		statement.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

// Le shutdown va sauvegarder les modifications dans la base de données

public void shutdown ()
{
	execute("SHUTDOWN");
	try {
		connexion.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


*/
