import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe de test qui vérifie le nombre de matériels empruntés
class VueCatalogueTest {

	// fonction qui test si le nombre de matériels emprunté est correct comptant le nombre d'objets dans la base avant et après l'ajout
	@Test
	void getLesMaterielsEmpruntes() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMaterielsEmpruntes();
		Assert.assertEquals("Nombre de matériels incorrect", lesMateriels.size(), 1);
	}

}