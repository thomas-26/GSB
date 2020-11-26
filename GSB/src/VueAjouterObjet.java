import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueAjouterObjet extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText;
	private JButton ajouterButton;
	private JLabel idLabel, nomLabel, welcome, etatLabel;
	private JFrame frame;

	public VueAjouterObjet(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);

		/* texte informatif */
		welcome = new JLabel("Ajout d'un objet");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(90, 40, 380, 100);
		
		idLabel = new JLabel("Login");
		idLabel.setBounds(115, 200, 80, 25);
			
		/* champ de saisi de l'id */
		idText = new JTextField(20);
		idText.setBounds(220, 200, 160, 25);
		
		nomLabel = new JLabel("Mot de passe");
		nomLabel.setBounds(115, 230, 100, 25);
		
		etatLabel = new JLabel("Mot de passe");
		etatLabel.setBounds(115, 260, 100, 25);
		
		/* champ de saisi du mot de passe */

		/* bouton de connexion */
		ajouterButton = new JButton("Se connecter");
		ajouterButton.setBounds(190, 270, 150, 25);
		ajouterButton.setBounds(175, 270, 150, 25);
		ajouterButton.setBackground(new Color(59, 89, 182));
		ajouterButton.setForeground(Color.WHITE);
		ajouterButton.setFocusPainted(false);
		ajouterButton.setFont(new Font("Arial", Font.BOLD, 12));
		ajouterButton.addActionListener(this);
		
		/* la touche Entrée éxécute l'action du bouton */
		frame.getRootPane().setDefaultButton(ajouterButton);
		
		/* ajoute les éléments à notre panel */
		this.add(welcome);
		this.add(idLabel);
		this.add(idText);
		this.add(nomLabel);
		this.add(etatLabel);
		
		this.add(ajouterButton);

		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this);
        this.frame.revalidate();
		/*
		 * appelle notre fonction de connexion à la base de données et affiche notre
		 * menu si la connexion a bien été effectuée
		 */
		if (e.getSource() == ajouterButton) {
			
		}
	}
}