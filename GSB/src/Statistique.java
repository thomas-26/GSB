// Classe Statistique qui sont gérés par le directeur
public class Statistique {

	private int idStatistique, valeur;
	private String libelle;
	private Directeur leDirecteur;
	
	// Constructeur Statistique qui prend en paramètre l'idStatistique, la valeur, le libelle et  le directeur qui les gèrent
	public Statistique(int idStatistique, int valeur, String libelle, Directeur leDirecteur) {
		this.idStatistique = idStatistique;
		this.valeur = valeur;
		this.libelle = libelle;
		this.leDirecteur = leDirecteur;
	}
	
	/* fonction de récupération de l'idStatistique
	*
	* @return l'idStatistique
	*/
	public int getIdStatistique() {
		return this.idStatistique;
	}

	/* fonction de récupération de la valeur 
	*
	* @return la valeur 
	*/
	public int getValeur() {
		return this.valeur;
	}

	/* fonction de récupération du libelle
	*
	* @return le libelle
	*/
	public String getLibelle() {
		return this.libelle;
	}

	/* fonction de récupération du directeur
	*
	* @return le directeur
	*/
	public Directeur getLeDirecteur() {
		return leDirecteur;
	}
	
}
