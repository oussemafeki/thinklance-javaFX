/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.Services.Impl.ParticiperService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class ShowEventController implements Initializable {

    @FXML
    private Label titre;
    @FXML
    private JFXButton Ajouter1;
    @FXML
    private Label prix;
    @FXML
    private Label description;
    @FXML
    private Label lieu;
    @FXML
    private JFXButton participer;
    @FXML
    private Label datedebut;
    @FXML
    private Label datefin;
    @FXML
    private Label nbrplace;
    private Label idid;

    int id;
    @FXML
    private JFXButton estceque;
    @FXML
    private Label message;
    @FXML
    private Label complet;
    @FXML
    private ImageView Imageviewer;
    @FXML
    private JFXButton annulerparticiper;
    @FXML
    private Label categorie;

    public Label getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie.setText(categorie);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Label getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.setText(titre);
    }

    public Label getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix.setText(prix);
    }

    public Label getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public Label getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu.setText(lieu);
    }

    public Label getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut.setText(datedebut);
    }

    public Label getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin.setText(datefin);
    }

    public Label getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(String nbrplace) {
        this.nbrplace.setText(nbrplace);
    }

    public Label getIdid() {
        return idid;
    }

    public void setIdid(Label idid) {
        this.idid = idid;
    }

    public ImageView getImageviewer() {
        return Imageviewer;
    }

    public void setImageviewer(String Imageviewer) {
         File file = new File(Imageviewer);
        Image image = new Image(file.toURI().toString(), 100, 150, true, true);
        this.Imageviewer.setImage(image);
    }
  

    /**
     * Initializes the controller class.
     */
    @FXML
    private void Annuler(ActionEvent event) {
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("ShowAllEvents.fxml"));
            Parent root = Loader.load();
            ShowAllEventsController pc = Loader.getController();

            titre.getScene().setRoot(root);
        } catch (IOException ex) {
        }
        // initialize(null, null);

    }

    public int hetId() {
        return id;
    }

    @FXML
    private void Participer(ActionEvent event) {
        ParticiperService ps = new ParticiperService();
        ps.ajouterParticiper(1, id);
        participer.setVisible(false);
        annulerparticiper.setVisible(true);
        message.setVisible(true);
        System.out.println(id);
        EvenementService es = new EvenementService();
        es.incrementerNbr(id);
        email();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement à participer");
        alert.setHeaderText(null);
        alert.setContentText("L'evenement "+titre.getText()+" aura lieu à "+lieu.getText()+" du "+datedebut.getText()+" jusqu'à "+datefin.getText());
        
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        participer.setVisible(false);
        complet.setVisible(false);
        message.setVisible(false);
        annulerparticiper.setVisible(false);
        // TODO
    }

    @FXML
    private void estceque(ActionEvent event) {
        estceque.setVisible(false);
        System.out.println(id);
        ParticiperService ps = new ParticiperService();
        if (ps.rechercherParticiper(1, id) == false) {
            message.setVisible(true);
            annulerparticiper.setVisible(true);
            participer.setVisible(false);
        } else {
            participer.setVisible(true);
        }
        String n = nbrplace.getText();
        int number = Integer.parseInt(n);
        System.out.println(n);
        if (number == 0) {
            complet.setVisible(true);
            participer.setVisible(false);

        }
    }

    @FXML
    private void annulerParticiper(ActionEvent event) {
         participer.setVisible(true);
        annulerparticiper.setVisible(false);
        message.setVisible(false);
         ParticiperService ps = new ParticiperService();
        ps.annulerParticiper(1, id);
        EvenementService es = new EvenementService();
        es.decrementerNbr(id);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Vous avez annuler votre participation !");
        
        alert.showAndWait();
        
    }
    public void email()
    {
         try{
            String host ="smtp.gmail.com" ;
            String user = "oussemafeki1997@gmail.com";
            String pass = "99081197";
            String to = "mohamedoussema.feki@esprit.tn";
            String from = "oussemafeki1997@gmail.com";
            String subject = "Rappel";
            String messageText = "Vous avez un événement à participer ("+titre.getText()+") le "+datedebut.getText()+" Soyez à l'heure !";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
             Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
             Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

             Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }

    }

}
