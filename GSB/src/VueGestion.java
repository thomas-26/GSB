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

public class VueGestion extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton reserverButton;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueGestion(JFrame frame) {
        this.frame = frame;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesMateriels().size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("nom");
        tableModel.addColumn("longueur");
        tableModel.addColumn("largeur");
        tableModel.addColumn("etat");
        
        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesMateriels().size(); i++) {
            donnees[i][0] = Database.getLesMateriels().get(i).getId();
            donnees[i][1] = Database.getLesMateriels().get(i).getNom();
            donnees[i][2] = Database.getLesMateriels().get(i).getLongueur();
            donnees[i][3] = Database.getLesMateriels().get(i).getLargeur();
            donnees[i][4] = Database.getLesMateriels().get(i).getEtat();
            tableModel.addRow(donnees[i]);
        }
        
        reserverButton = new JButton("Supprimer");
        reserverButton.setBounds(175, 270, 150, 25);
        reserverButton.setBackground(new Color(59, 89, 182));
        reserverButton.setForeground(Color.WHITE);
        reserverButton.setFocusPainted(false);
        reserverButton.setFont(new Font("Arial", Font.BOLD, 12));
//        reserverButton.addActionListener(this);        
        
        /* création du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau à un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour réserver le produit */
        this.add(reserverButton);
    }
    
    /* procédure à éxécuter si le produit est disponible */
    public void disponible() {
    	JOptionPane.showMessageDialog(this, "Séléction faite");
    	removeAll();
    }
    
    /* procédure à éxécuter si le produit n'est pas disponible */
    public void nonDisponible() {
        JOptionPane.showMessageDialog(this, "Le produit voulu n'est pas disponible", "Erreur de séléction", JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
        
        reserverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 /* on récupére la colonne et la ligne sur laquelle la séléction est faite */
                int row = table.getSelectedRow();
                int column = 4;
                
                /* on la stock dans une variable */
                String etat = table.getModel().getValueAt(row, column).toString();
                
            	int id = (int) table.getModel().getValueAt(row, 0);
                
                /* condition qui agit en fonction de l'état actuel du produit */
                if (etat.equals("disponible")) {
                	disponible();
                	
                	/*VueReservation reservation = new VueReservation(frame);
                	frame.setContentPane(reservation);*/
                	
                	System.out.println(id);
                	Database.supprimerObjet(id);
                	
                	repaint();
                	table.revalidate();
                	//frame.revalidate();
                } else {
                	nonDisponible();
                }
            }
        });
    }
 }