import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueConnexion extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userText;
	private JButton loginButton;
	private JLabel userLabel, passwordLabel, welcome;
	private JPasswordField passwordText;
	private JFrame frame;

	public VueConnexion(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);

		/* texte informatif */
		welcome = new JLabel("Bienvenue sur l'interface d'authentification");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(90, 40, 380, 100);
		userLabel = new JLabel("Login");
		userLabel.setBounds(115, 200, 80, 25);

		/* champ de saisi du login */
		userText = new JTextField(20);
		userText.setBounds(220, 200, 160, 25);

		passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setBounds(115, 230, 100, 25);
		
		/* champ de saisi du mot de passe */
		passwordText = new JPasswordField(20);
		passwordText.setBounds(220, 230, 160, 25);

		/* bouton de connexion */
		loginButton = new JButton("Se connecter");
		loginButton.setBounds(190, 270, 150, 25);
		loginButton.setBounds(175, 270, 150, 25);
		loginButton.setBackground(new Color(59, 89, 182));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		loginButton.setFont(new Font("Arial", Font.BOLD, 12));
		loginButton.addActionListener(this);
		
		/* la touche Entrée éxécute l'action du bouton */
		frame.getRootPane().setDefaultButton(loginButton);
		
		/* ajoute les éléments à notre panel */
		this.add(welcome);
		this.add(userLabel);
		this.add(userText);
		this.add(passwordLabel);
		this.add(passwordText);
		this.add(loginButton);

		setVisible(true);

	}

	/* fonction qui affiche une boite de dialogue pour confirmer la connexion */
	public void connected() {
		JOptionPane.showMessageDialog(this, "Connexion réussie.");
		revalidate();
		removeAll();
	}

	/*
	 * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
	 * la connexion
	 */
	public void notConnected() {
		JOptionPane.showMessageDialog(this, "L'identifiant ou le mot de passe saisi est incorrect.",
				"Erreur de connexion", JOptionPane.WARNING_MESSAGE);
		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		/*
		 * appelle notre fonction de connexion à la base de données et affiche notre
		 * menu si la connexion a bien été effectuée
		 */
		if (e.getSource() == loginButton) {
			String login = userText.getText();
			String mdp = String.valueOf(passwordText.getPassword());
			if (Database.connexion(login, mdp)) {
				connected();
				switch(Database.getRole(login)) {
				  case "visiteur":
					new VueMenuVisiteur(this.frame, login);
				    break;
				  case "responsable":
					new VueMenuResponsable(this.frame, login);
				    break;
				  case "directeur":
					new VueMenuDirecteur(this.frame, login);
					break;
				  default:
				}
				revalidate();
			} else {
				notConnected();
			}
		}
	}
}