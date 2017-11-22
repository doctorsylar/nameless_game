package Main.VC;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FightController {
    protected MainController mainController;
    protected Model model = Model.getModelInstance();

    @FXML
    ImageView playerAvatar, enemyAvatar;
    @FXML
    Label characterNameLabel, characterHealthLabel, characterOdLabel, characterDefenceLabel;


    void fillAvatars () {
        playerAvatar.setImage(new Image(model.description.get(("classLabel").concat(".jpg"))));
        //playerAvatar.setImage(new Image("\\FireMage.jpg"));
    }

    public void fillInformation() {
        //characterNameLabel.setText(model.description.get("classLabel"));
        characterNameLabel.setText("12345");
    }
}
