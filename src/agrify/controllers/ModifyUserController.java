package agrify.controllers;

import agrify.entities.User;
import agrify.services.ServiceUser;
import agrify.utils.DataSource;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModifyUserController {

    @FXML
    private TextField EditUserEmailTextField;

    @FXML
    private Label EditUserMessage1;

    @FXML
    private Label EditUserMessage11;

    @FXML
    private Label EditUserMessage111;

    @FXML
    private TextField EditUserNbrAbscTextField;

    @FXML
    private TextField EditUserNomTextField;

    @FXML
    private TextField EditUserPasswordTextField;

    @FXML
    private TextField EditUserPhoneTextField;

    @FXML
    private TextField EditUserPrenomTextField;

    @FXML
    private ComboBox<String> EditUserRoleCombo;

    @FXML
    private ComboBox<String> EditUserSexCombo;

    @FXML
    private TextField EditUserUsernameTextField;

    @FXML
    private Button ModifySearchUserBtn;

    @FXML
    private Button ModifyUser1Btn;

    @FXML
    private Button ModifyUserBackBtn;

    @FXML
    private TextField SearchModifyUserTextFieldBtn;
    private Connection connect;
    private ServiceUser userService;
    private User currentUser;


 @FXML
    void initialize() {
        // Initialisation de la connexion à la base de données et du service
        connect = DataSource.getInstance().getConnection();
        userService = new ServiceUser(connect);
    }

    
    @FXML
    void ModifySearchUser(ActionEvent event) {
        String searchQuery = SearchModifyUserTextFieldBtn.getText();

        if (searchQuery.isEmpty()) {
            EditUserMessage1.setText("Veuillez entrer un ID valide.");
        } else {
            User user = userService.getOne(Integer.parseInt(searchQuery));

            if (user != null) {
                // Remplir les champs avec les informations de l'utilisateur
                currentUser = user;
                EditUserNomTextField.setText(user.getUser_nom());
                EditUserPrenomTextField.setText(user.getUser_prenom());
                EditUserEmailTextField.setText(user.getUser_email());
                EditUserPhoneTextField.setText(user.getUser_telephone());

                // Remplir les ComboBox avec les valeurs statiques
                EditUserRoleCombo.getItems().setAll("Admin", "User");
                EditUserSexCombo.getItems().setAll("Homme", "Femme");

                EditUserRoleCombo.setValue(user.getUser_role());
                EditUserSexCombo.setValue(user.getUser_genre());
                EditUserNbrAbscTextField.setText(String.valueOf(user.getUser_nbrabscence()));
                EditUserUsernameTextField.setText(user.getUsername());
                EditUserPasswordTextField.setText(user.getPassword());
                EditUserMessage1.setText("Utilisateur trouvé.");
            } else {
                EditUserMessage1.setText("Utilisateur non trouvé.");
            }
        }
    }

       
@FXML
    void ModifyUser1(ActionEvent event) {
        if (currentUser != null) {
            try {
                // Récupérez les valeurs des champs
                String nom = EditUserNomTextField.getText();
                String prenom = EditUserPrenomTextField.getText();
                String email = EditUserEmailTextField.getText();
                String telephone = EditUserPhoneTextField.getText();
                String role = EditUserRoleCombo.getValue();
                String genre = EditUserSexCombo.getValue();
                int nbrAbscences = Integer.parseInt(EditUserNbrAbscTextField.getText());
                String username = EditUserUsernameTextField.getText();
                String password = EditUserPasswordTextField.getText();

                // Mettez à jour l'utilisateur actuellement modifié
                currentUser.setUser_nom(nom);
                currentUser.setUser_prenom(prenom);
                currentUser.setUser_email(email);
                currentUser.setUser_telephone(telephone);
                currentUser.setUser_role(role);
                currentUser.setUser_genre(genre);
                currentUser.setUser_nbrabscence(nbrAbscences);
                currentUser.setUsername(username);
                currentUser.setPassword(password);

                userService.modifier(currentUser);

                EditUserMessage111.setText("Utilisateur mis à jour avec succès.");
            } catch (NumberFormatException e) {
                EditUserMessage111.setText("Veuillez saisir un nombre valide pour le nombre d'absences.");
            }
        } else {
            EditUserMessage111.setText("Aucun utilisateur à mettre à jour. Veuillez effectuer une recherche d'abord.");
        }
    }

    @FXML
    void ModifyUserBack(ActionEvent event) throws Exception{
      // Load the sign-Up interface
   Parent signUpRoot = FXMLLoader.load(getClass().getResource("/agrify/views/UserHome.fxml"));
   Scene signUpScene = new Scene(signUpRoot);
   

   // Create a new stage for the sign-in interface
   Stage signUpStage = new Stage();
   signUpStage.initStyle(StageStyle.TRANSPARENT);
   signUpStage.setScene(signUpScene);
   signUpStage.show();

   // Close the splash screen stage
   Stage splashSignInStage = (Stage) ModifyUserBackBtn.getScene().getWindow();
   splashSignInStage.close();
    }

    
    @FXML
    void GenreModify(ActionEvent event) {

    }

    @FXML
    void RoleModify(ActionEvent event) {

    }
    
    
}