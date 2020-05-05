/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oussema-PC
 */
public class MyConnection {
    String url = "jdbc:mysql://localhost:3306/thinklancedb";
            String login="root";
            String mdp="";
             Connection cnx;
             static MyConnection myCNX ;
             

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance()
    {
        if(myCNX==null)
        {
             myCNX=new MyConnection();
        }
        return myCNX;
    }
             
    private MyConnection() {
      
            
             
              try {
            cnx=DriverManager.getConnection(url, login, mdp);
            System.out.println("Connexion etablie !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
              
    }
 
    
}
