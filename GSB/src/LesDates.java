import java.util.Date;

public class LesDates {
	private Date dateDebut;
	private Date dateFin;
	
	public LesDates(Date uneDateDebut, Date uneDateFin) {
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
	}
	
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	public Date getDateFin() {
		return this.dateFin;
	}
}