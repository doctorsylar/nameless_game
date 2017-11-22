package Main.VC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
    static Stage mainStage;
    static Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.setTitle("Nameless game");
        root = FXMLLoader.load(getClass().getResource("CharacterCreationScreen.fxml"));
        //checking for updates
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
