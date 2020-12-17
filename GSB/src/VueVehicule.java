import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueVehicule qui affiche les véhicules
public class VueVehicule extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton reserverButton;
    private String nomLibelle, login;

    private static final long serialVersionUID = 1L;

    // Constructeur VueVehicule qui prend en paramètre la fenêtre, le libellé du véhicule et le login du visiteur
    public VueVehicule(JFrame frame, String nomLibelle, String login) {
        this.login = login;
        this.frame = frame;
        this.nomLibelle = nomLibelle;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* création des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesVehicules(nomLibelle).size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("code");
        tableModel.addColumn("immatriculation");
        tableModel.addColumn("modèle");
        tableModel.addColumn("marque");
        tableModel.addColumn("nombre de places");

        /* ajout à notre tableau à 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesVehicules(nomLibelle).size(); i++) {
            donnees[i][0] = Database.getLesVehicules(nomLibelle).get(i).getId();
            donnees[i][2] = Database.getLesVehicules(nomLibelle).get(i).getNom();
            donnees[i][1] = Database.getLesVehicules(nomLibelle).get(i).getModele();
            donnees[i][3] = Database.getLesVehicules(nomLibelle).get(i).getMarque();
            donnees[i][4] = Database.getLesVehicules(nomLibelle).get(i).getNbPlaces();
            tableModel.addRow(donnees[i]);
        }

        // bouton de réservation
        reserverButton = new JButton("Réserver");
        reserverButton.setBounds(175, 270, 150, 25);
        reserverButton.setBackground(new Color(59, 89, 182));
        reserverButton.setForeground(Color.WHITE);
        reserverButton.setFocusPainted(false);
        reserverButton.setFont(new Font("Arial", Font.BOLD, 12));
        reserverButton.addActionListener(this);

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
        JOptionPane.showMessageDialog(this, "Le produit voulu n'est pas disponible", "Erreur de séléction",
                JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        /* test si le bouton a été cliqué */
        if (e.getSource() == reserverButton) {
            /* on récupére la colonne et la ligne sur laquelle la séléction est faite */
            int row = table.getSelectedRow();

            /* on stock les données dans des variables */
            int id = (int) table.getModel().getValueAt(row, 0);
            String nom = table.getModel().getValueAt(row, 1).toString();

            /* condition qui agit en fonction de l'état actuel du produit */
            disponible();

            /* on passe les variables en paramètre */
            VueReservation reservation = new VueReservation(frame, login, id, nom);
            // VueCalendrier reservation = new VueCalendrier(frame);
            frame.setContentPane(reservation);

            frame.revalidate();

        }
    }
}