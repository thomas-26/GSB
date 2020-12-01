
public class MaterielTrie {

	private int nbEmprunts;
	private int idEmprunt;
	private int idObjet;
	private String nom;
	
	public MaterielTrie(int unNbEmprunt, int unIdEmprunt, int unIdObjet, String unNom) {
		this.nbEmprunts = unNbEmprunt;
		this.idEmprunt = unIdEmprunt;
		this.idObjet = idObjet;
		this.nom = unNom;
	}
	
	public int getNbEmprunt() {
		return this.nbEmprunts;
	}
	
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	public int getIdObjet() {
		return this.nbEmprunts;
	}
	
	public String getNom() {
		return this.nom;
	}
}
