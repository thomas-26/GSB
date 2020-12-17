import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe VueVehicule qui affiche les v�hicules
public class VueVehicule extends JPanel implements ActionListener {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton reserverButton;
    private String nomLibelle, login;

    private static final long serialVersionUID = 1L;

    // Constructeur VueVehicule qui prend en param�tre la fen�tre, le libell� du v�hicule et le login du visiteur
    public VueVehicule(JFrame frame, String nomLibelle, String login) {
        this.login = login;
        this.frame = frame;
        this.nomLibelle = nomLibelle;

        remplirPanel();
        frame.setVisible(true);
    }

    public void remplirPanel() {
        /* cr�ation des titres de notre JTable */
        Object[][] donnees = new Object[Database.getLesVehicules(nomLibelle).size()][5];

        tableModel = new DefaultTableModel();
        tableModel.addColumn("code");
        tableModel.addColumn("immatriculation");
        tableModel.addColumn("mod�le");
        tableModel.addColumn("marque");
        tableModel.addColumn("nombre de places");

        /* ajout � notre tableau � 2 dimensions des informations du visiteur */
        for (int i = 0; i < Database.getLesVehicules(nomLibelle).size(); i++) {
            donnees[i][0] = Database.getLesVehicules(nomLibelle).get(i).getId();
            donnees[i][2] = Database.getLesVehicules(nomLibelle).get(i).getNom();
            donnees[i][1] = Database.getLesVehicules(nomLibelle).get(i).getModele();
            donnees[i][3] = Database.getLesVehicules(nomLibelle).get(i).getMarque();
            donnees[i][4] = Database.getLesVehicules(nomLibelle).get(i).getNbPlaces();
            tableModel.addRow(donnees[i]);
        }

        // bouton de r�servation
        reserverButton = new JButton("R�server");
        reserverButton.setBounds(175, 270, 150, 25);
        reserverButton.setBackground(new Color(59, 89, 182));
        reserverButton.setForeground(Color.WHITE);
        reserverButton.setFocusPainted(false);
        reserverButton.setFont(new Font("Arial", Font.BOLD, 12));
        reserverButton.addActionListener(this);

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
        JOptionPane.showMessageDialog(this, "Le produit voulu n'est pas disponible", "Erreur de s�l�ction",
                JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();

        /* test si le bouton a �t� cliqu� */
        if (e.getSource() == reserverButton) {
            /* on r�cup�re la colonne et la ligne sur laquelle la s�l�ction est faite */
            int row = table.getSelectedRow();

            /* on stock les donn�es dans des variables */
            int id = (int) table.getModel().getValueAt(row, 0);
            String nom = table.getModel().getValueAt(row, 1).toString();

            /* condition qui agit en fonction de l'�tat actuel du produit */
            disponible();

            /* on passe les variables en param�tre */
            VueReservation reservation = new VueReservation(frame, login, id, nom);
            // VueCalendrier reservation = new VueCalendrier(frame);
            frame.setContentPane(reservation);

            frame.revalidate();

        }
    }
}