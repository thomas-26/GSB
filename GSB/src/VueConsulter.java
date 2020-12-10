import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueConsulter qui affiche les matériels empruntés du visiteur connecté
public class VueConsulter extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRetour;
    private String login;

    private static final long serialVersionUID = 1L;

    // Constructeur qui prend en paramètre la fenêtre et le login du visiteur connecté
    public VueConsulter(JFrame frame, String login) {
        this.frame = frame;
        this.login = login;
  
        remplirPanel();
        frame.setVisible(true);
    }
    
    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesObjetsEmpruntes().size()][6];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("idEmprunt");
        tableModel.addColumn("dateDebut");
        tableModel.addColumn("dateFin");
        tableModel.addColumn("heureDebut");
        tableModel.addColumn("heureFin");
        tableModel.addColumn("idObjet");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesMaterielsEmpruntesParVisiteur(login).size(); i++) {
            donnees[i][0] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getIdEmprunt();
            donnees[i][1] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getDateDebut();
            donnees[i][2] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getDateFin();
            donnees[i][3] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getHeureDebut();
            donnees[i][4] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getHeureFin();
            donnees[i][5] = Database.getLesMaterielsEmpruntesParVisiteur(login).get(i).getIdObjet();
            tableModel.addRow(donnees[i]);
        }
        
        // bouton retour qui renvoie au menu du visiteur
        btnRetour = new JButton("Retour");
        btnRetour.setBounds(175, 270, 150, 25);
        btnRetour.setBackground(new Color(59, 89, 182));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFocusPainted(false);
        btnRetour.setFont(new Font("Arial", Font.BOLD, 12));
        btnRetour.addActionListener(this);
  
        /* création du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour réserver le produit */
        this.add(btnRetour);
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	VueStatistique stats = new VueStatistique(frame);
        this.frame.setContentPane(stats);
        this.frame.revalidate();
    }
    
}
    