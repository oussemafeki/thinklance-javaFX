/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;


import com.thinklance.pidev.Services.Impl.CategorieService;
import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.Services.Impl.ParticiperService;
import com.thinklance.pidev.entities.Categorie;
import com.thinklance.pidev.entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class AfficherEvenementController implements Initializable {

    @FXML
    private TableColumn<Evenement, Integer> id_id;
    @FXML
    private TableColumn<Evenement, String> id_nom;
    @FXML
    private TableColumn<Evenement, String> id_lieu;
    @FXML
    private TableColumn<Evenement, String> id_desc;
    @FXML
    private TableColumn<Evenement, Date> id_datedeb;
    @FXML
    private TableColumn<Evenement, Date> id_datefin;
    @FXML
    private TableColumn<Evenement, Integer> id_nbrplace;
    @FXML
    private TableColumn<Evenement, Float> id_prix;
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    ObservableList<Evenement> Elist;
    @FXML
    private TableView<Evenement> table_evenement;
    @FXML
    private TableView<Categorie> tableau_categorie;
    @FXML
    private TableColumn<Categorie, Integer> idc_id;
    @FXML
    private TableColumn<Categorie, String> idc_nom;
    @FXML
    private TableColumn<Categorie, String> idc_desc;
    ObservableList<Categorie> Clist;
    @FXML
    private JFXButton ajouterC;
    @FXML
    private TableColumn<Evenement, String> id_categorie;
    @FXML
    private JFXButton SupprimerId;

    private IntegerProperty Index = new SimpleIntegerProperty();
    @FXML
    private JFXButton modifierid;
    @FXML
    private JFXButton SupprimerId1;
    @FXML
    private JFXButton retouuur;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EvenementService edao = new EvenementService();
        Elist = edao.listerEvenement();

        id_id.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("id"));

        id_nom.setCellValueFactory(new PropertyValueFactory<Evenement, String>("nom"));
        id_lieu.setCellValueFactory(new PropertyValueFactory<Evenement, String>("lieu"));
        id_desc.setCellValueFactory(new PropertyValueFactory<Evenement, String>("description"));
        id_datedeb.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_debut"));
        id_datefin.setCellValueFactory(new PropertyValueFactory<Evenement, Date>("date_fin"));
        id_nbrplace.setCellValueFactory(new PropertyValueFactory<Evenement, Integer>("nbr_place"));
        id_prix.setCellValueFactory(new PropertyValueFactory<Evenement, Float>("prix"));
        id_categorie.setCellValueFactory(new PropertyValueFactory<Evenement, String>("categorie"));

        //col_delete = new TableColumn("SUPPRIMER");
        // col_addTache = new TableColumn("Tâches");
        table_evenement.setItems(Elist);
        
        // TODO
        CategorieService cdao = new CategorieService();
        Clist = cdao.listerCategorie();
        idc_id.setCellValueFactory(new PropertyValueFactory<Categorie, Integer>("id"));

        idc_nom.setCellValueFactory(new PropertyValueFactory<Categorie, String>("Nom"));
        idc_desc.setCellValueFactory(new PropertyValueFactory<Categorie, String>("Description"));
        tableau_categorie.setItems(Clist);
        tableau_categorie.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                    Index.set(Clist.indexOf(newValue));
                
            }
        });
                
                    
                
        
    }
    
   
    public TableColumn<Evenement, Integer> getId_id() {
        return id_id;
    }

    public void setId_id(TableColumn<Evenement, Integer> id_id) {
        this.id_id = id_id;
    }
    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        try {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            Parent root = Loader.load();
            AjouterEvenementController pc = Loader.getController();
            
            ajouterC.getScene().setRoot(root);
        } catch (IOException ex) {
        }
                initialize(null, null);

    }

    @FXML
    private void supprimerCategorie(ActionEvent event) {
        
        Categorie c = tableau_categorie.getSelectionModel().getSelectedItem();
        if (c==null)
        {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("echec");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner une categorie ! ");
        
        alert.showAndWait();
        }
        else
        {
            EvenementService es = new EvenementService();
            if(es.verifierSupprimer(c.getId())==false)
            {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("echec");
        alert.setHeaderText(null);
        alert.setContentText("Impossible de supprimer cette categorie , il y a au mois un événement qui l'utilise !");
        
        alert.showAndWait();
            }
            else{
       CategorieService cs = new CategorieService();
        
        cs.supprimerCategorie(c.getId());
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Categorie supprimee !");
        
        alert.showAndWait();
            }
        }
        initialize(null, null);
    }

    @FXML
    private void ModifierCategorie(ActionEvent event) {
        
        if (tableau_categorie.getSelectionModel().getSelectedItem()==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("echec");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner une categorie ! ");
        
        alert.showAndWait(); 
        }
        else
        {
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("ModifierEvenement.fxml"));
                
                Parent root = Loader.load();
                ModifierEvenementController pc = Loader.getController();
                pc.setId(tableau_categorie.getSelectionModel().getSelectedItem().getId());
                pc.setTf_nom_id(tableau_categorie.getSelectionModel().getSelectedItem().getNom());
                pc.setDescription_Id(tableau_categorie.getSelectionModel().getSelectedItem().getDescription());
                  ajouterC.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
        Evenement e = table_evenement.getSelectionModel().getSelectedItem();
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("echec");
            alert.setHeaderText(null);
            alert.setContentText("veuillez sélectionner un événement ! ");

            alert.showAndWait();
        } else {
            ParticiperService ps = new ParticiperService();
            if (ps.verifierSupprimer(e.getId()) == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("echec");
                alert.setHeaderText(null);
                alert.setContentText("Impossible de supprimer cet événement , veuillez contacter les participants !");

                alert.showAndWait();
            } else {
                EvenementService cs = new EvenementService();

                cs.supprimerEvenement(e.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Evenement supprime!");

                alert.showAndWait();
            }
        }
        initialize(null, null);
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
            Parent root = Loader.load();
            AfficherEventController pc = Loader.getController();

            SupprimerId.getScene().setRoot(root);
        } catch (IOException ex) {
        }
         initialize(null, null);
    }

   


}
