import java.util.ArrayList;

// Classe Visiteur 
public class Visiteur {
	private String nom, prenom, dateNaissance, mail;
	private int numeroTelephone;
	private ArrayList<Emprunt> lesEmprunts;
	
	// Constructeur qui prend en param�tre le nom, pr�nom, date de naissance, mail et num�ro de t�l�phone du visiteur
	public Visiteur(String nom, String prenom, String dateNaissance, String mail, int numeroTelephone) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.mail = mail;
		this.numeroTelephone = numeroTelephone;
		this.lesEmprunts = new ArrayList<Emprunt>();
	}
	
	/* fonction de r�cup�ration du nom
	*
	* @return le nom
	*/
	public String getNom() {
		return this.nom;
	}
	
	/* fonction de r�cup�ration du pr�nom
	*
	* @return le pr�nom
	*/
	public String getPrenom() {
		return this.prenom;
	}
	
	/* fonction de r�cup�ration de la date de naissance
	*
	* @return la date de naissance
	*/
	public String getDateNaissance() {
		return this.dateNaissance;
	}
	
	/* fonction de r�cup�ration du num�ro de t�l�phone
	*
	* @return le num�ro de t�l�phone
	*/
	public int getNumeroTelephone() {
		return this.numeroTelephone;
	}
	
	/* fonction de r�cup�ration du mail
	*
	* @return le mail
	*/
	public String getMail() {
		return this.mail;
	}

	/* fonction de r�cup�ration des emprunts du visiteur
	*
	* @return un ArrayList contenant les emprunts du visiteur
	*/
	public ArrayList<Emprunt> getLesEmprunts() {
		return lesEmprunts;
	}
	
}
