import java.util.ArrayList;

// Classe Objet qui contient les objets empruntables
public class Objet {
	private int id;
	private String nom;
	private ArrayList<Statistique> lesStatistiques;
	private ArrayList<Emprunt> lesEmprunts;
	
	// Constructeur qui prend en param�tre l'id et le nom 
	public Objet(int id, String nom) {
		this.id = id;
		this.nom = nom;
		this.lesStatistiques = new ArrayList<Statistique> ();
		this.lesEmprunts = new ArrayList<Emprunt>();
	}
	
	/* fonction de r�cup�ration de l'id de l'objet
	*
	* @return l'id de l'objet
	*/
	public int getId() {
		return this.id;
	}
	
	/* fonction de r�cup�ration du nom de l'objet 
	*
	* @return le nom de l'objet
	*/
	public String getNom() {
		return this.nom;
	}
	
	/* fonction de r�cup�ration des statistiques
	*
	* @return un ArrayList de type Statistique
	*/
	public ArrayList<Statistique> getLesStatistiques() {
		return this.lesStatistiques;
	}

	/* fonction de r�cup�ration des emprunts
	*
	* @return un ArrayList de type Emprunt
	*/
	public ArrayList<Emprunt> getLesEmprunts() {
		return this.lesEmprunts;
	}
	
}