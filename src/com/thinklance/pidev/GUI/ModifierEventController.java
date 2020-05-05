/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinklance.pidev.GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Oussema-PC
 */
public class ModifierEventController implements Initializable {

    @FXML
    private TextField nom1;
    @FXML
    private TextField lieu1;
    @FXML
    private DatePicker datedebut1;
    @FXML
    private DatePicker datefin1;
    @FXML
    private TextField nbr_place1;
    @FXML
    private TextField prix1;
    @FXML
    private TextArea description1;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton Ajouter1;
    @FXML
    private ImageView photo1;
    @FXML
    private Label Image_label1;
    @FXML
    private JFXComboBox<String> categorie1;

     private String tessssssssssst;
    private FileChooser filechooser = new FileChooser();
    private File file;
    private Image img;
    int id ;

    public TextField getNom1() {
        return nom1;
        // TODO
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom1(String nom1) {
        this.nom1.setText(nom1);
    }

    public TextField getLieu1() {
        return lieu1;
    }

    public void setLieu1(String lieu1) {
        this.lieu1.setText(lieu1);
    }

    public DatePicker getDatedebut1() {
        return datedebut1;
    }

    public void setDatedebut1(LocalDate datedebut1) {
        this.datedebut1.setValue((datedebut1));
    }

    public DatePicker getDatefin1() {
        return datefin1;
    }

    public void setDatefin1(LocalDate datefin1) {
        this.datefin1.setValue(datefin1);
    }

    public TextField getNbr_place1() {
        return nbr_place1;
    }

    public void setNbr_place1(String nbr_place1) {
        this.nbr_place1.setText(nbr_place1);
    }

    public TextField getPrix1() {
        return prix1;
    }

    public void setPrix1(String prix1) {
        this.prix1.setText(prix1);
    }

    public TextArea getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1.setText(description1);
    }

    public ImageView getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
       File file = new File(photo1);
        Image image = new Image(file.toURI().toString(), 100, 150, true, true);
        this.photo1.setImage(image);
    }

    public Label getImage_label1() {
        return Image_label1;
    }

    public void setImage_label1(String Image_label1) {
        this.Image_label1.setText(Image_label1);
    }

    public JFXComboBox<String> getCategorie1() {
        return categorie1;
    }

    /**
     * Initializes the controller class.
     */
    public void setCategorie1(JFXComboBox<String> categorie1) {    
        this.categorie1 = categorie1;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         CategorieService cs=new CategorieService();
       List<Categorie> L = new ArrayList();
       L=cs.afficherCategorieFAQ();
       
       for(int i=0;i<L.size();i++)
       {
           categorie1.getItems().add(L.get(i).getNom());
       }
        String [] possiblewords ={"Ariana,Tunisie ","Béja,Tunisie ","Ben Arous,Tunisie ","Bizerte,Tunisie ","Gabès,Tunisie ","Gafsa,Tunisie ","Jendoubah,Tunisie ","Kairaouan,Tunisie ","Kasserine,Tunisie ","Kébili,Tunisie ","Le Kef,Tunisie ","Mahdia,Tunisie ","La Manouba,Tunisie ","Médenine,Tunisie ","Monastir,Tunisie ","Nabeul,Tunisie ","Sfax,Tunisie ","Sidi Bouzid,Tunisie ","Siliana,Tunisie ","Sousse,Tunisie ","Tataouine,Tunisie ","Tozeur,Tunisie ","Tunis,Tunisie ","Zaghouan,Tunisie " };
       
        TextFields.bindAutoCompletion(lieu1, possiblewords);
        // TODO
    }    

    @FXML
    private void modifierAction(ActionEvent event) {
         if (nbr_place1.getText()!=null)
        {
                    try {
            Integer.parseInt(nbr_place1.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("le nombre de place est de type entier !");
        alert.showAndWait();
            System.out.println("error"); 
        }
        } 
        if (prix1.getText()!=null)
        {
             try {
            Float.parseFloat(prix1.getText());
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
        if((nom1.getText()==null)||(lieu1.getText()==null)||(description1.getText()==null)||(datedebut1.getValue()==null)||(datefin1.getValue()==null)||(prix1.getText()==null)||(nbr_place1.getText()==null)||(categorie1.getValue()==null))
        {  Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Il est obligatoire de remplire tout les champs !");
        alert.showAndWait();
            System.out.println("error");
            
        }
     
      else if(datedebut1.getValue().compareTo(date.getValue())<0)
       {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Echec");
     alert.setHeaderText(null);
       alert.setContentText("Verifier la date de debut !");
       alert.showAndWait();
           System.out.println("error"); 
        }
       else if(datedebut1.getValue().compareTo(datefin1.getValue())>0)
        {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Verifier la date de fin ! ");
        alert.showAndWait();
            System.out.println("error"); 
        }
       
       
         else if(( Float.parseFloat(prix1.getText())<1))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Echec");
        alert.setHeaderText(null);
        alert.setContentText("Le champ prix doit être positive !");
        alert.showAndWait();
            System.out.println("error");
        }
         
       else if(( Integer.parseInt(nbr_place1.getText())<10))
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
        Evenement e = new Evenement();
                String Nom = nom1.getText();
        String lieuu = lieu1.getText();
        String Description = description1.getText();
        // Date datedeb = datedebut.getValue();
        //  Date date_fin = datefin.getValue();
     
        e.setNom(Nom);
        e.setLieu(lieuu);
        e.setDescription(Description);
        e.setDate_debut(Date.valueOf(datedebut1.getValue()));
        e.setDate_fin(Date.valueOf(datefin1.getValue()));
        if (tessssssssssst == null) {
            tessssssssssst = "C:\\Users\\Oussema-PC\\Documents\\NetBeansProjects\\thinklance\\build\\classes\\com\\thinklance\\pidev\\kisspng-event-management-computer-icons-business-engagemen-event-5abc9368264de4.3741092015223079441569.jpg";
            e.setImage(tessssssssssst);
        } else {
            e.setImage(tessssssssssst);
        }
        e.setCategorie(categorie1.getValue());
        //  System.out.println("123"+tessssssssssst);

        //  e.setDate_debut(datedeb);
        //  e.setDate_fin(date_fin);
        e.setPrix(Float.parseFloat(prix1.getText()));
        e.setNbr_place(Integer.parseInt(nbr_place1.getText()));
        e.setIdadmin(1);

   //     CategorieService cs2 = new CategorieService();
//        categorie.getItems().addAll(
//                "Option 4",
//                "Option 5",
//                "Option 6"
//        );

        //categorie.setItems(options);
        // System.out.println("tessst"+file.getAbsolutePath());
        //System.out.println(tessssssssssst);
        EvenementService cs = new EvenementService();
        System.out.println(id);
        cs.modifierEvenement(e, id);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Evenement modifiee!");

        alert.showAndWait();
        }
        
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

    @FXML
    private void choosefile(MouseEvent event) {
        Node node = (Node) event.getSource();

        file = filechooser.showOpenDialog(node.getScene().getWindow());
        if (file != null) {

            img = new Image(file.toURI().toString(), 100, 150, true, true);
            photo1.setImage(img);
            System.out.println(file.getAbsolutePath());
            //  tessssssssssst=file.getPath();
            // System.out.println(tessssssssssst);
            Image_label1.setText(file.getAbsolutePath());
            tessssssssssst = file.getAbsolutePath();
            System.out.println(tessssssssssst);
    }
    
}}
