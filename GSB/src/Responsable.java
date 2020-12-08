// Classe Responsable qui g�re les catalogues
public class Responsable {
	private int idResponsable;
	private String nom, prenom, login, mdp;
	
	// Constructeur qui prend en param�tre l'id, le nom, le pr�nom, le login et le mdp du responsable
	public Responsable(int idResponsable, String nom, String prenom, String login, String mdp) {	
		this.idResponsable = idResponsable;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}
	
	/* fonction de r�cup�ration de l'id
	*
	* @return l'id
	*/
	public int getIdResponsable() {
		return this.idResponsable;
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
	
	/* fonction de r�cup�ration du login
	*
	* @return le login
	*/
	public String getLogin() {
		return this.login;
	}
	
	/* fonction de r�cup�ration du mdp
	*
	* @return le mdp
	*/
	public String getMotDePasse() {
		return this.mdp;
	}
	
	
	
	
	
}
