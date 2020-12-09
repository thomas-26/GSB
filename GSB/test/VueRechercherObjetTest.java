import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe VueRechercherObjet qui vérifie la recherche d'un objet
class VueRechercherObjetTest {

	// fonction qui test si un objet est présent dans le catalogue
	@Test
	void testRechercherObjet() {
		boolean recherche = Database.rechercherObjet(1);
		Assert.assertTrue("Recherche impossible",recherche);
	}

}
