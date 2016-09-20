package fxapp;

import controller.AppViewController;
import controller.LoginController;
import controller.WelcomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import model.Authenticator;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    /** the main container for the application window */
    private Stage mainScreen;
    private User loggedUser;
    private final double MINIMUM_WINDOW_WIDTH = 800.0;
    private final double MINIMUM_WINDOW_HEIGHT = 450.0;

    @Override
    public void start(Stage primaryStage) {
        try {
            mainScreen = primaryStage;
//            Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
            primaryStage.setTitle("Clean Water Map");
            mainScreen.setMinWidth(MINIMUM_WINDOW_WIDTH);
            mainScreen.setMinHeight(MINIMUM_WINDOW_HEIGHT);

//            primaryStage.setScene(new Scene(root, 800, 450));
        gotoWelcome();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public User getLoggedUser() {return loggedUser;}

    public boolean userLogging(String userId, String password){
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoApp();
            return true;
        } else {
            return false;
        }
    }
    void userLogout(){
        loggedUser = null;
        gotoLogin();
    }
    private void gotoWelcome() {
        try {
            WelcomeController welcome = (WelcomeController) replaceSceneContent("../view/welcome.fxml");
            welcome.setApp(this);

        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("login.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void gotoApp() {
        try {
            AppViewController appView = (AppViewController) replaceSceneContent("appview.fxml");
            appView.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page, 800, 450);
        mainScreen.setScene(scene);
        mainScreen.sizeToScene();
        return (Initializable) loader.getController();
    }
    public static void main(String[] args) {
        launch(args);
//        Application.launch(Main.class, (java.lang.String[])null);
    }
}
