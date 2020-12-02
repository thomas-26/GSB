
public class EmpruntVisiteur {

	private int nbEmprunts;
	private String loginVisiteur;
	
	public EmpruntVisiteur(int unNbEmprunt, String unLoginVisiteur) {
		this.nbEmprunts = unNbEmprunt;
		this.loginVisiteur = unLoginVisiteur;
	}
	
	public int getNbEmprunt() {
		return this.nbEmprunts;
	}
	
	public String getLoginVisiteur() {
		return this.loginVisiteur;
	}
	
}
