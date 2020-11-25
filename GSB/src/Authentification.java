public class Authentification {
	public String login, mdp;
	
	public Authentification(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getMotDePasse() {
		return this.mdp;
	}
}
