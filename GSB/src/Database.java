import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;
	private static PreparedStatement preparedStatement, preparedStatement2;
	private static ResultSet result, resultObjets, resultLibelle, resultNbLibelle;
	private static int resultInsert, resultInsert2;
		
	/* fonction de connexion � la base de donn�es 
	 *   
	 * @exception SQLException au cas o� il y aurait une erreur lors de la connexion � la bdd
	 * @exception ClassNotFoundException au cas o� le driver jdbc ne serait pas charg�
	 * 
	 * */
	public static void connexionBdd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.250.7/gsb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","sio","slam");
			connexion.createStatement();
		}
		
		catch (SQLException e) {
			System.err.println("Erreur de connexion � la base de donn�ees " + e);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver non charg� " + e);
		}
	}
	
	/* fonction de d�connexion de notre base de donn�es 
	 * 
	 * @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	 * 
	 */
	public static void deconnexionBdd() {
		try {
			connexion.close();
		} catch (SQLException e) {
			System.err.println("Erreur lors de la d�connexion " + e);
		}
	}
	
	 /* fonction de connexion au formulaire d'authentification avec fonction de chiffrement md5
	 *
	 * @param login
	 * @param mdp
	 * @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	 * @return un bool�en en fonction du r�sultat de la requete
	 */
	public static boolean connexion(String login, String mdp) {
		boolean rep = false;
		try {
			connexionBdd();
			String rsInfo = "select count(login) as nb from authentification where login = ? and mdp = md5(?);";
			
			preparedStatement = connexion.prepareStatement(rsInfo);
			
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, mdp);
			
			result = preparedStatement.executeQuery();
			
			/* v�rifie avec le count que le nombre d'occurence est correct */
			if (result.next() && result.getInt("nb") > 0) {
				rep = true;
			}
			/* fermeture des flux */
			result.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rep;
	}
	
	/* fonction de r�cup�ration du r�le de l'utilisateur connect�
	*
	* @param login
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return une cha�ne contenant le r�le de l'utilisateur
	*/
	public static String getRole(String login) {
		String role = "";
		try {
			connexionBdd();
			String rsInfo = "select role from authentification where login = ?";
			
			preparedStatement = connexion.prepareStatement(rsInfo);
			preparedStatement.setString(1, login);	
			
			result = preparedStatement.executeQuery();
			
			/* v�rifie avec le count que le nombre d'occurence est correct */
			if (result.next()) {
				role = result.getString(1);
			}
			/* fermeture des flux */
			result.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

	/* fonction de r�cup�ration des mat�riels
	*
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return une lisete de mat�riels contenant les mat�riels
	*/ 
	public static ArrayList<Materiel> getLesMateriels() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		try {
			connexionBdd();
			String rsObjets = "select id, nom, longueur, largeur, codemateriel, etat from objet o, materiel m where m.code = id;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				int codeMateriel = resultObjets.getInt("codemateriel");
				float largeur = resultObjets.getFloat("largeur");
				String etat = resultObjets.getString("etat");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur, etat, codeMateriel));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	/* fonction de r�cup�ration des mat�riels emprunt�s
	*
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return une lisete de mat�riels contenant les mat�riels emprunt�s
	*/ 
	public static ArrayList<Materiel> getLesMaterielsEmpruntes() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		try {
			connexionBdd();
			String rsObjets = "select id, nom, longueur, largeur, codemateriel, etat from objet o, materiel m where m.code = id and etat = 'emprunte';";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				int codeMateriel = resultObjets.getInt("codemateriel");
				float largeur = resultObjets.getFloat("largeur");
				String etat = resultObjets.getString("etat");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur, etat, codeMateriel));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	/* fonction de r�cup�ration de l'�tat de chaque mat�riel
	*
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return une liste contenant l'�tat des mat�riels
	*/
	public static ArrayList<String> getLesEtats() {
		ArrayList<String> lesEtats = new ArrayList<String>();
		try {
			connexionBdd();
			String rsObjets = "select etat from objet o;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				String etat = resultObjets.getString("etat");
				
				lesEtats.add(new String(etat));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesEtats;
	}
	
	/* fonction de suppression du r�le de l'utilisateur connect�
	*
	* @param numero
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return un bool�en qui contient vrai si le mat�riel a bien �t� supprim�
	*/
	public static boolean supprimerObjet(int id) { 
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("delete from materiel where code = ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
			
			PreparedStatement statement2 = connexion.prepareStatement("delete from objet where id = ?;");
			statement2.setInt(1, id);
			statement2.executeUpdate();
			etat = true;
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	/* fonction de recherche d'un objet
	*
	* @param idObje
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return un bool�en qui contient vrai si le mat�riel a bien �t� trouv�
	*/
	public static boolean rechercherObjet(int idObjet) { 
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("select count(id) as nb from objet where id = ?;");
			statement.setInt(1, idObjet);
			resultObjets = statement.executeQuery();
			if (resultObjets.next()) {
				if (resultObjets.getInt("nb") == 1) {
					etat = true;
				}
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	/* fonction de suppression */
	public static boolean reserverObjet(int id) {
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("update objet set etat = 'emprunte' where id = ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
			
			etat = true;
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	/* fonction qui ajoute un visiteur avec les donn�es pass�es en param�tre */
	public static int ajouterObjet(int id, String nom, String etat, int longueur, int largeur, int codeMateriel) {
		try {
			connexionBdd();
			String rsInsert = "insert into objet (id, nom, etat) VALUES (?, ?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, etat);

			resultInsert = preparedStatement.executeUpdate();
			
			String rsInsert2 = "insert into materiel (code, longueur, largeur, codeMateriel) VALUES (?, ?, ?, ?);";
			preparedStatement2 = connexion.prepareStatement(rsInsert2);
			
			preparedStatement2.setInt(1, id);
			preparedStatement2.setInt(2, longueur);
			preparedStatement2.setInt(3, largeur);
			preparedStatement2.setInt(4, codeMateriel);
			
			preparedStatement2.executeUpdate();
			
			resultInsert2 = preparedStatement2.executeUpdate();
			
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert + resultInsert2;
	}
	
	/* fonction qui compte le nombre d'objets
	*
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return un entier qui contient le nombre d'objets
	*/
	public static int getNbObjets() {
		int nb = 0;
		try {
			connexionBdd();
			String rsObjets = "select count(id) as nb from objet";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();

			if (resultObjets.next()) {
				int getNb = resultObjets.getInt("nb");
				nb = getNb;
			}
			resultObjets.close();
			deconnexionBdd();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nb;
	}
	
	/* fonction qui compte le nombre d'objets emprunt�s
	*
	* @exception SQLException au cas o� il y aurait un probl�me lors de la d�connexion de la bdd
	* @return un entier qui contient le nombre d'objets emprunt�s
	*/
	public static int getNbObjetsEmpruntes() {
		int nb = 0;
		try {
			connexionBdd();
			String rsObjets = "select count(id) as nb from objet where etat = 'emprunte'";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();

			if (resultObjets.next()) {
				int getNb = resultObjets.getInt("nb");
				nb = getNb;
			}
			resultObjets.close();
			deconnexionBdd();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nb;
	}
	
	public static ArrayList<String> getLibelle() {
		ArrayList<String> lesNoms = new ArrayList<String>();
		try {
			connexionBdd();
			String rsLibelle = "select libelle from type_materiel;";
			
			preparedStatement = connexion.prepareStatement(rsLibelle);
			resultLibelle = preparedStatement.executeQuery();
			
			while (resultLibelle.next()) {
				lesNoms.add(resultLibelle.getString("libelle"));
			}
			resultLibelle.close();
			deconnexionBdd();	
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return lesNoms;
	}
	
	public static int getNbLibelle() {
		int nbLibelle = 0;
		try {
			connexionBdd();
			String rsNbObjets = "select count(libelle) as nbLibelle from type_materiel;";
			preparedStatement = connexion.prepareStatement(rsNbObjets);
			resultNbLibelle = preparedStatement.executeQuery();

			if (resultNbLibelle.next()) {
				int getNb = resultNbLibelle.getInt("nbLibelle");
				nbLibelle = getNb;
			}
			
			resultNbLibelle.close();
			deconnexionBdd();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nbLibelle;
	}
	
	public static ArrayList<MaterielTrie> getNbEmpruntsParVisiteur() {
		ArrayList<MaterielTrie> lesEmprunts = new ArrayList<MaterielTrie>();
		try {
			connexionBdd();
			String rsNbObjets = "SELECT COUNT(idobjet) as nbEmprunts, idEmprunt, idobjet, nom\n" + 
					"	FROM emprunt E, objet O, materiel M\n" + 
					"	WHERE E.idObjet = O.id\n" + 
					"	AND O.id = M.code\n" + 
					"	GROUP BY idobjet\n" + 
					"	ORDER BY nbEmprunts DESC;";
			preparedStatement = connexion.prepareStatement(rsNbObjets);
			resultNbLibelle = preparedStatement.executeQuery();

			while (resultNbLibelle.next()) {
				int compteur = resultNbLibelle.getInt(1);
				//String compteur = Integer.toString(resultNbLibelle.getInt(1)); 
				//String idEmprunt = Integer.toString(resultNbLibelle.getInt(2)); 
				//String idObjet = Integer.toString(resultNbLibelle.getInt(3)); 
				int idEmprunt = resultNbLibelle.getInt(2);
				int idObjet = resultNbLibelle.getInt(3);
				String nom = resultNbLibelle.getString(4);
				MaterielTrie materielTrie = new MaterielTrie(compteur, idEmprunt, idObjet, nom);
				lesEmprunts.add(materielTrie);
			}

			resultNbLibelle.close();
			deconnexionBdd();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesEmprunts;
	}	
	
}