package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import fxapp.Main;
import javafx.stage.Stage;
import model.Authenticator;

public class LoginController extends AnchorPane implements Initializable {
    private Stage dialogStage;
    private boolean confirmLogin;
    @FXML
    private TextField userId;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    private Main application;

    public void setApp(Main application) {this.application = application;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void processLogin(ActionEvent event) {
        if (application == null){
            errorMessage.setText("Hello " + userId.getText());
        } else {
            if (!application.userLogging(userId.getText())){
                errorMessage.setText("Username/Password is incorrect");
            }
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void loginCancelPressed() {
        dialogStage.close();
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage for this dialog
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return  true if the user clicked the OK button
     */
    public boolean confirmLoginClicked() {
        return confirmLogin;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOKPressed() {


        if (Authenticator.validatePassword(userId.getText(), password.getText())) {
            application.userLogging(userId.getText());
            confirmLogin = true;
            dialogStage.close();
        } else {
            errorMessage.setVisible(true);
        }
    }
}