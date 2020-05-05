/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Impl;

import com.thinklance.pidev.Services.Interfaces.ICategorie;
import com.thinklance.pidev.Utils.MyConnection;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Oussema-PC
 */
public class CategorieService implements ICategorie {
 private Connection cn;

    public CategorieService() {
        this.cn = MyConnection.getInstance().getCnx();
    }
    @Override
    public void ajouterCategorie(Categorie c) {
try {
            String requete = "INSERT INTO categorie(Nom,Description)" + "VALUES('" + c.getNom() + "','" + c.getDescription()+ "') ";
            Statement st = cn.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifierCategorie(Categorie c, int id) {
 String requete2 = "UPDATE categorie SET Nom=?,Description=? WHERE id=?";
        try {
            PreparedStatement pst = cn.prepareStatement(requete2);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setInt(3, id);

  

            pst.executeUpdate();
            System.out.println("Categorie modifié !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimerCategorie(int id) {
 try {
            String requete3 = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cn.prepareStatement(requete3);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Categorie supprimé !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public ObservableList<Categorie> listerCategorie() {
  ObservableList<Categorie> myList1 = FXCollections.observableArrayList();
        
        try {
            String requete4 = "SELECT * FROM categorie";
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st2.executeQuery(requete4);
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString(2));

                c.setDescription(rs.getString(3));
               

                myList1.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return myList1;    }

    @Override
    public List<Categorie> afficherCategorieFAQ() {
         List<Categorie> myList = new ArrayList();
        String requete4="SELECT nom FROM categorie";
        try {
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            
            ResultSet rs = st2.executeQuery(requete4);
            
            while(rs.next())
            {
                Categorie cf = new Categorie();
                cf.setNom(rs.getString(1));
                myList.add(cf);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }

        return myList;
    }
    
}
