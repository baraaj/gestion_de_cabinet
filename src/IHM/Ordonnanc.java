package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import dao.ConsultDAO;
import dao.Model;
import dao.PatientDAO;
import OO.Ordonnance;
import dao.OrdonnanceDAO;
public class Ordonnanc extends JFrame implements ActionListener,WindowListener,ItemListener {
	private JLabel labelName;
	private JLabel labelCode;
	private JTextField textC;
	private JComboBox<String> idOrd;
	private JLabel labelC;
	private JLabel labelM;
	private JComboBox<String> idMed;
	private JLabel labelN;
	private JCheckBox nbp;
 
	private JLabel labelI;
	private JTextArea textI;
	private JCheckBox nbp1;
	private JCheckBox nbp2;
	private JCheckBox nbp3;
	private JCheckBox nbp4;
	private JPanel p;
	private JButton btAjout;
	private JButton btAnnuler;
	private JSplitPane js;
	private Model tm;
	private JTable jt;
	private JScrollPane scr;
	private JLabel labelm;
	private JScrollPane sc;
	private JComboBox<String> n;
	private JTextField textnb;
	private JList<String> lst;

	 
	Ordonnanc(){
		
		ConsultDAO cm=new ConsultDAO();
		tm=new Model(cm.afficherTout());
		jt=new JTable(tm);
		
		this.setTitle("Ordonnance");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter une Ordonnance");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(Color.MAGENTA);
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(8,2,20,10));
		//pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		labelCode = new JLabel("Code");
		labelCode.setFont(new Font("Arial",Font.ITALIC,15));
		labelCode.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelCode);
		//code
		textC = new JTextField();
		textC.setPreferredSize(new Dimension(150,50));
		pAPr.add(textC,BorderLayout.CENTER);
		labelC = new JLabel("Code Consultation");
		labelC.setFont(new Font("Arial",Font.ITALIC,15));
		labelC.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelC);
		idOrd = new JComboBox<String>();
		//remplir la liste à partir de la BD
		  
		//idPatient.addItemListener(this);     
		pAPr.add(idOrd);
		idOrd.addItem("");
		this.addWindowListener(this);
		labelM = new JLabel("Pharmacie");
		labelM.setFont(new Font("Arial",Font.ITALIC,15));
		labelM.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelM);
		idMed = new JComboBox<String>();
		idMed.addItem("SERESTA");
		idMed.addItem("LOVENOX");
		idMed.addItem("LAROXYL");
		idMed.addItem("FUCIDINE");
		idMed.addItem("KARDEGIC");
		idMed.addItem("PRIMPERAN");
		idMed.addItem("ELIQUIS");
		idMed.addItem("	ACUPAN");
		idMed.addItem("	ATARAX");
		idMed.addItem("DOLIPRANE");
		pAPr.add(idMed);
		 
		textnb=new JTextField();
		textnb.setPreferredSize(new Dimension(150,50));
	 	//pAPr.add(textnb,BorderLayout.CENTER);
			labelI= new JLabel("Indication");
			labelI.setFont(new Font("Arial",Font.ITALIC,15));
			labelI.setHorizontalAlignment(JLabel.CENTER);
			pAPr.add(labelI);
			textI=new JTextArea(5,3);
			scr=new JScrollPane(textI);
			textI.setPreferredSize(new Dimension(150,50));
		 	pAPr.add(scr,BorderLayout.CENTER);
		 	labelm= new JLabel("Ordonnance");
			labelm.setFont(new Font("Arial",Font.ITALIC,15));
			labelm.setHorizontalAlignment(JLabel.CENTER);
			pAPr.add(labelm);
			 
			 
			DefaultListModel<String> l=new DefaultListModel<String>();
			 lst=new JList<String>(l);
			l.addElement("");
			sc=new JScrollPane(lst);
			
			 
		 
			pAPr.add(sc);
			idMed.addMouseListener(new MouseListener() {
				
				private String elt;

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
             
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
					 
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					elt=idMed.getSelectedItem().toString();
					 
					 l.addElement(elt+" "+textI.getText());
					 idMed.enable();
				}
			});
	
			 
			// Les boutons
						btAjout = new JButton("Ajouter");
						pAPr.add(btAjout);
						btAjout.addActionListener(this);
						btAnnuler = new JButton("Annuler");
						pAPr.add(btAnnuler);
						 
						//pAPr.add(btOrdonnance);
						btAjout.addActionListener(this);
						btAnnuler.addActionListener(this);
						js=new JSplitPane();
						this.add(js);
						js.setRightComponent(pAPr);
						js.setLeftComponent(jt);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	 if (e.getSource() == btAjout) {
		 	int code = Integer.parseInt(textC.getText());
		 	int codec=Integer.parseInt(idOrd.getSelectedItem().toString());
			 String ord =lst.getSelectedValuesList().toString();
			 
				 
				 Ordonnance Ord = new OO.Ordonnance(code, codec, ord);
				OrdonnanceDAO cd = new OrdonnanceDAO();
			 Ordonnance s = cd.ajouter(Ord);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Nouvelle ordonnance est ajoutée avec succès");
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout d'ordonnance");
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
			 
				idOrd.addItemListener(this); 
				idOrd.addItem(""+id+"");
			
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
		// TODO Auto-generated method stub
		
	}
	

}
