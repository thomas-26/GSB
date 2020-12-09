import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe de test lors de l'jout d'un objet
class VueAjouterObjetTest {

	// fonction qui test si l'objet a bien été ajouté en comptant le nombre d'objets dans la base avant et après l'ajout
	@Test
	void testAjouterObjet() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMateriels();
		int tailleAvant = lesMateriels.size();
		Database.ajouterObjet(3,"marteau",15,10,1,"outil");
		lesMateriels = Database.getLesMateriels();
		int tailleApres = lesMateriels.size();
		System.out.println(tailleAvant + " " + tailleApres);
		Assert.assertNotEquals("Ajout non effectué",tailleAvant,tailleApres);
	}

}
