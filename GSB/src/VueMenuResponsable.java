import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenuResponsable extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu;
    private JMenuItem itemAjouter, itemConsulter, itemRechercher;
    private JLabel lblVisiteur;
    private JLabel lblLogin;

    public VueMenuResponsable(JFrame frame, String login) {
        barre = new JMenuBar();
        menu = new JMenu("Emprunts");
        
        itemAjouter = new JMenuItem("Ajout un produit");
        itemConsulter = new JMenuItem("Supprimer un produit");
        itemRechercher = new JMenuItem("Rechercher un produit");
        
        lblVisiteur = new JLabel("Connecté en tant que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(Color.GREEN.darker());
        
        itemAjouter.addActionListener(new VueAjouterObjet(frame));
        itemConsulter.addActionListener(new VueGestion(frame));
        itemRechercher.addActionListener(new VueRechercherObjet(frame));
        
        menu.add(itemAjouter);
        menu.add(itemConsulter);
        menu.add(itemRechercher);
        
        barre.add(menu);
        barre.add(lblVisiteur);
        barre.add(lblLogin);

        this.add(barre);

        frame.getContentPane().add(this);

        frame.setJMenuBar(barre);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == itemConsulter) {
    		System.out.println("ok");
    	}
    	if (e.getSource() == itemRechercher) {
    		System.out.println("ok");
    	}
    }
}