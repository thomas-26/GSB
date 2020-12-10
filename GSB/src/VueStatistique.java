import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

// Classe VueStatistique qui affiche les statistiques
public class VueStatistique extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnValider, btnGenerer;
	private JLabel welcome, welcome2, welcome3, welcome4;
	private JComboBox cb;
	private JFrame frame;
	private Date dateActuelle, dateDebut, dateFin;
	
	// Constructeur qui prend en paramètre la fenêtre
	public VueStatistique(JFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		
		/* texte informatif */
		welcome = new JLabel("Statistiques");
		welcome.setFont(new Font("Arial", Font.BOLD, 14));
		welcome.setBounds(190, 0, 380, 100);
		
		welcome2 = new JLabel("Veuillez cliquer sur le bouton");
		welcome2.setFont(new Font("Arial", Font.PLAIN, 14));
		welcome2.setBounds(145, 15, 380, 100);
		
		welcome3 = new JLabel("Un fichier au format PDF sera généré");
		welcome3.setFont(new Font("Arial", Font.PLAIN, 14));
		welcome3.setBounds(135, 30, 380, 100);
		
		welcome4 = new JLabel("Il contiendra les statistiques");
		welcome4.setFont(new Font("Arial", Font.PLAIN, 14));
		welcome4.setBounds(145, 45, 380, 100);
		
		/* JComboBox */
		String statistiques[]={"Nombre d'objets","Nombre objets empruntés","Nombre matériels empruntés triés","Nombre d'emprunt par visiteur","Objets empruntés"};        
		cb = new JComboBox(statistiques);    
		cb.setBounds(150, 125, 200, 20);     
        
		/* bouton d'emprunt */
		btnValider = new JButton("Valider");
		btnValider.setBounds(160, 230, 150, 25);
		btnValider.setBackground(new Color(59, 89, 182));
		btnValider.setForeground(Color.WHITE);
		btnValider.setFocusPainted(false);
		btnValider.setFont(new Font("Arial", Font.BOLD, 12));
		btnValider.addActionListener(this);	
		
		/* bouton de génération d'un PDF */
		btnGenerer = new JButton("Générer");
		btnGenerer.setBounds(160, 270, 150, 25);
		btnGenerer.setBackground(new Color(59, 89, 182));
		btnGenerer.setForeground(Color.WHITE);
		btnGenerer.setFocusPainted(false);
		btnGenerer.setFont(new Font("Arial", Font.BOLD, 12));
		btnGenerer.addActionListener(this);	

		this.add(welcome);
		this.add(welcome2);
		this.add(welcome3);
		this.add(welcome4);
		this.add(cb);
		
		this.add(btnValider);
		this.add(btnGenerer);

		frame.setVisible(true);
			
	}
	
	public void nonSelect() {
        JOptionPane.showMessageDialog(this, "Vous devez cocher la case", "Erreur", JOptionPane.WARNING_MESSAGE);
        repaint();
        revalidate();
	}
	
	public void selected() {
    	JOptionPane.showMessageDialog(this, "Produit réservé !");
        repaint();
        revalidate();
	}
	
	public void generated() {
    	JOptionPane.showMessageDialog(this, "PDF généré avec succès !");
        repaint();
        revalidate();
	}
	
	/* fonction qui permet de générer un PDF avec les statistiques */
	public void topdf() {
		Document document = new Document();
	      try {
	    	 String filename = "C:\\Users\\tbonneville\\Documents\\pdf\\Statistiques.pdf";
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
	         document.open();
	         Image img = Image.getInstance("src/gsb.png");
	         img.scaleAbsolute(200f, 120f);
//	         Font pageNumberFont = new Font(Arial, 9, Font.BOLD);
	         
	         document.add(img);
	         document.add(new Paragraph("Nombre d'objets : " +  Database.getNbObjets()));
	         document.add(new Paragraph("Nombre d'objets empruntés : " +  Database.getNbObjetsEmpruntes()));
	         document.close();
	         writer.close();
	      } catch (DocumentException e) {
	    	  e.printStackTrace();
	      } catch (FileNotFoundException e) {
	    	  e.printStackTrace();
	      } catch (MalformedURLException e) {
	    	  e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	public void actionPerformed(ActionEvent e) {
		this.frame.setContentPane(this);
	    this.frame.revalidate();
	    // Validation de la statistique choisie
	    if (btnValider.equals(e.getSource())) {
	        String data = "" + cb.getItemAt(cb.getSelectedIndex());  
	        int index = cb.getSelectedIndex(); 
	        JOptionPane d = new JOptionPane();
	        switch (index) {
	        	// Statistique du nombre d'objet dans la base
	        	case 0:
	        		d.showMessageDialog( this.frame, data + " : " + Database.getNbObjets());
	        		break;
	        	// Statistique du nombre d'objets empruntés
	        	case 1:
	        		// Récupère la date actuelle
		        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        	Date dateString = new Date();
		        	String dateActuelle = dateFormat.format(dateString);
		        	
		        	int compteur = 0;
		        	// pour chaque dates dans les emprunts
		            for (int i = 0; i < Database.getLesDatesEmprunts().size(); i++) {
		                dateDebut = Database.getLesDatesEmprunts().get(i).getDateDebut();
		                dateFin = Database.getLesDatesEmprunts().get(i).getDateFin();   
		     
		                try {
		                	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		                	 Date date = new Date();  
		                	 String sDate1 = formatter.format(date);  
				             Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);   
				             // si un emprunt est actuellement en cours
				             if(date1.before(dateFin) && date1.after(dateDebut)) {
				                	compteur += 1;
				             }
		                } catch (java.text.ParseException e2) {
		                    // TODO Auto-generated catch block
		                    e2.printStackTrace();
		                }		                
		                
		            }
		        	
	        		d.showMessageDialog(this.frame, data + " : " + compteur);
	        		break;
	        	case 2:
	        		/* on passe les variables en paramètre */
                	VueStatMaterielTrie materiel = new VueStatMaterielTrie(frame);
                	this.frame.setContentPane(materiel);
	        	    this.frame.revalidate();
	        		break;
	        	case 3:
	        		/* on passe les variables en paramètre */
                	VueEmpruntVisiteur empruntVisiteur = new VueEmpruntVisiteur(frame);
                	this.frame.setContentPane(empruntVisiteur);
	        	    this.frame.revalidate();
	        		break;
	        	case 4:
	        		// récupère la date actuelle
	        		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date dateString2 = new Date();
		        	String dateActuelle2 = dateFormat2.format(dateString2);
		        	
		        	int compteur2 = 0;

		            for (int i = 0; i < Database.getLesDatesEmprunts().size(); i++) {
		                dateDebut = Database.getLesDatesEmprunts().get(i).getDateDebut();
		                dateFin = Database.getLesDatesEmprunts().get(i).getDateFin();   

		                try {
		                	 String sDate1="2020-12-10";  
				             Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);
				             // si un emprunt est actuellement en cours
				             if(date1.before(dateFin) && date1.after(dateDebut)) {
				                	compteur2 += 1;
				             }
		                } catch (java.text.ParseException e2) {
		                    // TODO Auto-generated catch block
		                    e2.printStackTrace();
		                }		                
		                
		            }
	        		/* on passe les variables en paramètre */
                	VueEmpruntObjets empruntObjets = new VueEmpruntObjets(frame);
                	this.frame.setContentPane(empruntObjets);
	        	    this.frame.revalidate();
	        		break;
	        }
	    } else if (btnGenerer.equals(e.getSource())) {
	    	System.out.println("Fichier PDF généré avec succès!");
	    	generated();
	    	topdf();
	    }
	}
}