/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Impl;

import com.sun.javafx.collections.ElementObservableListDecorator;
import com.thinklance.pidev.Utils.MyConnection;
import com.thinklance.pidev.Services.Interfaces.IEvenement;
import com.thinklance.pidev.entities.Categorie;
import com.thinklance.pidev.entities.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 *
 * @author Oussema-PC
 */
public class EvenementService implements IEvenement {

    private Connection cn;

    public EvenementService() {
        this.cn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterEvenement(Evenement e) {
       
            String requete = "INSERT INTO evenement(nom,lieu,description,date_debut,date_fin,nbr_place,prix,nom_image,IdAdmin,nom_categorie) VALUES(?,?,?,?,?,?,?,?,?,?)";
       try {
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getLieu());

            pst.setString(3, e.getDescription());
            pst.setDate(4, (Date) e.getDate_debut());
            pst.setDate(5, (Date) e.getDate_fin());
            pst.setInt(6, e.getNbr_place());
            pst.setFloat(7, e.getPrix());
            pst.setString(8, e.getImage());

            pst.setInt(9, e.getIdadmin());
            pst.setString(10, e.getCategorie());

            pst.executeUpdate();
            System.out.println("Evenement ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierEvenement(Evenement e, int id) {
        String requete2 = "UPDATE evenement SET nom=?,lieu=?,description=?,date_debut=?,date_fin=?,nbr_place=?,prix=?,nom_image=? WHERE id=?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete2);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getLieu());

            pst.setString(3, e.getDescription());
            pst.setDate(4, (Date) e.getDate_debut());
            pst.setDate(5, (Date) e.getDate_fin());
            pst.setInt(6, e.getNbr_place());
            pst.setFloat(7, e.getPrix());
            pst.setString(8, e.getImage());

            pst.setInt(9, id);

            pst.executeUpdate();
            System.out.println("Evenement modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimerEvenement(int id) {
        try {
            String requete3 = "DELETE FROM evenement WHERE id=?";
            PreparedStatement pst = cn.prepareStatement(requete3);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("evenement supprimé !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Evenement> listerEvenement() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();

        try {
            String requete4 = "SELECT * FROM evenement ORDER BY date_debut";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString(2));
                e.setLieu(rs.getString(3));
                e.setDescription(rs.getString(4));

                e.setDate_debut(rs.getDate(5));
                e.setDate_fin(rs.getDate(6));
                e.setNbr_place(rs.getInt(7));
                e.setPrix(rs.getFloat(8));
                e.setCategorie(rs.getString(12));
                e.setImage(rs.getString(10));
                e.setIdadmin(rs.getInt(11));
                String id = e.getCategorie();

                myList.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

    @Override
    public void incrementerNbr(int id) {
        try {
            String requete1="UPDATE evenement SET nbr_place=nbr_place-1 WHERE id="+id+"";
            PreparedStatement ps=cn.prepareStatement(requete1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public boolean verifierSupprimer(int idCategorie) {
try {
            String requete4 = "SELECT * FROM evenement where Idcategorie="+idCategorie+"";
            Statement st2 = cn.createStatement();                                                                               
            ResultSet rs = st2.executeQuery(requete4);
            if(rs.next())
            {return false ;} 
            else {return true ;}
        } catch (SQLException ex) {
            Logger.getLogger(ParticiperService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;       }

    @Override
    public void decrementerNbr(int id) {
          try {
            String requete1="UPDATE evenement SET nbr_place=nbr_place+1 WHERE id="+id+"";
            PreparedStatement ps=cn.prepareStatement(requete1);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObservableList<Evenement> listerEvenementParticiper(int id) {

         ObservableList<Evenement> myList = FXCollections.observableArrayList();

        try {
            String requete4 = "SELECT evenement.* FROM evenement,participer  where participer.iduser="+id+" AND evenement.id=participer.idevent";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString(2));
                e.setLieu(rs.getString(3));
                e.setDescription(rs.getString(4));

                e.setDate_debut(rs.getDate(5));
                e.setDate_fin(rs.getDate(6));
                e.setNbr_place(rs.getInt(7));
                e.setPrix(rs.getFloat(8));
                e.setCategorie(rs.getString(12));
                e.setImage(rs.getString(10));
                e.setIdadmin(rs.getInt(11));
               // String id = e.getCategorie();

                myList.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList;
    }

}
