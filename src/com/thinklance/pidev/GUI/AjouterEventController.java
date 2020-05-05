/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.thinklance.pidev.Services.Impl.CategorieService;
import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.entities.Categorie;
import com.thinklance.pidev.entities.Evenement;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javax.management.Notification;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class AjouterEventController implements Initializable {

    private WebView map;
    @FXML
    private TextField nom;
    @FXML
    private TextField lieu;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private TextField nbr_place;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> categorie;
    @FXML
    private TextArea description;
    @FXML
    private JFXButton Ajouter;

    @FXML
    private JFXButton Ajouter1;
    @FXML
    private ImageView photo;
    @FXML
    private Label Image_label;
    private String tessssssssssst;
    private FileChooser filechooser = new FileChooser();
    private File file;
    private Image img;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private void choosefile(MouseEvent event) {
        Node node = (Node) event.getSource();

        file = filechooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {

            img = new Image(file.toURI().toString(), 100, 150, true, true);
            photo.setImage(img);
            System.out.println(file.getAbsolutePath());
            //  tessssssssssst=file.getPath();
            // System.out.println(tessssssssssst);
            Image_label.setText(file.getAbsolutePath());
            tessssssssssst = file.getAbsolutePath();
            System.out.println(tessssssssssst);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategorieService cs=new CategorieService();
       List<Categorie> L = new ArrayList();
       L=cs.afficherCategorieFAQ();
       
       for(int i=0;i<L.size();i++)
       {
           categorie.getItems().add(L.get(i).getNom());
       }
       String [] possiblewords ={"Ariana,Tunisie ","Béja,Tunisie ","Ben Arous,Tunisie ","Bizerte,Tunisie ","Gabès,Tunisie ","Gafsa,Tunisie ","Jendoubah,Tunisie ","Kairaouan,Tunisie ","Kasserine,Tunisie ","Kébili,Tunisie ","Le Kef,Tunisie ","Mahdia,Tunisie ","La Manouba,Tunisie ","Médenine,Tunisie ","Monastir,Tunisie ","Nabeul,Tunisie ","Sfax,Tunisie ","Sidi Bouzid,Tunisie ","Siliana,Tunisie ","Sousse,Tunisie ","Tataouine,Tunisie ","Tozeur,Tunisie ","Tunis,Tunisie ","Zaghouan,Tunisie " };
       
        TextFields.bindAutoCompletion(lieu, possiblewords);

    }

    private void choosefile(ActionEvent event) {

    }

    @FXML
    private void ajouterAction(ActionEvent event) {
        if (nbr_place.getText()!=null)
        {
                    try {
            Integer.parseInt(nbr_place.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("le nombre de place est de type entier !");
        alert.showAndWait();
            System.out.println("error"); 
        }
        } 
        if (prix.getText()!=null)
        {
             try {
            Float.parseFloat(prix.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("le prix est de  type réel !");
        alert.showAndWait();
            System.out.println("error"); 
        }
        }

        DatePicker date = new DatePicker(LocalDate.now());
        System.out.println(date.getValue());
        if((nom.getText()==null)||(lieu.getText()==null)||(description.getText()==null)||(datedebut.getValue()==null)||(datefin.getValue()==null)||(prix.getText()==null)||(nbr_place.getText()==null)||(categorie.getValue()==null))
        {  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Il est obligatoire de remplire tout les champs !");
        alert.showAndWait();
            System.out.println("error");
            
        }
       
        
     
      else if(datedebut.getValue().compareTo(date.getValue())<0)
       {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Echec");
     alert.setHeaderText(null);
       alert.setContentText("Verifier la date de debut !");
       alert.showAndWait();
           System.out.println("error"); 
        }
       else if(datedebut.getValue().compareTo(datefin.getValue())>0)
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Verifier la date de fin ! ");
        alert.showAndWait();
            System.out.println("error"); 
        }
       
       
         else if(( Float.parseFloat(prix.getText())<1))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit être positive !");
        alert.showAndWait();
            System.out.println("error");
        }
         
       else if(( Integer.parseInt(nbr_place.getText())<10))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Le nombre min de place est 10 !");
        alert.showAndWait();
            System.out.println("error"); 
        }
        else
        {
        String Nom = nom.getText();
        String lieuu = lieu.getText();
        String Description = description.getText();
        // Date datedeb = datedebut.getValue();
        //  Date date_fin = datefin.getValue();
        Evenement e = new Evenement();
        e.setNom(Nom);
        e.setLieu(lieuu);
        e.setDescription(Description);
        e.setDate_debut(Date.valueOf(datedebut.getValue()));
        e.setDate_fin(Date.valueOf(datefin.getValue()));
        if (tessssssssssst == null) {
            tessssssssssst = "C:\\Users\\Oussema-PC\\Documents\\NetBeansProjects\\thinklance\\build\\classes\\com\\thinklance\\pidev\\kisspng-event-management-computer-icons-business-engagemen-event-5abc9368264de4.3741092015223079441569.jpg";
            e.setImage(tessssssssssst);
        } else {
            e.setImage(tessssssssssst);
        }
        e.setCategorie(categorie.getValue());
        //  System.out.println("123"+tessssssssssst);

        //  e.setDate_debut(datedeb);
        //  e.setDate_fin(date_fin);
        e.setPrix(Integer.parseInt(prix.getText()));
        e.setNbr_place(Integer.parseInt(nbr_place.getText()));
        e.setIdadmin(1);

        CategorieService cs2 = new CategorieService();
//        categorie.getItems().addAll(
//                "Option 4",
//                "Option 5",
//                "Option 6"
//        );

        //categorie.setItems(options);
        // System.out.println("tessst"+file.getAbsolutePath());
        //System.out.println(tessssssssssst);
        EvenementService cs = new EvenementService();
        cs.ajouterEvenement(e);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
      
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
            Parent root = Loader.load();
            AfficherEventController pc = Loader.getController();

            Ajouter1.getScene().setRoot(root);
        } catch (IOException ex) {
        }}
    }

    @FXML
    private void Annuler(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
            Parent root = Loader.load();
            AfficherEventController pc = Loader.getController();

            Ajouter1.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        initialize(null, null);
    }

}
