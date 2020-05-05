/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Services.Impl;

import com.thinklance.pidev.Services.Interfaces.IParticiper;
import com.thinklance.pidev.Utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oussema-PC
 */
public class ParticiperService implements IParticiper{
    private Connection cn;

    public ParticiperService() {
        this.cn = MyConnection.getInstance().getCnx();
    }

    @Override
    public void ajouterParticiper(int idadmin, int idevent) {
try {
            String requete = "INSERT INTO participer(idevent,iduser)" + "VALUES('" +idevent + "','" +idadmin+ "') ";
            Statement st = cn.createStatement();
            st.executeUpdate(requete);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     }

    @Override
    public boolean rechercherParticiper(int idadmin, int idevent) {
        try {
            String requete4 = "SELECT * FROM participer where Idevent="+idevent+" AND iduser="+idadmin+"";
            Statement st2 = cn.createStatement();                                                                               
            ResultSet rs = st2.executeQuery(requete4);
            if(rs.next())
            {return false ;} 
            else {return true ;}
        } catch (SQLException ex) {
            Logger.getLogger(ParticiperService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }

    @Override
    public boolean verifierSupprimer(int idevent) {
 try {
            String requete4 = "SELECT * FROM participer where Idevent="+idevent+"";
            Statement st2 = cn.createStatement();                                                                               
            ResultSet rs = st2.executeQuery(requete4);
            if(rs.next())
            {return false ;} 
            else {return true ;}
        } catch (SQLException ex) {
            Logger.getLogger(ParticiperService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;    }

    @Override
    public void annulerParticiper(int idadmin, int idevent) {
            try {
            String requete3 = "DELETE FROM participer WHERE iduser=? and Idevent=?";
            PreparedStatement pst = cn.prepareStatement(requete3);
            pst.setInt(1, idadmin);
             pst.setInt(2, idevent);
            pst.executeUpdate();
            System.out.println("participation annulée !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
    }
    
}
