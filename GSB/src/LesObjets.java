import java.util.Date;

public class LesObjets {
	private int id;
	private String nom, login;
	private Date dateDebut, dateFin;
	
	// Constructeur qui prend en param�tre l'idEmprunt, la date de d�but et fin, l'heure de d�but et fin et le login du visiteur qui a emprunt� l'objet
	public LesObjets(int id, String nom, Date uneDateDebut, Date uneDateFin, String unLogin) {
		this.id = id;
		this.nom = nom;
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
		this.login = unLogin;
	}
	
	/* fonction de r�cup�ration de l'id de l'objet emprunt�
	*
	* @return l'id de l'objet emprunt�
	*/
	public int getId() {
		return this.id;
	}
	
	/* fonction de r�cup�ration du nom de l'objet emprunt�
	*
	* @return le nom de l'objet emprunt�
	*/
	public String getNom() {
		return this.nom;
	}
	
	/* fonction de r�cup�ration de la date de d�but d'emprunt 
	*
	* @return la date de d�but d'emprunt
	*/
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de r�cup�ration de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Date getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de r�cup�ration du login du visiteur qui a emprunt� l'objet
	*
	* @return l'id de l'objet emprunt�
	*/
	public String getLogin() {
		return this.login;
	}
}