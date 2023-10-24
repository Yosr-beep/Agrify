/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class MaterielMain extends Application {
    
 
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
             
            Parent root = FXMLLoader.load(getClass().getResource("MaterielAjout.fxml"));
            
             Scene scene = new Scene(root);
              
        primaryStage.setTitle("Gestion de Materiel");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      
        
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
