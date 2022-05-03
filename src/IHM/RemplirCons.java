package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import OO.Consultation;
import dao.ConsultDAO;
import dao.Model;


public class RemplirCons extends JFrame implements ActionListener,WindowListener,ItemListener{
	private JLabel labelName;
	private JLabel labelCode;
	private JTextField textC;
	private JComboBox<String> idCons;
	private JLabel labelP;
	private JSplitPane js;
	private JLabel labelR;
	private JTextArea textR;
	private JButton btAnnuler;
	private JButton btAjout;
	private Model tm;
	private JTable jt;

	public RemplirCons() {
			// Conception de la page
			this.setTitle("Fiche Consultation");
			this.setSize(new Dimension(700,450));
			this.setBackground(Color.GRAY);
			this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
			this.setVisible(true);
			
			// Layout
			this.setLayout(new BorderLayout());
			
			// Titre
			labelName = new JLabel("Remplir une consultation");
			labelName.setPreferredSize(new Dimension(200,100));
			labelName.setHorizontalAlignment(JLabel.CENTER);
			labelName.setVerticalAlignment(JLabel.CENTER);
			labelName.setForeground(Color.MAGENTA);
			labelName.setFont(new Font("Arial",Font.BOLD,30));
			this.add(labelName,BorderLayout.NORTH);	
			
			// Formulaire
			JPanel pAPr = new JPanel();
			pAPr.setLayout(new GridLayout(6,2,20,10));
			//pAPr.setBorder(new EmptyBorder(5,5,5,5));
			this.add(pAPr);
			js=new JSplitPane();
			labelCode = new JLabel("Code Consultation");
			labelCode.setFont(new Font("Arial",Font.ITALIC,15));
			labelCode.setHorizontalAlignment(JLabel.CENTER);
			pAPr.add(labelCode);
			
			
			js.setRightComponent(pAPr);
			this.add(js);
			ConsultDAO cm=new ConsultDAO();
			 
			
			jt=new JTable(tm);
			tm=new Model(cm.afficherTout());
			jt=new JTable(tm);
			js.setLeftComponent(jt);
			idCons = new JComboBox<String>();
			//remplir la liste à partir de la BD
			  
			//idPatient.addItemListener(this);     
			pAPr.add(idCons);
			idCons.addItem("");
			//code
			labelP = new JLabel("Code Patient");
			labelP.setFont(new Font("Arial",Font.ITALIC,15));
			labelP.setHorizontalAlignment(JLabel.CENTER);
			pAPr.add(labelP);
			textC = new JTextField();
			textC.setPreferredSize(new Dimension(150,50));
			pAPr.add(textC,BorderLayout.CENTER);
			labelR = new JLabel("Remarques");
			labelR.setFont(new Font("Arial",Font.ITALIC,15));
			labelR.setHorizontalAlignment(JLabel.CENTER);
			pAPr.add(labelR);
			textR = new JTextArea();
			textR.setPreferredSize(new Dimension(150,50));
			pAPr.add(textR,BorderLayout.CENTER);
		//	js.setRightComponent(pAPr);
			// Les boutons
			btAjout = new JButton("Ajouter");
			pAPr.add(btAjout);
			btAjout.addActionListener(this);
			btAnnuler = new JButton("Annuler");
			pAPr.add(btAnnuler);
			//pAPr.add(btOrdonnance);
			btAjout.addActionListener(this);
			btAnnuler.addActionListener(this);
			
			this.addWindowListener(this);
			idCons.addItemListener(this);   
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjout) {
			String remarques = textR.getText();
			
			 
			int id = Integer.parseInt(idCons.getSelectedItem().toString());
				ConsultDAO cd = new ConsultDAO();
         int s = cd.modifier(id,remarques);
				if (s != 0) {
					JOptionPane.showMessageDialog(null, "Modification avec succès");
				}
				else
					JOptionPane.showMessageDialog(null, "Problème de modification");
				cd.fermerConnexion();
}
		
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		ConsultDAO cd = new ConsultDAO();
		ResultSet rs = cd.afficherTout();
		
		try {
			 
			while(rs.next()) {
	
				int id=rs.getInt(1);
			 
				
				idCons.addItem(""+id+"");
				idCons.addItemListener(this); 
		}} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cd.fermerConnexion();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		
		ConsultDAO cd = new ConsultDAO();
		int id = Integer.parseInt(idCons.getSelectedItem().toString());
		ResultSet rs = cd.rechercherId(id);
		try {
			if(rs.next()) {
				
		 
				textC.setText(Integer.toString(rs.getInt(4)));
				//textR.setText(rs.getString(3));
				id=rs.getInt(4);
				ConsultDAO cm=new ConsultDAO();
				tm=new Model(cm.affiche(id));
				 
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.fermerConnexion();
		
	}
	
}