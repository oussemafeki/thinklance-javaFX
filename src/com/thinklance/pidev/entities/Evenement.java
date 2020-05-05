/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.entities;

import java.util.Date;

/**
 *
 * @author Oussema-PC
 */
public class Evenement {
    int id ;
    String nom ;
    String lieu ;
    String description ;
    Date date_debut ;
    Date date_fin ;
    int nbr_place ;
    float prix ;
    String categorie;
    int idadmin;
    String image ;

    public int getIdadmin() {
        return idadmin;
    }

    public void setIdadmin(int idadmin) {
        this.idadmin = idadmin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategorie() {
        return categorie;
    }

    public Evenement(int id, String nom, String lieu, String description, Date date_debut, Date date_fin, int nbr_place, float prix, String categorie, int idadmin, String image) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_place = nbr_place;
        this.prix = prix;
        this.categorie = categorie;
        this.idadmin = idadmin;
        this.image = image;
    }

   

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    
    public Evenement ()
    {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    
     
    
    
    
    
    
    
}
