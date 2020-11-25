import java.util.ArrayList;

public class Visiteur {
	private String nom, prenom, dateNaissance, mail;
	private int numeroTelephone;
	private ArrayList<Emprunt> lesEmprunts;
	
	public Visiteur(String nom, String prenom, String dateNaissance, String mail, int numeroTelephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.mail = mail;
		this.numeroTelephone = numeroTelephone;
		this.lesEmprunts = new ArrayList<Emprunt>();
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getDateNaissance() {
		return this.dateNaissance;
	}
	
	public int getNumeroTelephone() {
		return this.numeroTelephone;
	}
	
	public String getMail() {
		return this.mail;
	}

	public ArrayList<Emprunt> getLesEmprunts() {
		return lesEmprunts;
	}
	
}
