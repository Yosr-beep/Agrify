/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import GestionMateriel.entities.Materiel;
import GestionMateriel.services.MaterielCRUD;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import GestionMateriel.entities.Materiel;
import static java.sql.Types.NULL;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class MaterielAjoutController implements Initializable {
    //Boutons de gestion
    @FXML
    private Button btgesmatmat;
    @FXML
    private Button btgesmaintmat;
    
    //formulaire données a recuperer 
    @FXML
    private TextField tfidmat;
    @FXML
    private ComboBox combotypemat;
    @FXML
    private TextField tfetatmat;
    @FXML
    private TextField tfcapmmat;
    @FXML
    private TextField tfcapvmat;
    @FXML
    private TextField tfprixmat;
    @FXML
    private TextField tfrecherchermat;
    
    //boutons CRUD
    @FXML
    private Button btajoutermat;
    @FXML
    private Button btmodifiermat;
    @FXML
    private Button btrechmat;
    @FXML
    private Button btsuppmat;
    
    //affichage tableview
    @FXML
    private TableView tvmat;
    @FXML
    private TableColumn<Materiel, String> tabidmat;
    @FXML
    private TableColumn<Materiel, String> tabtypemat;
    @FXML
    private TableColumn<Materiel, String> tabetatmat;
    @FXML
    private TableColumn<Materiel, String> tabcapmmat;
    @FXML
    private TableColumn<Materiel, String> tabcapvmat;
    @FXML
    private TableColumn<Materiel, String> tabprixmat;
    @FXML
    private Label lbconfmat;
    @FXML
    private Label lbconfmat2;
    @FXML
    private Button btreloadmat1;
    @FXML
    private Label lberrmat;
    @FXML
    private Button btsmailmat1;
    
    
    ObservableList<Materiel> observableMats = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     *//*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> list1 = FXCollections.observableArrayList( "vehicule","outil","silo");
         combotypemat.setItems(list1);
         
        tabidmat.setCellValueFactory(new PropertyValueFactory<>("id")); 
        tabtypemat.setCellValueFactory(new PropertyValueFactory<>("type")); 
        tabetatmat.setCellValueFactory(new PropertyValueFactory<>("etat")); 
        tabcapmmat.setCellValueFactory(new PropertyValueFactory<>("capacite_masse")); 
        tabcapvmat.setCellValueFactory(new PropertyValueFactory<>("capacite_volume")); 
        tabprixmat.setCellValueFactory(new PropertyValueFactory<>("prix")); 
        
        MaterielCRUD mc =new MaterielCRUD();
        List<Materiel> mats = mc.afficherMateriel();
        ObservableList<Materiel> observableMats = FXCollections.observableArrayList(mats);
        
        tvmat.setItems(observableMats); 

    }    */
    public void initialize(URL url, ResourceBundle rb) {
    // TODO
    ObservableList<String> list1 = FXCollections.observableArrayList("vehicule", "outil", "silo");
    combotypemat.setItems(list1);

    tabidmat.setCellValueFactory(new PropertyValueFactory<>("id"));
    tabtypemat.setCellValueFactory(new PropertyValueFactory<>("type"));
    tabetatmat.setCellValueFactory(new PropertyValueFactory<>("etat"));
    tabcapmmat.setCellValueFactory(new PropertyValueFactory<>("capacite_masse"));
    tabcapvmat.setCellValueFactory(new PropertyValueFactory<>("capacite_volume"));
    tabprixmat.setCellValueFactory(new PropertyValueFactory<>("prix"));

    MaterielCRUD mc = new MaterielCRUD();
    List<Materiel> mats = mc.afficherMateriel();
    ObservableList<Materiel> observableMats = FXCollections.observableArrayList(mats);

    tvmat.setItems(observableMats);

    // Add listener to handle row selection
    tvmat.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Display confirmation dialog
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Voulez-vous vraiment sélectionner cet élément?");
            confirmationAlert.setContentText("Cliquez sur OK pour confirmer.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User confirmed the selection
                lbconfmat.setText("Élément sélectionné! ");
            } else {
                // User canceled the selection
                // Clear the selection in the TableView
                tvmat.getSelectionModel().clearSelection();
                lbconfmat.setText("Sélection annulée");
            }
        }
    });
}


    @FXML
    private void BoutonGestionMaterielMat(ActionEvent event) {
    }

    @FXML
    private void BoutonGestionMaintenanceMat(ActionEvent event) {
    }

    
    
@FXML
private void BoutonAjouterMateriel(ActionEvent event) {
    try {
        String type = combotypemat.getSelectionModel().getSelectedItem().toString();
        String etat = tfetatmat.getText();
        int capacite_m = Integer.parseInt(tfcapmmat.getText());
        int capacite_v = Integer.parseInt(tfcapvmat.getText());
        int prix = Integer.parseInt(tfprixmat.getText());

        Materiel m = new Materiel(type, etat, capacite_m, capacite_v, prix);
        MaterielCRUD mc = new MaterielCRUD();

        if (etat.isEmpty() || capacite_m == 0 || capacite_v == 0 || prix == 0 || (combotypemat.getSelectionModel().getSelectedIndex()) == -1) {
            // Display an error message
            lberrmat.setText("Remplir tous les champs!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Remplir tous les champs!");
            alert.showAndWait();
        } else {
            mc.ajouterMateriel(m);
            lbconfmat.setText(type + " ajoutée!");
            
        }
    } catch (NumberFormatException e) {
        // Handle NumberFormatException (if parseInt fails)
        lberrmat.setText("Vérifiez les champs!");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Vérifiez les champs!");
        alert.showAndWait();
    } catch (NullPointerException e) {
        // Handle NullPointerException (if selected item is null)
        lberrmat.setText("Sélectionnez un type de matériel!");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Sélectionnez un type de matériel!");
        alert.showAndWait();
    } catch (Exception e) {
        // Handle other exceptions
        lberrmat.setText("Une erreur est survenue!");
        e.printStackTrace(); // Print the stack trace for debugging purposes
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Une erreur est survenue!");
        alert.showAndWait();
    }
}

@FXML
    private void BoutonModifierMateriel(ActionEvent event) {
        try {
            int id = Integer.parseInt(tfidmat.getText());
            String type = combotypemat.getSelectionModel().getSelectedItem().toString();
            String etat = tfetatmat.getText();
            int capacite_m = Integer.parseInt(tfcapmmat.getText());
            int capacite_v = Integer.parseInt(tfcapvmat.getText());
            int prix = Integer.parseInt(tfprixmat.getText());

            Materiel m1 = new Materiel(type, etat, capacite_m, capacite_v, prix);
            MaterielCRUD mc = new MaterielCRUD();
            mc.modifierMateriel(m1, id);

            // Send SMS notification upon successful modification
            sendSmsNotification();

            lbconfmat.setText(type + " Modifié!");
        } catch (NumberFormatException e) {
            // Handle NumberFormatException (if parseInt fails)
            showErrorAlert("Vérifiez tous les champs!");
        } catch (NullPointerException e) {
            // Handle NullPointerException (if selected item is null)
            showErrorAlert("Sélectionnez un type de matériel!");
        } catch (Exception e) {
            // Handle other exceptions
            showErrorAlert("Une erreur est survenue!");
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }
private static final String ACCOUNT_SID = "ACe058792ac5c1c947260765c684419c1f";
private static final String AUTH_TOKEN = "626d4908ba6f167bdfdbcbaf25cfcc13";
private static final String TWILIO_PHONE_NUMBER = "+19299305820";
private static final String RECIPIENT_PHONE_NUMBER = "+21655040677"; 

    private void sendSmsNotification() {
        
        
    // Initialize Twilio
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Message content
    String messageBody = "Material has been modified successfully!";

    // Send SMS message
    Message message = Message.creator(
            new PhoneNumber(RECIPIENT_PHONE_NUMBER), // Recipient's phone number
            new PhoneNumber(TWILIO_PHONE_NUMBER),
            messageBody).create();

    // Print the SID of the sent message (for debugging purposes)
    System.out.println("Message SID: " + message.getSid());
}

    private void showErrorAlert(String message) {
        lberrmat.setText(message);
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(message);
        alert.showAndWait();
    }
/*
@FXML
private void BoutonModifierMateriel(ActionEvent event) {
    try {
        int id = Integer.parseInt(tfidmat.getText());
        String type = combotypemat.getSelectionModel().getSelectedItem().toString();
        String etat = tfetatmat.getText();
        int capacite_m = Integer.parseInt(tfcapmmat.getText());
        int capacite_v = Integer.parseInt(tfcapvmat.getText());
        int prix = Integer.parseInt(tfprixmat.getText());

        Materiel m1 = new Materiel(type, etat, capacite_m, capacite_v, prix);
        MaterielCRUD mc = new MaterielCRUD();
        mc.modifierMateriel(m1, id);

        lbconfmat.setText(type + " Modifié!");
    } catch (NumberFormatException e) {
        // Handle NumberFormatException (if parseInt fails)
        lberrmat.setText("Vérifiez tous les champs!");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Vérifiez tous les champs!");
        alert.showAndWait();
    } catch (NullPointerException e) {
        // Handle NullPointerException (if selected item is null)
        lberrmat.setText("Sélectionnez un type de matériel!");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Sélectionnez un type de matériel!");
        alert.showAndWait();
    } catch (Exception e) {
        // Handle other exceptions
        lberrmat.setText("Une erreur est survenue!");
        e.printStackTrace(); // Print the stack trace for debugging purposes
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText("Une erreur est survenue!");
        alert.showAndWait();
    }
}
*/

    @FXML
    private void BoutonRechercherMateriel(ActionEvent event) {
       String text = tfrecherchermat.getText(); 
       int id = Integer.parseInt(text);
       
       MaterielCRUD mc =new MaterielCRUD();
       Materiel searched_mat= mc.recherchermateriel(id);
       
       ObservableList<Materiel> searchedList = FXCollections.observableArrayList(searched_mat);
        
       tabidmat.setCellValueFactory(new PropertyValueFactory<>("id")); 
       tabtypemat.setCellValueFactory(new PropertyValueFactory<>("type")); 
       tabetatmat.setCellValueFactory(new PropertyValueFactory<>("etat")); 
       tabcapmmat.setCellValueFactory(new PropertyValueFactory<>("capacite_masse")); 
       tabcapvmat.setCellValueFactory(new PropertyValueFactory<>("capacite_volume")); 
       tabprixmat.setCellValueFactory(new PropertyValueFactory<>("prix")); 
       tvmat.setItems(searchedList);
       
       lbconfmat2.setText("Matériel trouvé");
        
    }

    @FXML
private void BoutonSupprimerMateriel(ActionEvent event) {
    String text = tfrecherchermat.getText();
    int searchTerm = Integer.parseInt(text);

    // Display confirmation dialog
    Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmationAlert.setTitle("Confirmation");
    confirmationAlert.setHeaderText("Voulez-vous vraiment supprimer ce matériel?");
    confirmationAlert.setContentText("Cette action est irréversible.");

    Optional<ButtonType> result = confirmationAlert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        // User clicked 'OK', proceed with deletion
        MaterielCRUD mc = new MaterielCRUD();
        mc.supprimerMateriel(searchTerm);

        lbconfmat2.setText("Matériel supprimé");
    } else {
        // User clicked 'Cancel' or closed the dialog, do nothing
        lbconfmat2.setText("Suppression annulée");
    }
}


    @FXML
    private void BoutonReloadMateriel(ActionEvent event) {
        
        try {
        Parent aff1Root = FXMLLoader.load(getClass().getResource("MaterielAjout.fxml"));
        Scene Scene1 = new Scene(aff1Root);
        
        
        // Create a new stage  interface
        Stage ret1Stage = new Stage();
        ret1Stage.initStyle(StageStyle.TRANSPARENT);
        ret1Stage.setScene(Scene1);
        ret1Stage.show();
        
        // Close the splash screen stage
        Stage splash2 = (Stage) btreloadmat1.getScene().getWindow();
        splash2.close();
    } catch (IOException ex) {
        Logger.getLogger(MaterielAjoutController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    @FXML
    private void BoutonEmailMateriel(ActionEvent event) {

        try {
        String outputFile = "materiel_data.pdf";

        // Créez un document PDF
        Document document = new Document();

        // Créez un écrivain PDF en spécifiant le fichier de sortie
        PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(outputFile));

        // Ouvrez le document
        document.open();

        // Ajoutez un titre
        Paragraph title = new Paragraph("Données du Materiel");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Créez une table pour afficher les données
        PdfPTable table = new PdfPTable(6); // 7 colonnes pour vos données
        table.setWidthPercentage(100);

        // En-têtes de colonne
        table.addCell("ID");
        table.addCell("TYPE");
        table.addCell("ETAT");
        table.addCell("CAPACITE_Masse");
        table.addCell("CAPACITE_VOLUME");
        table.addCell("PRIX");

        // Remplissez la table avec les données des animaux
        
        ObservableList<Materiel> observableMats = FXCollections.observableArrayList();
        
        for (Materiel m : observableMats) {
            table.addCell(String.valueOf(m.getId()));
            table.addCell(String.valueOf(m.getType()));
            table.addCell(String.valueOf(m.getEtat()));
            table.addCell(String.valueOf(m.getCapacite_masse()));
            table.addCell(String.valueOf(m.getCapacite_volume()));
            table.addCell(String.valueOf(m.getPrix()));
       
        }
        
        observableMats.addAll();

        // Ajoutez la table au document
        document.add(table);

        // Fermez le document
        document.close();
         // Affichez une alerte de succès
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier PDF a été exporté avec succès !");
        alert.showAndWait();
    } catch (DocumentException | IOException e) {
        e.printStackTrace();

        // En cas d'erreur, affichez une alerte d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Erreur lors de l'exportation du PDF");
        alert.setContentText("Une erreur s'est produite lors de l'exportation du fichier PDF.");
        alert.showAndWait();
    }
   
    }
    

}
    
    
  /*
    @FXML
    private void BoutonAjouterMateriel(ActionEvent event) {
        
        String type = combotypemat.getSelectionModel().getSelectedItem().toString();
        String etat = tfetatmat.getText();
        int capacite_m = Integer.parseInt(tfcapmmat.getText());
        int capacite_v = Integer.parseInt(tfcapvmat.getText());
        int prix = Integer.parseInt(tfprixmat.getText());
        
            Materiel m = new Materiel(type, etat, capacite_m, capacite_v, prix);
            MaterielCRUD mc = new MaterielCRUD();
        
        System.out.println("bonjour");
         
        if (etat.isEmpty()|| capacite_m==NULL|| capacite_v==NULL|| capacite_v==NULL ||(combotypemat.getSelectionModel().getSelectedIndex())==-1) {
            // Display an error message
            lberrmat.setText("Repmlir tt les champs!");
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Remplir tous les champs!");
            alert.showAndWait();
        }
    
        
            mc.ajouterMateriel(m);
            lbconfmat.setText(type+" ajoutée!");
        
        
        

    } */

/*
    @FXML
    private void BoutonModifierMateriel(ActionEvent event) {
        
        int id= Integer.parseInt(tfidmat.getText());
   
        String type = combotypemat.getSelectionModel().getSelectedItem().toString();
        String etat = tfetatmat.getText();
        int capacite_m = Integer.parseInt(tfcapmmat.getText());
        int capacite_v = Integer.parseInt(tfcapvmat.getText());
        int prix = Integer.parseInt(tfprixmat.getText());
        
       
        Materiel m1 =new Materiel(type,etat,capacite_m,capacite_v,prix);
        MaterielCRUD mc =new MaterielCRUD();
        mc.modifierMateriel(m1,id);
        
        lbconfmat.setText(type+" Modifié!");
    }*/
    

/*
    @FXML
    private void BoutonSupprimerMateriel(ActionEvent event) {
        
       String text = tfrecherchermat.getText(); 
       int searchTerm = Integer.parseInt(text);
       MaterielCRUD mc =new MaterielCRUD();
       mc.supprimerMateriel(searchTerm);
       
       lbconfmat2.setText("Materiel supprimé");

    }*/