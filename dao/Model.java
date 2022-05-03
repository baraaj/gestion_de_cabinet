
package dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

import IHM.Consult;
import OO.Consultation;
public class Model extends AbstractTableModel {
	ResultSetMetaData rsmd=null;
  
	ArrayList<Object[]> data=new ArrayList<Object[]>();
    public Model(ResultSet rs) {
		try {
			rsmd=rs.getMetaData();
			while(rs.next())
			{
				
				Object[]ligne=new Object[rsmd.getColumnCount()];
				for(int i=0;i<ligne.length;i++)
				{
					ligne[i]=rs.getObject(i+1);
				}	
				
					data.add(ligne);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object  getValueAt(int l, int c) {
		// TODO Auto-generated method stub
		return data.get(l)[c];
	}
	public void addElement(Consultation p) {
		ConsultDAO mp = new ConsultDAO();
		 Consultation a=mp.ajouter(p);
		 if(a!=null)
		 {System.out.println("ajout avec succes ");}
		 else {System.out.println("erreur d'ajout.");}
		  Object lignes[]= new Object[2];
		  lignes[0]=p.getDateC();
		  lignes[0]=p.getTypeC();
		  data.add(lignes);
		  this.fireTableDataChanged();
		  
		 }
	
	//rennomer
	/*public void setValueAt(Object val, int l, int c) {
		
		  ConsultDAO mp= new ConsultDAO();
	
		      int a = mp.Modifier(""+data.get(l)[c], ""+ val );
		      System.out.println(val);
		      System.out.println(l);
		      System.out.println(c);
			  if(a>0)
			  { data.get(l)[c]=val;
			  System.out.println("ajout avec succes ");
			   }
			  else{System.out.println("erreur d'ajout.");}
		  
		  
	     data.get(l)[c]=val;
		 this.fireTableDataChanged();
		}
	public void supprimer (int ligne) {
		PersonManager pm= new PersonManager();
		pm.Supprimer(""+data.get(ligne)[0]);
		data.remove(ligne);
	    this.fireTableDataChanged();//rafraishissement
	
	}*/
	/*public void supprimerall () {
		ConsultDAO pm= new ConsultDAO();
		int a=pm.SupprimerTous();
		data.clear();
	    this.fireTableDataChanged();//rafraishissement
	
	}*/
	
}



