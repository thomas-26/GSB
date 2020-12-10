import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueGestion qui affiche la page de suppression d'un matériel
public class VueAfficherRerchercheVehicule extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton retourButton;
    private int numero;

    private static final long serialVersionUID = 1L;

    // Constructeur qui prend en paramètre la fenêtre et le libellé du matériel
    public VueAfficherRerchercheVehicule(JFrame frame, int numero) {
        this.frame = frame;
        this.numero = numero;
        
        remplirPanel();
        frame.setVisible(true);
    }
    
    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesMateriels().size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("immat");
        tableModel.addColumn("marque");
        tableModel.addColumn("nbplaces");
        tableModel.addColumn("codevehicule");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getUnVehicule(numero).size(); i++) {
            donnees[i][0] = Database.getUnVehicule(numero).get(i).getCode();
            donnees[i][1] = Database.getUnVehicule(numero).get(i).getImmatriculation();
            donnees[i][2] = Database.getUnVehicule(numero).get(i).getModele();
            donnees[i][3] = Database.getUnVehicule(numero).get(i).getNbPlaces();
            donnees[i][4] = Database.getUnVehicule(numero).get(i).getCodeType();
            tableModel.addRow(donnees[i]);
        }
        
        // bouton de retour 
        retourButton = new JButton("Retour");
        retourButton.setBounds(175, 270, 150, 25);
        retourButton.setBackground(new Color(59, 89, 182));
        retourButton.setForeground(Color.WHITE);
        retourButton.setFocusPainted(false);
        retourButton.setFont(new Font("Arial", Font.BOLD, 12));
        retourButton.addActionListener(this);
                
        /* création du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour réserver le produit */
        this.add(retourButton);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
        
            if (e.getSource() == retourButton) {
				VueRechercherObjet recherche = new VueRechercherObjet(frame);
				frame.setContentPane(recherche);
	            frame.revalidate();	           	
            }
        }

}