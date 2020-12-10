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

// Classe VueGestion qui affiche la page de suppression d'un matériel
public class VueGestion extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton supprimerButton;
    private String nomLibelle;

    private static final long serialVersionUID = 1L;

    // Constructeur qui prend en paramètre la fenêtre et le libellé du matériel
    public VueGestion(JFrame frame, String nomLibelle) {
        this.frame = frame;
        this.nomLibelle = nomLibelle;
        
        remplirPanel();
        frame.setVisible(true);
    }
    
    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesMateriels(nomLibelle).size()][4];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("nom");
        tableModel.addColumn("longueur");
        tableModel.addColumn("largeur");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesMateriels(nomLibelle).size(); i++) {
            donnees[i][0] = Database.getLesMateriels(nomLibelle).get(i).getId();
            donnees[i][1] = Database.getLesMateriels(nomLibelle).get(i).getNom();
            donnees[i][2] = Database.getLesMateriels(nomLibelle).get(i).getLongueur();
            donnees[i][3] = Database.getLesMateriels(nomLibelle).get(i).getLargeur();
            tableModel.addRow(donnees[i]);
        }
        
        // bouton de suppression 
        supprimerButton = new JButton("Supprimer");
        supprimerButton.setBounds(175, 270, 150, 25);
        supprimerButton.setBackground(new Color(59, 89, 182));
        supprimerButton.setForeground(Color.WHITE);
        supprimerButton.setFocusPainted(false);
        supprimerButton.setFont(new Font("Arial", Font.BOLD, 12));
        supprimerButton.addActionListener(this);
                
        /* création du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour réserver le produit */
        this.add(supprimerButton);
    }
    
    /* fonction qui affiche une boite de dialogue pour confirmer la supression d'un
    * produit
    */
   public void removed() {
       JOptionPane.showMessageDialog(this, "Suppression réussie.");
       removeAll();
       remplirPanel();
       repaint();
       revalidate();
   }

   /*
    * fonction qui affiche une boite de dialogue pour signaler une erreur lors de
    * la supression
    */
   public void notRemoved() {
       JOptionPane.showMessageDialog(this, "Erreur.", "Erreur lors de la suppression", JOptionPane.WARNING_MESSAGE);
       removeAll();
       remplirPanel();
       repaint();
       revalidate();
   }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
        
            if (e.getSource() == supprimerButton) {
                /* appelle la boite de dialogue en fonction du résultat renvoyé par la fonction */
                /* on récupére la colonne et la ligne sur laquelle la séléction est faite */
            	
                int row = table.getSelectedRow();
                
                /* on la stock dans une variable */
                int id = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
              
                 if (Database.supprimerObjet(id)) {
                     removed();
                 } else {
                    notRemoved();
                 }
            }
        }

}