/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.gui;

import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author HP
 */
public class Maintenance2Controller implements Initializable {

    @FXML
    private Button btgestmatmaint;
    @FXML
    private Button btgestmaintmaint;
    @FXML
    private TextField tfidmaint;
    @FXML
    private ComboBox<?> combotypemaint;
    @FXML
    private TextField tfdatedmaint;
    @FXML
    private TextField tfdatefmaint;
    @FXML
    private TextArea tadescmaint;
    @FXML
    private Label lbconfmaint;
    @FXML
    private Button btajoutermaint;
    @FXML
    private Button btmodifiermaint;
    @FXML
    private Label lberrmaint;
    @FXML
    private TableView<?> tvmaint;
    @FXML
    private TableColumn<?, ?> tabidmaint;
    @FXML
    private TableColumn<?, ?> tabmatmaint;
    @FXML
    private TableColumn<?, ?> tabdatedmaint;
    @FXML
    private TableColumn<?, ?> tabdatefmaint;
    @FXML
    private TableColumn<?, ?> tabdescmaint;
    @FXML
    private TextField tfrecherchermaint;
    @FXML
    private Button btrechmaint;
    @FXML
    private Button btsuppmaint;
    @FXML
    private Button btareloadmaint1;
    @FXML
    private Label lbconfmaint2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BoutonGestionMaterielMaint(ActionEvent event) {
    }

    @FXML
    private void BoutonGestionMaintenanceMaint(ActionEvent event) {
    }

    @FXML
    private void BoutonAjouterMaintenance(ActionEvent event) {
    }

    @FXML
    private void BoutonModifierMaintenance(ActionEvent event) {
    }

    @FXML
    private void BoutonRechercherMaintenance(ActionEvent event) {
    }

    @FXML
    private void BoutonSupprimerMaintenance(ActionEvent event) {
    }

    @FXML
    private void BoutonReloadMaintenance(ActionEvent event) {
    }
    
}
