	package ma.map.controller;

	
import ma.map.model.ModelAuthentification;
import ma.map.model.ModelUtilisateur;
import ma.map.dao.DaoAuthentification;
import ma.map.dao.DaoUtilisateur;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletUtilisateur", urlPatterns = {"/ServletUtilisateur"})
public class ServletUtilisateur extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/indexAdmin.utilisateur")) {
            try {
                DaoUtilisateur utilisateur = new DaoUtilisateur();
                request.setAttribute("utilisateur", utilisateur.getAllUsersAdmin());
                request.getRequestDispatcher("ListeAdmin.jsp").forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ServletUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else if (path.equals("/supprimer.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            DaoUtilisateur utilisateur = new DaoUtilisateur();
            utilisateur.SupprimerUtilisateurAdmin(id);
            response.sendRedirect("indexAdmin.utilisateur");
            
        } else if (path.equals("/modifier.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            DaoUtilisateur utilisateur = new DaoUtilisateur();
            try {
                request.setAttribute("utilisateur", utilisateur.getUtilisateurAdminById(id));
            } catch (SQLException ex) {
                Logger.getLogger(ServletUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("ModifierUtilisateur.jsp").forward(request, response);

        } else if (path.equals("/modifierAdmin.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            DaoUtilisateur utilisateur = new DaoUtilisateur();
            try {
                request.setAttribute("utilisateur", utilisateur.getUtilisateurAdminById(id));
            } catch (SQLException ex) {
                Logger.getLogger(ServletUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("ModifierUtilisateur.jsp").forward(request, response);

        
        }else if (path.equals("/informationAdmin.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            DaoUtilisateur utilisateur = new DaoUtilisateur();
            try {
                request.setAttribute("utilisateur", utilisateur.getUtilisateurAdminById(id));
            } catch (SQLException ex) {
                Logger.getLogger(ServletUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("informationAdmin.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (request.getMethod().equals("POST") && path.equals("/ajouteAdmin.utilisateur")) {
        	
            String nom = request.getParameter("nom").trim();
            String prenom = request.getParameter("prenom").trim();
            String email = request.getParameter("email").trim();
            String nomDeUtilisateur = request.getParameter("nom_utilisateur").trim();
            String motDePasse = request.getParameter("mot_de_passe").trim();
            String role =request.getParameter("role");
            //String role = request.getParameter("role").trim();
         

            ModelUtilisateur utilisateur = new ModelUtilisateur();

            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setEmail(email);
            utilisateur.setRole(role);
            //utilisateur.setId_role(id_role);
            utilisateur.setNom_de_utilisateur(nomDeUtilisateur);
     
            utilisateur.setNom_de_utilisateur(nomDeUtilisateur);
            utilisateur.setMot_de_passe(motDePasse);
            DaoUtilisateur ajouteUtilisateur = new DaoUtilisateur();
            int requete = ajouteUtilisateur.AjouteUtilisateurAdmin(utilisateur);

            if (requete > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("oui", "administrateur à été bien ajouté ");
                //request.getRequestDispatcher("/utilisateurs.jsp").forward(request, response);
                response.sendRedirect("indexAdmin.utilisateur");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("non", "administrateur  pas ajoute");
                request.getRequestDispatcher("utilisateurs.jsp").forward(request, response);
            }
        } else if (request.getMethod().equals("POST") && path.equals("/updateAdmin.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom").trim();
            String prenom = request.getParameter("prenom").trim();
            String email = request.getParameter("email").trim();
           
            ModelUtilisateur utilisateur = new ModelUtilisateur();
            utilisateur.setId(id);
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setEmail(email);
            
            DaoUtilisateur user = new DaoUtilisateur();
            user.UpdateUtilisateurAdmin(utilisateur);

            response.sendRedirect("indexAdmin.utilisateur");

        }
        else if (request.getMethod().equals("POST") && path.equals("/modifierMotDePasseAdmin.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String motDePasse = request.getParameter("motDePasse").trim();
            ModelUtilisateur utilisateur = new ModelUtilisateur();
            utilisateur.setId(id);
            utilisateur.setMot_de_passe(motDePasse);
            DaoUtilisateur user = new DaoUtilisateur();
            user.UpdateMotDePasseAdmin(utilisateur);
            String modifierMotDePasseAdmin = "Le mot de passe à été bien modifié ";
            request.getSession().setAttribute("modifierMotDePasseAdmin", modifierMotDePasseAdmin);
            response.sendRedirect("informationAdmin.utilisateur?id=" + id);

        }
        else if (request.getMethod().equals("POST") && path.equals("/modifierNomDeUtilisateurAdmin.utilisateur")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nomDeUtilisateur = request.getParameter("nomDeUtilisateur").trim();
            ModelUtilisateur utilisateur = new ModelUtilisateur();
            utilisateur.setId(id);
            utilisateur.setNom_de_utilisateur(nomDeUtilisateur);
            DaoUtilisateur user = new DaoUtilisateur();
            user.UpdateNomDeUtilisateurAdmin(utilisateur);
            String modifierNomDeUtilisateurAdmin = "Le nom d'utilisateur � �t� bien modifi� ";
            request.getSession().setAttribute("modifierNomDeUtilisateurAdmin", modifierNomDeUtilisateurAdmin);
            response.sendRedirect("informationAdmin.utilisateur?id=" + id);
            

        }
 
    }

}
