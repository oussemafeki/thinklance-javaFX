/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.thinklance.pidev.Services.Impl.CategorieService;
import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.Services.Impl.ParticiperService;
import com.thinklance.pidev.entities.Categorie;
import com.thinklance.pidev.entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class ShowAllEventsController implements Initializable {

    @FXML
    private TableView<Evenement> table_evenement;
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
    @FXML
    private TableColumn<Evenement, String> id_categorie;
    private JFXButton Ajouter1;
    private Connection connection;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    ObservableList<Evenement> Elist;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton retour;
    @FXML
    private JFXButton voir;
    private FileChooser filechooser = new FileChooser();
    private File file;
    private Image img;
    int id = 1;
    @FXML
    private JFXTextField recherche_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
       // addPhotoViewToTable();
        //col_delete = new TableColumn("SUPPRIMER");
        // col_addTache = new TableColumn("Tâches");
        table_evenement.setItems(Elist);
FilteredList <Evenement> FilterData = new FilteredList<>(Elist, e-> true);
        recherche_id.setOnKeyReleased(e->{
           recherche_id.textProperty().addListener((observableValue,oldValue,newValue)->{
                FilterData.setPredicate((Predicate<? super Evenement>)event->{
                    if(newValue==null || newValue.isEmpty()){
                        return true;
                    }
                    String lower = newValue.toLowerCase();
                    if(event.getNom().toLowerCase().contains(lower)){
                        return true;
                    }
                    else if(event.getDescription().toLowerCase().contains(lower)){
                        return true;
                    }
                    else if(event.getLieu().toLowerCase().contains(lower)){
                        return true;
                    }
                    else if(event.getCategorie().toLowerCase().contains(lower)){
                        return true;
                    }
                    
                    return false;
                });
            });
            SortedList<Evenement> sortedData= new SortedList<>(FilterData);
            sortedData.comparatorProperty().bind(table_evenement.comparatorProperty());
            table_evenement.setItems(sortedData);
        });
    }
    

    @FXML
    private void Annuler(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
            Parent root = Loader.load();
            AfficherEventController pc = Loader.getController();

            retour.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        initialize(null, null);
    }

    @FXML
    private void Supprimer(ActionEvent event) {

        Evenement e = table_evenement.getSelectionModel().getSelectedItem();
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("echec");
            alert.setHeaderText(null);
            alert.setContentText("veuillez sélectionner un événement ! ");

            alert.showAndWait();
        }
        else {
            if(e.getIdadmin()!=id)
            {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez pas la permission de supprimer cet événement !");

                alert.showAndWait();
            }
            else
            {
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
        }
        initialize(null, null);
    }

    @FXML
    private void Show(ActionEvent event) {

        Evenement e = table_evenement.getSelectionModel().getSelectedItem();
        if (e == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("echec");
            alert.setHeaderText(null);
            alert.setContentText("veuillez sélectionner un événement ! ");

            alert.showAndWait();
        } else {
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("ShowEvent.fxml"));

                Parent root = Loader.load();
                ShowEventController pc = Loader.getController();
                pc.setTitre(e.getNom());
                pc.setLieu(e.getLieu());
                pc.setDescription(e.getDescription());
                pc.setDatedebut(e.getDate_debut().toString());
                pc.setDatefin(e.getDate_fin().toString());
                pc.setNbrplace(e.getNbr_place() + "");
                pc.setPrix(e.getPrix() + " DT");
                pc.setId(e.getId());
                pc.setImageviewer(e.getImage());
                pc.setCategorie(e.getCategorie());

                voir.getScene().setRoot(root);

            } catch (IOException ex) {
                Logger.getLogger(ShowAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void addPhotoViewToTable() {
        TableColumn<Evenement, Void> photoColonne = new TableColumn("Image");
        Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>> cellFactory = new Callback<TableColumn<Evenement, Void>, TableCell<Evenement, Void>>() {
            @Override
            public TableCell<Evenement, Void> call(final TableColumn<Evenement, Void> param) {
                final TableCell<Evenement, Void> cell = new TableCell<Evenement, Void>() {
                    @FXML
                    private ImageView imageEvenement;

                    {
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Evenement e = getTableView().getItems().get(getIndex());
                           // file = new File(e.getImage());

                            //img = new Image(file.toURI().toString(), 100, 150, true, true);
                           imageEvenement = new ImageView(e.getImage());
                          //  imageEvenement.setImage(img);
                          //  imageEvenement.setFitHeight(100);
                          //  imageEvenement.setFitWidth(100);
                            setGraphic(imageEvenement);
                        }
                    }
                };
                return cell;
            }
        };
        photoColonne.setCellFactory(cellFactory);
        table_evenement.getColumns().add(photoColonne);
    }

    @FXML
    private void ModifierAction(ActionEvent event) {
        
          if (table_evenement.getSelectionModel().getSelectedItem()==null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("echec");
        alert.setHeaderText(null);
        alert.setContentText("veuillez sélectionner un événement ! ");
        
        alert.showAndWait(); 
        }
        else
        {
            if(table_evenement.getSelectionModel().getSelectedItem().getIdadmin()!=id)
            {
                  Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Echec");
                alert.setHeaderText(null);
                alert.setContentText("Vous n'avez pas la permission de modifier cet événement !");

                alert.showAndWait();
            }
            else
            {
            try {
                FXMLLoader Loader = new FXMLLoader(getClass().getResource("modifierEvent.fxml"));
                Evenement e = new Evenement();
                e=table_evenement.getSelectionModel().getSelectedItem();
                Parent root = Loader.load();
                ModifierEventController pc = Loader.getController();
                pc.setId(e.getId());
                pc.setNom1(e.getNom());
                pc.setDescription1(e.getDescription());
                pc.setImage_label1(e.getImage());
                pc.setNbr_place1(e.getNbr_place()+"");
                pc.setPrix1(e.getPrix()+"");
                pc.setLieu1(e.getLieu());
                pc.setPhoto1(e.getImage());
                
                
                
                  supprimer.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AfficherEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        }
        
    }

}
