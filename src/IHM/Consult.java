package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import OO.Consultation;
import OO.Patient;
import dao.ConsultDAO;
import dao.Model;
import dao.PatientDAO;
public class Consult extends JFrame implements ActionListener,WindowListener,ItemListener {
	private JLabel labelNom;
	private JTextField textNom;
	private JLabel labelName;
	private JLabel labelPrenom;
	private JTextField textPrenom;
	private JLabel labelTel;
	private JTextField textTel;
	private JLabel labelAdresse;
	private JTextField textAdresse;
	private JButton btAjouter;
	private JButton btAnnuler;
	private JPanel dn;
	private Integer[] jour;
	private JComboBox<Integer> jour_list;
	private JLabel slash1;
	private Integer[] mois;
	private JLabel slash2;
	private Integer[] annee;
	private JComboBox<Integer> annee_list;
	private JLabel lb_dateNais;
	private JComboBox mois_list;
	private JLabel slash3;
	private JLabel slash4;
	private JLabel labelCode;
	private JTextField textC;
	private JLabel labelType;
	private JComboBox<String> textType;
	private JLabel labelR;
	private JTextArea textR;
	private JLabel labelCodeP;
	private JTextField textP;
	private JLabel lb_dateC;
	private JSplitPane js;
	private JTable jt;
	private DefaultListModel<Object>model;
	private Model tm;
	private JComboBox<String> co;
	 
	public Consult() {
		js=new JSplitPane();
		this.add(js);
		ConsultDAO cm=new ConsultDAO();
		tm=new Model(cm.afficherTout());
		jt=new JTable(tm);
		
		// Conception de la page
		this.setTitle("Ajouter une consultation");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter une consultation");
		labelName.setPreferredSize(new Dimension(200,100));
		labelName.setHorizontalAlignment(JLabel.CENTER);
		labelName.setVerticalAlignment(JLabel.CENTER);
		labelName.setForeground(Color.MAGENTA);
		labelName.setFont(new Font("Arial",Font.BOLD,30));
		this.add(labelName,BorderLayout.NORTH);	
		
		// Formulaire
		JPanel pAPr = new JPanel();
		pAPr.setLayout(new GridLayout(8,2,10,10));
		//pAPr.setBorder(new EmptyBorder(5,5,5,5));
		this.add(pAPr);
		//scrollpane elements
		js.setRightComponent(pAPr);
		js.setLeftComponent(jt);
		
		
		this.add(js);
		//code
		labelCode = new JLabel("Code Consultation");
		labelCode.setFont(new Font("Arial",Font.ITALIC,15));
		labelCode.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(labelCode);
	
		textC = new JTextField();
		textC.setPreferredSize(new Dimension(150,50));
		pAPr.add(textC,BorderLayout.CENTER);
		
		
	
		
		// Date de Consultation
		lb_dateC = new JLabel("Date de consultation");
		lb_dateC.setFont(new Font("Arial",Font.ITALIC,15));
		lb_dateC.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_dateC);
		
		dn = new JPanel();//Panel Date de consult ComboBoxes
		dn.setLayout(new GridLayout());
		
		jour = new Integer[31];//liste des jours
		for(int i=1;i<=31;i++)
			jour[i-1]=i;
		jour_list = new JComboBox<Integer>(jour);
		dn.add(jour_list);
		
		slash1 = new JLabel("/",JLabel.CENTER);
		slash1.setFont(new Font("Arial",Font.BOLD,20));
		dn.add(slash1);

		mois = new Integer[12];//liste des mois
		for(int i=1;i<=12;i++)
			mois[i-1]=i;
		mois_list = new JComboBox<>(mois);
		dn.add(mois_list);

		slash2 = new JLabel("/",JLabel.CENTER);
		slash2.setFont(new Font("Arial",Font.BOLD,20));
		dn.add(slash2);
		
		annee = new Integer [100];//liste des mois
		for(int i=Year.now().getValue()-60;i < Year.now().getValue()+1;i++)
			annee[i-(Year.now().getValue()-60)]=i;
		annee_list = new JComboBox<Integer>(annee);
		dn.add(annee_list);
		dn.setBounds(0,10,340,20);
		pAPr.add(dn);
		// type
				labelType = new JLabel("type");
				labelType.setFont(new Font("Arial",Font.ITALIC,15));
				labelType.setHorizontalAlignment(JLabel.CENTER);
				pAPr.add(labelType);
				
				textType = new JComboBox<String>();
				textType.setPreferredSize(new Dimension(150,50));
				pAPr.add(textType,BorderLayout.CENTER);
				textType.addItem("Consultation");
				textType.addItem("Controle");
				
				
				// Remarques
				labelR = new JLabel("Remarques");
				labelR.setFont(new Font("Arial",Font.ITALIC,15));
				labelR.setHorizontalAlignment(JLabel.CENTER);
				pAPr.add(labelR);
				
				textR = new JTextArea();
				textR.setPreferredSize(new Dimension(150,50));
				pAPr.add(textR,BorderLayout.CENTER);

				//code patient
				labelCodeP = new JLabel("Code patient");
				labelCodeP.setFont(new Font("Arial",Font.ITALIC,15));
				labelCodeP.setHorizontalAlignment(JLabel.CENTER);
				pAPr.add(labelCodeP);
				co = new JComboBox<String>();
				//remplir la liste à partir de la BD
			
				co.addItem("");
				
				pAPr.add(co);
		 
		// Les boutons
		btAjouter = new JButton("Ajouter");
		pAPr.add(btAjouter);
		btAjouter.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		this.addWindowListener(this);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		ConsultDAO cd = new ConsultDAO();
		ResultSet rs = cd.affichep();
		
		try {
			 
			while(rs.next()) {
				
				int id=rs.getInt(1);
			 
				co.addItemListener(this); 
				co.addItem(""+id+"");
			
		}} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		cd.fermerConnexion();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			int codec = Integer.parseInt(textC.getText());
			int j1 = Integer.parseInt(jour_list.getSelectedItem().toString());
			int m1 = Integer.parseInt(mois_list.getSelectedItem().toString());
			int a1 = Integer.parseInt(annee_list.getSelectedItem().toString());
			String dateC = a1+"-"+m1+"-"+j1;
			String typeC = (String) textType.getSelectedItem();
			String remarques = textR.getText();
			int codep = Integer.parseInt((String) co.getSelectedItem());
			
				Consultation c = new Consultation(codec,typeC,remarques,codep,dateC);
				ConsultDAO cd = new ConsultDAO();
          Consultation s = cd.ajouter(c);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Nouvelle consultation est ajouté avec succès");
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout du consultation");
				cd.fermerConnexion();
			}
		
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
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
		
	}}