package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import OO.Patient;
public class PatientDAO   {

		private Connection connexion = null;
		private Statement st = null;
		private PreparedStatement ps = null;
		
		public PatientDAO() {
			
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
		
	 
		public Patient ajouter(Patient element) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("INSERT INTO patient VALUES (?,?,?,?,?,?)");
					ps.setInt(1, element.getCode());
					ps.setString(2, element.getNom());
					ps.setString(3, element.getPrenom());
					ps.setString(4, element.getAdresse());
					ps.setInt(5, element.getTel());
					ps.setString(6, element.getDate());
					ps.executeUpdate();
					return element;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}

	 
		public Patient supprimer(Patient element) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("DELETE FROM patient WHERE code="+element.getCode()); 
				 
					ps.executeUpdate();
					connexion.close();
					return element;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}

		//@Override
		/*public Employe modifier(Employe element) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("UPDATE employe SET idEmploye = "+element.getIdEmploye()+", nom ='"+element.getNom()+"', prenom ='"+element.getPrenom()+"', tel = "+element.getTel()+", adresse = '"+element.getAdresse()+"' , salaire = "+element.getSalaire()+" , dateEmbauche = '"+element.getDateEmbauche()+"' , dateNaissance = '"+element.getDateNaissance()+"' WHERE `idEmploye` = "+element.getIdEmploye()); 
					ps.executeUpdate();
					connexion.close();
					return element;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}*/

		public ResultSet rechercherId(int code) {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("SELECT * FROM patient WHERE code = "+code);
					return rs;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}

		public ResultSet afficherTout() {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("SELECT * FROM patient");
					return rs;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}
		
		public ResultSetMetaData colonne() {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("select * from patient");
					ResultSetMetaData rsmd = rs.getMetaData();
					return rsmd;
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
			
		

	}
}
