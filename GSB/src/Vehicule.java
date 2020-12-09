// Classe V�hicule 
public class Vehicule {

	private String immatriculation, modele, marque;
	private int code, nbPlaces;

	// Constructeur qui prend en param�tre le code, l'immatriculation, le mod�le, la marque et le nb de places de la voiture
	public Vehicule(int code, String immatriculation, String modele, String marque, int nbPlaces) {
		this.code = code;
		this.immatriculation = immatriculation;
		this.modele = modele;
		this.marque = marque;
		this.nbPlaces = nbPlaces;
	}

	/* fonction de r�cup�ration du code
	*
	* @return le code
	*/
	public int getCode() {
		return this.code;
	}

	/* fonction de r�cup�ration de l'immatriculation
	*
	* @return l'immatriculation
	*/
	public String getImmatriculation() {
		return this.immatriculation;
	}

	/* fonction de r�cup�ration du mod�le
	*
	* @return le mod�le
	*/
	public String getModele() {
		return this.modele;
	}

	/* fonction de r�cup�ration de la marque
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

}