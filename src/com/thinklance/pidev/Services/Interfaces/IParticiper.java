/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Interfaces;

/**
 *
 * @author Oussema-PC
 */
public interface IParticiper {
    void ajouterParticiper(int idadmin , int idevent);
    void annulerParticiper(int idadmin , int idevent);
    boolean rechercherParticiper(int idadmin , int idevent);
     boolean verifierSupprimer(int idevent);
    
}
