import javax.swing.*;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Properties;

// Classe VueCalendier qui affiche les calendriers pour la réservation
public class VueCalendrier extends JPanel implements ActionListener {
	
	// Attributs
	private JFrame frame;
	private JPanel jPanel, jPanel2, jPanel3;
	private JDatePanelImpl datePanel;
	private JDatePanelImpl datePanel2;
	private JLabel lblDate, lblHeureDebut, lblHeureFin;
	private JButton emprunterButton;
	private int id;
	private JComboBox<Date> cb, cb2;
	private String login;
	
	// Constructeur qui prend en paramètre la fenêtre, le login du visiteur et l'id de l'objet
	public VueCalendrier(JFrame frame, String login, int id) {
		this.login = login;
		this.frame = frame;
		this.setLayout(null);
		this.id = id;
		
		// bouton emprunter
		emprunterButton = new JButton("Emprunter");
		emprunterButton.setBounds(160, 400, 150, 25);
		emprunterButton.setBackground(new Color(59, 89, 182));
		emprunterButton.setForeground(Color.WHITE);
		emprunterButton.setFocusPainted(false);
		emprunterButton.setFont(new Font("Arial", Font.BOLD, 12));
		emprunterButton.addActionListener(this);	
		
		// panel qui contient le label des dates
		jPanel3 = new JPanel();
		jPanel3.setBounds(0,50,475,500);
		
		// lblDate qui affiche les dates
		lblDate = new JLabel("Date début / Date fin");	
		lblDate.setBounds(0,0,50,100);	
		lblDate.setFont(new Font("Arial", Font.BOLD, 14));

		// panel qui contient les calendriers
		jPanel = new JPanel();
		jPanel.setBounds(0,100,500,500);
		
		// génération du calendrier 1
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		datePanel = new JDatePanelImpl(model, p);
		datePanel.getModel().setSelected(true);
		
		// génération du calendrier 2
		UtilDateModel model2 = new UtilDateModel();
		Properties p2 = new Properties();
		p2.put("text.today", "Aujourd'");
		p2.put("text.month", "Mois");
		p2.put("text.year", "Année");
		datePanel2 = new JDatePanelImpl(model2, p2);
		datePanel2.getModel().setSelected(true);
		
		// panel qui contient les heures
		jPanel2 = new JPanel();
		jPanel2.setBounds(90,300,300,300);
		
		// label qui affiche "Heure début"
		lblHeureDebut = new JLabel("Heure début");	
		
		// génération des heures qui commencent à 7h00
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);

		// génération des heures qui terminent à 19h59
        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 19);
        end.set(Calendar.MINUTE, 59);
        DefaultComboBoxModel<Date> model3 = new DefaultComboBoxModel<>();
        do {
            model3.addElement(calendar.getTime());
            calendar.add(Calendar.MINUTE, 15);
        } while (calendar.getTime().before(end.getTime()));

        // affichage formaté en heure - minute
        cb = new JComboBox<>(model3);
        cb.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
        
        // label qui affiche "Heure fin"
        lblHeureFin = new JLabel("Heure fin");	
		
        // génération des heures qui commencent à 7h00
		Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 7);
        calendar2.set(Calendar.MINUTE, 0);

        // génération des heures qui terminent à 19h59
        Calendar end2 = Calendar.getInstance();
        end2.set(Calendar.HOUR_OF_DAY, 19);
        end2.set(Calendar.MINUTE, 59);
        DefaultComboBoxModel<Date> model4 = new DefaultComboBoxModel<>();
        do {
            model4.addElement(calendar2.getTime());
            calendar2.add(Calendar.MINUTE, 15);
        } while (calendar2.getTime().before(end2.getTime()));

        cb2 = new JComboBox<>(model4);
        cb2.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		
		// ajout des différents éléments dans leur panel
		jPanel.add(datePanel);
		jPanel.add(datePanel2);
		jPanel2.add(lblHeureDebut);
		jPanel2.add(cb);
		jPanel2.add(lblHeureFin);
		jPanel2.add(cb2);
		jPanel3.add(lblDate);
		
		// ajout du bouton emprunt dans le panel courant
		this.add(emprunterButton);

		// ajout des panels dans le panel courant
		this.add(jPanel2);
		this.add(jPanel);
		this.add(jPanel3);
		
		// affichage de la fenêtre
		frame.setVisible(true);
	}
	
	// acions lors du déclanchement du bouton emprunter
	public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
  
        // Récupère la date de début sous forme de chaîne
        Date dateSelectedDebut = (Date) datePanel.getModel().getValue();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDateDebut = fmt.format(dateSelectedDebut); 
        
        // Récupère la date de fin sous forme de chaîne
        Date dateSelectedFin= (Date) datePanel2.getModel().getValue();
        java.text.SimpleDateFormat fmt2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDateFin = fmt2.format(dateSelectedFin);
        
        // récupère les heures de début et de fin
    	String data = "" + cb.getItemAt(cb.getSelectedIndex()); 
    	String data2 = "" + cb2.getItemAt(cb2.getSelectedIndex()); 
    	System.out.println("data : " + data);
    	
    	// récupère l'heure de début sous forme de chaîne
    	String[] splited = data.split(" ");
    	String splitedbis = splited[3];
    	String heureDebut = splitedbis.substring(0,5);  	
    	
    	// récupère l'heure de fin sous forme de chaîne
    	String[] splited2 = data2.split(" ");
    	String splited2bis = splited2[3];
    	String heureFin = splited2bis.substring(0,5);
    		
    	// Si l'objet a bien été emprunté
    	if(Database.emprunterObjetDate(dateFormatedDateDebut,dateFormatedDateFin,heureDebut,heureFin,id) == 1) {
    		Database.emprunterObjet(dateFormatedDateDebut,dateFormatedDateFin,heureDebut,heureFin,login,id);
    		JOptionPane.showMessageDialog(this, "L'objet a bien été réservé", "Félicitation", JOptionPane.INFORMATION_MESSAGE);
    		/* on passe la fenêtre et le login en paramètre */
            VueMenuVisiteur menu = new VueMenuVisiteur(frame,login);
            //VueCalendrier reservation = new VueCalendrier(frame);
            frame.setContentPane(menu);
            frame.revalidate();	
    	}
    	// Si l'objet est déjà emprunté aux dates selectionnées
    	else if(Database.emprunterObjetDate(dateFormatedDateDebut,dateFormatedDateFin,heureDebut,heureFin,id) == 0) {
    		JOptionPane.showMessageDialog(this, "L'objet est déjà emprunté à cette date", "Erreur", JOptionPane.WARNING_MESSAGE);
    	}
    	// Si l'objet est disponible dans la journée mais plus tôt
    	else if(Database.emprunterObjetDate(dateFormatedDateDebut,dateFormatedDateFin,heureDebut,heureFin,id) == 2) {
    		JOptionPane.showMessageDialog(this, "Réservez avant dans la journée", "Erreur", JOptionPane.WARNING_MESSAGE);
    	}
    	// Si l'objet est disponible dans la journée mais plus tard
    	else if(Database.emprunterObjetDate(dateFormatedDateDebut,dateFormatedDateFin,heureDebut,heureFin,id) == 3) {
    		JOptionPane.showMessageDialog(this, "Réservez plus tard dans la journée", "Erreur", JOptionPane.WARNING_MESSAGE);
    	}
    	
	}

}