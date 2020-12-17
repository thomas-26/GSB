// Classe Véhicule 
public class Vehicule extends Objet{

	private String immatriculation, modele, marque;
	private int code, nbPlaces, codeType;

	// Constructeur qui prend en paramètre le code, l'immatriculation, le modèle, la marque et le nb de places de la voiture
	public Vehicule(int code, String immatriculation, String modele, String marque, int nbPlaces) {
		super(code,immatriculation);
		this.modele = modele;
		this.marque = marque;
		this.nbPlaces = nbPlaces;
	}
	
	// Constructeur qui prend en paramètre le code, l'immatriculation, le modèle, la marque, le nb de places et le codeType de la voiture
		public Vehicule(int code, String immatriculation, String modele, String marque, int nbPlaces, int codeType) {
			super(code,immatriculation);
			this.modele = modele;
			this.marque = marque;
			this.nbPlaces = nbPlaces;
			this.codeType = codeType;
		}

	/* fonction de récupération du modèle
	*
	* @return le modèle
	*/
	public String getModele() {
		return this.modele;
	}

	/* fonction de récupération de la marque
	*
	* @return la marque
	*/
	public String getMarque() {
		return this.marque;
	}

	/* fonction du nombre de places
	*
	* @return le nombre de places
	*/
	public int getNbPlaces() {
		return this.nbPlaces;
	}
	
	/* fonction du codeType
	*
	* @return le codeType
	*/
	public int getCodeType() {
		return this.codeType;
	}

}