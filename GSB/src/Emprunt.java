// Classe Emprunter qui sert à voir tous les emprunts
public class Emprunt {
	private int idEmprunt;
	private String dateDebut, dateFin, heureDebut, heureFin;
	
	// Constructeur qui prend en paramètre l'idEmprunt, la date de début et fin, l'heure de début et fin des emprunts
	public Emprunt(int idEmprunt, String dateDebut, String dateFin, String heureDebut, String heureFin) {
		this.idEmprunt = idEmprunt;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
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
	public String getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de récupération de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public String getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de récupération de l'heure de début d'emprunt 
	*
	* @return l'heure de début d'emprunt
	*/
	public String getHeureDebut() {
		return this.heureDebut;
	}
	
	/* fonction de récupération de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public String getHeureFin() {
		return this.heureFin;
	}
	
}
