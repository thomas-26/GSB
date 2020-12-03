import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class VueAjouterObjet extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idText, nomText, longueurText, largeurText;
	private JButton ajouterButton;
	private JLabel idLabel, nomLabel, welcome, largeurLabel, longueurLabel, type;
	private JFrame frame;
	private JComboBox<String> lesNoms = new JComboBox<>();


	public VueAjouterObjet(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
        
		/* texte informatif */
		welcome = new JLabel("Ajout d'un objet");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(180, 35, 380, 100);
		
		idLabel = new JLabel("Id");
		idLabel.setBounds(115, 150, 80, 25);
			
		/* champ de saisi de l'id */
		idText = new JTextField(20);
		idText.setBounds(200, 150, 160, 25);
		
		nomLabel = new JLabel("Nom");
		nomLabel.setBounds(115, 180, 100, 25);
		
		nomText = new JTextField(20);
		nomText.setBounds(200, 180, 160, 25);
		
		longueurLabel = new JLabel("Longueur");
		longueurLabel.setBounds(115, 210, 100, 25);
		
		longueurText = new JTextField(20);
		longueurText.setBounds(200, 210, 160, 25);
		
		largeurLabel = new JLabel("Largeur");
		largeurLabel.setBounds(115, 240, 100, 25);
		
		largeurText = new JTextField(20);
		largeurText.setBounds(200, 240, 160, 25);
		
		/* ajoute chaque �l�ment de notre liste de parcs dans une JComboBox */
		for (int i = 0; i < Database.getNbLibelle(); i++) {
			lesNoms.addItem(Database.getLibelle().get(i));
		}
		
		type = new JLabel("Type");
		type.setBounds(115, 270, 160, 25);
		lesNoms.setBounds(200, 270, 160, 25);
		
		/* bouton de connexion */
		ajouterButton = new JButton("Ajouter");
		ajouterButton.setBounds(190, 345, 150, 25);
		ajouterButton.setBackground(new Color(59, 89, 182));
		ajouterButton.setForeground(Color.WHITE);
		ajouterButton.setFocusPainted(false);
		ajouterButton.setFont(new Font("Arial", Font.BOLD, 12));
		ajouterButton.addActionListener(this);

		/* la touche Entr�e �x�cute l'action du bouton */
		frame.getRootPane().setDefaultButton(ajouterButton);

		/* ajoute les �l�ments � notre panel */
		this.add(welcome);
		
		this.add(idLabel);
		this.add(idText);
		
		this.add(nomLabel);
		this.add(nomText);
		
		this.add(longueurLabel);
		this.add(longueurText);
		
		this.add(largeurLabel);
		this.add(largeurText);
		
		this.add(type);
		this.add(lesNoms);
		
		this.add(ajouterButton);

		frame.setVisible(true);

	}

	/*
     * fonction qui affiche une boite de dialogue pour confirmer l'ajout d'un
     * visiteur
     */
    public void added() {
        JOptionPane.showMessageDialog(this, "Ajout r�ussi.");
        repaint();
        revalidate();
    }

    /*
     * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
     * l'ajout
     */
    public void notAdded() {
        JOptionPane.showMessageDialog(this, "L'id saisi existe d�j� .", "Erreur d'ajout'", JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
    }
    /*
     * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
     * l'ajout du � des champs vides
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
		 * appelle notre fonction de connexion � la base de donn�es et affiche notre
		 * menu si la connexion a bien �t� effectu�e
		 */
        if (e.getSource() == ajouterButton && !idText.getText().isEmpty() && !nomText.getText().isEmpty() && !longueurText.getText().isEmpty() && !largeurText.getText().isEmpty()) {
			/* � compl�ter pour check si les valeurs sont conformes */
        	String nom = nomText.getText();
        	int id = 0;
        	int longueur = 0;
        	int largeur = 0;
        	int codemateriel = 0;
        	id = Integer.parseInt(idText.getText());
        	longueur = Integer.parseInt(longueurText.getText());
        	largeur = Integer.parseInt(largeurText.getText());
        	codemateriel = Integer.parseInt(largeurText.getText());
        	String typemateriel = lesNoms.getSelectedItem().toString();
        	
        	try {
        		/* reste du code � mettre dedans */
			} catch (NumberFormatException exception) {
			    System.out.println("Not a number");
			}

            /* vide les champs de texte une fois l'ajout effectu� */
            idText.setText("");
            nomText.setText("");
            longueurText.setText("");
            largeurText.setText("");
            
            /* il faut que l'id saisit ne soit pas d�j� dans la base de donn�es sql pour pouvoir proc�der */
            if (Database.rechercherObjet(id) == false) {
            	Database.ajouterObjet(id, nom, longueur, largeur, codemateriel, typemateriel);
            	System.out.println(Database.ajouterObjet(id, nom, longueur, largeur, codemateriel, typemateriel));
	            /*
	             * appelle la boite de dialogue en fonction du r�sultat renvoy� par la fonction
	             */
	            if (Database.ajouterObjet(id, nom, longueur, largeur, codemateriel, typemateriel) == 1) {
	            	added();
	            }
            } else {
            	notAdded();
            }
		} else if ((e.getSource() == ajouterButton && (idText.getText().isEmpty() || nomText.getText().isEmpty() || longueurText.getText().isEmpty() || largeurText.getText().isEmpty()))) {
			champsVides();
		}
	}
}