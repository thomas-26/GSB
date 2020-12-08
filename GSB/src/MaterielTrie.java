// Classe Mat�rielTrie qui trie les mat�riels des plus emprunt�s aux moins emprunt�s
public class MaterielTrie {

	private int nbEmprunts;
	private int idEmprunt;
	private int idObjet;
	private String nom;
	
	
	// Constructeur prenant en param�tre le nombre d'emprunt, l'id de l'emprunt, l'id de l'objet emprunt� et son nom
	public MaterielTrie(int unNbEmprunt, int unIdEmprunt, int unIdObjet, String unNom) {
		this.nbEmprunts = unNbEmprunt;
		this.idEmprunt = unIdEmprunt;
		this.idObjet = unIdObjet;
		this.nom = unNom;
	}
	
	/* fonction de r�cup�ration du nombre d'emprunts
	*
	* @return le nombre d'emprunts
	*/
	public int getNbEmprunt() {
		return this.nbEmprunts;
	}
	
	/* fonction de r�cup�ration de l'id d'emprunt
	*
	* @return l'id de l'emprunt
	*/
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	/* fonction de r�cup�ration de l'id de l'objet emprunt�
	*
	* @return l'id de l'objet emprunt�
	*/
	public int getIdObjet() {
		return this.idObjet;
	}
	
	/* fonction de r�cup�ration du nom de l'objet emprunt�
	*
	* @return le nom de l'objet emprunt�
	*/
	public String getNom() {
		return this.nom;
	}
}
