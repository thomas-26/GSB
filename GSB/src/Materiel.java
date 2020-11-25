public class Materiel extends Objet{
	private float largeur, longueur;
	
	public Materiel(int id, String nom, float largeur, float longueur, String etat) {
		super(id,nom,etat);
		this.largeur = largeur;
		this.longueur = longueur;
	}
	
	public float getLargeur() {
		return this.largeur;
	}
	
	public float getLongueur() {
		return this.longueur;
	}
	
}
