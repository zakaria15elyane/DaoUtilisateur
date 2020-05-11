package ma.map.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import ma.map.database.DbConnexion;
import ma.map.model.ModelRole;
import ma.map.model.ModelUtilisateur;

public class DaoRole {

	private static DbConnexion dbConnexion = DbConnexion.getInstance();
	
	 public ArrayList<ModelRole> getAllRole() throws SQLException {
	        ArrayList<ModelRole> roles = new ArrayList<ModelRole>();
	        Connection conn = DbConnexion.getConnection();
     	PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM role");
     	
     	ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            ModelRole role = new ModelRole();
	            role.setId_role(rs.getInt("id_role"));
	            role.setRole(rs.getString("role"));
	            roles.add(role);
	        }
	        return roles;
	    }
	 public ModelRole getUtilisateurRoleById(int id) throws SQLException {
    	 Connection conn = DbConnexion.getConnection();
      
         ModelRole role = null;
         try {
        	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM `role` WHERE id_role = ?");

             ps.setInt(1, id);
             ResultSet res = ps.executeQuery();
             if (res.next()) {
                 role = new ModelRole();
                 role.setId_role(res.getInt("id_role"));
                 role.setRole(res.getString("role"));
                
             }
         } catch (Exception e) {
             System.out.println(e);
         }
         return role;
     }
	 
	 public ModelRole getRoleById(int id) throws SQLException {
    	 Connection conn = DbConnexion.getConnection();
      
         ModelRole role = null;
         try {
        	 PreparedStatement ps =(PreparedStatement) conn.prepareStatement("SELECT * FROM `role` WHERE id = ?");
             
  
             ps.setInt(1, id);
             ResultSet res = ps.executeQuery();
             if (res.next()) {
                 role = new ModelRole();
                 role.setId_role(res.getInt("id_role"));
                 role.setRole(res.getString("role"));
                 
             }
         } catch (Exception e) {
             System.out.println(e);
         }
         return role;
     }
}
