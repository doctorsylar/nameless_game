package Main.Model.Mechanics;

import java.util.Scanner;

/**
 * Created by Andrey on 14.07.2017.
 */
public class Fight {

    private Mage fighter1;
    private Mage fighter2;

    //public static void main(String[] args) {
    //    fightExample();
    //}

    public static void fightExample() {
        WaterMage mage1 = new WaterMage("WaterMage", 2, 2, 2, 0, 0);
        WindMage mage2 = new WindMage("WindMage", 2, 2, 2, 0, 0);
        FireMage mage3 = new FireMage("FireMage", 2, 2, 2, 0, 0);
        EarthMage mage4 = new EarthMage("EarthMage", 2, 2, 2, 0, 0);
        System.out.println(mage1.getName()+" имеет "+mage1.getLevel()+" уровень!");
        System.out.println(mage2.getName()+" имеет "+mage2.getLevel()+" уровень!");
        System.out.println(mage3.getName()+" имеет "+mage3.getLevel()+" уровень!");
        System.out.println(mage4.getName()+" имеет "+mage4.getLevel()+" уровень!");
        System.out.println();

        Fight fight = new Fight(mage1, mage4);
        fight.fightProcess();
        Scanner scanner = new Scanner(System.in);
        //String exit = scanner.next();
    }

    Fight (Mage mage1, Mage mage2) {
        System.out.println("На арену выходят два чемпиона: "+mage1.getName()+" против "+mage2.getName());
        this.fighter1 = mage1;
        this.fighter2 = mage2;
        fighter1.setTurnPriority(1);
        fighter2.setTurnPriority(0);

    }

    private void fightProcess() {
        while (this.fighter1.getHealth() > 0 & this.fighter2.getHealth() > 0) {
            this.makeTurnByPriority(this.fighter1, this.fighter2);
        }
    }

    private void makeTurnByPriority(Mage mage1, Mage mage2) {
        if (mage1.getTurnPriority() == 1) {
            this.turn(mage1, mage2);
        }
        else
            this.turn(mage2, mage1);
    }

    private void turn (Mage mage, Mage target){
        turnBeginsWith(mage);

        if (!mage.getDebuffType().equals("Earth")) {
            mage.sharedPattern(mage, target);
        }

        turnEndsWith(mage, target);
    }

    private void turnBeginsWith(Mage mage) {
        mage.setCurrentOD(mage.getBaseOD()+mage.getStoredOd());
        System.out.println(mage.getCurrentOD());
        mage.setBaseDefActivated(false);
        mage.setEnergyDefActivated(false);
        mage.feelEffects();
        System.out.println(mage.getCurrentOD());
    }
    private void turnEndsWith(Mage mage, Mage target) {
        mage.setDebuffType("None");
        mage.setStoredOd(mage.getCurrentOD());
        mage.setTurnPriority(0);
        target.setTurnPriority(1);

    }
}
