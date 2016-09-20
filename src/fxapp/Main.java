package fxapp;

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
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application {
    /** the main container for the application window */
    private Stage mainScreen;
    private User loggedUser;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScreen = primaryStage;
//        Parent root = FXMLLoader.load(getClass().getResource("welcome.fxml"));
        primaryStage.setTitle("Clean Water Map");
//        primaryStage.setScene(new Scene(root, 800, 450));
        gotoWelcome();
        primaryStage.show();

    }
    public User getLoggedUser() {return loggedUser;}

    private void gotoWelcome() throws Exception {
        WelcomeController welcome = (WelcomeController) replaceSceneContent("welcome.fxml");
    }

    public boolean userLogging(String userId, String password){
        if (Authenticator.validate(userId, password)) {
            loggedUser = User.of(userId);
            gotoProfile();
            return true;
        } else {
            return false;
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
    }
}
