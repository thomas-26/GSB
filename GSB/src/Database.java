import java.sql.*;
import java.util.ArrayList;

public class Database {
	private static Connection connexion;
	private static PreparedStatement preparedStatement;
	private static ResultSet result, resultObjets;
		
	/* fonction de connexion à la base de données 
	 *   
	 * @exception SQLException au cas où il y aurait une erreur lors de la connexion à la bdd
	 * @exception ClassNotFoundException au cas où le driver jdbc ne serait pas chargé
	 * 
	 * */
	public static void connexionBdd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.250.7/gsb?user=sio&password=slam");
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
	public static boolean supprimerObjet(int numero) { 
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("delete from materiel where code = ?;");
			statement.setInt(1, numero);
			statement.executeUpdate();
			PreparedStatement statement2 = connexion.prepareStatement("delete from objet where id = ?;");
			statement2.setInt(1, numero);
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
}