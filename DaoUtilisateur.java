	package ma.map.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.PreparedStatement;
import ma.map.model.ModelRole;
import ma.map.database.DbConnexion;
import ma.map.model.ModelUtilisateur;;
public class DaoUtilisateur {
	private static DbConnexion dbConnexion = DbConnexion.getInstance();
	 
	 public ArrayList<ModelUtilisateur> getAllUsersAdmin() throws SQLException {
	        ArrayList<ModelUtilisateur> utilisateurAdmin = new ArrayList<ModelUtilisateur>();
	        Connection conn = DbConnexion.getConnection();
        	PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM users ");
        	
        	ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            ModelUtilisateur utilisateur = new ModelUtilisateur();
	            ModelRole role=new ModelRole();
	            utilisateur.setId(rs.getInt("id"));
	            utilisateur.setNom(rs.getString("nom"));
	            utilisateur.setPrenom(rs.getString("prenom"));
	            utilisateur.setEmail(rs.getString("email")); 
	            utilisateur.setEtat(rs.getInt("etat"));
	            role.setRole(rs.getString("role"));
	      	   //utilisateur.setId_role(rs.getInt("id_role"));
	            utilisateurAdmin.add(utilisateur);
	        }
	        return utilisateurAdmin;
	    }
     public void SupprimerUtilisateurAdmin(int id) {
         try {
        	 Connection conn = DbConnexion.getConnection();
        	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("delete from users where id=? ");
             ps.setInt(1, id);
             ps.executeUpdate();
         } catch (Exception e) {
             System.out.println(e);
         }
     }
         public static int AjouteUtilisateurAdmin(ModelUtilisateur utilisateur) {
             int status = 0;
             try {
            	 Connection conn = DbConnexion.getConnection();
            	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("insert into users(nom,prenom,email,nom_utilisateur,mot_de_passe,etat,id_role) values(?,?,?,?,?,?,?)");
                 
                 ps.setString(1, utilisateur.getNom());
                 ps.setString(2, utilisateur.getPrenom());
                 ps.setString(3, utilisateur.getEmail());
                 ps.setString(4, utilisateur.getNom_de_utilisateur());
                 ps.setString(5, utilisateur.getMot_de_passe());
                 ps.setInt(6, 1);
                 ps.setInt(7,utilisateur.getRole().getId());
                 status = ps.executeUpdate();
             } catch (Exception e) {
                 System.out.println(e);
             }
             return status;
         }
         public static int AjouteRole(ModelUtilisateur utilisateur) {
             int status = 0;
             try {
            	 Connection conn = DbConnexion.getConnection();
            	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("insert into users(nom,prenom,email,nom_utilisateur,mot_de_passe,etat,role) values(?,?,?,?,?,?,?)");
                 
                 ps.setString(1, utilisateur.getNom());
                 ps.setString(2, utilisateur.getPrenom());
                 ps.setString(3, utilisateur.getEmail());
                 ps.setString(4, utilisateur.getNom_de_utilisateur());
                 ps.setString(5, utilisateur.getMot_de_passe());
                 ps.setInt(6, 1);
                 ps.setString(7,utilisateur.getRole().getId());
                 status = ps.executeUpdate();
             } catch (Exception e) {
                 System.out.println(e);
             }
             return status;
         }

         public ModelUtilisateur getUtilisateurAdminById(int id) throws SQLException {
        	 Connection conn = DbConnexion.getConnection();
          
             ModelUtilisateur utilisateur = null;
             try {
            	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM `users` WHERE id = ?");
                 
      
                 ps.setInt(1, id);
                 ResultSet res = ps.executeQuery();
                 if (res.next()) {
                     utilisateur = new ModelUtilisateur();
                     utilisateur.setId(res.getInt("id"));
                     utilisateur.setNom(res.getString("nom"));
                     utilisateur.setPrenom(res.getString("prenom"));
                     utilisateur.setEmail(res.getString("email"));
                     utilisateur.setRole(res.getString("role"));
                     utilisateur.setEtat(res.getInt("etat"));
                 }
             } catch (Exception e) {
                 System.out.println(e);
             }
             return utilisateur;
         }
         public ModelUtilisateur getRoleById(int id) throws SQLException {
        	 Connection conn = DbConnexion.getConnection();
          
             ModelUtilisateur utilisateur = null;
             try {
            	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM `role` WHERE id = ?");
                 
      
                 ps.setInt(1, id);
                 ResultSet res = ps.executeQuery();
                 if (res.next()) {
                     utilisateur = new ModelUtilisateur();
                     utilisateur.setId(res.getInt("id"));
                     utilisateur.setNom(res.getString("nom"));
                     utilisateur.setPrenom(res.getString("prenom"));
                     utilisateur.setEmail(res.getString("email"));
                     utilisateur.setRole(res.getString("role"));
                     utilisateur.setEtat(res.getInt("etat"));
                 }
             } catch (Exception e) {
                 System.out.println(e);
             }
             return utilisateur;
         }
         public ModelUtilisateur UpdateUtilisateurAdmin(ModelUtilisateur  utilisateur) {
             try {
            	 Connection conn = DbConnexion.getConnection();
                 PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE users	SET nom=?, prenom=?, email=?,etat=? WHERE id=?");
                 ps.setString(1, utilisateur.getNom());
                 ps.setString(2, utilisateur.getPrenom());
                 ps.setString(3, utilisateur.getEmail());
                 ps.setInt(4,utilisateur.getEtat());
                 ps.setInt(5, utilisateur.getId());
                 ps.executeUpdate();
             } catch (Exception e) {
                 System.out.println("n'est pas modifier");
             }
             return utilisateur;

         }
         public ModelUtilisateur UpdateMotDePasseAdmin(ModelUtilisateur  utilisateur) {
             try {
            	 Connection conn = DbConnexion.getConnection();
                 PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE users SET mot_de_passe=? WHERE id=?");
                 ps.setString(1, utilisateur.getMot_de_passe());
                 ps.setInt(2, utilisateur.getId());           
                 ps.executeUpdate();
             } catch (Exception e) {
                 System.out.println("n'est pas modifier mot de passe");
             }return utilisateur;
              
         }
         public ModelUtilisateur UpdateNomDeUtilisateurAdmin(ModelUtilisateur  utilisateur) {
             try {
            	 Connection conn = DbConnexion.getConnection();
                 PreparedStatement ps = (PreparedStatement) conn.prepareStatement("UPDATE users SET nom_utilisateur=? WHERE id=?");
                 ps.setString(1, utilisateur.getNom_de_utilisateur());
                 ps.setInt(2, utilisateur.getId());           
                 ps.executeUpdate();
             } catch (Exception e) {
                 System.out.println("");
             }return utilisateur;
              
         }
     }



