import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 

public class VueStatistique extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnValider;
	private JLabel welcome;
	private JComboBox cb;
	private JFrame frame;
	private Date dateActuelle, dateDebut, dateFin;
	
	public VueStatistique(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Statistiques");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(150, 0, 380, 100);
		
		/* JComboBox */
		String statistiques[]={"Nombre d'objets","Nombre objets empruntés","Nombre matériels empruntés triés","Nombre d'emprunt par visiteur"};        
		cb = new JComboBox(statistiques);    
		cb.setBounds(150, 100,200,20);    
        
		/* bouton d'emprunt */
		btnValider = new JButton("Valider");
		btnValider.setBounds(160, 230, 150, 25);
		btnValider.setBackground(new Color(59, 89, 182));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFocusPainted(false);
		btnValider.setFont(new Font("Arial", Font.BOLD, 12));
		btnValider.addActionListener(this);	

		this.add(welcome);
		this.add(cb);
		this.add(btnValider);

		frame.setVisible(true);
			
	}
	
	public void nonSelect() {
        JOptionPane.showMessageDialog(this, "Vous devez cocher la case", "Erreur", JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
	}
	
	public void selected() {
    	JOptionPane.showMessageDialog(this, "Produit réservé !");
        repaint();
        revalidate();
	}
	

	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this);
	    this.frame.revalidate();      
	    if (btnValider.equals(e.getSource())) {
	        String data = "" + cb.getItemAt(cb.getSelectedIndex());  
	        int index = cb.getSelectedIndex(); 
	        JOptionPane d = new JOptionPane();
	        switch (index) {
	        	case 0:
	        		d.showMessageDialog( this.frame, data + " : " + Database.getNbObjets());
	        		break;
	        	case 1:
		        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        	Date dateString = new Date();
		        	String dateActuelle = dateFormat.format(dateString);
		        	
		        	int compteur = 0;

		            for (int i = 0; i < Database.getLesDatesEmprunts().size(); i++) {
		                dateDebut = Database.getLesDatesEmprunts().get(i).getDateDebut();
		                dateFin = Database.getLesDatesEmprunts().get(i).getDateFin();   

		                try {
		                	 String sDate1="2020-12-08";  
				             Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
				             if(date1.before(dateFin) && date1.after(dateDebut)) {
				                	compteur += 1;
				             }
		                } catch (java.text.ParseException e2) {
		                    // TODO Auto-generated catch block
		                    e2.printStackTrace();
		                }		                
		                
		            }
		        	
	        		d.showMessageDialog(this.frame, data + " : " + compteur);
	        		break;
	        	case 2:
	        		/* on passe les variables en paramètre */
                	VueStatMaterielTrie materiel = new VueStatMaterielTrie(frame);
                	this.frame.setContentPane(materiel);
	        	    this.frame.revalidate();
	        		break;
	        	case 3:
	        		/* on passe les variables en paramètre */
                	VueEmpruntVisiteur empruntVisiteur = new VueEmpruntVisiteur(frame);
                	this.frame.setContentPane(empruntVisiteur);
	        	    this.frame.revalidate();
	        		break;
	        }
	    }
	}
}