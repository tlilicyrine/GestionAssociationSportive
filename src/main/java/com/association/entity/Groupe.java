

    package com.association.entity;

    import jakarta.persistence.*;
    import java.util.List;

    @Entity
    public class Groupe {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nom;

        @Column(length = 500) // Limite de la description (facultatif)
        private String description;

        @OneToMany(mappedBy = "groupe",  fetch = FetchType.EAGER)
        private List<Adherent> adherents;

        // Getters et setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<Adherent> getAdherents() {
            return adherents;
        }

        public void setAdherents(List<Adherent> adherents) {
            this.adherents = adherents;
        }
    }
