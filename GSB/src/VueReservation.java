import javax.swing.*;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.util.Properties;

// Classe VueReservation qui affiche l'objet à réserver à confirmer
public class VueReservation extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton emprunterButton;
	private JLabel welcome, idObjet, nomObjet;
	private JFrame frame;
	private JCheckBox confirm;
	private String login;
	
	// Constructeur qui prend en paramètre la fenêtre, le login du visiteur, l'id de l'objet et son nom
	public VueReservation(JFrame frame, String login, int id, String nom) {
		this.login = login;
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Réservation du produit");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(150, 40, 380, 100);
		
		/* texte informatif */
		idObjet = new JLabel("Id du produit à réserver : " + id);
		idObjet.setFont(new Font("Arial", Font.BOLD, 12));
		idObjet.setBounds(150, 70, 380, 100);
		
		/* texte informatif */
		nomObjet = new JLabel("Nom du produit à réserver : " + nom);
		nomObjet.setFont(new Font("Arial", Font.BOLD, 12));
		nomObjet.setBounds(150, 110, 380, 50);
			
		/* checkbox à cocher pour réserver le produit */
        confirm = new JCheckBox("confirmer");  
        confirm.setBounds(185, 160, 100, 50);  
		confirm.addActionListener(this);	
        
		/* bouton d'emprunt */
		emprunterButton = new JButton("Valider");
		emprunterButton.setBounds(160, 230, 150, 25);
		emprunterButton.setBackground(new Color(59, 89, 182));
		emprunterButton.setForeground(Color.WHITE);
		emprunterButton.setFocusPainted(false);
		emprunterButton.setFont(new Font("Arial", Font.BOLD, 12));
		emprunterButton.addActionListener(this);	

		/* ajoute les éléments à notre panel */
		this.add(welcome);
		this.add(idObjet);
		this.add(nomObjet);
				
		this.add(confirm);
		
		this.add(emprunterButton);
		
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
        String stringid = idObjet.getText().substring(27,28);
        int id = Integer.parseInt(stringid);
        /* condition pour vérifier que la checkbox est bien cochée et que le bouton réserver est coché */
    	if(confirm.isSelected() && e.getSource() == emprunterButton) {
    		/* on passe les variables en paramètre */
        	VueCalendrier calendrier = new VueCalendrier(frame,login,id);
        	//VueCalendrier reservation = new VueCalendrier(frame);
        	frame.setContentPane(calendrier);
        	frame.revalidate();
    	} else if (e.getSource() == emprunterButton && !confirm.isSelected()) {
    		nonSelect();
    	}
	}
}
