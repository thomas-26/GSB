import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenuDirecteur extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu;
    private JMenuItem itemStatistique;
    private JLabel lblDirecteur;
    private JLabel lblLogin;

    public VueMenuDirecteur(JFrame frame, String login) {
        barre = new JMenuBar();
        menu = new JMenu("Statistique");
        itemStatistique = new JMenuItem("Consulter");
        lblDirecteur = new JLabel("Connecté en temps que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(Color.GREEN.darker());
                
        itemStatistique.addActionListener(new VueStatistique(frame));
        
        menu.add(itemStatistique);
        
        barre.add(menu);
        barre.add(lblDirecteur);
        barre.add(lblLogin);

        this.add(barre);

        frame.getContentPane().add(this);

        frame.setJMenuBar(barre);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }
}