import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueStatMaterielTrie extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueStatMaterielTrie(JFrame frame) {
        this.frame = frame;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getNbEmpruntsParVisiteur().size()][1];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("nbEmprunts");
        tableModel.addColumn("idEmprunt");
        tableModel.addColumn("idObjet");
        tableModel.addColumn("nom");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getNbEmpruntsParVisiteur().size(); i++) {
            donnees[i][0] = Database.getNbEmpruntsParVisiteur().get(i).getNbEmprunt();
            donnees[i][1] = Database.getNbEmpruntsParVisiteur().get(i).getIdEmprunt();
            donnees[i][2] = Database.getNbEmpruntsParVisiteur().get(i).getIdObjet();
            donnees[i][3] = Database.getNbEmpruntsParVisiteur().get(i).getNom();
            tableModel.addRow(donnees[i]);
        }
  
        /* création du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
    }
    
}
    