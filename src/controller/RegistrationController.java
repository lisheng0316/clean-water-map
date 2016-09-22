package controller;

import fxapp.Main;
import javafx.stage.Stage;

/**
 * Created by dawit on 9/22/16.
 */
public class RegistrationController {

    private Stage dialogStage;
    private Main application;
    private boolean confirmRegistration;

    public void setApp(Main application) {this.application = application;}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean confirmRegisterClicked() {
        return confirmRegistration;
    }
}
