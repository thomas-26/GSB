import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
	private static Connection connexion;
	private static PreparedStatement preparedStatement, preparedStatement2, preparedStatement3;
	private static ResultSet result, resultObjets, resultLibelle, resultNbLibelle, resultEmprunt, resultVehicules;
	private static int resultInsert, resultInsert2, resultInsert3;
		
	/* fonction de connexion à la base de données 
	 *   
	 * @exception SQLException au cas où il y aurait une erreur lors de la connexion à la bdd
	 * @exception ClassNotFoundException au cas où le driver jdbc ne serait pas chargé
	 * 
	 * */
	public static void connexionBdd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.250.7/gsb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","sio","slam");
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
			String rsObjets = "select id, nom, longueur, largeur, codemateriel from objet o, materiel m where m.code = id;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				int codeMateriel = resultObjets.getInt("codemateriel");
				float largeur = resultObjets.getFloat("largeur");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur, codeMateriel));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	/* fonction de récupération des matériels
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une lisete de matériels contenant les matériels
	*/ 
	public static ArrayList<Materiel> getLesMateriels(String nomLibelle) {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		try {
			connexionBdd();
			String rsObjets = "select id, nom, longueur, largeur from objet o, materiel m, type_materiel t where m.code = id and t.code = m.codemateriel and libelle = ?;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			preparedStatement.setString(1, nomLibelle);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				float largeur = resultObjets.getFloat("largeur");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	
	/* Fonction à faire en vérifiant la date actuelle*/
	
	/* fonction de récupération des matériels empruntés
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une lisete de matériels contenant les matériels empruntés
	*/ 
	public static ArrayList<Materiel> getLesMaterielsEmpruntes() {
		ArrayList<Materiel> lesMateriels = new ArrayList<Materiel>();
		try {
			connexionBdd();
			String rsObjets = "select id, nom, longueur, largeur, codemateriel from objet o, materiel m,emprunt e where m.code = id and idObjet = id;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("id");
				String nom = resultObjets.getString("nom");
				float longueur = resultObjets.getFloat("longueur");
				int codeMateriel = resultObjets.getInt("codemateriel");
				float largeur = resultObjets.getFloat("largeur");
				
				lesMateriels.add(new Materiel(id, nom, longueur, largeur, codeMateriel));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesMateriels;
	}
	
	public static ArrayList<Vehicule> getLesVehicules(String nomLibelle) {
		ArrayList<Vehicule> lesVehicules = new ArrayList<Vehicule>();
		try {
			connexionBdd();
			String rsObjets = "select v.code, immat, modele, marque, nbplaces from vehicule v, type_vehicule where v.code = type_vehicule.code and libelle = ?;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			preparedStatement.setString(1, nomLibelle);
			resultVehicules = preparedStatement.executeQuery();

			while (resultVehicules.next()) {
				int code = resultVehicules.getInt("code");
				String immat = resultVehicules.getString("immat");
				String modele = resultVehicules.getString("modele");
				String marque = resultVehicules.getString("marque");
				int nbplaces = resultVehicules.getInt("nbplaces");

				lesVehicules.add(new Vehicule(code, immat, modele, marque, nbplaces));
			}
			resultVehicules.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesVehicules;
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
	
	/*
	 * fonction de suppression du rôle de l'utilisateur connecté
	 *
	 * @param numero
	 * 
	 * @exception SQLException au cas où il y aurait un problème lors de la
	 * déconnexion de la bdd
	 * 
	 * @return un booléen qui contient vrai si le matériel a bien été supprimé
	 */
	public static boolean supprimerVehicule(int id) {
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("delete from vehicule where code = ?;");
			statement.setInt(1, id);
			statement.executeUpdate();
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
	
	public static boolean rechercherVehicule(int idVehicule) {
		boolean etat = false;
		try {
			connexionBdd();
			PreparedStatement statement = connexion.prepareStatement("select count(code) as nb from vehicule where code = ?;");
			statement.setInt(1, idVehicule);
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
	
	/* fonction qui ajoute un visiteur avec les données passées en paramètre
	* @param id, nom, etat, longueur, largeur, codeMateriel, typemateriel
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return un entier qui contient le résultat des requetes
	*/
	public static int ajouterObjet(int id, String nom, int longueur, int largeur, int codeMateriel, String typemateriel) {
		int codeAjout = 0;		
		try {
			connexionBdd();
			String rsInsert = "insert into objet (id, nom) VALUES (?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, nom);
			resultInsert = preparedStatement.executeUpdate();
			
			PreparedStatement statement = connexion.prepareStatement("select distinct codemateriel as codeRecupere from materiel M, type_materiel T where M.codemateriel = T.code and libelle = ?;");
			statement.setString(1, typemateriel);
			resultObjets = statement.executeQuery();
			
			if (resultObjets.next()) {
				codeAjout = resultObjets.getInt("codeRecupere");
			}
			
			String rsInsert2 = "insert into materiel (code, longueur, largeur, codemateriel) VALUES (?, ?, ?, ?);";
			preparedStatement2 = connexion.prepareStatement(rsInsert2);
			preparedStatement2.setInt(1, id);
			preparedStatement2.setInt(2, longueur);
			preparedStatement2.setInt(3, largeur);
			preparedStatement2.setInt(4, codeAjout);
			preparedStatement2.executeUpdate();
			resultInsert2 = preparedStatement2.executeUpdate();
			
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert + resultInsert2 + resultInsert3;
	}
	
	/*
	 * fonction qui ajoute un visiteur avec les données passées en paramètre
	 * 
	 * @param id, nom, etat, longueur, largeur, codeMateriel, typemateriel
	 * 
	 * @exception SQLException au cas où il y aurait un problème lors de la
	 * déconnexion de la bdd
	 * 
	 * @return un entier qui contient le résultat des requetes
	 */
	
	public static int ajouterVehicule(int code, String immatriculation, String modele, String marque, int nbplaces, String libellevehicule) {
		int codeAjout = 0;		
		try {
			connexionBdd();
			String rsInsert = "insert into objet (id, nom) VALUES (?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);
			preparedStatement.setInt(1, code);
			preparedStatement.setString(2, modele);
			resultInsert = preparedStatement.executeUpdate();
			
			PreparedStatement statement = connexion.prepareStatement("select distinct T.code as codeRecupere from vehicule V, type_vehicule T where V.codevehicule = T.code and libelle = ?;");
			statement.setString(1, libellevehicule);
			resultObjets = statement.executeQuery();
			
			if (resultObjets.next()) {
				codeAjout = resultObjets.getInt("codeRecupere");
			}
			
			String rsInsert2 = "insert into vehicule (code, immat, modele, marque, nbplaces, codevehicule) VALUES (?, ?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert2);
			preparedStatement.setInt(1, code);
			preparedStatement.setString(2, immatriculation);
			preparedStatement.setString(3, modele);
			preparedStatement.setString(4, marque);
			preparedStatement.setInt(5, nbplaces);
			preparedStatement.setInt(6, codeAjout);
			
			resultInsert = preparedStatement.executeUpdate();

			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert + resultInsert3;
	}
	
	/* fonction qui compte le nombre d'objets
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
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
	
	/* fonction qui compte le nombre d'objets empruntés
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return un entier qui contient le nombre d'objets empruntés
	*/
	public static int getNbObjetsEmpruntes() {
		int nb = 0;
		try {
			connexionBdd();
			String rsObjets = "select count(idEmprunt) as nb from emprunt";
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
	
	/* fonction qui récupére les libellés de tous les types de matériel
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return les noms sous forme de ArrayList
	*/
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
	
	/*
	 * fonction qui récupére les libellés de tous les types de véhicules
	 *
	 * @exception SQLException au cas où il y aurait un problème lors de la
	 * déconnexion de la bdd
	 * 
	 * @return les noms des véhicules sous forme de ArrayList
	 */
	public static ArrayList<String> getLibelleVehicule() {
		ArrayList<String> lesNoms = new ArrayList<String>();
		try {
			connexionBdd();
			String rsLibelle = "select libelle from type_vehicule;";

			preparedStatement = connexion.prepareStatement(rsLibelle);
			resultLibelle = preparedStatement.executeQuery();

			while (resultLibelle.next()) {
				lesNoms.add(resultLibelle.getString("libelle"));
			}
			resultLibelle.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesNoms;
	}

	
	/* fonction qui compte le nombre de libelle
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return un entier qui représente le nombre de libellés
	*/
	public static int getNbLibelle() {
		int nbLibelle = 0;
		try {
			connexionBdd();
			String rsNbObjets = "select count(distinct libelle) as nbLibelle from type_materiel;";
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
	
	/*
	 * fonction qui compte le nombre de libelle différents des véhicules
	 *
	 * @exception SQLException au cas où il y aurait un problème lors de la
	 * déconnexion de la bdd
	 * 
	 * @return un entier qui représente le nombre de libellés des véhicules
	 */
	public static int getNbLibelleVehicule() {
		int nbLibelle = 0;
		try {
			connexionBdd();
			String rsNbObjets = "select count(distinct libelle) as nbLibelle from type_vehicule;";
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
	
	/* fonction qui compte combien de fois a été emprunté un objet
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une liste qui contient le nombre de fois qu'à été emprunté un objet pour tous
	*/
	public static ArrayList<MaterielTrie> getNbEmpruntsParObjet() {
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
	
	/* fonction qui compte le nombre d'emprunts par visiteur
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une liste qui contient le nombre d'objets empruntés par un visiteur
	*/
	public static ArrayList<EmpruntVisiteur> getEmpruntsParVisiteur() {
		ArrayList<EmpruntVisiteur> lesEmprunts = new ArrayList<EmpruntVisiteur>();
		try {
			connexionBdd();
			String rsNbObjets = "select count(idObjet) as nbObjetsEmpruntes, loginvisiteur\n" + 
					"from emprunt E\n" + 
					"GROUP BY loginvisiteur;";
			preparedStatement = connexion.prepareStatement(rsNbObjets);
			resultNbLibelle = preparedStatement.executeQuery();

			while (resultNbLibelle.next()) {
				int compteur = resultNbLibelle.getInt(1);
				String login = resultNbLibelle.getString(2);
				EmpruntVisiteur empruntVisiteur = new EmpruntVisiteur(compteur,login);
				lesEmprunts.add(empruntVisiteur);
			}

			resultNbLibelle.close();
			deconnexionBdd();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesEmprunts;
	}
	
	/* fonction qui ajoute un objet dans la table emprunté
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @returne un entier qui contient le résultat des requetes
	*/
	public static int emprunterObjet(String dateDebut, String dateFin, String heureDebut, String heureFin, String login, int id) {
		try {
			connexionBdd();
			String rsInsert = "insert into emprunt(datedebut,datefin,heuredebut,heurefin,loginvisiteur,idobjet) VALUES (?, ?, ?, ?, ?, ?);";
			preparedStatement = connexion.prepareStatement(rsInsert);

			preparedStatement.setString(1, dateDebut);
			preparedStatement.setString(2, dateFin);
			preparedStatement.setString(3, heureDebut);
			preparedStatement.setString(4, heureFin);
			preparedStatement.setString(5, login);
			preparedStatement.setInt(6, id);

			resultInsert = preparedStatement.executeUpdate();
			
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultInsert;
	}
	
	/* fonction qui récupère la date de début et de fin des emprunts pour 
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @returne un entier qui contient le résultat des requetes
	*/
	public static int emprunterObjetDate(String dateDebut, String dateFin, String heureDebut, String heureFin, int idObjet) {
		int rep = 1;
		try {
			connexionBdd();
			String rsSelect = "select datedebut, datefin, heuredebut, heurefin from emprunt where idObjet = ?;";
			preparedStatement = connexion.prepareStatement(rsSelect);
			preparedStatement.setInt(1, idObjet);
			resultEmprunt = preparedStatement.executeQuery();
			while (resultEmprunt.next()) {
				String uneDateDebut = resultEmprunt.getString(1);
				String uneDateFin = resultEmprunt.getString(2);
				String uneHeureDebut = resultEmprunt.getString(3);
				String uneHeureFin = resultEmprunt.getString(4);
				
				String[] splitedbis = uneHeureDebut.split(":");
				String uneHeureDebutString = splitedbis[0] + splitedbis[1];
				String[] splited2bis = uneHeureFin.split(":");
				String uneHeureFinString = splited2bis[0] + splited2bis[1];
				
				String[] splited3bis = heureDebut.split(":");
				String heureDebutString = splited3bis[0] + splited3bis[1];
				String[] splited4bis = heureFin.split(":");
				String heureFinString = splited4bis[0] + splited4bis[1];
				
				int uneHeureDebutInt=Integer.parseInt(uneHeureDebutString); 
		    	int uneHeureFinInt=Integer.parseInt(uneHeureFinString); 
		    	int heureDebutInt=Integer.parseInt(heureDebutString); 
		    	int heureFinInt=Integer.parseInt(heureFinString); 
				
				SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd"); 
				try {
				    Date datePasseDebut = formatter2.parse(dateDebut); 
				    Date datePasseFin = formatter2.parse(dateFin); 
				    Date laDateDebut = formatter2.parse(uneDateDebut); 
				    Date laDateFin = formatter2.parse(uneDateFin); 
				    if((datePasseDebut.before(laDateDebut) && datePasseFin.after(laDateFin)) 
				    		|| (datePasseDebut.after(laDateDebut) && datePasseDebut.before(laDateFin)) 
				    		|| (datePasseDebut.before(laDateDebut)) && (datePasseFin.after(laDateDebut)) 
				    		|| (datePasseDebut.equals(laDateDebut))) 
				    {
				    	rep = 0;
					}
				    else if(datePasseFin.equals(laDateDebut)) {
				    	if(heureFinInt >= uneHeureDebutInt) {
				    		rep = 2;
				    	}
				    }
				    else if(datePasseDebut.equals(laDateFin)) {
				    	if(heureDebutInt <= uneHeureFinInt) {
				    		rep = 3;
				    	}
				    }
				 } catch (java.text.ParseException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
				 }
			}
			resultEmprunt.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rep;
	}	
	
	/* Fonction qui récupère les objets empruntés
	*
	* @exception SQLException au cas où il y aurait un problème lors de la déconnexion de la bdd
	* @return une liset d'objets contenant les objets empruntés
	*/ 
	public static ArrayList<Consulter> getLesMaterielsEmpruntesParVisiteur(String login) {
		ArrayList<Consulter> lesEmprunts = new ArrayList<Consulter>();
		try {
			connexionBdd();
			String rsObjets = "select idemprunt, datedebut, datefin, heuredebut, heurefin, idobjet from emprunt where loginvisiteur = ?;";
			preparedStatement = connexion.prepareStatement(rsObjets);
			preparedStatement.setString(1, login);
			resultObjets = preparedStatement.executeQuery();
			
			while (resultObjets.next()) {
				int id = resultObjets.getInt("idemprunt");
				Date dateDeDebut = resultObjets.getDate("datedebut");
				Date dateDeFin = resultObjets.getDate("datefin");
				Time heureDeDebut = resultObjets.getTime("heuredebut");
				Time heureDeFin = resultObjets.getTime("heurefin");
				int idObjet = resultObjets.getInt("idobjet");
				
				lesEmprunts.add(new Consulter(id, dateDeDebut, dateDeFin, heureDeDebut, heureDeFin, idObjet));
			}
			resultObjets.close();
			deconnexionBdd();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lesEmprunts;
	}
	
}