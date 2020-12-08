import java.util.Date;

// Classe LesDates qui récupère les dates des emprunts
public class LesDates {
	private Date dateDebut;
	private Date dateFin;
	
	// Constructeur qui prend en paramètres la date de début et de fin des emprunts
	public LesDates(Date uneDateDebut, Date uneDateFin) {
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
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
}