package controller;

import model.Authenticator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import fxapp.Main;
import javafx.stage.Stage;


public class LoginController extends AnchorPane implements Initializable {
    private Stage dialogStage;
    private boolean confirmLogin;
    @FXML
    private TextField accountId;
    @FXML
    private PasswordField password;
    @FXML
    private Label errorMessage;

    private Main application;

    public void setApp(Main application) { this.application = application;}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void processLogin(ActionEvent event) {
        if (application == null){
            errorMessage.setText("Hello " + accountId.getText());
        } else {
            if (!application.accountLogging(accountId.getText())){
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


        if (Authenticator.validatePassword(accountId.getText(), password.getText())) {
            application.accountLogging(accountId.getText());
            confirmLogin = true;
            dialogStage.close();
        } else {
            errorMessage.setVisible(true);
        }
    }
}