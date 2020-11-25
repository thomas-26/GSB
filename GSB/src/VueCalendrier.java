import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.jdatepicker.JDateComponentFactory;
import org.jdatepicker.JDatePicker;

public class VueCalendrier extends JPanel {
	
	private JFrame frame;
	
	public VueCalendrier(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		JPanel jPanel = new JPanel();
		
		JDatePicker picker = new JDateComponentFactory().createJDatePicker();
	    picker.setTextEditable(true);
	    picker.setShowYearButtons(true);
	    jPanel.add((JComponent) picker);
	    
	    picker.getModel().setYear(2010);
	    picker.getModel().setMonth(1);
	    //picker.getModel().setMonth(1);
	    picker.getModel().setDay(15);
	    picker.getModel().setSelected(true);
	    
	    JPanel datePanel = new JPanel();
	    datePanel.setLayout(new BorderLayout());
	    datePanel.add(jPanel, BorderLayout.WEST);
	    BorderLayout fb = new BorderLayout();
	    this.setLayout(fb);
	    this.add(datePanel, BorderLayout.WEST);

		setVisible(true);

	}
	
	//public static void main(String[] args) {
	    /*testFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	    testFrame.setSize(500, 500);
	    JPanel jPanel = new JPanel();

	    JDatePicker picker = new JDateComponentFactory().createJDatePicker();
	    picker.setTextEditable(true);
	    picker.setShowYearButtons(true);
	    jPanel.add((JComponent) picker);

	    picker.getModel().setYear(2010);
	    picker.getModel().setMonth(1);
	    //picker.getModel().setMonth(1);
	    picker.getModel().setDay(15);
	    picker.getModel().setSelected(true);

	    JPanel datePanel = new JPanel();
	    datePanel.setLayout(new BorderLayout());
	    datePanel.add(jPanel, BorderLayout.WEST);
	    BorderLayout fb = new BorderLayout();
	    testFrame.setLayout(fb);
	    testFrame.getContentPane().add(datePanel, BorderLayout.WEST);
	    testFrame.setVisible(true);*/
//	}

}