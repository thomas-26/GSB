// Classe Matériel qui hérite d'objet
public class Materiel extends Objet{
	private float largeur, longueur;
	private int codeMateriel;
	
	// Constructeur qui prend en paramètre l'id et le nom de l'objet parent, la largeur et la longueur du matériel
	public Materiel(int id, String nom, float largeur, float longueur) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
	}
	
	// Constructeur qui prend en paramètre l'id et le nom de l'objet parent, la largeur, longueur, code du matériel
	public Materiel(int id, String nom, float largeur, float longueur, int codeMateriel) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
		this.codeMateriel = codeMateriel;
	}
	
	/* fonction de récupération de la largeur
	*
	* @return la largeur du matériel
	*/
	public float getLargeur() {
		return this.largeur;
	}
	
	/* fonction de récupération de la longueur
	*
	* @return la longueur du matériel
	*/
	public float getLongueur() {
		return this.longueur;
	}
	
	/* fonction de récupération du code matériel
	*
	* @return le code du matériel
	*/
	public int getCodeMateriel() {
		return this.codeMateriel;
	}
	
}