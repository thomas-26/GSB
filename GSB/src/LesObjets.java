import java.util.Date;

public class LesObjets {
	private int id;
	private String nom, login;
	private Date dateDebut, dateFin;
	
	// Constructeur qui prend en paramètre l'idEmprunt, la date de début et fin, l'heure de début et fin et le login du visiteur qui a emprunté l'objet
	public LesObjets(int id, String nom, Date uneDateDebut, Date uneDateFin, String unLogin) {
		this.id = id;
		this.nom = nom;
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
		this.login = unLogin;
	}
	
	/* fonction de récupération de l'id de l'objet emprunté
	*
	* @return l'id de l'objet emprunté
	*/
	public int getId() {
		return this.id;
	}
	
	/* fonction de récupération du nom de l'objet emprunté
	*
	* @return le nom de l'objet emprunté
	*/
	public String getNom() {
		return this.nom;
	}
	
	/* fonction de récupération de la date de début d'emprunt 
	*
	* @return la date de début d'emprunt
	*/
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de récupération de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Date getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de récupération du login du visiteur qui a emprunté l'objet
	*
	* @return l'id de l'objet emprunté
	*/
	public String getLogin() {
		return this.login;
	}
}