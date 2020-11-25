public class Vehicule {
	
	private String immatriculation, modele, marque;
	private int nbPlaces;
	
	public Vehicule(String immatriculation, String modele, String marque, int nbPlaces) {
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.marque = marque;
		this.nbPlaces = nbPlaces;
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
