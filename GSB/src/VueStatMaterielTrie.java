import javax.swing.*;
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
    private JButton btnRetour;
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
        /* cr�ation des titres de notre JTable */
        Object[][] donnees = new Object[Database.getNbEmpruntsParObjet().size()][4];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("nbEmprunts");
        tableModel.addColumn("idEmprunt");
        tableModel.addColumn("idObjet");
        tableModel.addColumn("nom");
        
        /* ajout � notre tableau � 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getNbEmpruntsParObjet().size(); i++) {
            donnees[i][0] = Database.getNbEmpruntsParObjet().get(i).getNbEmprunt();
            donnees[i][1] = Database.getNbEmpruntsParObjet().get(i).getIdEmprunt();
            donnees[i][2] = Database.getNbEmpruntsParObjet().get(i).getIdObjet();
            donnees[i][3] = Database.getNbEmpruntsParObjet().get(i).getNom();
            tableModel.addRow(donnees[i]);
        }
        
        btnRetour = new JButton("Retour");
        btnRetour.setBounds(175, 270, 150, 25);
        btnRetour.setBackground(new Color(59, 89, 182));
        btnRetour.setForeground(Color.WHITE);
        btnRetour.setFocusPainted(false);
        btnRetour.setFont(new Font("Arial", Font.BOLD, 12));
        btnRetour.addActionListener(this);
  
        /* cr�ation du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau � un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour r�server le produit */
        this.add(btnRetour);
      
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	VueStatistique stats = new VueStatistique(frame);
        this.frame.setContentPane(stats);
        this.frame.revalidate();
    }
    
}
    