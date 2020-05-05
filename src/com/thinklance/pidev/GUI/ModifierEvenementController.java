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
public class ModifierEvenementController implements Initializable {

    @FXML
    private AnchorPane nomId;
    @FXML
    private TextArea description_Id;
    @FXML
    private JFXButton modifer;
    @FXML
    private JFXButton retourId;
    @FXML
    private TextField tf_nom_id;
    int id ;

    public TextArea getDescription_Id() {
        return description_Id;
    }

    public void setDescription_Id(String description_Id) {
        this.description_Id.setText(description_Id);
    }

    public TextField getTf_nom_id() {
        return tf_nom_id;
    }

    public void setTf_nom_id(String tf_nom_id) {
        this.tf_nom_id.setText(tf_nom_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    

    @FXML
    private void retourAction(ActionEvent event) {
         try {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = Loader.load();
            AfficherEvenementController pc = Loader.getController();
            
            description_Id.getScene().setRoot(root);
        } catch (IOException ex) {
        }
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
         if((tf_nom_id.getText()==null)||(description_Id.getText()==null))
          {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Echec");
alert.setHeaderText(null);
alert.setContentText("Il est oblligatoire ed remplir tout les champs !");

alert.showAndWait(); 
          }
         else{
        Categorie c = new Categorie();
        c.setDescription(description_Id.getText());
        c.setNom(tf_nom_id.getText());
        CategorieService cs = new CategorieService();
        cs.modifierCategorie(c, id);
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Categorie modifiée !");

        alert.showAndWait();
        
         }
    }
    
}
