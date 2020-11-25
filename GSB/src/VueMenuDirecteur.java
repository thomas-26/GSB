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
    private JMenuItem itemConsulter;
    private JMenuItem itemEmprunter;
    private JLabel lblDirecteur;
    private JLabel lblLogin;

    public VueMenuDirecteur(JFrame frame, String login) {
        barre = new JMenuBar();
        menu = new JMenu("Emprunts");
        itemConsulter = new JMenuItem("Consulter");
        itemEmprunter = new JMenuItem("Emprunter");
        lblDirecteur = new JLabel("Connecté en temps que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(Color.GREEN.darker());

        menu.add(itemConsulter);
        menu.add(itemEmprunter);
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