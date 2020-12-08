// Classe Emprunter qui sert � voir tous les emprunts
public class Emprunt {
	private int idEmprunt;
	private String dateDebut, dateFin, heureDebut, heureFin;
	
	// Constructeur qui prend en param�tre l'idEmprunt, la date de d�but et fin, l'heure de d�but et fin des emprunts
	public Emprunt(int idEmprunt, String dateDebut, String dateFin, String heureDebut, String heureFin) {
		this.idEmprunt = idEmprunt;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.heureDebut = heureDebut;
		this.heureFin = heureFin;
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
	public String getDateDebut() {
		return this.dateDebut;
	}
	
	/* fonction de r�cup�ration de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public String getDateFin() {
		return this.dateFin;
	}
	
	/* fonction de r�cup�ration de l'heure de d�but d'emprunt 
	*
	* @return l'heure de d�but d'emprunt
	*/
	public String getHeureDebut() {
		return this.heureDebut;
	}
	
	/* fonction de r�cup�ration de la date de fin d'emprunt 
	*
	* @return la date de fin d'emprunt
	*/
	public String getHeureFin() {
		return this.heureFin;
	}
	
}
