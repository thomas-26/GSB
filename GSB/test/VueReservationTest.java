import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class VueReservationTest {

	
	@Test
	void testEmprunterObjet() {
		int etatAvant  = Database.getNbObjetsEmpruntes();
		Database.emprunterObjet("2020-01-10", "2020-01-15", "07:00", "19:00", "dandre", 1);
		int etatApres  = Database.getNbObjetsEmpruntes();
		Assert.assertNotEquals("Ajout non effectué",etatAvant,etatApres);
	}
	
	@Test
	void testEmprunterObjetDate() {
		int dejaEmprunte = Database.emprunterObjetDate("2020-01-10", "2020-01-15", "08:00", "19:00", 1);
		Assert.assertEquals("Objet déjà réservé",dejaEmprunte,0);
		int dateDisponible = Database.emprunterObjetDate("2020-01-16", "2020-01-20", "08:00", "19:00", 1);
		Assert.assertEquals("Objet disponible",dateDisponible,1);
		int datePlusTot = Database.emprunterObjetDate("2020-01-05", "2020-01-10", "09:00", "19:00", 1);
		Assert.assertEquals("Réservez plus tôt",datePlusTot,2);
		int datePlusTard = Database.emprunterObjetDate("2020-01-15", "2020-01-20", "07:00", "19:00", 1);
		Assert.assertEquals("Réservez plus tard",datePlusTard,3);
	}

}
