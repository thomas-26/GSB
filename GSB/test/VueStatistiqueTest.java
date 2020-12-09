import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe VueStatistique qui affiche les statistiques
class VueStatistiqueTest {
	
	// fonction qui test si le nombre d'objet est correct
	@Test
	void testGetnbObjets() {
		Assert.assertEquals("Nombre d'�tats incorrect",Database.getNbObjets(),2);
	}

	// fonction qui test si le nombre d'objet emprunt� est correct
	@Test
	void testGetNbObjetsEmpruntes() {
		Assert.assertEquals("Nombre d'�tats incorrect",Database.getNbObjetsEmpruntes(),2);
	}

}
