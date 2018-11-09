package motmele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GrilleMotMele {
	public  ArrayList<String [][]> listGrilles = new ArrayList<String [][]>() ; 
//	public  ArrayList<ArrayList <String>> listMots = new ArrayList<ArrayList <String>>(); 
	public ArrayList<String> dico= new ArrayList<String >();
	public ArrayList<String> listMots = new ArrayList<String>();
	public int GRID_SIZE=0 ; 
	public String [][] grille ;
	public int tabPositions []= new int [2];
	public enum orientations  {DIAGONALE,DIAGONALE_INV,GAUCHE, DROITE  ,BAS, HAUT };
	GrilleMotMele(){
		String[][] grille1 =
			{{"F","A","Z","R","T","Y","U","J","I","O"},
			{"O","E","D","V","I","O","K","U","K","E"},
			{"O","D","A","W","I","Q","Z","D","R","S"},
			{"T","C","N","V","G","F","G","O","H","C"},
			{"B","A","S","K","E","T","Y","I","U","R"},
			{"A","N","E","I","P","E","S","Q","P","I"},
			{"L","A","M","A","D","N","L","A","U","M"},
			{"L","S","A","R","E","N","B","P","L","E"},
			{"X","P","D","F","D","I","S","A","A","Z"},
			{"G","O","L","F","Z","S","N","G","H","M"}};
		ArrayList<String> listMot1 = new ArrayList<String>();
		listMot1.add("FOOTBALL");
		listMot1.add("GOLF");
		listMot1.add("JUDO");
		listMot1.add("TENNIS");
		listMot1.add("DANSE");
		
		this.listGrilles.add(grille1);
		//this.listMots.add(listMot1);
	}
	public void Dico ()
	{
		String pathFichier = "D:\\black jack\\MotMêlé\\Dico.txt";
		
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
				dico.add(ligneLue);
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


public void getMots (int nbMots)
	{
		int j =0;
		
		for (int i=0; i<nbMots;i++)
		{
			while ( j <nbMots)
			{

				Random r = new Random ();
				
				int ligne = r.nextInt(dico.size()) +1 ;
				listMots.add((dico.get(ligne)));
				j++;
				
			}				
		}
		for(String p: listMots)
	       {
	       	System.out.println (p.toUpperCase());
	       }
		
	}
public int getNbLettres() {
	 int nbLettres =0 ;
	for(String s : listMots) {
	nbLettres=	s.length()+ nbLettres ; 
	}
	return nbLettres;
}
public void setTaille() {
	int taillePotentielle =(int)Math.sqrt(getNbLettres()) + getNbLettres()/20;
	if(motPlusGrand()> taillePotentielle) {
		GRID_SIZE = motPlusGrand()+2 ;
	}else GRID_SIZE = taillePotentielle;
	
}
public int motPlusGrand() {
	int maxLength= listMots.get(0).length() ;
	for(String s : listMots) {
		if(maxLength < s.length()) {
			maxLength = s.length();
		}
	}
	return maxLength;
}
public String renverseMot(String mot) {
	String motEnvers ="" ; 
	for(int i =mot.length()-1 ; i>=0 ; i--) {
	motEnvers = motEnvers.concat(mot.charAt(i)+"");
	}
	System.out.println(motEnvers);
	return motEnvers;
}
public boolean trouverPlaceDiag(String mot ) {
	boolean place = false ; 
	//int tabPositions [] = new int [1];
	int ligneInitiale=0, colonneInitiale=0 ,ligne = ligneInitiale , colonne =colonneInitiale ,indiceLettreMot=0; 
	while(mot.length()<GRID_SIZE-ligneInitiale && indiceLettreMot <mot.length()&& !place) {
		while (mot.length()<GRID_SIZE-colonneInitiale && indiceLettreMot <mot.length()&& !place) {
			while((grille[ligne][colonne]== null || grille[ligne][colonne].equals(mot.charAt(indiceLettreMot=0)+"")) && !place) {
				if(indiceLettreMot==mot.length()-1) {
					place=true ;
					tabPositions[0]=ligneInitiale;
					tabPositions[1]= colonneInitiale ;
				}
				else {
				ligne++ ; 
				colonne++ ; 
				indiceLettreMot ++ ;
				} 
			}
			colonneInitiale++ ; 
			colonne= colonneInitiale ;
			ligne = ligneInitiale;
			indiceLettreMot =0 ;
		}
		ligneInitiale ++ ;
		ligne=ligneInitiale;
		colonneInitiale=0 ; 
		colonne=colonneInitiale ;
	}
	
	return place;
}
public void setGrille() {
	
	grille = new String[GRID_SIZE][GRID_SIZE];
	for(String s : listMots) {
	Random r = new Random ();
	int indice = r.nextInt(5)  ;
	switch (indice) {
	case 0 : 
		if(trouverPlaceDiag(s)){
			
			int ligne = tabPositions[0];
			int colonne= tabPositions[1];
		for(int i = 0; i<s.length();i++) {	
			grille[ligne+i][colonne+i]= s.charAt(i)+"";
			}
		}else {
			while(!trouverPlaceDiag(s));
			GRID_SIZE++ ; 
			setGrille(); 
			System.out.println("merde");
		}
		break;
	case 1 : 
		//rajouter une condition
		String invMot = renverseMot(s);
		if(trouverPlaceDiag(invMot)){
			int ligne = tabPositions[0];
			int colonne= tabPositions[1];
		for(int i = 0; i<s.length();i++) {	
			grille[ligne+i][colonne+i]= s.charAt(i)+"";
			}
		}
		for(int i =0; i<s.length();i++) {	
			grille[i][i]= invMot.charAt(i)+"";
			}
		break;
	case 2 : 
		for(int i= 0;i<s.length();i++) {// a modifier 
			for(int j=0 ; j<s.length();j++) {
				grille[i][j]=s.charAt(j)+"";
			}
		}
		break;
	case 3 : 
		String invMot2 = renverseMot(s);

		for(int i= 0;i<s.length();i++) {// a modifier 
			for(int j=0 ; j<s.length();j++) {
				grille[i][j]=invMot2.charAt(j)+"";
			}
		}
		break; 
	case 4 :
		
		for(int i= 0;i<s.length();i++) {// a modifier 
			for(int j=0 ; j<s.length();j++) {
				grille[j][i]=s.charAt(j)+"";
			}
		}
		break; 
	case 5 :
		String invMot3 = renverseMot(s);
		
		for(int i= 0;i<s.length();i++) {// a modifier 
		for(int j=0 ; j<s.length();j++) {
			grille[j][i]=invMot3.charAt(j)+"";
		}
	}
		break ;
	
	}
}
	}
public void orientation () {
	
}
public static void main (String [] args) {
	GrilleMotMele gmm = new GrilleMotMele ();
	gmm.Dico();
	gmm.getMots(5);
	System.out.println(gmm.getNbLettres());
	gmm.setTaille();
	gmm.setGrille();
//	gmm.renverseMot(gmm.listMots.get(0));
}
	
	
}
