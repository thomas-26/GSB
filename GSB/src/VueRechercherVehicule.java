import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Classe VueRechercherVehicule qui affiche la page de recherche pour un v�hicule
public class VueRechercherVehicule extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton rechercherButton;
	private JLabel welcome;
	private JTextField jtfNumero;
	private JFrame frame;

	// Constructeur VueRechercherVehicule qui prend en param�tre la fen�tre
	public VueRechercherVehicule(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Rechercher un v�hicule (num�ro)");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(130, 40, 380, 100);
		
		jtfNumero = new JTextField();
		jtfNumero.setFont(new Font("Arial", Font.BOLD, 14));
		jtfNumero.setBounds(215, 135, 30, 30);

		/* bouton de recherche */
		rechercherButton = new JButton("Rechercher");
		rechercherButton.setBounds(160, 230, 150, 25);
		rechercherButton.setBackground(new Color(59, 89, 182));
		rechercherButton.setForeground(Color.WHITE);
		rechercherButton.setFocusPainted(false);
		rechercherButton.setFont(new Font("Arial", Font.BOLD, 12));
		rechercherButton.addActionListener(this);		

		/* ajoute les �l�ments � notre panel */
		this.add(welcome);
		this.add(jtfNumero);
		this.add(rechercherButton);

		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this);
        this.frame.revalidate();
        if(!jtfNumero.getText().equals("")) {
		int numero =  Integer.parseInt(jtfNumero.getText());
			// si le num�ro est pr�sent dans les mat�riels
			if(Database.rechercherVehicule(numero)) {
				JOptionPane.showMessageDialog(rechercherButton, "Le v�hicule est pr�sent dans le catalogue !", "F�licitation", JOptionPane.INFORMATION_MESSAGE);
				VueAfficherRerchercheVehicule recherche = new VueAfficherRerchercheVehicule(frame,numero);
				frame.setContentPane(recherche);
	            frame.revalidate();	
			}
			// sinon
			else {
				JOptionPane.showMessageDialog(rechercherButton, "Le v�hicule n'existe pas dans le catalogue !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
        }
	}
}