package le_pendu;

import java.util.*;
import java.io.*;


public class Pendu {


	
	public static int NB_ERREURS_MAX=10; //Le nombre d'erreur maximale toélérées
	
	//public static void main (String args[]){
		
		
		int i; //Une variable de boucle
		
	
		
		String tabMots[] = {"ablation","hypocrisie","interminable","revolution","feudiste","explosion","reliure","niais","petit","blond","punk"};//Tableau contenant tous les mots a trouver.
		
		Random rand=new Random(); //Déclaration de l'objet rand, qui servira a utiliser des nombre aléatoire.
				
		int nbAleatoire=rand.nextInt(tabMots.length); //Cette variable contient un nombre compris entre 0 et la valeur de la taille du tableau des mots a chercher.
		
		String motADeviner=tabMots[nbAleatoire]; //On insère dans la variable motAdeviner le mots qui a été tiré au hasard
		
		int longueurMotAChercher=motADeviner.length(); //On stock dans cette variable la longueur du mot a Deviner.
				
		char[] tabMotAChercher = new char [longueurMotAChercher];; //On créer un tableau de caractère de même taille que la longueur du mot a chercher
		
		//char tabMotAChercher[] =new char [longueurMotAChercher];
		
		char lettreSaisie; //Servira pour la saisi de la lettre
		
		boolean motTrouve=false; //Nous permettra de savoir si le mot est trouve ou pas.
		
		int nbLettresBonnes=0; //Le nombre de bonnes lettres trouvés.
		
		boolean bonneLettre=false; //Nous permettra de savoir si l'utilisateur a entrée une lettre qui se trouve dans le mot ou non?
		
		int erreur=0; //Le nombre d'erreur.
		
		boolean identique=false; //Nous permettra de savoir si l'utilisateur a saisi une lettre identique, qui est déjà afficher a l'écran dans le mot.
		
		String continuer=""; //Afin de faire des pauses dans le programme
		
		int k=0;//Permettra de décaler les cases du tableau de lettres fausse de 1 a chaque erreur.
		
		boolean difference;//Nous permettra de juger si la lettre que l'utilisateur a saisi est différente ou non du mot.
		
		boolean identique2=false; //Nous permettra de savoir si l'utilisateur a saisi une lettre identique, qui n'est pas contenu dans le mot mais qui a été déjà saisie par l'utilisateur.
		
		List <Character> listLF = new ArrayList <Character>();//Liste Lettre Fausse contiendra toutes les lettre fausses saisie par l'utilisateur, sa taille est égal au nombre d'erreur.
		
		//verifSaisie=new char [NB_ERREURS_MAX];
		
		Scanner sc = new Scanner(System.in);
		
		
		//On insère les différents lettres du mots a l'aide de cette boucle
		public void afficheMot() {
		
		System.out.print("Mot à deviner: ");
		
		
		for (i=0;i<tabMotAChercher.length;i++){
			
			if (i==0){ //Affichage de la première lettre
				tabMotAChercher[i]=motADeviner.charAt(i);
			}
			else if (i==tabMotAChercher.length-1){ //Affichage de la dernière lettre
				tabMotAChercher[i]=motADeviner.charAt(i);	
			}
			else{
				tabMotAChercher[i]='-'; //On cache le reste par des tirets
			}
			
		}
		for (i=0;i<tabMotAChercher.length;i++){
		System.out.print(tabMotAChercher[i]); //Affichage du mot à deviner
		}
		}
		
		public void SaisirLettre ()
		
		
		{
			nbLettresBonnes=0; 
			bonneLettre=false; 
			
			while (!motTrouve && erreur!=NB_ERREURS_MAX) {
				
			System.out.println("");
			System.out.println("Quelle lettre desirez vous entrer? ");
			
			String r1 = sc.nextLine();
			lettreSaisie= r1.toLowerCase().charAt(0);
			
			
			// 1er cas = Lettre bonne
			
			for (i=1;i<tabMotAChercher.length-1;i++){
				
				if (lettreSaisie==motADeviner.charAt(i))
				{	
					tabMotAChercher[i]=lettreSaisie;// On affiche la lettre 
					bonneLettre=true;
					nbLettresBonnes++;
				}
			}
			
			
			//MAUVAISE LETTRE
			
			for (i=1;i<tabMotAChercher.length-1;i++){
				 if (lettreSaisie!=motADeviner.charAt(i)){
					difference = true;
					bonneLettre = false;
							
					if (listLF.contains(lettreSaisie))
						{
							bonneLettre = true;
						}
						
						else listLF.add(lettreSaisie);
				
					
				 }
				 
			/*	 for(int m=0 ; m<longueurMotAChercher;m++) {
						System.out.print(tabMotAChercher[m]);
					}*/
				 

				
			}
			for(int m=0 ; m<longueurMotAChercher;m++) {
				System.out.print(tabMotAChercher[m]);
			}
			erreur++;
					
	}
		
			if (nbLettresBonnes==(motADeviner.length()-2))
			{
				motTrouve = true;
				System.out.println("Vous avez trouvez le mot feliciations!");
				System.out.println("");
				System.out.println("Le mot etait: "+motADeviner);
			}
			
			
			if (erreur==NB_ERREURS_MAX){ //Quand on atteint le nombre d'erreur max le joueur perds... 
				System.out.println("");
				
				System.out.println("Fin de partie vous avez perdu!");
				System.out.println("");
				System.out.println("Le mot etait: "+motADeviner);
			
			}
			
		} //Fin de saisie lettre
		
		
		}




