package com.association.dao;

import com.association.entity.Groupe;
import com.association.utils.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class GroupeDAO {

    public void save(Groupe groupe) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(groupe);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde du groupe", ex);
        } finally {
            em.close();
        }
    }

    public Groupe findById(Long id) {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.find(Groupe.class, id);
        } catch (Exception ex) {
            throw new RuntimeException("Erreur lors de la recherche du groupe avec l'ID : " + id, ex);
        } finally {
            em.close();
        }
    }

    public List<Groupe> findAll() {
        EntityManager em = HibernateUtil.getEntityManager();
        try {
            return em.createQuery("SELECT g FROM Groupe g", Groupe.class).getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Erreur lors de la récupération de tous les groupes", ex);
        } finally {
            em.close();
        }
    }

    public void update(Groupe groupe) {
        EntityManager em = HibernateUtil.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.merge(groupe);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour du groupe", ex);
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
            Groupe groupe = em.find(Groupe.class, id);
            if (groupe != null) {
                em.remove(groupe);
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression du groupe avec l'ID : " + id, ex);
        } finally {
            em.close();
        }
    }
}
