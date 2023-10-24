/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.gui;

import GestionMateriel.entities.Maintenance;
import GestionMateriel.services.MaintenanceCRUD;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MaintenanceController implements Initializable {
    
    //parcours de pages
    @FXML
    private Button btgestmatmaint;
    @FXML
    private Button btgestmaintmaint;
    
    
    //recuperation des données
    @FXML
    private TextField tfidmaint;
    @FXML
    private ComboBox combotypemaint;
    @FXML
    private TextField tfdatedmaint;
    @FXML
    private TextField tfdatefmaint;
    @FXML
    private TextArea tadescmaint;
    
    //les boutons 
    @FXML
    private Button btajoutermaint;
    @FXML
    private Button btmodifiermaint;
    @FXML
    private Button btrechmaint;
    @FXML
    private Button btsuppmaint;
    @FXML
    private Button btareloadmaint1;
    
    //affichage de table 
    
    @FXML
    private TableColumn<Maintenance, String> tabidmaint;
    @FXML
    private TableColumn<Maintenance, String> tabmatmaint;
    @FXML
    private TableColumn<Maintenance, String> tabdatedmaint;
    @FXML
    private TableColumn<Maintenance, String> tabdatefmaint;
    @FXML
    private TableColumn<Maintenance, String> tabdescmaint;
    @FXML
    private TextField tfrecherchermaint;
    @FXML
    private TableView tvmaint;
    
    //error labels 
    @FXML
    private Label lberrmaint;
    @FXML
    private Label lbconfmaint;
    @FXML
    private Label lbconfmaint2;
   
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<String> list1 = FXCollections.observableArrayList( "vehicule","outil","silo");
        combotypemaint.setItems(list1);
        
        tabidmaint.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabmatmaint.setCellValueFactory(new PropertyValueFactory<>("materiel")); 
        tabdatedmaint.setCellValueFactory(new PropertyValueFactory<>("datedebut")); 
        tabdatefmaint.setCellValueFactory(new PropertyValueFactory<>("datefin")); 
        tabdescmaint.setCellValueFactory(new PropertyValueFactory<>("description")); 
        
        MaintenanceCRUD mc =new MaintenanceCRUD();
        List<Maintenance> mats = mc.afficherMaintenance();
        ObservableList<Maintenance> observableMats = FXCollections.observableArrayList(mats);
        
        tvmaint.setItems(observableMats); 
    }    
    
    

    @FXML
    private void BoutonGestionMaterielMaint(ActionEvent event) {
    }

    @FXML
    private void BoutonGestionMaintenanceMaint(ActionEvent event) {
    }

    @FXML
    private void BoutonAjouterMaintenance(ActionEvent event) {
        
        String materiel = combotypemaint.getSelectionModel().getSelectedItem().toString();
        String datedebut = tfdatedmaint.getText();
        String datefin = tfdatefmaint.getText();
        String description = tadescmaint.getText();
        
        Maintenance m = new Maintenance(materiel, datedebut, datefin, description);
        MaintenanceCRUD mc = new MaintenanceCRUD();
        mc.ajouterMaintenance(m);
        
        lbconfmaint.setText(materiel + " ajoutée!");
    }

    @FXML
    private void BoutonModifierMaintenance(ActionEvent event) {
        int id= Integer.parseInt(tfidmaint.getText());
   
        String materiel = combotypemaint.getSelectionModel().getSelectedItem().toString();
        String datedebut = tfdatedmaint.getText();
        String datefin = tfdatefmaint.getText();
        String description = tadescmaint.getText();
        
       
        Maintenance m1 =new Maintenance(materiel, datedebut, datefin, description);
        MaintenanceCRUD mc =new MaintenanceCRUD();
        mc.modifierMaintenance(m1,id);
        
        lbconfmaint.setText(materiel+" Modifié!");
    }

    @FXML
    private void BoutonRechercherMaintenance(ActionEvent event) {
        
       String text = tfrecherchermaint.getText(); 
       int id = Integer.parseInt(text);
       
       MaintenanceCRUD mc =new MaintenanceCRUD();
       Maintenance searched_mat= mc.rechercherMaintenance(id);
       
       ObservableList<Maintenance> searchedList = FXCollections.observableArrayList(searched_mat);
        
       tabidmaint.setCellValueFactory(new PropertyValueFactory<>("id"));
       tabmatmaint.setCellValueFactory(new PropertyValueFactory<>("materiel")); 
       tabdatedmaint.setCellValueFactory(new PropertyValueFactory<>("datedebut")); 
       tabdatefmaint.setCellValueFactory(new PropertyValueFactory<>("datefin")); 
       tabdescmaint.setCellValueFactory(new PropertyValueFactory<>("description"));
        
       tvmaint.setItems(searchedList);
       
       lbconfmaint2.setText("Maintenance trouvé");
    }

    @FXML
    private void BoutonSupprimerMaintenance(ActionEvent event) {
        
       String text = tfrecherchermaint.getText(); 
       int searchTerm = Integer.parseInt(text);
       MaintenanceCRUD mc =new MaintenanceCRUD();
       mc.supprimerMaintenance(searchTerm);
       
       lbconfmaint2.setText("Maintenance supprimé");

    }

    @FXML
    private void BoutonReloadMaintenance(ActionEvent event) {
    }
    
}
