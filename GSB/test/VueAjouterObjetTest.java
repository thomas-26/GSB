import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe de test lors de l'jout d'un objet
class VueAjouterObjetTest {

	// fonction qui test si le mat�riel a bien �t� ajout� en comptant le nombre d'objets dans la base avant et apr�s l'ajout
	@Test
	void testAjouterMateriel() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMateriels();
		int tailleAvant = lesMateriels.size();
		Database.ajouterObjet(6,"marteau",15,10,1,"outil");
		lesMateriels = Database.getLesMateriels();
		int tailleApres = lesMateriels.size();
		System.out.println(tailleAvant + " " + tailleApres);
		Assert.assertNotEquals("Ajout non effectu�",tailleAvant,tailleApres);
	}
	
	// fonction qui test si le v�hicule a bien �t� ajout� en comptant le nombre d'objets dans la base avant et apr�s l'ajout
		@Test
		void testAjouterVehicule() {
			ArrayList<Vehicule> lesVehicules = new ArrayList<Vehicule>();
			lesVehicules = Database.getLesVehicules("camion");
			int tailleAvant = lesVehicules.size();
			Database.ajouterVehicule(7,"AA-123-AA","Vario 815 D blind�","Mercedes-Benz",2,"camion");
			lesVehicules = Database.getLesVehicules("camion");
			int tailleApres = lesVehicules.size();
			Assert.assertNotEquals("Ajout non effectu�",tailleAvant,tailleApres);
		}

}
