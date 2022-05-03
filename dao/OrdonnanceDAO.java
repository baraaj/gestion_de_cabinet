package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import OO.Consultation;
import OO.Ordonnance;
import OO.Patient;
public class OrdonnanceDAO   {

		private Connection connexion = null;
		private Statement st = null;
		private PreparedStatement ps = null;
		
		public OrdonnanceDAO() {
			
			// Chargement du Driver
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			// Connexion
			try {
				String url = "jdbc:postgresql://localhost:5432/Cabinet";
				String user = "postgres";
				String passwd = "baraa";
				connexion = DriverManager.getConnection(url, user,passwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error"+e.getMessage());
			}
			
		}
		
	 
		public Ordonnance ajouter(Ordonnance element) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("INSERT INTO ordonnance VALUES (?,?,?)");
					ps.setInt(1, element.getCodeO());
					ps.setInt(2, element.getCodeC());
					ps.setString(3, element.getOrd());
					 
					
					 
					ps.executeUpdate();
					return element;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}
		public void fermerConnexion() {
			if(connexion != null) {
				try {
					connexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		

	}}

	 