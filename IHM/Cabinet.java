package IHM;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Cabinet extends JFrame  {
	private JMenuBar menubar;
	private JMenuItem menu1;
	private JMenu menu2;
	private JMenuItem item1;
	private JMenuItem item2;
	private JDesktopPane desktop;
	private JMenuItem menu3;
	private JMenuItem item3;
	private JMenuItem item4;
	private JMenuItem item5;
	private JMenuItem item6;
	private JPanel pimg;
	private ImageIcon icone;
	private JLabel img;

	public Cabinet() {
		setTitle("cabinet");
		setVisible(true);
		this.setSize(700,500);
		this.setLayout(new BorderLayout());
		pimg=new JPanel();
		
	    icone = new ImageIcon("images/cabinet.jpg");
	    img=new JLabel(icone);
	    pimg.add(img);
	    this.add(pimg);
	    this.pack();
	    this.setResizable(false);
		menubar=new JMenuBar();
		
		 menu1=new JMenu("Fiche patient");
	     menu2=new JMenu("Consultation");
	     item2=new JMenuItem("Ordonnance");
	     item1=new JMenuItem("Ajouter Consultation");
	     menu2.add(item1);
	    
	    
	     item3=new JMenuItem("Ajout patient");
	     item4=new JMenuItem("Supprimer patient");
	     item6=new JMenuItem("Remplir consultation");
	     menu1.add(item3);
	     menu2.add(item6);
	     menu1.add(item4);
	     menu2.add(item2);
	    menubar.add(menu2);
	    menubar.add(menu1);
	    this.setJMenuBar(menubar);
	    item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			new SuppPatient();
			
			
				
			};
		});
	    item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Ordonnanc();
			}
		});
	    item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new FicheP();
			}	
		});
	    item6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 new RemplirCons();
			}
		});
item1.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new Consult();
		
	}
});}


	 
	public static void main(String[] args) {
		Cabinet C=new Cabinet();
		C.setVisible(true);
		
	}
}
