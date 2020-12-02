import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenuVisiteur extends JPanel implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JMenuBar barre;
    private JMenu menu;
    private JMenuItem itemConsulter;
    private JLabel lblVisiteur;
    private JLabel lblLogin;

    public VueMenuVisiteur(JFrame frame, String login) {
        barre = new JMenuBar();
        menu = new JMenu("Emprunts");
        itemConsulter = new JMenuItem("Emprunter un objet");
        
        lblVisiteur = new JLabel("Connecté en tant que " + Database.getRole(login) + " : ");
        lblLogin = new JLabel(login);
        lblLogin.setForeground(Color.GREEN.darker());
        
        itemConsulter.addActionListener(new VueObjets(frame,login));

        menu.add(itemConsulter);
        
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
    }
}