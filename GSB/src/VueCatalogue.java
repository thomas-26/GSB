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

// Classe VueCatalogue qui affiche le catalogue
public class VueCatalogue extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Constructeur qui prend en paramètre la fenêtre
    public VueCatalogue(JFrame frame) {
        this.frame = frame;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesMaterielsEmpruntes().size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("nom");
        tableModel.addColumn("longueur");
        tableModel.addColumn("largeur");
        tableModel.addColumn("code materiel");
        tableModel.addColumn("etat");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesMaterielsEmpruntes().size(); i++) {
            donnees[i][0] = Database.getLesMaterielsEmpruntes().get(i).getId();
            donnees[i][1] = Database.getLesMaterielsEmpruntes().get(i).getNom();
            donnees[i][2] = Database.getLesMaterielsEmpruntes().get(i).getLongueur();
            donnees[i][3] = Database.getLesMaterielsEmpruntes().get(i).getLargeur();
            donnees[i][4] = Database.getLesMaterielsEmpruntes().get(i).getCodeMateriel();
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