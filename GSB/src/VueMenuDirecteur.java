import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueMenuDirecteur qui s'affiche pour le directeur
public class VueMenuDirecteur extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu;
    private JMenuItem itemStatistique;
    private JLabel lblDirecteur;
    private JButton deconnexion;
    private JLabel lblLogin;
    private JFrame frame;

    // Constructeur qui prend en paramètre la fenêtre et le login du directeur 
    public VueMenuDirecteur(JFrame frame, String login) {
    	this.frame = frame;
        barre = new JMenuBar();
        menu = new JMenu("Statistique");
        itemStatistique = new JMenuItem("Consulter");
        
		/* bouton de déconnexion */
        deconnexion = new JButton("Déconnexion");
		deconnexion.setBackground(new Color(59, 89, 182));
		deconnexion.setForeground(Color.WHITE);
		deconnexion.setFocusPainted(false);
		deconnexion.setFont(new Font("Arial", Font.BOLD, 12));
		deconnexion.addActionListener(this);        
        
        lblDirecteur = new JLabel("Connecté en tant que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(new Color(59, 89, 182));
                
        itemStatistique.addActionListener(new VueStatistique(frame));
        
        menu.add(itemStatistique);
        
        barre.add(menu);
        barre.add(lblDirecteur);
        barre.add(lblLogin);
        barre.add(Box.createHorizontalGlue());
        barre.add(deconnexion);
        
        this.add(barre);

        frame.getContentPane().add(this);

        frame.setJMenuBar(barre);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        /* bouton de déconnexion
         * on clear tout le panel et les éléments de notre fenetre
         * puis on retourne sur la vue de connexion 
         */
    	
    	if (arg0.getSource() == deconnexion) {
    		barre.removeAll();
    		menu.removeAll();
    		
    		frame.getContentPane().removeAll();
    		frame.getContentPane().repaint();
    		
    		VueConnexion connexion = new VueConnexion(frame);
            frame.setContentPane(connexion);
            frame.revalidate();
    	}
    }
}