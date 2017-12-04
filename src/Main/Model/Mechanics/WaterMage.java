package Main.Model.Mechanics;

import java.util.Random;

public class WaterMage extends Mage {


    public WaterMage(String name, int upgrLVLattack, int upgrLVLbaseDef, int upgrLVLbuff, int actionPointsModifier, int healthPointsModifier) {
        super(name, upgrLVLattack, upgrLVLbaseDef, upgrLVLbuff, actionPointsModifier, healthPointsModifier);
        this.setDefElement("Water");
        this.setStrongOpponent("Earth");
        this.setWeakOpponent("Fire");
    }

    public void attack(Mage target) {

        int random = new Random().nextInt(19)+1;
        int damage = this.dealDamage(target, random);

        //debuff
        if (!target.isEnergyDefActivated() & damage > 0) {
            target.setDebuffType("Water");
            target.setDebuffLVL(this.getUpgrLVLattack());
            System.out.println(this.getName()+" накладывает замедление уровня "+this.getUpgrLVLattack());
        }
        else if (target.isEnergyDefActivated()) {
            if (target.getUpgrLVLbaseDef() < this.getUpgrLVLattack()) {
                target.setDebuffType("Water");
                target.setDebuffLVL(this.getUpgrLVLattack()-target.getUpgrLVLbaseDef());
                System.out.println(this.getName()+" накладывает замедление уровня "+ (this.getUpgrLVLattack()-target.getUpgrLVLbaseDef()));
            }
        }
        if (this.isBuffActivated() & damage > 0) {
            this.regenHpFromDamage();
            System.out.println(this.getName()+" восстановил своё ХП до "+this.getHealth());
        }
        System.out.println(target.getName()+ " имеет "+target.getHealth()+" hp");
    }

    public void castBaseDef() {
        this.setCurrentOD(this.getCurrentOD()-1);
        this.setBaseDefActivated(true);
        System.out.println(this.getName()+" кастует на себя водяную защиту ");
    }


    public void castBuff() {
        this.setCurrentOD(this.getCurrentOD()-2);
        this.setBuffActivated(true);
        System.out.println(this.getName()+" кастует на себя водяной бафф");
    }

    private void regenHpFromDamage() {
        switch (this.getUpgrLVLbuff()) {
            case 1 : this.setHealth(this.getHealth()+1);
            break;
            case 2 : this.setHealth(this.getHealth()+2);
            break;
            case 3 : this.setHealth(this.getHealth()+4);
            break;
        }
    }

    public void turnWith1od(Mage enemy) {
        this.castEnergyDef();
    }

    public void turnWith2od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            this.attack(enemy);
        }
        else {
            if (enemy.getDefElement().equals("Fire")) {
                this.castEnergyDef();
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
        }
    }

    public void turnWith3od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            this.attack(enemy);
            if (enemy.getDefElement().equals("Fire")) {
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                } else {
                    this.castEnergyDef();
                }
            } else {
                if (enemy.getDefElement().equals("Fire")) {
                    castBaseDef();
                } else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
            }
        }
    }

    public void turnWith4od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 2) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 2) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 2) ){
                this.attack(enemy);
                this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
            else {
                this.attack(enemy);
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
        }
    }

    public void turnWith5od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 2) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 2) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 2) ){
            if (enemy.getDefElement().equals("Fire")) {
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
            this.attack(enemy);
            this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
            }
            else {
                this.attack(enemy);
                this.attack(enemy);
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
        }
    }

    public void turnWith6od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 3) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 3) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 3) ){
            this.attack(enemy);
            this.attack(enemy);
            this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
            }
            else {
                this.attack(enemy);
                this.attack(enemy);
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
        }
    }

    public void turnWith7od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 3) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 3) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 3) ){
            if (enemy.getDefElement().equals("Fire")) {
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
            this.attack(enemy);
            this.attack(enemy);
            this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
                this.attack(enemy);
            }
            else {
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
        }
    }

    public void turnWith8od(Mage enemy) {
        if ((enemy.getDefElement().equals("Earth") & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * 4) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * 4) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack() * 4) ){
            this.attack(enemy);
            this.attack(enemy);
            this.attack(enemy);
            this.attack(enemy);
        }
        else {
            if (!this.isBuffActivated()) {
                this.castBuff();
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
                this.attack(enemy);
                this.attack(enemy);
            }
            else {
                this.attack(enemy);
                this.attack(enemy);
                this.attack(enemy);
                if (enemy.getDefElement().equals("Fire")) {
                    this.castBaseDef();
                }
                else {
                    this.castEnergyDef();
                }
            }
        }
    }

    public void turnWith9od(Mage enemy) {
        System.out.println("9 od НЕ НАПИСАЛ");
    }

    public void turnWith10od(Mage enemy) {
        System.out.println("10 od НЕ НАПИСАЛ");
    }
}
