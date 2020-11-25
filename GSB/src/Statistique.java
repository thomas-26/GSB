public class Statistique {

	private int idStatistique, valeur;
	private String libelle;
	private Directeur leDirecteur;
	
	public Statistique(int idStatistique, int valeur, String libelle, Directeur leDirecteur) {
		this.idStatistique = idStatistique;
		this.valeur = valeur;
		this.libelle = libelle;
		this.leDirecteur = leDirecteur;
	}
	
	public int getIdStatistique() {
		return this.idStatistique;
	}

	public int getValeur() {
		return this.valeur;
	}

	public String getLibelle() {
		return this.libelle;
	}

	public Directeur getLeDirecteur() {
		return leDirecteur;
	}
	
}
