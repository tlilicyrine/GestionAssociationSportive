package com.association.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory entityManagerFactory;

    static {
        EntityManagerFactory tempEntityManagerFactory = null;
        try {
            tempEntityManagerFactory = Persistence.createEntityManagerFactory("associationPU");
            System.out.println("EntityManagerFactory initialisée avec succès.");
        } catch (Exception ex) {
            System.err.println("Échec d'initialisation de l'EntityManagerFactory. Vérifiez votre fichier persistence.xml et la configuration.");
            ex.printStackTrace();
            throw new ExceptionInInitializerError("Erreur lors de l'initialisation de l'EntityManagerFactory : " + ex.getMessage());
        }
        entityManagerFactory = tempEntityManagerFactory;
    }

    /**
     * Fournit un EntityManager pour interagir avec la base de données.
     *
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory n'a pas été initialisée correctement.");
        }
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Ferme l'EntityManagerFactory lors de l'arrêt de l'application.
     */
    public static void close() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("EntityManagerFactory fermée correctement.");
        }
    }
}
