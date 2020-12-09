 //Classe Authentification qui sert lors de la connexion à l'application 
public class Authentification {
	public String login, mdp;
	
	// Constructeur qui prend en paramètre le login et le mdp de l'utilisateur qui se connecte
	public Authentification(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
	}
	
	/* fonction de récupération du login 
	*
	* @return le login
	*/
	public String getLogin() {
		return this.login;
	}
	
	/* fonction de récupération du mot de passe 
	*
	* @return le mot de passe
	*/
	public String getMotDePasse() {
		return this.mdp;
	}
}
