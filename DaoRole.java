package ma.map.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import ma.map.database.DbConnexion;
import ma.map.model.ModelUtilisateur;

public class DaoRole {

	private static DbConnexion dbConnexion = DbConnexion.getInstance();
	
	 public ArrayList<ModelUtilisateur> getAllRole() throws SQLException {
	        ArrayList<ModelUtilisateur> role = new ArrayList<ModelUtilisateur>();
	        Connection conn = DbConnexion.getConnection();
     	PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM role");
     	
     	ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            ModelUtilisateur utilisateur = new ModelUtilisateur();
	            utilisateur.setId(rs.getInt("id"));
	            utilisateur.setNom(rs.getString("nom"));
	            utilisateur.setPrenom(rs.getString("prenom"));
	            utilisateur.setEmail(rs.getString("email"));    
	            utilisateur.setRole(rs.getString("role"));
	            utilisateur.setEtat(rs.getInt("etat"));
	         
	            role.add(utilisateur);
	        }
	        return role;
	    }
	 public ModelUtilisateur getUtilisateurRoleById(int id) throws SQLException {
    	 Connection conn = DbConnexion.getConnection();
      
         ModelUtilisateur utilisateur = null;
         try {
        	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM `role` WHERE id_role = ?");

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
}
