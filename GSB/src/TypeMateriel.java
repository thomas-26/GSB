// Classe TypeMateril qui est lié aux matériels
public class TypeMateriel {
	private int code;
	private String libelle;
	
	// Constructeur qui prend en paramètre le code et le libellé 
	public TypeMateriel(int code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	/* fonction de récupération du code
	*
	* @return le code
	*/
	public int getCode() {
		return this.code;
	}

	/* fonction de récupération du libelle
	*
	* @return le libelle
	*/
	public String getLibelle() {
		return this.libelle;
	}
	
}
