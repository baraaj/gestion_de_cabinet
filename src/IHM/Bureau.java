package IHM;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Bureau extends JFrame implements ActionListener{
		private JButton bt1;
		private JButton bt2;
		private JLabel labelName;
		private JPanel p;
		private JPanel s;
		private JPanel r;
		private JPanel pimg;
		private Object cabinet;
		private ImageIcon icone;
		private JLabel img;
		 
		public Bureau() {
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setSize(700,500);
			this.setTitle("User");
			this.setVisible(true);
			this.setLayout(new BorderLayout());
			  
			p=new JPanel();
			r=new JPanel();
			p.setLayout(new FlowLayout());
			
			labelName = new JLabel("Bienvenue");
			labelName.setPreferredSize(new Dimension(500,100));
			labelName.setHorizontalAlignment(JLabel.CENTER);
			labelName.setVerticalAlignment(JLabel.CENTER);
			labelName.setForeground(Color.MAGENTA);
			labelName.setFont(new Font("Arial",Font.BOLD,50));
			this.add(labelName,BorderLayout.NORTH);	
			 bt1 = new JButton("Medecin");
			 bt2= new JButton("Sécrétaire");
			 bt1.setPreferredSize(new Dimension(250, 100));
			 bt2.setPreferredSize(new Dimension(250, 100));
			 r.add(labelName);
			p.add(bt1);
			 
			 p.add(bt2);
			
			 s=new JPanel();
			//s.setBackground(Color.blue);
			 icone = new ImageIcon("images/user.jpg");
			    img=new JLabel(icone);
			    s.add(img,BorderLayout.CENTER);
			   // this.add(s);
			  // this.pack();
			    
			this.add(p,BorderLayout.SOUTH);
			 this.add(r,BorderLayout.NORTH);
			 this.add(s,BorderLayout.CENTER);
			 bt1.addActionListener(this);
			 bt2.addActionListener(this);
			 
			 
			//this.setResizable(false);
			
		}
	public static void main(String[] args) {
		Bureau b=new Bureau() ;
		b.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt1) {
			this.dispose();
			new Cabinet();
			
		}
		else if(e.getSource()==bt2) {
			this.dispose();
			new Secretaire();
			
			
		}
	}
	}
