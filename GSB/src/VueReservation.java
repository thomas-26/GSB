import javax.swing.*;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueReservation extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton emprunterButton;
	private JLabel welcome, idObjet, nomObjet;
	private JFrame frame;
	private JCheckBox confirm;
	
	public VueReservation(JFrame frame, int id, String nom) {
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
		emprunterButton = new JButton("Emprunter");
		emprunterButton.setBounds(160, 230, 150, 25);
		emprunterButton.setBackground(new Color(59, 89, 182));
		emprunterButton.setForeground(Color.WHITE);
		emprunterButton.setFocusPainted(false);
		emprunterButton.setFont(new Font("Arial", Font.BOLD, 12));
		emprunterButton.addActionListener(this);	
		
		/*JPanel jPanel = new JPanel();
		jPanel.setBounds(50,50,500,500);
		
		JDatePicker picker = new JDateComponentFactory().createJDatePicker();
	    picker.setTextEditable(true);
	    picker.setShowYearButtons(true);
	    jPanel.add((JComponent) picker);
	    
	    picker.getModel().setYear(2010);
	    picker.getModel().setMonth(1);
	    //picker.getModel().setMonth(1);
	    picker.getModel().setDay(15);
	    picker.getModel().setSelected(true);*/

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
    		Database.reserverObjet(id);
    	} else if (e.getSource() == emprunterButton && !confirm.isSelected()) {
    		nonSelect();
    	}
	}
}
