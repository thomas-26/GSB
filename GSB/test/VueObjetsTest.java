import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe VueObjet qui les gèrent
class VueObjetsTest {

	// fonction qui test si le nombre de matériel est correct
	@Test
	void testGetLesMateriels() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		lesMateriels = Database.getLesMateriels();
		Assert.assertEquals("Nombre de matériels incorrect",lesMateriels.size(),4);
	}
	
	// fonction qui test si tous les libellés sont récupérés
	@Test
	void testGetLibelle() {
		ArrayList<String> lesNoms = new ArrayList<String>();
		lesNoms = Database.getLibelle();
		Assert.assertEquals("Tous les libellés ne sont pas récupérés",lesNoms.size(),2);
	}
	
	// fonction qui test si le nombre de libellé récupéré est correct
	@Test
	void testGetNbLibelle() {
		Assert.assertEquals("Nombre d'états incorrect",Database.getNbLibelle(),2);
	}
	
	
	// fonction qui test si le nombre d'emprunt par objet est correct
	@Test
	void testGetNbEmpruntsParObjet() {
		ArrayList<MaterielTrie> lesEmprunts = new ArrayList<MaterielTrie>();
		lesEmprunts = Database.getNbEmpruntsParObjet();
		Assert.assertEquals("Nombre d'emprunt par objet incorrect",Database.getNbEmpruntsParObjet().size(),3);
	}
	
	// fonction qui test si le nombre d'emprunt par visiteur est correct
	@Test
	void testGetEmpruntsParVisiteurs() {
		ArrayList<EmpruntVisiteur> lesEmprunts = new ArrayList<EmpruntVisiteur>();
		lesEmprunts = Database.getEmpruntsParVisiteur();
		Assert.assertEquals("Nombre d'emprunt par visiteur incorrect",Database.getEmpruntsParVisiteur().size(),1);
	}

}
