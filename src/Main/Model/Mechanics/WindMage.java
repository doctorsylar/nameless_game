package Main.Model.Mechanics;

import java.util.Random;

public class WindMage extends Mage {

    public WindMage(String name, int upgrLVLattack, int upgrLVLbaseDef, int upgrLVLbuff, int actionPointsModifier, int healthPointsModifier) {
        super(name, upgrLVLattack, upgrLVLbaseDef, upgrLVLbuff, actionPointsModifier, healthPointsModifier);
        this.setDefElement("Wind");
        this.setStrongOpponent("Fire");
        this.setWeakOpponent("Earth");
    }

    @Override
    public void attack(Mage target) {

        int random = new Random().nextInt(19)+1;
        int damage = this.dealDamage(target, random);

        //debuff
        if (!target.isEnergyDefActivated() & damage > 0) {
            target.setDebuffType("Wind");
            target.setDebuffLVL(this.getUpgrLVLattack());
            System.out.println(this.getName()+" накладывает ослепление уровня "+this.getUpgrLVLattack());
        }
        else if (target.isEnergyDefActivated()) {
            if (target.getUpgrLVLbaseDef() < this.getUpgrLVLattack()) {
                target.setDebuffType("Wind");
                target.setDebuffLVL(this.getUpgrLVLattack()-target.getUpgrLVLbaseDef());
                System.out.println(this.getName()+" накладывает ослепление уровня "+ (this.getUpgrLVLattack()-target.getUpgrLVLbaseDef()));
            }
        }
        System.out.println(target.getName()+ " имеет "+target.getHealth()+" hp");
    }

    @Override
    public void castBaseDef() {
        this.setCurrentOD(this.getCurrentOD()-1);
        this.setBaseDefActivated(true);
        System.out.println(this.getName()+" кастует на себя воздушную защиту ");
    }

    @Override
    public void castBuff() {
        this.setCurrentOD(this.getCurrentOD()-2);
        this.setBuffActivated(true);
        System.out.println(this.getName()+" кастует на себя воздушный бафф");
    }

    public void turnWith1od(Mage enemy) {
        this.castEnergyDef();
    }

    public void turnWith2od(Mage enemy) {
        if ((enemy.getDefElement().equals("Fire") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            this.attack(enemy);
        }
        else {
            if (enemy.getDefElement().equals("Earth")) {
                this.castEnergyDef();
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
        }
    }

    public void turnWith3od(Mage enemy) {
        if ((enemy.getDefElement().equals("Fire") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            if (enemy.getDefElement().equals("Earth")) {
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
            this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Earth")) {
                    this.castBaseDef();
                } else {
                    this.castEnergyDef();
                }
            } else {
                if (enemy.getDefElement().equals("Earth")) {
                    castBaseDef();
                } else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
            }
        }
    }

    public void turnWith4od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Fire":
                    this.attack(enemy);
                    break;
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    break;
                default:
                    this.castEnergyDef();
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Fire")) {
                this.attack(enemy);
                this.attack(enemy);
            } else {
                if (enemy.getDefElement().equals("Earth")) {
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                }
                else {
                    if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 2) |
                            (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 2) |
                            (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 2) ) {
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                    else {
                        this.castEnergyDef();
                        this.attack(enemy);
                    }
                }
            }
        }
    }

    public void turnWith5od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Earth") & (this.getHealth() <= enemy.getAttack() & this.getHealth() > enemy.getAttack() - (int) Math.pow(2, this.getUpgrLVLbaseDef()))) {
                this.castEnergyDef();
                this.castBaseDef();
                this.attack(enemy);
            }
            else {
                this.castEnergyDef();
                this.attack(enemy);
                this.attack(enemy);
            }
        }
    }

    public void turnWith6od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Fire":
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Fire")) {
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            } else {
                if (enemy.getDefElement().equals("Earth")) {
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else {
                    if ((enemy.getDefElement().equals("Fire") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 3) |
                            (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 3) |
                            (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 3) ) {
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                    else {
                        this.castEnergyDef();
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                }
            }
        }
    }

    public void turnWith7od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Earth") & (this.getHealth() <= enemy.getAttack() & this.getHealth() > enemy.getAttack() - (int) Math.pow(2, this.getUpgrLVLbaseDef()))) {
                this.castEnergyDef();
                this.castBaseDef();
                this.attack(enemy);
                this.attack(enemy);
            }
            else {
                this.castEnergyDef();
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            }
        }
    }

    public void turnWith8od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Fire":
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Fire")) {
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            } else {
                if (enemy.getDefElement().equals("Earth")) {
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else {
                    if ((enemy.getDefElement().equals("Fire") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 4) |
                            (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 4) |
                            (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 4) ) {
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                    else {
                        this.castEnergyDef();
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                }
            }
        }
    }

    public void turnWith9od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Earth") & (this.getHealth() <= enemy.getAttack() & this.getHealth() > enemy.getAttack() - (int) Math.pow(2, this.getUpgrLVLbaseDef()))) {
                this.castEnergyDef();
                this.castBaseDef();
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            }
            else {
                this.castEnergyDef();
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            }
        }
    }

    public void turnWith10od(Mage enemy) {
        if (!this.isBuffActivated()) {
            this.castBuff();
            switch (enemy.getDefElement()) {
                case "Fire":
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                case "Earth":
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
                default:
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    break;
            }
        }
        else {
            if (enemy.getDefElement().equals("Fire")) {
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
            } else {
                if (enemy.getDefElement().equals("Earth")) {
                    this.castBaseDef();
                    this.castEnergyDef();
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                    this.attack(enemy);
                }
                else {
                    if ((enemy.getDefElement().equals("Fire") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 5) |
                            (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 5) |
                            (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 5) ) {
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                    else {
                        this.castEnergyDef();
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                        this.attack(enemy);
                    }
                }
            }
        }
    }
}
