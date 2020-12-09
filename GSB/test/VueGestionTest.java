import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe VueGestion qui test la suppression d'un mat�riel
class VueGestionTest {

	// fonction qui test si le mat�riel a bien �t� supprim� en comptant le nombre d'objets dans la base avant et apr�s la suppression
	@Test
	void testSupprimerObjet() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMateriels();
		int tailleAvant = lesMateriels.size();
		Database.supprimerObjet(3);
		lesMateriels = Database.getLesMateriels();
		int tailleApres = lesMateriels.size();
		Assert.assertNotEquals("Suppression non effectu�",tailleAvant,tailleApres);
	}

}
