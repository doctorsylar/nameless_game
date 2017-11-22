package Main.Mechanics;

import java.util.Random;

public class EarthMage extends Mage {
    public EarthMage(String name, int upgrLVLattack, int upgrLVLbaseDef, int upgrLVLbuff, int actionPointsModifier, int healthPointsModifier) {
        super(name, upgrLVLattack, upgrLVLbaseDef, upgrLVLbuff, actionPointsModifier, healthPointsModifier);
        this.setDefElement("Earth");
        this.setStrongOpponent("Wind");
        this.setWeakOpponent("Water");
    }
    public void attack(Mage target) {

        int random = new Random().nextInt(19)+1;
        int damage = this.dealDamage(target, random);

        //debuff:
        if (!target.isEnergyDefActivated() & damage > 0) {
            if (random > 20 - (this.getUpgrLVLattack())) {
                target.setDebuffType("Earth");
            }
        }
        else if (target.isEnergyDefActivated() & damage > 0 ) {
            if (random > 20 - (this.getUpgrLVLattack() - target.getUpgrLVLbaseDef())) {
                target.setDebuffType("Earth");
            }
        }
        System.out.println(target.getName()+ " имеет "+target.getHealth()+" hp");
    }

    public void castBuff() {
        this.setCurrentOD(this.getCurrentOD()-2);
        this.setBuffActivated(true);
        System.out.println(this.getName()+" кастует на себя земляной бафф");
    }

    public void castBaseDef() {
        this.setCurrentOD(this.getCurrentOD()-1);
        this.setBaseDefActivated(true);
        System.out.println(this.getName()+" кастует на себя земляную защиту ");
    }
}
