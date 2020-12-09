//Classe Directeur qui sert pour le directeur
public class Directeur {
	private int idDirecteur;
	private String nom, prenom, login, mdp;
	
	// constructeur qui prend en paramètre l'id, le login, le nom, le prénom, login et le mdp du directeur
	public Directeur(int idDirecteur, String nom, String prenom, String login, String mdp) {
		this.idDirecteur = idDirecteur;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}

	/* fonction de récupération de l'id 
	*
	* @return l'id
	*/
	public int getIdDirecteur() {
		return this.idDirecteur;
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
	public String getMdp() {
		return this.mdp;
	}
	
	
	
}
