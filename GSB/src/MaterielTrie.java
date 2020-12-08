// Classe MatérielTrie qui trie les matériels des plus empruntés aux moins empruntés
public class MaterielTrie {

	private int nbEmprunts;
	private int idEmprunt;
	private int idObjet;
	private String nom;
	
	
	// Constructeur prenant en paramètre le nombre d'emprunt, l'id de l'emprunt, l'id de l'objet emprunté et son nom
	public MaterielTrie(int unNbEmprunt, int unIdEmprunt, int unIdObjet, String unNom) {
		this.nbEmprunts = unNbEmprunt;
		this.idEmprunt = unIdEmprunt;
		this.idObjet = unIdObjet;
		this.nom = unNom;
	}
	
	/* fonction de récupération du nombre d'emprunts
	*
	* @return le nombre d'emprunts
	*/
	public int getNbEmprunt() {
		return this.nbEmprunts;
	}
	
	/* fonction de récupération de l'id d'emprunt
	*
	* @return l'id de l'emprunt
	*/
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	/* fonction de récupération de l'id de l'objet emprunté
	*
	* @return l'id de l'objet emprunté
	*/
	public int getIdObjet() {
		return this.idObjet;
	}
	
	/* fonction de récupération du nom de l'objet emprunté
	*
	* @return le nom de l'objet emprunté
	*/
	public String getNom() {
		return this.nom;
	}
}
