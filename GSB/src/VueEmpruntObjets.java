import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueEmpruntObjts qui affiche les objets empruntés avec leur emprunteur
public class VueEmpruntObjets extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRetour;

    private static final long serialVersionUID = 1L;

    // Constructeur VueEmpruntObjets qui prend en paramètre la fenêtre
    public VueEmpruntObjets(JFrame frame) {
        this.frame = frame;
  
        remplirPanel();
        frame.setVisible(true);
    }
    
    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesObjetsEmpruntes().size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("nom");
        tableModel.addColumn("dateDebut");
        tableModel.addColumn("dateFin");
        tableModel.addColumn("loginVisiteur");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesObjetsEmpruntes().size(); i++) {
            donnees[i][0] = Database.getLesObjetsEmpruntes().get(i).getId();
            donnees[i][1] = Database.getLesObjetsEmpruntes().get(i).getNom();
            donnees[i][2] = Database.getLesObjetsEmpruntes().get(i).getDateDebut();
            donnees[i][3] = Database.getLesObjetsEmpruntes().get(i).getDateFin();
            donnees[i][4] = Database.getLesObjetsEmpruntes().get(i).getLogin();
            tableModel.addRow(donnees[i]);
        }
        
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
    