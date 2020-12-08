// Classe Responsable qui gère les catalogues
public class Responsable {
	private int idResponsable;
	private String nom, prenom, login, mdp;
	
	// Constructeur qui prend en paramètre l'id, le nom, le prénom, le login et le mdp du responsable
	public Responsable(int idResponsable, String nom, String prenom, String login, String mdp) {	
		this.idResponsable = idResponsable;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}
	
	/* fonction de récupération de l'id
	*
	* @return l'id
	*/
	public int getIdResponsable() {
		return this.idResponsable;
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
	
	/* fonction de récupération du login
	*
	* @return le login
	*/
	public String getLogin() {
		return this.login;
	}
	
	/* fonction de récupération du mdp
	*
	* @return le mdp
	*/
	public String getMotDePasse() {
		return this.mdp;
	}
	
	
	
	
	
}
