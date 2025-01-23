package com.association.servlet;

import com.association.entity.Adherent;
import com.association.entity.Groupe;
import com.association.service.AdherentService;
import com.association.service.GroupeService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/adherents")
public class AdherentServlet extends HttpServlet {

    private AdherentService adherentService;
    private GroupeService groupeService;

    @Override
    public void init() throws ServletException {
        this.adherentService = new AdherentService();
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
                deleteAdherent(req, resp);
                break;
            case "details":
                showDetails(req, resp); // Action "details"
                break;
            default:
                listAdherents(req, resp);
                break;
        }
    }

    private void listAdherents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Adherent> adherents = adherentService.getAllAdherents();
        req.setAttribute("adherents", adherents);
        req.getRequestDispatcher("/WEB-INF/views/adherents/adherents.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Groupe> groupes = groupeService.getAllGroupes();
        req.setAttribute("groupes", groupes);
        req.getRequestDispatcher("/WEB-INF/views/adherents/adherent-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Adherent adherent = adherentService.getAdherentById(id);
        List<Groupe> groupes = groupeService.getAllGroupes();
        req.setAttribute("adherent", adherent);
        req.setAttribute("groupes", groupes);
        req.getRequestDispatcher("/WEB-INF/views/adherents/adherent-form.jsp").forward(req, resp);
    }

    private void deleteAdherent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        adherentService.deleteAdherent(id);
        resp.sendRedirect(req.getContextPath() + "/adherents");
    }

    private void showDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(req.getParameter("id"));
            Adherent adherent = adherentService.getAdherentById(id);

            if (adherent != null) {
                req.setAttribute("adherent", adherent);
                req.getRequestDispatcher("/WEB-INF/views/adherents/adherent-details.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Adhérent introuvable.");
                resp.sendRedirect(req.getContextPath() + "/adherents");
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID d'adhérent invalide.");
            resp.sendRedirect(req.getContextPath() + "/adherents");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        LocalDate dateNaissance = LocalDate.parse(req.getParameter("dateNaissance"));
        String adresse = req.getParameter("adresse");
        String codePostal = req.getParameter("codePostal");
        String ville = req.getParameter("ville");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        LocalDate dateCotisation = LocalDate.parse(req.getParameter("dateCotisation"));
        String groupeIdParam = req.getParameter("groupeId");

        // Créer ou mettre à jour un adhérent
        Adherent adherent = new Adherent();
        adherent.setNom(nom);
        adherent.setPrenom(prenom);
        adherent.setDateNaissance(dateNaissance);
        adherent.setAdresse(adresse);
        adherent.setCodePostal(codePostal);
        adherent.setVille(ville);
        adherent.setEmail(email);
        adherent.setTelephone(telephone);
        adherent.setDateCotisation(dateCotisation);

        // Associer le groupe si renseigné
        if (groupeIdParam != null && !groupeIdParam.isEmpty()) {
            Long groupeId = Long.parseLong(groupeIdParam);
            Groupe groupe = groupeService.getGroupeById(groupeId);
            if (groupe != null) {
                adherent.setGroupe(groupe);
            } else {
                req.setAttribute("error", "Le groupe sélectionné est introuvable.");
                req.getRequestDispatcher("/WEB-INF/views/adherents/adherent-form.jsp").forward(req, resp);
                return;
            }
        }

        if (id == null || id.isEmpty()) {
            adherentService.addAdherent(adherent);
        } else {
            adherent.setId(Long.parseLong(id));
            adherentService.updateAdherent(adherent);
        }

        resp.sendRedirect(req.getContextPath() + "/adherents");
    }
}
