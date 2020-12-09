import java.util.Date;
import java.sql.Time;

// Classe Consulter qui sert au visiteur connecté à voir ses emprunts
public class Consulter {
	
	private int idEmprunt;
	private Date dateDebut;
	private Date dateFin;
	private Time heureDebut;
	private Time heureFin;
	private int idObjet;
	
	// Constructeur qui prend en paramètre l'idEmprunt, la date de début et fin, l'heure de début et fin, l'idObjet de tous les objets pour l'utilisateur connecté
	public Consulter(int unIdEmprunt, Date uneDateDebut, Date uneDateFin, Time uneHeureDebut, Time uneHeureFin, int unIdObjet) {
		this.idEmprunt = unIdEmprunt;
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
		this.heureDebut = uneHeureDebut;
		this.heureFin = uneHeureFin;
		this.idObjet = unIdObjet;
	}

	/* fonction de récupération de l'id de l'emprunt 
	*
	* @return l'id de l'emprunt
	*/
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	/* fonction de récupération de la date de début d'emprunt 
	*
	* @return la date de début d'emprunt
	*/
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de récupération de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Date getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de récupération de l'heure de début d'emprunt 
	*
	* @return l'heure de début d'emprunt
	*/
	public Time getHeureDebut() {
		return this.heureDebut;
	}
	
	/* fonction de récupération de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Time getHeureFin() {
		return this.heureFin;
	}
	
	/* fonction de récupération de l'id de l'objet emprunté
	*
	* @return l'id de l'objet emprunté
	*/
	public int getIdObjet() {
		return this.idObjet;
	}
	
}