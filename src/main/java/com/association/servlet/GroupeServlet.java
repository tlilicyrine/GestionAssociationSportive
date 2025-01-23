package com.association.servlet;

import com.association.entity.Groupe;
import com.association.service.GroupeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/groupes")
public class GroupeServlet extends HttpServlet {

    private GroupeService groupeService;

    @Override
    public void init() throws ServletException {
        this.groupeService = new GroupeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteGroupe(req, resp);
                break;
            case "details":
                showDetails(req, resp);
                break;
            default:
                listGroupes(req, resp);
                break;
        }
    }

    private void listGroupes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Groupe> groupes = groupeService.getAllGroupes();

      
        for (Groupe groupe : groupes) {
            if (groupe.getAdherents() != null) {
                groupe.getAdherents().size(); 
            }
        }

        req.setAttribute("groupes", groupes);
        req.getRequestDispatcher("/WEB-INF/views/groupes/groupes.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/groupes/groupe-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Groupe groupe = groupeService.getGroupeById(id);

        if (groupe != null) {
            req.setAttribute("groupe", groupe);
            req.getRequestDispatcher("/WEB-INF/views/groupes/groupe-form.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Groupe introuvable.");
            resp.sendRedirect(req.getContextPath() + "/groupes");
        }
    }

    private void deleteGroupe(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            groupeService.deleteGroupe(id);
            resp.sendRedirect(req.getContextPath() + "/groupes");
        } catch (RuntimeException e) {
            req.setAttribute("error", "Impossible de supprimer le groupe : " + e.getMessage());
            try {
                listGroupes(req, resp);
            } catch (ServletException ex) {
                throw new IOException("Erreur lors de la tentative de suppression du groupe.", ex);
            }
        }
    }

    private void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Groupe groupe = groupeService.getGroupeById(id);

        if (groupe != null) {
            // Initialisation explicite de la collection lazy
            if (groupe.getAdherents() != null) {
                groupe.getAdherents().size();
            }

            req.setAttribute("groupe", groupe);
            req.getRequestDispatcher("/WEB-INF/views/groupes/groupe-details.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Groupe introuvable.");
            resp.sendRedirect(req.getContextPath() + "/groupes");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");

        Groupe groupe = new Groupe();
        groupe.setNom(nom);
        groupe.setDescription(description);

        if (id == null || id.isEmpty()) {
            groupeService.addGroupe(groupe);
        } else {
            groupe.setId(Long.parseLong(id));
            groupeService.updateGroupe(groupe);
        }

        resp.sendRedirect(req.getContextPath() + "/groupes");
    }
}
