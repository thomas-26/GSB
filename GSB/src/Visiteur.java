import java.util.ArrayList;

// Classe Visiteur 
public class Visiteur {
	private String nom, prenom, dateNaissance, mail;
	private int numeroTelephone;
	private ArrayList<Emprunt> lesEmprunts;
	
	// Constructeur qui prend en paramètre le nom, prénom, date de naissance, mail et numéro de téléphone du visiteur
	public Visiteur(String nom, String prenom, String dateNaissance, String mail, int numeroTelephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.mail = mail;
		this.numeroTelephone = numeroTelephone;
		this.lesEmprunts = new ArrayList<Emprunt>();
	}
	
	/* fonction de récupération du nom
	*
	* @return le nom
	*/
	public String getNom() {
		return this.nom;
	}
	
	/* fonction de récupération du prénom
	*
	* @return le prénom
	*/
	public String getPrenom() {
		return this.prenom;
	}
	
	/* fonction de récupération de la date de naissance
	*
	* @return la date de naissance
	*/
	public String getDateNaissance() {
		return this.dateNaissance;
	}
	
	/* fonction de récupération du numéro de téléphone
	*
	* @return le numéro de téléphone
	*/
	public int getNumeroTelephone() {
		return this.numeroTelephone;
	}
	
	/* fonction de récupération du mail
	*
	* @return le mail
	*/
	public String getMail() {
		return this.mail;
	}

	/* fonction de récupération des emprunts du visiteur
	*
	* @return un ArrayList contenant les emprunts du visiteur
	*/
	public ArrayList<Emprunt> getLesEmprunts() {
		return lesEmprunts;
	}
	
}
