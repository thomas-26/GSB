import javax.swing.*;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;
import java.util.Properties;

public class VueCalendrier extends JPanel implements ActionListener {
	
	private JFrame frame;
	private JDatePanelImpl datePanel;
	private JDatePanelImpl datePanel2;
	private JButton emprunterButton;
	private int id;
	
	public VueCalendrier(JFrame frame, int id) {
		this.frame = frame;
		this.setLayout(null);
		this.id = id;
		
		emprunterButton = new JButton("Emprunter");
		emprunterButton.setBounds(160, 300, 150, 25);
		emprunterButton.setBackground(new Color(59, 89, 182));
		emprunterButton.setForeground(Color.WHITE);
		emprunterButton.setFocusPainted(false);
		emprunterButton.setFont(new Font("Arial", Font.BOLD, 12));
		emprunterButton.addActionListener(this);	
		
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0,50,500,500);
		
		UtilDateModel model = new UtilDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Mois");
		p.put("text.year", "Année");
		datePanel = new JDatePanelImpl(model, p);
		datePanel.getModel().setSelected(true);
		jPanel.add(datePanel);
		
		UtilDateModel model2 = new UtilDateModel();
		//model.setDate(20,04,2014); 
		// Need this...
		Properties p2 = new Properties();
		p2.put("text.today", "Aujourd'");
		p2.put("text.month", "Mois");
		p2.put("text.year", "Année");
		datePanel2 = new JDatePanelImpl(model2, p2);
		datePanel2.getModel().setSelected(true);
		jPanel.add(datePanel2);
		
		this.add(emprunterButton);
		
		this.add(jPanel);
		
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
        this.frame.setContentPane(this);
        this.frame.revalidate();
  
        Date dateSelected = (Date) datePanel.getModel().getValue();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDate = fmt.format(dateSelected);
        System.out.println(dateFormatedDate); 
        
        Date dateSelected2= (Date) datePanel2.getModel().getValue();
        java.text.SimpleDateFormat fmt2 = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String dateFormatedDate2 = fmt2.format(dateSelected2);
        System.out.println(dateFormatedDate2); 
        //Database.reserverObjet(id);
	}
}