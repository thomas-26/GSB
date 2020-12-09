import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe connexion qui verifie si les utilisateurs existent et ont leur bon rôle
class VueConnexionTest {

	// fonction qui test si tous les utilisateurs existent et possèdent le bon rôle
	@Test
	void testGetRole() {
		Assert.assertEquals("Rôle inexistant","visiteur",Database.getRole("dandre"));
		Assert.assertEquals("Rôle inexistant","responsable",Database.getRole("mludo"));
		Assert.assertEquals("Rôle inexistant","directeur",Database.getRole("dmartin"));
	}

}
