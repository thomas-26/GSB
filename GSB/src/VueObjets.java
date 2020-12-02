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

public class VueObjets extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton reserverButton;
    private String login;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VueObjets(JFrame frame, String login) {
        this.frame = frame;
    	this.login = login;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* cr�ation des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesMateriels().size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("nom");
        tableModel.addColumn("longueur");
        tableModel.addColumn("largeur");
        tableModel.addColumn("etat");
        
        /* ajout � notre tableau � 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesMateriels().size(); i++) {
            donnees[i][0] = Database.getLesMateriels().get(i).getId();
            donnees[i][1] = Database.getLesMateriels().get(i).getNom();
            donnees[i][2] = Database.getLesMateriels().get(i).getLongueur();
            donnees[i][3] = Database.getLesMateriels().get(i).getLargeur();
            donnees[i][4] = Database.getLesMateriels().get(i).getEtat();
            tableModel.addRow(donnees[i]);
        }
        
        reserverButton = new JButton("R�server");
        reserverButton.setBounds(175, 270, 150, 25);
        reserverButton.setBackground(new Color(59, 89, 182));
        reserverButton.setForeground(Color.WHITE);
        reserverButton.setFocusPainted(false);
        reserverButton.setFont(new Font("Arial", Font.BOLD, 12));
//        reserverButton.addActionListener(this);        
        
        /* cr�ation du table + remplissage */
        table = new JTable(tableModel);
        
        /* met police des titres en Arial et en gras */
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        /* ajout du tableau � un scrollpane */
        this.add(new JScrollPane(table));
        
        /* ajout du bouton pour r�server le produit */
        this.add(reserverButton);
    }
    
    /* proc�dure � �x�cuter si le produit est disponible */
    public void disponible() {
    	JOptionPane.showMessageDialog(this, "S�l�ction faite");
    	removeAll();
    }
    
    /* proc�dure � �x�cuter si le produit n'est pas disponible */
    public void nonDisponible() {
        JOptionPane.showMessageDialog(this, "Le produit voulu n'est pas disponible", "Erreur de s�l�ction", JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
        /* action listener du bouton pour r�server le mat�riel s�lectionn� */
        reserverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 /* on r�cup�re la colonne et la ligne sur laquelle la s�l�ction est faite */
                int row = table.getSelectedRow();
                
                /* on stock les donn�es dans des variables */
                int id = (int) table.getModel().getValueAt(row, 0);
                String etat = table.getModel().getValueAt(row, 4).toString();
                String nom = table.getModel().getValueAt(row, 1).toString();
                                
                /* condition qui agit en fonction de l'�tat actuel du produit */
                if (etat.equals("disponible")) {
                	disponible();
                
                	/* on passe les variables en param�tre */
                	VueReservation reservation = new VueReservation(frame, login, id, nom);
                	//VueCalendrier reservation = new VueCalendrier(frame);
                	frame.setContentPane(reservation);

                	frame.revalidate();
                } else {
                	nonDisponible();
                }
            }
        });
    }
 }