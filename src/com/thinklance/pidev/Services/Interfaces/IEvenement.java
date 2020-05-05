/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Interfaces;

import com.thinklance.pidev.entities.Evenement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussema-PC
 */
public interface IEvenement {
    
    void ajouterEvenement(Evenement e);
     void modifierEvenement(Evenement e,int id);
    void supprimerEvenement(int id );
   ObservableList<Evenement> listerEvenement();
   void incrementerNbr(int id);
    void decrementerNbr(int id);
   boolean verifierSupprimer(int idCategorie);
   ObservableList<Evenement> listerEvenementParticiper(int id);

    
}
