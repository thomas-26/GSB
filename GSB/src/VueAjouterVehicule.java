import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueAjouterVehicule extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField codeText, immatText, modeleText, nbPlacesText, marqueText;
	private JButton ajouterButton;
	private JLabel codeLabel, immatLabel, welcome, marqueLabel, modeleLabel, type, nbPlacesLabel;
	private JFrame frame;
	private JComboBox<String> lesVehicules = new JComboBox<>();

	public VueAjouterVehicule(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);

		/* texte informatif */
		welcome = new JLabel("Ajout d'un véhicule");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(180, 35, 380, 100);

		codeLabel = new JLabel("Code");
		codeLabel.setBounds(115, 150, 80, 25);

		/* champ de saisi de l'id */
		codeText = new JTextField(20);
		codeText.setBounds(200, 150, 160, 25);

		immatLabel = new JLabel("n° immatriculation");
		immatLabel.setBounds(80, 180, 150, 25);

		immatText = new JTextField(20);
		immatText.setBounds(200, 180, 160, 25);

		modeleLabel = new JLabel("modèle");
		modeleLabel.setBounds(115, 210, 100, 25);

		modeleText = new JTextField(20);
		modeleText.setBounds(200, 210, 160, 25);

		marqueLabel = new JLabel("marque");
		marqueLabel.setBounds(115, 240, 100, 25);
		
		marqueText = new JTextField(20);
		marqueText.setBounds(200, 240, 100, 25);
		
		nbPlacesLabel = new JLabel("nombre de places");
		nbPlacesLabel.setBounds(80, 270, 150, 25);

		nbPlacesText = new JTextField(20);
		nbPlacesText.setBounds(200, 270, 160, 25);

		/* ajoute chaque élément de notre liste de parcs dans une JComboBox */
		for (int i = 0; i < Database.getNbLibelleVehicule(); i++) {
			lesVehicules.addItem(Database.getLibelleVehicule().get(i));
		}

		type = new JLabel("Type");
		type.setBounds(120, 300, 160, 25);
		lesVehicules.setBounds(200, 300, 160, 25);

		/* bouton de connexion */
		ajouterButton = new JButton("Ajouter");
		ajouterButton.setBounds(190, 345, 150, 25);
		ajouterButton.setBackground(new Color(59, 89, 182));
		ajouterButton.setForeground(Color.WHITE);
		ajouterButton.setFocusPainted(false);
		ajouterButton.setFont(new Font("Arial", Font.BOLD, 12));
		ajouterButton.addActionListener(this);

		/* la touche Entrée éxécute l'action du bouton */
		frame.getRootPane().setDefaultButton(ajouterButton);

		/* ajoute les éléments à notre panel */
		this.add(welcome);

		this.add(codeLabel);
		this.add(codeText);

		this.add(immatLabel);
		this.add(immatText);

		this.add(modeleLabel);
		this.add(modeleText);
		
		this.add(marqueLabel);
		this.add(marqueText);

		this.add(nbPlacesLabel);
		this.add(nbPlacesText);

		this.add(type);
		
		this.add(lesVehicules);

		this.add(ajouterButton);

		frame.setVisible(true);

	}

	/*
	 * fonction qui affiche une boite de dialogue pour confirmer l'ajout d'un
	 * visiteur
	 */
	public void added() {
		JOptionPane.showMessageDialog(this, "Ajout réussi.");
		repaint();
		revalidate();
	}

	/*
	 * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
	 * l'ajout
	 */
	public void notAdded() {
		JOptionPane.showMessageDialog(this, "L'id saisi existe déjà .", "Erreur d'ajout'", JOptionPane.WARNING_MESSAGE);
		repaint();
		revalidate();
	}
	
	/*
	 * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
	 * l'ajout du à la plaque d'immatriculation
	 */
	public void probImmat() {
		JOptionPane.showMessageDialog(this, "Immatriculation incorrecte.");
		repaint();
		revalidate();
	}

	/*
	 * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
	 * l'ajout du à des champs vides
	 */
	public void champsVides() {
		JOptionPane.showMessageDialog(this, "Les champs sont vides.", "Erreur d'ajout'", JOptionPane.WARNING_MESSAGE);
		repaint();
		revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this);
		this.frame.revalidate();
		/*
		 * appelle notre fonction de connexion à la base de données et affiche notre
		 * menu si la connexion a bien été effectuée
		 */
		if (e.getSource() == ajouterButton && !codeText.getText().isEmpty() && !immatText.getText().isEmpty()
				&& !modeleText.getText().isEmpty() && !nbPlacesText.getText().isEmpty()) {
			/* à compléter pour check si les valeurs sont conformes */

			int code = Integer.parseInt(codeText.getText());
			String immatriculation = immatText.getText();
			String modele = modeleText.getText();
			String marque = marqueText.getText();
			int nbplaces = Integer.parseInt(nbPlacesText.getText());
			String typevehicule = lesVehicules.getSelectedItem().toString();
	
			try {
				/* reste du code à mettre dedans */
			} catch (NumberFormatException exception) {
				System.out.println("Not a number");
			}

			/* vide les champs de texte une fois l'ajout effectué */
			codeText.setText("");
			immatText.setText("");
			modeleText.setText("");
			nbPlacesText.setText("");

			/*
			* il faut que l'id saisit ne soit pas déjà dans la base de données sql pour
			* pouvoir procéder
		    */
			if (Database.rechercherVehicule(code) == false) {
				if(immatriculation.matches("[A-Za-z]{2}-[1-9]{3}-[A-Za-z]{2}")) {
					Database.ajouterVehicule(code, immatriculation, modele, marque, nbplaces, typevehicule);
					//System.out.println(Database.ajouterVehicule(code, immatriculation, modele, marque, nbplaces, typevehicule));
					/*
					 * appelle la boite de dialogue en fonction du résultat renvoyé par la fonction
					 */
					if (Database.ajouterVehicule(code, immatriculation, modele, marque, nbplaces, typevehicule) == 2) {
						added(); // ne rentre pas ici
					}
				} else {
					probImmat();
				}
			} else {
				notAdded();
			}
		} else if ((e.getSource() == ajouterButton && (codeText.getText().isEmpty() || immatText.getText().isEmpty()
				|| modeleText.getText().isEmpty() || nbPlacesText.getText().isEmpty()))) {
			champsVides();
		}
	}
}