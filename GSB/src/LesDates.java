import java.util.Date;

// Classe LesDates qui r�cup�re les dates des emprunts
public class LesDates {
	private Date dateDebut;
	private Date dateFin;
	
	// Constructeur qui prend en param�tres la date de d�but et de fin des emprunts
	public LesDates(Date uneDateDebut, Date uneDateFin) {
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
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
}