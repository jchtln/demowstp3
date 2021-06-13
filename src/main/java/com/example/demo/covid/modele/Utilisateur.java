package com.example.demo.covid.modele;

import java.time.LocalDate;

public class Utilisateur {
    //body out:  {"username":"fred","password":"fred","dateNaissance":"2020-01-07","lieu":"Orleans"}

    private String username;
    private String password;
    private LocalDate dateNiassance;
    private String lieu;

    public Utilisateur(String username, String password, LocalDate dateNiassance, String lieu) {
        this.username = username;
        this.password = password;
        this.dateNiassance = dateNiassance;
        this.lieu = lieu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateNiassance() {
        return dateNiassance;
    }

    public void setDateNiassance(LocalDate dateNiassance) {
        this.dateNiassance = dateNiassance;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
