package Main.Control;

import Main.Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FightController {
    private Model model = Model.getModelInstance(); // Instance of model
    private FightController fightController;
    @FXML
    ImageView playerAvatar, enemyAvatar;
    @FXML
    Label characterNameLabel, characterHealthLabel, characterOdLabel, characterDefenceLabel;
    @FXML
    Button startFightButton;

    @FXML
    void startFight() {
        fightController = new FightController();
        fightController.fillInformation();
    }




    void fillAvatars () {
        playerAvatar.setImage(new Image(model.description.get(("classLabel").concat(".jpg"))));
    }

    @FXML
    public void fillInformation() {
        //characterNameLabel.setText(model.description.get("classLabel"));
        characterNameLabel.setText("12345");
    }
}
