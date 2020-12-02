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

public class VueCalendrier extends JPanel implements ActionListener {
	
	private JFrame frame;
	private JPanel jPanel, jPanel2, jPanel3;
	private JDatePanelImpl datePanel;
	private JDatePanelImpl datePanel2;
	private JLabel lblDate, lblHeureDebut, lblHeureFin;
	private JButton emprunterButton;
	private int id;
	private JComboBox<Date> cb, cb2;
	private String login;
	
	public VueCalendrier(JFrame frame, String login, int id) {
		this.login = login;
		this.frame = frame;
		this.setLayout(null);
		this.id = id;
		
		emprunterButton = new JButton("Emprunter");
		emprunterButton.setBounds(160, 400, 150, 25);
		emprunterButton.setBackground(new Color(59, 89, 182));
		emprunterButton.setForeground(Color.WHITE);
		emprunterButton.setFocusPainted(false);
		emprunterButton.setFont(new Font("Arial", Font.BOLD, 12));
		emprunterButton.addActionListener(this);	
		
		jPanel3 = new JPanel();
		jPanel3.setBounds(0,50,475,500);
		
		lblDate = new JLabel("Date début / Date fin");	
		lblDate.setBounds(0,0,50,100);	
		lblDate.setFont(new Font("Arial", Font.BOLD, 14));

		jPanel = new JPanel();
		jPanel.setBounds(0,100,500,500);
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		datePanel = new JDatePanelImpl(model, p);
		datePanel.getModel().setSelected(true);
		
		UtilDateModel model2 = new UtilDateModel();
		//model.setDate(20,04,2014); 
		// Need this...
		Properties p2 = new Properties();
		p2.put("text.today", "Aujourd'");
		p2.put("text.month", "Mois");
		p2.put("text.year", "Année");
		datePanel2 = new JDatePanelImpl(model2, p2);
		datePanel2.getModel().setSelected(true);
		
		
		jPanel2 = new JPanel();
		jPanel2.setBounds(90,300,300,300);
		
		lblHeureDebut = new JLabel("Heure début");	
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        Calendar end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        DefaultComboBoxModel<Date> model3 = new DefaultComboBoxModel<>();
        do {
            model3.addElement(calendar.getTime());
            calendar.add(Calendar.MINUTE, 15);
        } while (calendar.getTime().before(end.getTime()));

        cb = new JComboBox<>(model3);
        cb.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
        
        lblHeureFin = new JLabel("Heure fin");	
		
		Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);

        Calendar end2 = Calendar.getInstance();
        end2.set(Calendar.HOUR_OF_DAY, 23);
        end2.set(Calendar.MINUTE, 59);
        DefaultComboBoxModel<Date> model4 = new DefaultComboBoxModel<>();
        do {
            model4.addElement(calendar2.getTime());
            calendar2.add(Calendar.MINUTE, 15);
        } while (calendar2.getTime().before(end2.getTime()));

        cb2 = new JComboBox<>(model4);
        cb2.setRenderer(new DateFormattedListCellRenderer(new SimpleDateFormat("HH:mm")));
		
		
		jPanel.add(datePanel);
		jPanel.add(datePanel2);
		jPanel2.add(lblHeureDebut);
		jPanel2.add(cb);
		jPanel2.add(lblHeureFin);
		jPanel2.add(cb2);
		jPanel3.add(lblDate);
		

		this.add(emprunterButton);
		
		this.add(jPanel2);
		this.add(jPanel);
		this.add(jPanel3);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
  
        Date dateSelected = (Date) datePanel.getModel().getValue();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDate = fmt.format(dateSelected); 
        
        Date dateSelected2= (Date) datePanel2.getModel().getValue();
        java.text.SimpleDateFormat fmt2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDate2 = fmt2.format(dateSelected2);
        
    	String data = "" + cb.getItemAt(cb.getSelectedIndex()); 
    	String data2 = "" + cb2.getItemAt(cb2.getSelectedIndex()); 

    	String[] splited = data.split(" ");
    	String test = splited[3];
    	String heureDebut = test.substring(0,5);
    	
    	String[] splited2 = data2.split(" ");
    	String test2 = splited2[3];
    	String heureFin = test2.substring(0,5);
   
        
        /*System.out.println(dateFormatedDate);
        System.out.println(dateFormatedDate2); 
    	System.out.println(heureDebut);
    	System.out.println(heureFin);*/
    	
    	System.out.println(Database.emprunterObjet(dateFormatedDate,id));

        //Database.reserverObjet(id);
    	
    	
    	
    	//Database.emprunterObjet(dateFormatedDate,dateFormatedDate2,heureDebut,heureFin,login,id);
	}

}