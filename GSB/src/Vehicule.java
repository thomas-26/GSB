public class Vehicule {

	private String immatriculation, modele, marque;
	private int code, nbPlaces;

	public Vehicule(int code, String immatriculation, String modele, String marque, int nbPlaces) {
		this.code = code;
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.marque = marque;
		this.nbPlaces = nbPlaces;
	}

	public int getCode() {
		return this.code;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public String getModele() {
		return this.modele;
	}

	public String getMarque() {
		return this.marque;
	}

	public int getNbPlaces() {
		return this.nbPlaces;
	}

}