/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinklance;

import com.thinklance.pidev.Services.Impl.EvenementService;
import com.thinklance.pidev.entities.Evenement;
import java.time.Instant;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *
 * @author Oussema-PC
 */
public class Thinklance extends Application {
    
    @Override
    public void start(Stage stage)  throws IOException {
         Parent root1 = FXMLLoader.load(getClass().getResource("/com/thinklance/pidev/GUI/AfficherEvent.fxml"));
        stage.setResizable(false);
        stage.centerOnScreen();
        Scene scene = new Scene(root1);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
