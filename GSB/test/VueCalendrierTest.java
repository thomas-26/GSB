import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

//Classe de test lors de l'emprunt d'un objet
class VueCalendrierTest {
	
	// fonction qui test si l'objet a bien �t� emprunt� en comptant le nombre d'objets emprunt�s dans la base avant et apr�s l'emprunt
	@Test
	void emprunterObjet() {
		int nbEmpruntAvant = Database.getNbObjetsEmpruntes();
		Database.emprunterObjet("2020-05-11","2020-05-20","14:00","18:00","dandre",2);
		int nbEmpruntApres = Database.getNbObjetsEmpruntes();
		System.out.println(nbEmpruntAvant + " " + nbEmpruntApres);
		Assert.assertNotEquals("Emprunt non effectu�",nbEmpruntAvant,nbEmpruntApres);
	}

}
