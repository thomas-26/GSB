import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;
	private static PreparedStatement preparedStatement, preparedStatement2;
	private static ResultSet result, resultObjets;
	private static int resultInsert, resultInsert2;
		
	/* fonction de connexion à la base de données 
	 *   
	 * @exception SQLException au cas où il y aurait une erreur lors de la connexion à la bdd
	 * @exception ClassNotFoundException au cas où le driver jdbc ne serait pas chargé
	 * 
	 * */
	public static void connexionBdd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			connexion.createStatement();
		}
		
		catch (SQLException e) {
			System.err.println("Erreur de connexion à la base de donnéees " + e);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver non chargé " + e);
		}
	}
	
	/* fonction de déconnexion de notre base de données 
	 * 
	 * @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	 * 
	 */
	public static void deconnexionBdd() {
		try {
			connexion.close();
		} catch (SQLException e) {
			System.err.println("Erreur lors de la déconnexion " + e);
		}
	}
	
	 /* fonction de connexion au formulaire d'authentification avec fonction de chiffrement md5
	 *
	 * @param login
	 * @param mdp
	 * @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	 * @return un booléen en fonction du résultat de la requete
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
			
			/* vérifie avec le count que le nombre d'occurence est correct */
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
	
	/* fonction de récupération du rôle de l'utilisateur connecté
	*
	* @param login
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une chaîne contenant le röle de l'utilisateur
	*/
	public static String getRole(String login) {
		String role = "";
		try {
			connexionBdd();
			String rsInfo = "select role from authentification where login = ?";
			
			preparedStatement = connexion.prepareStatement(rsInfo);
			preparedStatement.setString(1, login);	
			
			result = preparedStatement.executeQuery();
			
			/* vérifie avec le count que le nombre d'occurence est correct */
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

	/* fonction de récupération des matériels
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une lisete de matériels contenant les matériels
	*/ 
	public static ArrayList<Materiel> getLesMateriels() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		try {
			connexionBdd();
			String rsObjets = "select id, nom, longueur, largeur, etat from objet o, materiel m where m.code = id;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				float largeur = resultObjets.getFloat("largeur");
				String etat = resultObjets.getString("etat");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur, etat));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	/* fonction de récupération de l'état de chaque matériel
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une liste contenant l'état des matériels
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
	
	/* fonction de suppression du rôle de l'utilisateur connecté
	*
	* @param numero
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return un booléen qui contient vrai si le matériel a bien été supprimé
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
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return un booléen qui contient vrai si le matériel a bien été trouvé
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
			PreparedStatement statement = connexion.prepareStatement("update objet set etat = 'non disponible' where id = ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
			
			etat = true;
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return etat;
	}
	
	/* fonction qui ajoute un visiteur avec les données passées en paramètre */
	public static int ajouterObjet(int id, String nom, String etat, int longueur, int largeur) {
		try {
			connexionBdd();
			String rsInsert = "insert into objet (id, nom, etat) VALUES (?, ?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);

			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, etat);

			resultInsert = preparedStatement.executeUpdate();
			
			String rsInsert2 = "insert into materiel (code, longueur, largeur) VALUES (?, ?, ?);";
			preparedStatement2 = connexion.prepareStatement(rsInsert2);
			
			preparedStatement2.setInt(1, id);
			preparedStatement2.setInt(2, longueur);
			preparedStatement2.setInt(3, largeur);
			
			preparedStatement2.executeUpdate();
			
			resultInsert2 = preparedStatement2.executeUpdate();
			
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert + resultInsert2;
	}
	
	
	
}