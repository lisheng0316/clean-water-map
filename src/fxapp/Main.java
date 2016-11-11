package fxapp;

import model.Account;
import controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;


import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Modality;
import model.AccountType;
import model.Database;
import model.WaterPurityReport;


import java.io.IOException;

/**
 * the main method of the application that deals with the creation of windows
 * and handling of the interactions between the views and the controllers
 */
public class Main extends Application {
    /** the main container for the application window */
    private Stage stage;
    private Account loggedAccount;
    private final double MINIMUM_WINDOW_WIDTH = 800.0;
    private final double MINIMUM_WINDOW_HEIGHT = 600.0;

    @Override
    public void start(Stage primaryStage) {

        Database db = Database.getDatabase();
        Database.connectToDatabase();

        try {
            stage = primaryStage;
            stage.setTitle("CWC");
            stage.getIcons().add(new Image("file:resources/menu.png"));
            stage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMinHeight(MINIMUM_WINDOW_HEIGHT);
            stage.setMaxWidth(MINIMUM_WINDOW_WIDTH);
            stage.setMaxHeight(MINIMUM_WINDOW_HEIGHT);
            gotoWelcome();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * A method to get the account of the user who logged in
     * @return the account of the user who logged in
     */
    public Account getLoggedAccount() { return loggedAccount;}

    /**
     * a method to send the app to the welcome screen
     */
    private void gotoWelcome() {
        try {
            WelcomeController welcome = (WelcomeController) replaceSceneContent("/view/WelcomePage.fxml");
            welcome.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a method to send the app to the historical report page
     * @param  wpr water purity report to show the historical graph
     */
    public void gotoHistoricalReport(WaterPurityReport wpr) {
        try {
            HistoricalReportController controller = (HistoricalReportController) replaceSceneContent("/view/HistoricalReport.fxml");
            controller.setApp(this, wpr);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a method to send the app to the registration page
     */
    public void gotoRegistration() {
        try {
            RegistrationController registration = (RegistrationController) replaceSceneContent("/view/RegisterPage.fxml");
            registration.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a method to send the app to the profile page
     */
    public void gotoProfile() {
        try {
            ProfileController profile = (ProfileController) replaceSceneContent("/view/ProfilePage.fxml");
            profile.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a method to send the app to the manager's map page
     */
    public void gotoManagerApp() {
        try {
            UserAppController appView = (UserAppController) replaceSceneContent("/view/ManagerAppView.fxml");
            appView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    /**
     * a method to send the app to the worker's map page
     */
    private void gotoWorkerApp() {
        try {
            WorkerAppController appView = (WorkerAppController) replaceSceneContent("/view/WorkerAppView.fxml");
            appView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * a method to send the app to the user's map page
     */
    private void gotoUserApp() {
        try {
            UserAppController appView = (UserAppController) replaceSceneContent("/view/UserAppView.fxml");
            appView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * a method to check if the user logged into the app
     * @param userId the user trying to log in
     * @return whether the user logged in or not
     */
    public boolean accountLogging(String userId) {
        loggedAccount = Database.getAccount(userId);
        gotoApp();
        return true;
    }

    /**
     * a method to check if the user updated their information on the
     * registration page of the app
     * @param userId the user that is logged in
     * @return whether or not the data was updated
     */
    public boolean registrationLogging(String userId) {
        loggedAccount = Database.getAccount(userId);
        gotoApp();
        return true;
    }
    /**
     * a method to send the app to the map page
     */
    public void gotoApp() {
        if (loggedAccount.getType() == AccountType.User) {
            gotoUserApp();
        } else if (loggedAccount.getType() == AccountType.Worker) {
            gotoWorkerApp();
        } else if (loggedAccount.getType() == AccountType.Manager) {
            gotoManagerApp();
        }

    }




    /**
     * a method to confirm that the user has logged out of the app
     * @return whether or not the user has logged out of the app
     */
    public boolean accountLogout() {
        loggedAccount = null;
        gotoWelcome();
        return true;
    }

    /**
     * a method to initialize the page passed from the fxml
     * @param fxml the fxml files used with the app page
     * @return the controller of the page the app is currently on
     * @throws Exception if the loading happened multiple times
     */
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            //redundant error?
            page = loader.load(in);

        } finally {
            in.close();
        }
        Scene scene = new Scene(page, MINIMUM_WINDOW_WIDTH, MINIMUM_WINDOW_HEIGHT);
    //    String css = AppViewController.class.getResource("/fxapp/stylesheet.css").toExternalForm();
    //    scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    /**
     * a method to show the dialog for login
     * @return whether or not the dialog was shown
     */
    public boolean showLoginDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginPage.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Log in");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LoginController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.confirmLoginClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * the method to starts the application
     * @param args any arguments that may be applied to the program
     */
    public static void main(String[] args) {

        launch(args);
    }
}