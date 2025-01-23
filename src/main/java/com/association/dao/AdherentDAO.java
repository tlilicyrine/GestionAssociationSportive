package com.association.dao;

import com.association.entity.Adherent;
import com.association.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class AdherentDAO {

    public void save(Adherent adherent) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(adherent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde de l'adhérent", ex);
        } finally {
            em.close();
        }
    }

    public Adherent findById(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Adherent.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Erreur lors de la recherche de l'adhérent avec l'ID : " + id, ex);
        } finally {
            em.close();
        }
    }

    public List<Adherent> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Adherent a", Adherent.class).getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Erreur lors de la récupération de tous les adhérents", ex);
        } finally {
            em.close();
        }
    }

    public void update(Adherent adherent) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(adherent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour de l'adhérent", ex);
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            Adherent adherent = em.find(Adherent.class, id);
            if (adherent != null) {
                em.remove(adherent);
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression de l'adhérent avec l'ID : " + id, ex);
        } finally {
            em.close();
        }
    }
}
