import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//Classe Fenetre qui est lancé au démarage de l'application
public class Fenetre extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Constructeur par défaut
	public Fenetre() {
		this.setResizable(false);
		this.setSize(500, 525);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("GSB");

		// permet de centrer la frame au milieu de l'écran
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		// Appel de la vue Connexion
		VueConnexion pageConnexion = new VueConnexion(this);
		this.getContentPane().add(pageConnexion);

		this.setVisible(true);
	}

}