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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

// Classe VueStatistique qui affiche les statistiques
public class VueStatistique extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnValider, btnGenerer;
	private JLabel welcome, welcome2, welcome3, welcome4;
	private JComboBox cb;
	private JFrame frame;
	private Date dateActuelle, dateDebut, dateFin;
	
	// Constructeur qui prend en param�tre la fen�tre
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
		
		welcome3 = new JLabel("Un fichier au format PDF sera g�n�r�");
		welcome3.setFont(new Font("Arial", Font.PLAIN, 14));
		welcome3.setBounds(135, 30, 380, 100);
		
		welcome4 = new JLabel("Il contiendra les statistiques");
		welcome4.setFont(new Font("Arial", Font.PLAIN, 14));
		welcome4.setBounds(145, 45, 380, 100);
		
		/* JComboBox */
		String statistiques[]={"Nombre d'objets","Nombre objets emprunt�s","Nombre mat�riels emprunt�s tri�s","Nombre d'emprunt par visiteur","Objets emprunt�s"};        
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
		
		/* bouton de g�n�ration d'un PDF */
		btnGenerer = new JButton("G�n�rer");
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
    	JOptionPane.showMessageDialog(this, "Produit r�serv� !");
        repaint();
        revalidate();
	}
	
	public void generated() {
    	JOptionPane.showMessageDialog(this, "PDF g�n�r� avec succ�s !");
        repaint();
        revalidate();
	}
	
	/* fonction qui permet de g�n�rer un PDF avec les statistiques */
	public void topdf() {
		Document document = new Document();
	      try {
	    	 String filename = "C:\\Users\\tvercasson\\Documents\\pdf\\Statistiques.pdf";
	         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
	         document.open();
	         Image img = Image.getInstance("src/gsb.png");
	         img.scaleAbsolute(150f, 90f);	    
	         document.add(img);

	         /* titre t1 */
	         PdfPTable table = new PdfPTable(2);
	         PdfPCell c1 = new PdfPCell(new Phrase("nombre d'emprunts", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c1.setBackgroundColor(new BaseColor(102, 163, 211));
	         table.addCell(c1);
	         c1 = new PdfPCell(new Phrase("login visiteur", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c1.setBackgroundColor(new BaseColor(102, 163, 211));
	         table.addCell(c1);
	         table.setHeaderRows(1);

	         /* cr�ation du tableau du nombre d'objets emprunt� pour chaque visiteur */
	         for (int i = 0; i < Database.getEmpruntsParVisiteur().size(); i++) {
	        	 String nb = Integer.toString(Database.getEmpruntsParVisiteur().get(i).getNbEmprunt()); 
	        	 String loginv = Database.getEmpruntsParVisiteur().get(i).getLoginVisiteur();
	        	 table.addCell(nb);
		         table.addCell(loginv);
	         }

	         /* titre t2 */
	         PdfPTable table2 = new PdfPTable(4);
	         PdfPCell c2 = new PdfPCell(new Phrase("nombre d'emprunts", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c2.setBackgroundColor(new BaseColor(102, 163, 211));
	         table2.addCell(c2);
	         c2 = new PdfPCell(new Phrase("id emprunt", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c2.setBackgroundColor(new BaseColor(102, 163, 211));
	         table2.addCell(c2);
	         c2 = new PdfPCell(new Phrase("id objet", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c2.setBackgroundColor(new BaseColor(102, 163, 211));
	         table2.addCell(c2);
	         c2 = new PdfPCell(new Phrase("nom objet", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c2.setBackgroundColor(new BaseColor(102, 163, 211));
	         table2.addCell(c2);
	         table2.setHeaderRows(1);
	         
	         /* cr�ation du tableau du nombre de mat�riels emprunt�s (tri�s) */
	         for (int i = 0;i < Database.getNbEmpruntsParObjet().size(); i++) {
	        	 String nb = Integer.toString(Database.getNbEmpruntsParObjet().get(i).getNbEmprunt());
	        	 String idEmprunt = Integer.toString(Database.getNbEmpruntsParObjet().get(i).getIdEmprunt());
	        	 String idObjet = Integer.toString(Database.getNbEmpruntsParObjet().get(i).getIdObjet());
	        	 String nomObjet = Database.getNbEmpruntsParObjet().get(i).getNom();
	        	 
	        	 table2.addCell(nb);
	        	 table2.addCell(idEmprunt);
	        	 table2.addCell(idObjet);
	        	 table2.addCell(nomObjet);
	         }
	         
	         /* titre 3 */
	         PdfPTable table3 = new PdfPTable(5);
	         PdfPCell c3 = new PdfPCell(new Phrase("id objet", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c3.setBackgroundColor(new BaseColor(102, 163, 211));
	         table3.addCell(c3);
	         PdfPCell c4 = new PdfPCell(new Phrase("nom objet", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c4.setBackgroundColor(new BaseColor(102, 163, 211));
	         table3.addCell(c4);
	         PdfPCell c5 = new PdfPCell(new Phrase("date d�but", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c5.setBackgroundColor(new BaseColor(102, 163, 211));
	         table3.addCell(c5);
	         PdfPCell c6 = new PdfPCell(new Phrase("date fin", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c6.setBackgroundColor(new BaseColor(102, 163, 211));
	         table3.addCell(c6);
	         PdfPCell c7 = new PdfPCell(new Phrase("login visiteur", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	         c7.setBackgroundColor(new BaseColor(102, 163, 211));
	         table3.addCell(c7);
	         table3.setHeaderRows(1);
	         
	         /* cr�ation du tableau r�capitulatif des objets emprunt�s */
	         for (int i = 0; i < Database.getLesObjetsEmpruntes().size(); i++) {
	        	 String idobjet = Integer.toString(Database.getLesObjetsEmpruntes().get(i).getId());
	        	 String nomobjet = Database.getLesObjetsEmpruntes().get(i).getNom();
	        	 
	        	 /* r�cup�ration de la date de type Date, puis, conversion en String pour pouvoir la manipuler */
	        	 Date datedebut = Database.getLesObjetsEmpruntes().get(i).getDateDebut();
	      	     String pattern = "yyyy-MM-dd";
	        	 DateFormat df = new SimpleDateFormat(pattern);
	        	 String formateddebut = df.format(datedebut);
	        	 
	        	 /* meme manipulation */
	        	 Date datefin = Database.getLesObjetsEmpruntes().get(i).getDateFin();
	        	 String formatedfin = df.format(datefin);
	        	 
	        	 /* login du visiteur */
	        	 String loginv = Database.getLesObjetsEmpruntes().get(i).getLogin();
	        	 
	        	 table3.addCell(idobjet);
	        	 table3.addCell(nomobjet);
	        	 table3.addCell(formateddebut);
	        	 table3.addCell(formatedfin);
	        	 table3.addCell(loginv);
	         }
	         
	         Paragraph titre = new Paragraph("Statistiques des mat�riels",  FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18 , BaseColor.BLACK));
	         titre.setAlignment(Paragraph.ALIGN_CENTER);
	         
	         /* ajout des �l�ments au document cr��e */
	         document.add(titre);
	         document.add(new Paragraph(("\n")));
	         document.add(new Paragraph("               Nombre d'objets pr�sents : " +  Database.getNbObjets()));
	         document.add(new Paragraph("               Nombre d'objets emprunt�s : " +  Database.getNbObjetsEmpruntes()));
	         document.add(new Paragraph(("\n")));
	         document.add(table);
	         document.add(new Paragraph(("\n")));
	         document.add(table2);
	         document.add(new Paragraph(("\n")));
	         document.add(table3);
	         
	         /* fermeture des flux */
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
	        	// Statistique du nombre d'objets emprunt�s
	        	case 1:
	        		// R�cup�re la date actuelle
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
	        		/* on passe les variables en param�tre */
                	VueStatMaterielTrie materiel = new VueStatMaterielTrie(frame);
                	this.frame.setContentPane(materiel);
	        	    this.frame.revalidate();
	        		break;
	        	case 3:
	        		/* on passe les variables en param�tre */
                	VueEmpruntVisiteur empruntVisiteur = new VueEmpruntVisiteur(frame);
                	this.frame.setContentPane(empruntVisiteur);
	        	    this.frame.revalidate();
	        		break;
	        	case 4:
	        		// r�cup�re la date actuelle
	        		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		        	Date dateString2 = new Date();
		        	String dateActuelle2 = dateFormat2.format(dateString2);
		        	
		        	int compteur2 = 0;

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
				                	compteur2 += 1;
				             }
		                } catch (java.text.ParseException e2) {
		                    // TODO Auto-generated catch block
		                    e2.printStackTrace();
		                }		                
		                
		            }
	        		/* on passe les variables en param�tre */
                	VueEmpruntObjets empruntObjets = new VueEmpruntObjets(frame);
                	this.frame.setContentPane(empruntObjets);
	        	    this.frame.revalidate();
	        		break;
	        }
	    } else if (btnGenerer.equals(e.getSource())) {
	    	System.out.println("Fichier PDF g�n�r� avec succ�s!");
	    	generated();
	    	topdf();
	    }
	}
}