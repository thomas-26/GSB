// Classe Statistique qui sont g�r�s par le directeur
public class Statistique {

	private int idStatistique, valeur;
	private String libelle;
	private Directeur leDirecteur;
	
	// Constructeur Statistique qui prend en param�tre l'idStatistique, la valeur, le libelle et  le directeur qui les g�rent
	public Statistique(int idStatistique, int valeur, String libelle, Directeur leDirecteur) {
		this.idStatistique = idStatistique;
		this.valeur = valeur;
		this.libelle = libelle;
		this.leDirecteur = leDirecteur;
	}
	
	/* fonction de r�cup�ration de l'idStatistique
	*
	* @return l'idStatistique
	*/
	public int getIdStatistique() {
		return this.idStatistique;
	}

	/* fonction de r�cup�ration de la valeur 
	*
	* @return la valeur 
	*/
	public int getValeur() {
		return this.valeur;
	}

	/* fonction de r�cup�ration du libelle
	*
	* @return le libelle
	*/
	public String getLibelle() {
		return this.libelle;
	}

	/* fonction de r�cup�ration du directeur
	*
	* @return le directeur
	*/
	public Directeur getLeDirecteur() {
		return leDirecteur;
	}
	
}
