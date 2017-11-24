package Main.VC;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import static Main.VC.View.mainStage;    //Stage and root to change scene
import static Main.VC.View.root;    //Stage and root to change scene

public class MainController {
    private Model model = Model.getModelInstance(); //Instance of model with static constructor

    @FXML
    Button fireMageChoser, waterMageChoser, earthMageChoser, windMageChoser, attackMinusButton, attackPlusButton;
    @FXML
    Button deffenceMinusButton, deffencePlusButton, buffMinusButton, buffPlusButton, healthMinusButton, healthPlusButton;
    @FXML
    Button actionPointsMinusButton, actionPointsPlusButton, endButton;
    @FXML
    Label wasted, level, classLabel, attack, defence, buff, health, od;

    public MainController() {
        for (String key : this.getModel().description.keySet()) {
            this.getModel().description.put(key,this.getModel().description.get(key));
        }
    }

    //Results of pressing buttons
    @FXML
    public void fireMageChoice() {
        model.description.put("classLabel", "FireMage");
        reWriteResults();
    }
    @FXML
    public void waterMageChoice() {
        model.description.put("classLabel", "WaterMage");
        reWriteResults();
    }
    @FXML
    public void earthMageChoice() {
        model.description.put("classLabel", "EarthMage");
        reWriteResults();
    }
    @FXML
    public void windMageChoice() {
        model.description.put("classLabel", "WindMage");
        reWriteResults();
    }
    @FXML
    void plusAttack() {
        model.plusStat("attack");
        reWriteResults();
    }
    @FXML
    void minusAttack() {
        model.minusStat("attack");
        reWriteResults();
    }
    @FXML
    void plusDefence() {
        model.plusStat("defence");
        reWriteResults();
    }
    @FXML
    void minusDefence() {
        model.minusStat("defence");
        reWriteResults();
    }
    @FXML
    void plusBuff() {
        model.plusStat("buff");
        reWriteResults();
    }
    @FXML
    void minusBuff() {
        model.minusStat("buff");
        reWriteResults();
    }
    @FXML
    void plusHealth() {
        model.plusStat("health");
        reWriteResults();
    }
    @FXML
    void minusHealth() {
        model.minusStat("health");
        reWriteResults();
    }
    @FXML
    void plusActionPoints() {
        model.plusStat("od");
        reWriteResults();
    }
    @FXML
    void minusActionPoints() {
        model.minusStat("od");
        reWriteResults();
    }


    // ДОПИСАТЬ ЗАВЕРШЕНИЕ НАСТРОЕК
    @FXML
    public void finishSetup() throws Exception {
        root = FXMLLoader.load(getClass().getResource("fightScreen.fxml"));
        mainStage.setScene(new Scene(root, 600, 400));
        mainStage.show();
    }







    // Rewriting information on the screen
    private void reWriteResults() {
        wasted.setText(model.description.get("wasted"));
        level.setText(model.description.get("level"));
        classLabel.setText(model.description.get("classLabel"));
        attack.setText(model.description.get("attack"));
        defence.setText(model.description.get("defence"));
        buff.setText(model.description.get("buff"));
        health.setText(model.description.get("health"));
        od.setText(model.description.get("od"));
    }





    public Model getModel() {
        return model;
    }
}
