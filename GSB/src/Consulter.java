import java.util.Date;
import java.sql.Time;

public class Consulter {
	
	private int idEmprunt;
	private Date dateDebut;
	private Date dateFin;
	private Time heureDebut;
	private Time heureFin;
	private int idObjet;
	
	public Consulter(int unIdEmprunt, Date uneDateDebut, Date uneDateFin, Time uneHeureDebut, Time uneHeureFin, int unIdObjet) {
		this.idEmprunt = unIdEmprunt;
		this.dateDebut = uneDateDebut;
		this.dateFin = uneDateFin;
		this.heureDebut = uneHeureDebut;
		this.heureFin = uneHeureFin;
		this.idObjet = unIdObjet;
	}

	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	public Date getDateFin() {
		return this.dateFin;
	}
	
	public Time getHeureDebut() {
		return this.heureDebut;
	}
	
	public Time getHeureFin() {
		return this.heureFin;
	}
	
	public int getIdObjet() {
		return this.idObjet;
	}
	
}