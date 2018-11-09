package le_pendu;

import java.util.*;
import java.util.function.LongUnaryOperator;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Le jeu du Pendu tout simplement.

class Pendu extends JFrame implements ActionListener {
	
	public static final int GRID_SIZE = 20; // grille

    public static final int CELL_SIZE = 40; // taille case en pixels
    public static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
    public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;

    public static final Color CLOSED_CELL_TEXT = Color.BLACK;
    public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

    JPanel contentPanel = new JPanel();
    JPanel panGrille;
    JPanel panDeSaisie;
    
    ArrayList <String> mot = new ArrayList<String>();
    
    JTextField [] tfCells = new JTextField [GRID_SIZE];
    JTextField caseSaisie = new JTextField ();
  
   
    List <String> listeMUtil = new ArrayList <String> ();
    List <String> listeMDico = new ArrayList <String> ();
	Boolean valideLettre = false; 
	
	Pendu () {
	listeMotsDico();
	listeMotsUtil ();
		afficheMot();
		affichage();
		setSize(new Dimension(CANVAS_WIDTH+100,CANVAS_HEIGHT+100));
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
       
        
        
        panDeSaisie.add(caseSaisie);
        contentPanel.add(panGrille);
        contentPanel.add(panDeSaisie);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        
        setTitle("Pendu");
        this.setSize(800, 800);
        setVisible(true);
   
        
	}
	public void affichage() {
		   panGrille = new JPanel();
		   panDeSaisie= new JPanel();
	       panGrille.setPreferredSize(new Dimension(800, 100));
	       panGrille.setLayout( new GridLayout(1, GRID_SIZE,2,2));
	       panDeSaisie.setLayout(new GridLayout(1,1));
	       panDeSaisie.setPreferredSize(new Dimension(200, 40));
	      
	        caseSaisie = new JTextField();
	        caseSaisie.setText(null);
	        caseSaisie.setEditable(true); //utilisateur peut rentrer qqc dedans
	        caseSaisie.setSize(200,40);
	        caseSaisie.setHorizontalAlignment(JTextField.CENTER);
	        caseSaisie.setFont(FONT_NUMBERS);
	        
	        caseSaisie.addKeyListener(	new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(KeyEvent.VK_ENTER==arg0.getKeyCode()) {
			valideLettre = true ; 
		}	
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
	
	
	public static int tentativesMax=10; //Le nombre d'erreur maximale toélérées
		
		
		//String tabMots[] = {"ablation","hypocrisie","interminable","revolution","niais","petit","piquet","blond","punk"};//Tableau contenant tous les mots a trouver.
		//Random rand=new Random(); //Déclaration de String motATrouverl'objet rand, qui servira a utiliser des nombre aléatoire.
				
	//	int nbAleatoire=rand.nextInt(tabMots.length); //Cette variable contient un nombre compris entre 0 et la valeur de la taille du tableau des mots a chercher.
	
	String motInitial; //On insère dans la variable motAdeviner le mots qui a été tiré au hasard
		int longueurMotAChercher;//motInitial.length(); //On stock dans cette variable la longueur du mot a Deviner.		
	char tabMotAChercher[];//= new char [longueurMotAChercher]; //On créer un tableau de caractère de même taille que la longueur du mot a chercher
		
		int score = 0;
		Scanner sc = new Scanner(System.in);
	/*	
		Random rand1 = new Random();
		int nb = rand.nextInt(386030); // Un entier est choisit au hasard // pour prendre un mot dans le dico
		String chosenWord = dico.[nb];
		int longueurMotAChercher = chosenWord.length(); // longueur du mot a //Deviner.
		char tabMotAChercher[] = new char[longueurMotAChercher];
		*/
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

		public void listeMotsUtil ()
		{

					Random r = new Random ();
					int ligne = r.nextInt(listeMDico.size()) +1 ;
					
		
					motInitial = listeMDico.get(ligne);
					longueurMotAChercher = motInitial.length();
					tabMotAChercher = new char [longueurMotAChercher];
				
					
					for (int i=0; i<longueurMotAChercher; i++) {
						
					tabMotAChercher[i] = motInitial.charAt(i);
			
					}	//System.out.println(tabMotAChercher);
				
		}

		
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
		
	
public void SaisirLettre () {
	
	int i;
	int nbLettresBonnes=0;
	int erreur=0; 
	//int k=0;//Permettra de décaler les cases du tableau de lettres fausse de 1 a chaque erreur.
	
	char lettreSaisie; 
	char tabErreurs[] = new char [tentativesMax];//Ce tableau contiendra toutes les lettre fausses saisie par l'utilisateur, sa taille est égal au nombre d'erreur.
	
	boolean motTrouve=false; //Nous permettra de savoir si le mot est trouve ou pas.
	boolean bonneLettre=false; //Nous permettra de savoir si l'utilisateur a entrée une lettre qui se trouve dans le mot ou non?
	
	boolean bonneLettreDejaDite=false; //Nous permettra de savoir si l'utilisateur a saisi une lettre identique, qui est déjà afficher a l'écran dans le mot.
	boolean difference;//Nous permettra de juger si la lettre que l'utilisateur a saisi est différente ou non du mot.
	boolean mauvaiseLettreDejaDite=false; //Nous permettra de savoir si l'utilisateur a saisi une lettre identique, qui n'est pas contenu dans le mot mais qui a été déjà saisie par l'utilisateur.	
	
	
	
		do{
			
			nbLettresBonnes=0; 
			bonneLettre=false; 
			
			
			
		
			
				do{ 
		/* Cas où la lettre à déjà été dite
		 * 	qu'elle soit fausse
		 * ou déjà affichée à l'écran 		
		 */
					if ((bonneLettreDejaDite!=false) || (mauvaiseLettreDejaDite!=false)){	
					
						System.out.println("");
						System.out.println("-------------------------------------");
						System.err.println(" deja dans le mot ou déjà dit");
						System.out.println("");
						System.out.print("RAPPEL: le mot a chercher est: ");
						
					
						for (i=0;i<tabMotAChercher.length;i++){ //On réaffiche le mot a chercher en cas de saisie de lettre déjà saisie.
						
							System.out.print(tabMotAChercher[i]);
							
					
						}
					}
						System.out.println("Quelle lettre desirez vous entrer? ");
						
						while(!valideLettre || caseSaisie.getText().equals(null)) {
							Thread.yield();
						}
						
			
			       // String r1 = sc.nextLine();	
						String r1 = caseSaisie.getText();
					lettreSaisie= r1.toUpperCase().charAt(0); //On demande a l'utilisateur de saisir une lettre, on converti cette lettre en minuscule grace a toLowerCase afin d'éviter tout ambiguité
					caseSaisie.setText(null);
					
					
					
					/* 1er cas
					 * La lettre n'est pas dans le mot initial et n'a pas encore était dite
					 */
					difference=true; 
					bonneLettreDejaDite=false;
					mauvaiseLettreDejaDite=false;
					
					for (i=0;i<tentativesMax;i++){ //Cette boucle parcours le tableau des lettres érronés, si la lettre saisie par l'utilisateur est déjà contenu dans le tableau alors on considère cette lettre saisie a été saisie auparavant.
					
						if (tabErreurs[i]==lettreSaisie){
							
							mauvaiseLettreDejaDite=true;
							
							i = i+1;
									
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
								
						/* 3eme cas
						 * La lettre saisie n'est pas dans le mot initial donc on la met dans notre tableau d'erreurs
						 */
											
					//	if (difference==true){ //Si difference est vrai cela signifie que l'utilisateur s'est trompé on met alors la lettre fausse dans le tabmeau des lettre fausse VerifSaisie.
								
							
						//	verifSaisie[k]=lettreSaisie;
							
					//		k=k+1;
									
					//	}
	
					
					for (i=1;i<tabMotAChercher.length-1;i++){//On regarde si la lettre saisie par l'utilisateur n'est pas déjà visible a l'écran.
					
						if (lettreSaisie==tabMotAChercher[i]){
						
							bonneLettreDejaDite=true;
							score++;
						}
					
					}
						
					
				}	
			
			while((bonneLettreDejaDite!=false) || (mauvaiseLettreDejaDite!=false));
			
				
		//	System.out.println("");
			
			
			//On utilise cette boucle pour vérifier si la lettre saisie correspond bien avec une des lettre dans le mot.
						
			for (i=1;i<tabMotAChercher.length-1;i++){
				
				
				if (lettreSaisie==motInitial.charAt(i)){
					
										
					tabMotAChercher[i]=lettreSaisie;//S'il ya correspondance alors on remplace le tirer par la lettre.
					tfCells[i].setText(lettreSaisie+"");
					bonneLettre=true;//Cela est donc une bonne lettre
					score--;
				}
				
					
			}
			
			if (bonneLettre!=true){ //Si l'utilisateur s'est trompé alors on lui ajoute une erreur
					
				erreur=erreur+1;
				System.out.println("Aucune lettre ne correspond a votre saisie il vous reste : "+(tentativesMax-erreur)+" Chances! ");
				System.out.println("");
			}
			
			
			if (erreur!=tentativesMax){
		
						
				for (i=1;i<tabMotAChercher.length-1;i++){
					
					
					
					if (tabMotAChercher[i]==motInitial.charAt(i)){ //On comptabilise le nombre de bonnes lettre trouvé.
						
						nbLettresBonnes=nbLettresBonnes+1;
						
						
					}	
				
					if (nbLettresBonnes==motInitial.length()-2){ //Si le nombre de bonnes lettres correspond au nombre de tirets dans le mots alors MotTrouve prends vrai l'utilisateur a gagner.
						
						motTrouve=true;
						
						System.out.println("Vous avez trouvez le mot feliciations!");
						System.out.println("");
						System.out.println("Le mot etait: "+motInitial);
						score = score * longueurMotAChercher;
						System.out.println("Votre score est de " + score);
						
						
					}
				
				}
			
			
				
				if (motTrouve!=true){
				
				
					System.out.println("Vous avez trouve en tout "+nbLettresBonnes+" lettres."); //Si le mot n'est pas trouvé alors on affiche le nombre de lettre trouvé en tout
					System.out.println("");
					System.out.print("Maintenant le mot devient: ");
					
					for (i=0;i<tabMotAChercher.length;i++){
						
					System.out.print(tabMotAChercher[i]); //On affiche le mot après que l'utilisateur est trouver une des lettres
							
					
					}
					
					System.out.println("");
				
				}
				System.out.println("-----------------------------------");
			}
		}
		
		while ((motTrouve==false) && (erreur!=tentativesMax))	; // On execute les blocs d'instruction du dessus tant que le joueur n'a pas perdu ou tant que le mot a trouver n'est pas trouvé.
		
		if (erreur==tentativesMax){ //Quand on atteint le nombre d'erreur max le joueur perds... 
			System.out.println("-----------------------------------");
			System.out.println("Perdu!");
			System.out.println("");
			System.out.println("Le mot etait: "+motInitial);
		
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



