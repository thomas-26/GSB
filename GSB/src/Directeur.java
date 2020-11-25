public class Directeur {
	private int idDirecteur;
	private String nom, prenom, login, mdp;
	
	public Directeur(int idDirecteur, String nom, String prenom, String login, String mdp) {
		this.idDirecteur = idDirecteur;
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
	}

	public int getIdDirecteur() {
		return this.idDirecteur;
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

	public String getMdp() {
		return this.mdp;
	}
	
	
	
}
