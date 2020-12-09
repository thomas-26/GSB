import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe de test qui v�rifie le nombre de mat�riels emprunt�s
class VueCatalogueTest {

	// fonction qui test si le nombre de mat�riels emprunt� est correct comptant le nombre d'objets dans la base avant et apr�s l'ajout
	@Test
	void getLesMaterielsEmpruntes() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMaterielsEmpruntes();
		Assert.assertEquals("Nombre de mat�riels incorrect", lesMateriels.size(), 1);
	}

}