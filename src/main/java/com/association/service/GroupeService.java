package com.association.service;

import com.association.dao.GroupeDAO;
import com.association.entity.Groupe;

import java.util.List;

public class GroupeService {

    private GroupeDAO groupeDAO;

    public GroupeService() {
        this.groupeDAO = new GroupeDAO();
    }

    public void addGroupe(Groupe groupe) {
        groupeDAO.save(groupe);
    }

    public Groupe getGroupeById(Long id) {
        return groupeDAO.findById(id);
    }

    public List<Groupe> getAllGroupes() {
        return groupeDAO.findAll();
    }

    public void updateGroupe(Groupe groupe) {
        groupeDAO.update(groupe);
    }

    public void deleteGroupe(Long id) {
        groupeDAO.delete(id);
    }
}
