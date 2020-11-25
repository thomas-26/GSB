import java.util.ArrayList;

public class Objet {
	private int id;
	private String nom;
	private String etat;
	private ArrayList<Statistique> lesStatistiques;
	private ArrayList<Emprunt> lesEmprunts;
	
	public Objet(int id, String nom, String etat) {
		this.id = id;
		this.nom = nom;
		this.etat = etat;
		this.lesStatistiques = new ArrayList<Statistique> ();
		this.lesEmprunts = new ArrayList<Emprunt>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getEtat() {
		return this.etat;
	}

	public ArrayList<Statistique> getLesStatistiques() {
		return this.lesStatistiques;
	}

	public ArrayList<Emprunt> getLesEmprunts() {
		return this.lesEmprunts;
	}
	
}