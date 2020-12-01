import java.util.ArrayList;

public class Catalogue {
	private int numero;
	private String nom;
	private ArrayList<Materiel> lesMateriels;

	public Catalogue(int numero, String nom) {
		this.numero = numero;
		this.nom = nom;
		this.lesMateriels = new ArrayList<Materiel>();
	}

	public int getNumero() {
		return this.numero;
	}

	public String getNom() {
		return this.nom;
	}
	
	public ArrayList<Materiel> getLesMateriels() {
		return this.lesMateriels;
	}

}