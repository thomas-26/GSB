// Classe Consulter qui sert à connaître le nombre d'emprunts par visiteur
public class EmpruntVisiteur {

	private int nbEmprunts;
	private String loginVisiteur;
	
	// Constructeur qui prend en paramètre le nombre d'emprunts et le login du visiteur 
	public EmpruntVisiteur(int unNbEmprunt, String unLoginVisiteur) {
		this.nbEmprunts = unNbEmprunt;
		this.loginVisiteur = unLoginVisiteur;
	}
	
	/* fonction de récupération du nombre d'emprunts
	*
	* @return le nombre d'emprunts
	*/
	public int getNbEmprunt() {
		return this.nbEmprunts;
	}
	
	/* fonction de récupération du login du visiteur
	*
	* @return le login du visiteur
	*/
	public String getLoginVisiteur() {
		return this.loginVisiteur;
	}
	
}
