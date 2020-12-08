// Classe TypeMateril qui est li� aux mat�riels
public class TypeMateriel {
	private int code;
	private String libelle;
	
	// Constructeur qui prend en param�tre le code et le libell� 
	public TypeMateriel(int code, String libelle) {
		this.code = code;
		this.libelle = libelle;
	}

	/* fonction de r�cup�ration du code
	*
	* @return le code
	*/
	public int getCode() {
		return this.code;
	}

	/* fonction de r�cup�ration du libelle
	*
	* @return le libelle
	*/
	public String getLibelle() {
		return this.libelle;
	}
	
}
