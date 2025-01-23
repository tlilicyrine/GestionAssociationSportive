package com.association.service;

import com.association.dao.AdherentDAO;
import com.association.entity.Adherent;

import java.util.List;

public class AdherentService {

    private AdherentDAO adherentDAO;

    public AdherentService() {
        this.adherentDAO = new AdherentDAO();
    }

    public void addAdherent(Adherent adherent) {
        adherentDAO.save(adherent);
    }

    public Adherent getAdherentById(Long id) {
        return adherentDAO.findById(id);
    }

    public List<Adherent> getAllAdherents() {
        return adherentDAO.findAll();
    }

    public void updateAdherent(Adherent adherent) {
        adherentDAO.update(adherent);
    }

    public void deleteAdherent(Long id) {
        adherentDAO.delete(id);
    }
}
