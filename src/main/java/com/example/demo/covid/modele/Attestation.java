package com.example.demo.covid.modele;

import java.time.LocalDateTime;

public class Attestation {
    private String motif;
    private LocalDateTime debut;
    private Utilisateur utilisateur;

    public Attestation(String motif, LocalDateTime debut, Utilisateur utilisateur) {
        this.motif = motif;
        this.debut = debut;
        this.utilisateur = utilisateur;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public void setDebut(LocalDateTime debut) {
        this.debut = debut;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
