/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class AfficherEventController implements Initializable {

    ObservableList<Evenement> Elist;
    private Label label1;
    private Label label2;
    private Label label4;
    private Label label3;
    @FXML
    private Label lesevenements;
    @FXML
    private Label description1;
    @FXML
    private Label prix1;
    @FXML
    private Label titre1;
    @FXML
    private Label lieu1;
    private JFXButton supprimer1;
    @FXML
    private ImageView image1;
    @FXML
    private Label nbrplace1;
    @FXML
    private Label datedebut1;
    @FXML
    private Label datefin1;
    @FXML
    private Label description3;
    @FXML
    private Label prix3;
    @FXML
    private Label titre3;
    @FXML
    private Label lieu3;
    @FXML
    private ImageView image3;
    @FXML
    private Label nbrplace3;
    @FXML
    private Label datedebut3;
    @FXML
    private Label datefin3;
    @FXML
    private Label description2;
    @FXML
    private Label prix2;
    @FXML
    private Label titre2;
    @FXML
    private Label lieu2;
    @FXML
    private ImageView image2;
    @FXML
    private Label nbrplace2;
    @FXML
    private Label datedebut2;
    @FXML
    private Label datefin2;
    @FXML
    private Label description4;
    @FXML
    private Label prix4;
    @FXML
    private Label titre4;
    @FXML
    private Label lieu4;
    @FXML
    private ImageView image4;
    @FXML
    private Label nbrplace4;
    @FXML
    private Label datedebut4;
    @FXML
    private Label datefin4;
    @FXML
    private JFXButton Ajouterbtn;
    private FileChooser filechooser = new FileChooser();
    private File file;
    private Image img;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane3;
    @FXML
    private AnchorPane pane2;
    @FXML
    private AnchorPane pane4;
    @FXML
    private JFXButton consulter1;
    @FXML
    private JFXButton consulter3;
    @FXML
    private JFXButton consulter2;
    @FXML
    private JFXButton consulter4;
    @FXML
    private Label Categorie1;
    @FXML
    private Label Categorie3;
    @FXML
    private Label Categorie2;
    @FXML
    private Label Categorie4;
    @FXML
    private Label id_message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_message.setVisible(false);

        EvenementService edao = new EvenementService();
        Elist = edao.listerEvenementParticiper(1);
        image1.setVisible(true);
        image2.setVisible(true);
        image3.setVisible(true);
        image4.setVisible(true);

        int i = 0;
        for (i = 0; i < Elist.size(); i++) {
            if (i == 0) {
                titre1.setText(Elist.get(i).getNom());
                description1.setText(Elist.get(i).getDescription());
                lieu1.setText(Elist.get(i).getLieu());
                datedebut1.setText(Elist.get(i).getDate_debut().toString());
                datefin1.setText(Elist.get(i).getDate_fin().toString());

                prix1.setText("" + Elist.get(i).getPrix() + " DT");
                nbrplace1.setText("" + Elist.get(i).getNbr_place());
              //  nbrplace1.setText("" + Elist.get(i).getImage());

                file = new File(Elist.get(i).getImage());
                img = new Image(file.toURI().toString(), 208, 238, true, true);
                image1.setImage(img);
                Categorie1.setText(Elist.get(i).getCategorie());

            }
            if (i == 1) {
                titre2.setText(Elist.get(i).getNom());
                description2.setText(Elist.get(i).getDescription());
                lieu2.setText(Elist.get(i).getLieu());
                datedebut2.setText(Elist.get(i).getDate_debut().toString());
                datefin2.setText(Elist.get(i).getDate_fin().toString());
                prix2.setText("" + Elist.get(i).getPrix() + " DT");
                nbrplace2.setText("" + Elist.get(i).getNbr_place());
              //  nbrplace2.setText("" + Elist.get(i).getImage());

                file = new File(Elist.get(i).getImage());
                img = new Image(file.toURI().toString(), 208, 238, true, true);
                image2.setImage(img);
                                Categorie2.setText(Elist.get(i).getCategorie());


            }
            if (i == 2) {
                titre3.setText(Elist.get(i).getNom());
                description3.setText(Elist.get(i).getDescription());
                lieu3.setText(Elist.get(i).getLieu());
                datedebut3.setText(Elist.get(i).getDate_debut().toString());
                datefin3.setText(Elist.get(i).getDate_fin().toString());
                prix3.setText("" + Elist.get(i).getPrix() + " DT");
                nbrplace3.setText("" + Elist.get(i).getNbr_place());
                file = new File(Elist.get(i).getImage());
                img = new Image(file.toURI().toString(), 208, 238, true, true);
                image3.setImage(img);
                                Categorie3.setText(Elist.get(i).getCategorie());


            }
            if (i == 3) {
                titre4.setText(Elist.get(i).getNom());
                description4.setText(Elist.get(i).getDescription());
                lieu4.setText(Elist.get(i).getLieu());
                datedebut4.setText(Elist.get(i).getDate_debut().toString());
                datefin4.setText(Elist.get(i).getDate_fin().toString());
                prix4.setText("" + Elist.get(i).getPrix() + " DT");
                nbrplace4.setText("" + Elist.get(i).getNbr_place());
                file = new File(Elist.get(i).getImage());
                img = new Image(file.toURI().toString(), 208, 238, true, true);
                image4.setImage(img);
                                Categorie4.setText(Elist.get(i).getCategorie());

            }
            System.out.println("parararar");

        }
        int n = Elist.size();
        System.out.println(n);
        if (n == 0) {
            pane1.setVisible(false);
            pane2.setVisible(false);
            pane3.setVisible(false);
            pane4.setVisible(false);
            id_message.setVisible(true);

        }
        if (n == 1) {
            pane2.setVisible(false);
            pane3.setVisible(false);
            pane4.setVisible(false);

        }
        if (n == 2) {
   pane3.setVisible(false);
            pane4.setVisible(false);
        }
        if (n == 3) {
 pane4.setVisible(false);
        }
        // TODO
    }

    @FXML
    private void afficher(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("ShowAllEvents.fxml"));
            Parent root = Loader.load();
            ShowAllEventsController pc = Loader.getController();

            Ajouterbtn.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        initialize(null, null);
    }

    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("ajouterEvent.fxml"));
            Parent root = Loader.load();
            AjouterEventController pc = Loader.getController();

            consulter1.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        initialize(null, null);
    }

    @FXML
    private void retour(ActionEvent event) {
         try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = Loader.load();
            AfficherEvenementController pc = Loader.getController();

            Ajouterbtn.getScene().setRoot(root);
        } catch (IOException ex) {
        }
         initialize(null, null);
    }

    @FXML
    private void Consulter1(ActionEvent event) {
        Evenement e = Elist.get(0);
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
            prix1.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(ShowAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulter3(ActionEvent event) {
         Evenement e = Elist.get(2);
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
            prix1.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(ShowAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulter2(ActionEvent event) {
         Evenement e = Elist.get(1);
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
            prix1.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(ShowAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void consulter4(ActionEvent event) {
         Evenement e = Elist.get(3);
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
            pc.setId(e.getId()); file = new File(e.getImage());
            pc.setImageviewer(e.getImage());
            
            prix1.getScene().setRoot(root);
            
        } catch (IOException ex) {
            Logger.getLogger(ShowAllEventsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
