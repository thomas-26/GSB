import java.util.Date;
import java.sql.Time;

// Classe Consulter qui sert au visiteur connect� � voir ses emprunts
public class Consulter {
	
	private int idEmprunt;
	private Date dateDebut;
	private Date dateFin;
	private Time heureDebut;
	private Time heureFin;
	private int idObjet;
	
	// Constructeur qui prend en param�tre l'idEmprunt, la date de d�but et fin, l'heure de d�but et fin, l'idObjet de tous les objets pour l'utilisateur connect�
	public Consulter(int unIdEmprunt, Date uneDateDebut, Date uneDateFin, Time uneHeureDebut, Time uneHeureFin, int unIdObjet) {
		this.idEmprunt = unIdEmprunt;
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
		this.heureDebut = uneHeureDebut;
		this.heureFin = uneHeureFin;
		this.idObjet = unIdObjet;
	}

	/* fonction de r�cup�ration de l'id de l'emprunt 
	*
	* @return l'id de l'emprunt
	*/
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	/* fonction de r�cup�ration de la date de d�but d'emprunt 
	*
	* @return la date de d�but d'emprunt
	*/
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de r�cup�ration de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Date getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de r�cup�ration de l'heure de d�but d'emprunt 
	*
	* @return l'heure de d�but d'emprunt
	*/
	public Time getHeureDebut() {
		return this.heureDebut;
	}
	
	/* fonction de r�cup�ration de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public Time getHeureFin() {
		return this.heureFin;
	}
	
	/* fonction de r�cup�ration de l'id de l'objet emprunt�
	*
	* @return l'id de l'objet emprunt�
	*/
	public int getIdObjet() {
		return this.idObjet;
	}
	
}