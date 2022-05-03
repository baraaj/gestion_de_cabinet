package IHM;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import OO.Patient;
import dao.PatientDAO;
public class FicheP extends JFrame implements ActionListener {
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
	
	public FicheP() {
		// Conception de la page
		this.setTitle("Ajouter un patient");
		this.setSize(new Dimension(700,450));
		this.setBackground(Color.GRAY);
		this.setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		this.setVisible(true);
		
		// Layout
		this.setLayout(new BorderLayout());
		
		// Titre
		labelName = new JLabel("Ajouter un patient");
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
		lb_dateNais = new JLabel("Date de naissance");
		lb_dateNais.setFont(new Font("Arial",Font.ITALIC,15));
		lb_dateNais.setHorizontalAlignment(JLabel.CENTER);
		pAPr.add(lb_dateNais);
		
		dn = new JPanel();//Panel Date de naissance ComboBoxes
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
		for(int i=Year.now().getValue()-60;i < Year.now().getValue();i++)
			annee[i-(Year.now().getValue()-60)]=i;
		annee_list = new JComboBox<Integer>(annee);
		dn.add(annee_list);
		dn.setBounds(0,10,340,20);
		pAPr.add(dn);
		
		 
		// Les boutons
		btAjouter = new JButton("Ajouter");
		pAPr.add(btAjouter);
		btAjouter.addActionListener(this);
		btAnnuler = new JButton("Annuler");
		pAPr.add(btAnnuler);
		btAnnuler.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btAjouter) {
			int code = Integer.parseInt(textC.getText());
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			int j1 = Integer.parseInt(jour_list.getSelectedItem().toString());
			int m1 = Integer.parseInt(mois_list.getSelectedItem().toString());
			int a1 = Integer.parseInt(annee_list.getSelectedItem().toString());
			String dateNaissance = a1+"-"+m1+"-"+j1;
			String adresse = textAdresse.getText();
		
			
				int tel =Integer.parseInt(textTel.getText());
				Patient c = new Patient(code,nom,prenom,adresse,tel,dateNaissance);
				PatientDAO cd = new PatientDAO();
				Patient s = cd.ajouter(c);
				if (s != null) {
					JOptionPane.showMessageDialog(null, "Nouveau Patient est ajouté avec succès");
				}
				else
					JOptionPane.showMessageDialog(null, "Problème d’ajout du Patient");
				cd.fermerConnexion();
			}
		
		else if (e.getSource() == btAnnuler) {
			this.dispose();
		}
	}
	
}
