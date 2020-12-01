import javax.swing.*;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueStatistique extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnValider;
	private JLabel welcome;
	private JComboBox cb;
	private JFrame frame;
	
	public VueStatistique(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Statistiques");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(150, 0, 380, 100);
		
		/* JComboBox */
		String statistiques[]={"Nombre d'objets","Nombre objets empruntés","Nombre matériels empruntés triés"};        
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
	        		d.showMessageDialog( this.frame, data + " : " + Database.getNbObjetsEmpruntes());
	        		break;
	        	case 2:
	        		//d.showMessageDialog( this.frame, data + " : " + Database.getNbEmpruntsParVisiteur());
	        		/* on passe les variables en paramètre */
                	VueStatMaterielTrie materiel = new VueStatMaterielTrie(frame);
	        		break;
	        }
	    }
	}
}
