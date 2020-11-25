public class Emprunt {
	private int idEmprunt;
	private String dateDebut, dateFin, heureDebut, heureFin;
	
	public Emprunt(int idEmprunt, String dateDebut, String dateFin, String heureDebut, String heureFin) {
		this.idEmprunt = idEmprunt;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
	}
	
	public int getIdEmprunt() {
		return this.idEmprunt;
	}
	
	public String getDateDebut() {
		return this.dateDebut;
	}
	
	public String getDateFin() {
		return this.dateFin;
	}
	
	public String getHeureDebut() {
		return this.heureDebut;
	}
	
	public String getHeureFin() {
		return this.heureFin;
	}
	
}
