// Classe Mat�riel qui h�rite d'objet
public class Materiel extends Objet{
	private float largeur, longueur;
	private int codeMateriel;
	
	// Constructeur qui prend en param�tre l'id et le nom de l'objet parent, la largeur et la longueur du mat�riel
	public Materiel(int id, String nom, float largeur, float longueur) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
	}
	
	// Constructeur qui prend en param�tre l'id et le nom de l'objet parent, la largeur, longueur, code du mat�riel
	public Materiel(int id, String nom, float largeur, float longueur, int codeMateriel) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
		this.codeMateriel = codeMateriel;
	}
	
	/* fonction de r�cup�ration de la largeur
	*
	* @return la largeur du mat�riel
	*/
	public float getLargeur() {
		return this.largeur;
	}
	
	/* fonction de r�cup�ration de la longueur
	*
	* @return la longueur du mat�riel
	*/
	public float getLongueur() {
		return this.longueur;
	}
	
	/* fonction de r�cup�ration du code mat�riel
	*
	* @return le code du mat�riel
	*/
	public int getCodeMateriel() {
		return this.codeMateriel;
	}
	
}