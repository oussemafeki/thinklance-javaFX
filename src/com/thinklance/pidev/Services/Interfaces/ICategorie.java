/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Interfaces;

import com.thinklance.pidev.entities.Categorie;
import com.thinklance.pidev.entities.Evenement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussema-PC
 */
public interface ICategorie {
        void ajouterCategorie(Categorie c);
     void modifierCategorie(Categorie c,int id);
    void supprimerCategorie(int id );
   ObservableList<Categorie> listerCategorie();
   public List<Categorie> afficherCategorieFAQ();
    
}
