
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Classe VueMenuVisiteur qui affiche le menu pou le visiteur
public class VueMenuVisiteur extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu, sousmenu, sousmenu2;
    private JLabel lblVisiteur;
    private JLabel lblLogin;
    private JButton deconnexion;
    private JFrame frame;
    private int i;
    
    // Constructeur qui prend en param�tre la fen�tre et le login
    public VueMenuVisiteur(JFrame frame, String login) {
        this.frame = frame;
        barre = new JMenuBar();
        menu = new JMenu("Emprunts");

        lblVisiteur = new JLabel("Connect� en tant que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(new Color(59, 89, 182));

        /* bouton de d?connexion */
        deconnexion = new JButton("D�connexion");
        deconnexion.setBackground(new Color(59, 89, 182));
        deconnexion.setForeground(Color.WHITE);
        deconnexion.setFocusPainted(false);
        deconnexion.setFont(new Font("Arial", Font.BOLD, 12));
        deconnexion.addActionListener(this);

        // sous menus dynamiques
        sousmenu = new JMenu("Emprunter un objet");
        sousmenu2 = new JMenu("Louer un v�hicule");

        /* on cr�e une liste qui va nous servir ? stocker nos types de produits */
        ArrayList<String> liste = new ArrayList<String>();

        ArrayList<String> listevehicule = new ArrayList<String>();

        /*
         * on parcours le nombre de types de produits diff?rent et on ajoute chaque type
         * � notre liste pr�cedemment cr��e
         */
        for (int i = 0; i < Database.getNbLibelle(); i++) {
            liste.add(Database.getLibelle().get(i));
        }

        /*
         * on cr�� les JMenu Item qui vont accueillir les donn�es de notre liste et on
         * ajoute chaque menu item au sous menu
         */
        for (i = 0; i < liste.size(); i++) {
            String pos = liste.get(i);
            JMenuItem Jmi = new JMenuItem(pos);

            Jmi.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /* on passe les variables en param�tre */
                    VueObjets objet = new VueObjets(frame, Jmi.getText(), login);
                    frame.setContentPane(objet);
                    frame.setVisible(true);
                }
            });
            sousmenu.add(Jmi);
        }

        /*
         * on parcours le nombre de types de produits diff�rent et on ajoute chaque type
         * � notre liste pr�cedemment cr��e
         */
        for (int i = 0; i < Database.getNbLibelleVehicule(); i++) {
            listevehicule.add(Database.getLibelleVehicule().get(i));
        }

        /*
         * on cr�� les JMenu Item qui vont accueillir les donn�es de notre liste et on
         * ajoute chaque menu item au sous menu
         */
        for (i = 0; i < listevehicule.size(); i++) {
            String pos = listevehicule.get(i);
            JMenuItem Jmi2 = new JMenuItem(pos);

            Jmi2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    /* on passe les variables en param�tre */
                    VueVehicule vehicule = new VueVehicule(frame, Jmi2.getText(), login);
                    frame.setContentPane(vehicule);
                    frame.setVisible(true);
                }
            });
            sousmenu2.add(Jmi2);
        }
        
        JMenuItem Jmi3 = new JMenuItem("Consulter emprunts");
        Jmi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /* on passe les variables en param�tre */
                VueConsulter consulter = new VueConsulter(frame, login);
                frame.setContentPane(consulter);
                frame.setVisible(true);
            }
        });
        
        menu.addSeparator();

        menu.add(sousmenu);
        menu.add(sousmenu2);
        menu.add(Jmi3);

        barre.add(menu);
        barre.add(lblVisiteur);
        barre.add(lblLogin);
        barre.add(Box.createHorizontalGlue());
        barre.add(deconnexion);

        this.add(barre);

        frame.getContentPane().add(this);

        frame.setJMenuBar(barre);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        /*
         * bouton de d�connexion on clear tout le panel et les �l�ments de notre fenetre
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