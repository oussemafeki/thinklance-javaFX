/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.thinklance.pidev.Services.Impl.CategorieService;
import com.thinklance.pidev.entities.Categorie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton retourId;
    @FXML
    private AnchorPane nomId;
    @FXML
    private TextArea descriptionId;
    @FXML
    private TextField tf_nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterAction(ActionEvent event) {
          if((tf_nom.getText()==null)||(descriptionId.getText()==null))
          {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Echec");
alert.setHeaderText(null);
alert.setContentText("Il est oblligatoire ed remplir tout les champs !");

alert.showAndWait(); 
          }
          else{
            String Nom=tf_nom.getText();
            String Description=descriptionId.getText();
            Categorie c = new Categorie();
            c.setNom(Nom);
            c.setDescription(Description);
            CategorieService cs = new CategorieService();
            cs.ajouterCategorie(c);
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Success");
alert.setHeaderText(null);
alert.setContentText("Categorie ajoutée!");

alert.showAndWait();
          }
    }

    @FXML
    private void retourAction(ActionEvent event) {
        try {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = Loader.load();
            AfficherEvenementController pc = Loader.getController();
            
            Ajouter.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }
    
}
