package Main.VC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
    static Stage mainStage;     //Static fields for future scene changes
    static Parent root;     //Static fields for future scene changes

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainStage = primaryStage;
        primaryStage.setTitle("Nameless game");
        root = FXMLLoader.load(getClass().getResource("CharacterCreationScreen.fxml"));

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
