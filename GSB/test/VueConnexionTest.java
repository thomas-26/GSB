import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

// Classe connexion qui verifie si les utilisateurs existent et ont leur bon r�le
class VueConnexionTest {

	// fonction qui test si tous les utilisateurs existent et poss�dent le bon r�le
	@Test
	void testGetRole() {
		Assert.assertEquals("R�le inexistant","visiteur",Database.getRole("dandre"));
		Assert.assertEquals("R�le inexistant","responsable",Database.getRole("mludo"));
		Assert.assertEquals("R�le inexistant","directeur",Database.getRole("dmartin"));
	}

}
