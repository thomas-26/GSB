import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueRechercherObjet extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton rechercherButton;
	private JLabel welcome;
	private JTextField jtfNumero;
	private JFrame frame;

	public VueRechercherObjet(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Rechercher un objet (numéro)");
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

		/* ajoute les éléments à notre panel */
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
			if(Database.rechercherObjet(numero)) {
				JOptionPane.showMessageDialog(rechercherButton, "Le matériel est présent dans le catalogue !", "Félicitation", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(rechercherButton, "Le matériel n'existe pas dans le catalogue !", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
        }
	}
}