package Main.Model.Mechanics;

import java.util.Random;

/**
 * Created by Andrey on 14.07.2017.
 */
public class FireMage extends Mage {

    public FireMage(String name, int upgrLVLattack, int upgrLVLbaseDef, int upgrLVLbuff, int actionPointsModifier, int healthPointsModifier) {
        super(name, upgrLVLattack, upgrLVLbaseDef, upgrLVLbuff, actionPointsModifier, healthPointsModifier);
        this.setDefElement("Fire");
        this.setStrongOpponent("Water");
        this.setWeakOpponent("Wind");
    }

    public void attack(Mage target) {

        int random = new Random().nextInt(19)+1;
        int damage = this.dealDamage(target, random);

        //debuff:
        if (!target.isEnergyDefActivated() & damage > 0) {
            target.setHealth(target.getHealth()- (int)Math.pow(2, this.getUpgrLVLattack()-1));
            System.out.println(this.getName()+" наносит дополнительно "+(int)Math.pow(2, this.getUpgrLVLattack()-1));
        }
        else if (damage > 0){
            switch (this.getUpgrLVLattack()-target.getUpgrLVLbaseDef()) {
                case 2 : target.setHealth(target.getHealth()-2);
                    System.out.println(this.getName()+" наносит дополнительно "+2);
                    break;
                case 1 : target.setHealth(target.getHealth()-1);
                    System.out.println(this.getName()+" наносит дополнительно "+1);
                    break;
            }
        }
        System.out.println(target.getName()+ " имеет "+target.getHealth()+" hp");
    }

    public void castBuff() {
        this.setCurrentOD(this.getCurrentOD()-2);
        this.setBuffActivated(true);
        System.out.println(this.getName()+" кастует на себя огненный бафф");
    }

    public void castBaseDef() {
        this.setCurrentOD(this.getCurrentOD()-1);
        this.setBaseDefActivated(true);
        System.out.println(this.getName()+" кастует на себя огненную защиту ");
    }

    // Turn models
    public void turnWith1od(Mage enemy) {
        this.castEnergyDef();
    }

    public void turnWith2od(Mage enemy) {
        if ((enemy.getDefElement().equals("Water") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            this.attack(enemy);
        }
        else {
            if (enemy.getDefElement().equals("Wind")) {
                this.castEnergyDef();
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
        }
    }

    public void turnWith3od(Mage enemy) {
        switch (enemy.getDefElement()) {
            case "Wind":
                this.castBaseDef();
                if (!this.isBuffActivated()) {
                    this.castBuff();
                } else {
                    this.attack(enemy);
                }
                break;
            case "Fire":
                if (!this.isBuffActivated()) {
                    this.castBuff();
                } else {
                    this.attack(enemy);
                }
                break;
            default:
                this.castEnergyDef();
                if (!this.isBuffActivated()) {
                    this.castBuff();
                } else {
                    this.attack(enemy);
                }
                break;
        }
    }

    public void turnWith4od(Mage enemy) {
        if (enemy.getHealth() <= this.getAttack() * 2){
            if (enemy.getDefElement().equals("Water")) {
                if (enemy.getHealth() <= ((int) Math.pow(2, enemy.getUpgrLVLbaseDef()) * 2)) {
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else {
                    this.castEnergyDef();
                    this.attack(enemy);
                }
            }
            else {
                if (enemy.getHealth() <= this.getAttack() - enemy.getUpgrLVLbaseDef() * 2) {
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else if (enemy.getDefElement().equals("Fire")) {
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else {
                    this.castEnergyDef();
                    this.attack(enemy);
                }
            }
        }
        else {
            if (enemy.getDefElement().equals("Wind")) {
                this.castEnergyDef();
                this.castBaseDef();
                this.attack(enemy);
            }
            else {
                this.turnWith3od(enemy);
            }
        }
    }

    public void turnWith5od(Mage enemy) {

    }

    public void turnWith6od(Mage enemy) {

    }

    public void turnWith7od(Mage enemy) {

    }

    public void turnWith8od(Mage enemy) {

    }

    public void turnWith9od(Mage enemy) {

    }

    public void turnWith10od(Mage enemy) {

    }
}
