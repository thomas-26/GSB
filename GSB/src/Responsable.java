public class Responsable {
	private int idResponsable;
	private String nom, prenom, login, mdp;
	
	public Responsable(int idResponsable, String nom, String prenom, String login, String mdp) {	
		this.idResponsable = idResponsable;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}
	
	public int getIdResponsable() {
		return this.idResponsable;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public String getMotDePasse() {
		return this.mdp;
	}
	
	
	
	
	
}
