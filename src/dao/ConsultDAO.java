package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import OO.Consultation;
import OO.Patient;
public class ConsultDAO   {

		private Connection connexion = null;
		private Statement st = null;
		private PreparedStatement ps = null;
		
		public ConsultDAO() {
			
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
		
	 
		public Consultation ajouter(Consultation element) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("INSERT INTO consultation VALUES (?,?,?,?,?)");
					ps.setInt(1, element.getCodec());
					ps.setString(2, element.getTypeC());
					ps.setString(3, element.getRemarques());
					ps.setInt(4, element.getCodep());
					ps.setString(5, element.getDateC());
					ps.executeUpdate();
					return element;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}

	 
 public int  modifier(int code,String rq) {
			if(connexion != null) {
				try {
					ps = connexion.prepareStatement("UPDATE consultation SET  remarques= ?  WHERE consultation.codeC=?"); 
					ps.setString(1, rq);
					ps.setInt(2,code);
					ps.executeUpdate();
					connexion.close();
					return 1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return 0;
		}

	 public ResultSet rechercherId(int code) {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("SELECT * FROM consultation WHERE codec ="+code);
					return rs;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}
	 public ResultSet affiche(int id) {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("SELECT * FROM consultation where codep="+id);
					
					return rs;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Error"+e.getMessage());
				}
			}
			return null;
		}
	 public ResultSet affichep() {
			if(connexion != null) {
				try {
					st = connexion.createStatement(); 
					ResultSet rs = st.executeQuery("SELECT * FROM patient ");
					
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
					ResultSet rs = st.executeQuery("SELECT * FROM consultation");
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
					ResultSet rs = st.executeQuery("select * from consultation");
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
