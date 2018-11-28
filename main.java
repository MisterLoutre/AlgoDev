package finalAlgoDev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class main {
	
	public static Connection connecterDB() {
		try{
		 Class.forName("com.mysql.jdbc.Driver");
		 System.out.println("Driver ok");
		 String url= "";
		 String user = "root";
		 String password="";
		 Connection cnx = DriverManager.getConnection(url,user,password);
		 System.out.println("Connexion bien établie");
		 return cnx;
		}catch(Exception e){
		e.printStackTrace();
		return null;
		}
		}
	
public static void main (String args[]){
	
		//Menu menu = new Menu ();
	/*
	JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();

    String nom = jop.showInputDialog(null, "Veuillez entrez votre pseudo !", "Le Pendu - Connexion du joueur", JOptionPane.QUESTION_MESSAGE);

    jop2.showMessageDialog(null, "Bienvenue " + nom + ", appuyez sur ok pour relever le défi !", "Le Pendu", JOptionPane.INFORMATION_MESSAGE);
    
    
	Connection cnx = connecterDB();
	
	Statement st = null;
	  ResultSet rst = null;

	  try {
		st = cnx.createStatement ();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  try {
		rst=st.executeQuery("SELECT * FROM <nom BDD");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  try {
		while (rst.next()){
		  System.out.print(rst.getInt("id" + "\t"));
		  System.out.print(rst.getString("nom" + "\t"));
		  System.out.print(rst.getDouble("score" + "\t"));
		  System.out.println();
		  */
		  Pendu p = new Pendu ();
		/*	
		//	p.afficheMot();
			//p.SaisirLettre();
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/

}}