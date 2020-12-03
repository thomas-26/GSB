public class Materiel extends Objet{
	private float largeur, longueur;
	private int codeMateriel;
	
	public Materiel(int id, String nom, float largeur, float longueur) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
	}
	
	public Materiel(int id, String nom, float largeur, float longueur, int codeMateriel) {
		super(id,nom);
		this.largeur = largeur;
		this.longueur = longueur;
		this.codeMateriel = codeMateriel;
	}
	
	public float getLargeur() {
		return this.largeur;
	}
	
	public float getLongueur() {
		return this.longueur;
	}
	
	public int getCodeMateriel() {
		return this.codeMateriel;
	}
	
}