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
import java.time.Year;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.PatientDAO;
import OO.Patient;

	public class SuppPatient extends JFrame implements ActionListener,ItemListener,WindowListener {
	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelName;
	private JLabel labelPrenom;
	private JTextField textPrenom;
	private JLabel labelTel;
	private JTextField textTel;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JButton btAnnuler;
	private JButton btSupprimer;
	private JLabel labelId;
	private JTextField textDateNais;
	private JLabel labelDateNais;
	private JComboBox<String> idPatient;

	public SuppPatient() {
		
		// Conception de la page
		this.setTitle("Supprimer un patient");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Supprimer un patient");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(Color.MAGENTA);
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(8,2,20,10));
		pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		
		// Identifiant
		labelId = new JLabel("code patient");
		labelId.setFont(new Font("Arial",Font.ITALIC,15));
		labelId.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelId);
				
		idPatient = new JComboBox<String>();
		//remplir la liste à partir de la BD
	  
		//idPatient.addItemListener(this);     
		pAPr.add(idPatient);
		idPatient.addItem("");
		
		// Nom
		labelNom = new JLabel("Nom");
		labelNom.setFont(new Font("Arial",Font.ITALIC,15));
		labelNom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelNom);
		
		textNom = new JTextField();
		textNom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textNom,BorderLayout.CENTER);
		
		// Prénom
		labelPrenom = new JLabel("Prénom");
		labelPrenom.setFont(new Font("Arial",Font.ITALIC,15));
		labelPrenom.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelPrenom);
		
		textPrenom = new JTextField();
		textPrenom.setPreferredSize(new Dimension(150,50));
		pAPr.add(textPrenom,BorderLayout.CENTER);
		
		
		
		// Adresse
		labelAdresse = new JLabel("Adresse");
		labelAdresse.setFont(new Font("Arial",Font.ITALIC,15));
		labelAdresse.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelAdresse);
		textAdresse = new JTextField();
		textAdresse.setPreferredSize(new Dimension(150,50));
		pAPr.add(textAdresse,BorderLayout.CENTER);
		// Téléphone
				labelTel = new JLabel("Numéro de téléphone");
				labelTel.setFont(new Font("Arial",Font.ITALIC,15));
				labelTel.setHorizontalAlignment(JLabel.CENTER);
				pAPr.add(labelTel);
				
				textTel = new JTextField();
				textTel.setPreferredSize(new Dimension(150,50));
				pAPr.add(textTel,BorderLayout.CENTER);
		
		
		// Date de Naissance
		labelDateNais = new JLabel("Date de naissance");
		labelDateNais.setFont(new Font("Arial",Font.ITALIC,15));
		labelDateNais.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelDateNais);
		
		textDateNais = new JTextField();
		textDateNais.setPreferredSize(new Dimension(150,50));
		pAPr.add(textDateNais,BorderLayout.CENTER);
		
		
		// Les boutons
		btSupprimer = new JButton("Supprimer");
		pAPr.add(btSupprimer);
		btSupprimer.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
		this.addWindowListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btSupprimer) {
			
			int id = Integer.parseInt(idPatient.getSelectedItem().toString());
		String nom = textNom.getText();
		String prenom = textPrenom.getText();
		int tel = Integer.parseInt(textTel.getText());
		String adresse = textAdresse.getText();
			//String j1 = textDateNais.getText().substring(8,10);
			//String m1 = textDateNais.getText().substring(5,7);
			//String a1 = textDateNais.getText().substring(0,4);
			//String d1 = a1+"-"+m1+"-"+j1;
			String d1=textDateNais.getText();
		Patient p = new Patient(id, nom, prenom, adresse, tel, d1);
			
			PatientDAO cd = new PatientDAO();
			Patient s = cd.supprimer(p);
			if (s != null) {
				JOptionPane.showMessageDialog(null, "Patient est supprimé avec succès");	
			}
			else
				JOptionPane.showMessageDialog(null, "Problème de suppression de patient");
			
				cd.fermerConnexion();
		}
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}

	@Override
public void windowOpened(WindowEvent e) {

		PatientDAO cd = new PatientDAO();
		ResultSet rs = cd.afficherTout();
		
		try {
			 
			while(rs.next()) {
				
				int id=rs.getInt(1);
			 
				
				idPatient.addItem(""+id+"");
				idPatient.addItemListener(this); 
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
		
		PatientDAO cd = new PatientDAO();
		int id = Integer.parseInt(idPatient.getSelectedItem().toString());
		ResultSet rs = cd.rechercherId(id);
		try {
			if(rs.next()) {
				
				textNom.setText(rs.getString(2));
				textPrenom.setText(rs.getString(3));
				textTel.setText(Integer.toString(rs.getInt(5)));
				textAdresse.setText(rs.getString(4));
				textDateNais.setText(rs.getString(6));
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		cd.fermerConnexion();
		
	}
	
}

