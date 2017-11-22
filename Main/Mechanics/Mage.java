package Main.Mechanics;

/**
 * Created by Andrey on 14.07.2017.
 */
public abstract class Mage implements FeelEffects, ActsInFight, TurnModels {
    private String name;
    private int level;
    private int health;
    private int attack;
    private int baseOD = 3;
    private int currentOD;
    private int upgrLVLattack;
    private int upgrLVLbaseDef;
    private int upgrLVLbuff;
    private int actionPointsModifier = 0;
    private int healthPointsModifier = 0;
    private int criticalChance = 2;
    private int missChance = 2;
    private int storedOd;
    private int turnPriority;
    private boolean baseDefActivated = false;
    private boolean buffActivated = false;
    private boolean energyDefActivated = false;
    private String defElement;
    private int debuffLVL;
    private String debuffType = "None";
    private String strongOpponent;
    private String weakOpponent;

    public Mage(String name, int upgrLVLattack, int upgrLVLbaseDef, int upgrLVLbuff, int actionPointsModifier, int healthPointsModifier) {
        this.name = name;
        this.upgrLVLattack = upgrLVLattack;
        this.upgrLVLbaseDef = upgrLVLbaseDef;
        this.upgrLVLbuff = upgrLVLbuff;

        this.level = upgrLVLattack + upgrLVLbaseDef + upgrLVLbuff;

        if (level < 4) {
            this.health = 10;
        }
        else if (level < 8) {
            this.health = 15;
        }
        else
            this.health = 20;

        this.health += 5 * healthPointsModifier;

        this.baseOD += actionPointsModifier;

        switch (this.getUpgrLVLattack()){
            case 1 : this.attack = 3;
            break;
            case 2 : this.attack = 6;
            break;
            case 3 : this.attack = 10;
            break;
        }
    }

    public int getCurrentOD() {
        return currentOD;
    }

    public void setCurrentOD(int currentOD) {
        this.currentOD = currentOD;
    }

    public int getActionPointsModifier() {
        return actionPointsModifier;
    }

    public void setActionPointsModifier(int actionPointsModifier) {
        this.actionPointsModifier = actionPointsModifier;
    }

    public int getHealthPointsModifier() {
        return healthPointsModifier;
    }

    public void setHealthPointsModifier(int healthPointsModifier) {
        this.healthPointsModifier = healthPointsModifier;
    }

    public int getTurnPriority() {
        return turnPriority;
    }

    public void setTurnPriority(int turnPriority) {
        this.turnPriority = turnPriority;
    }

    public int getStoredOd() {
        return storedOd;
    }

    public void setStoredOd(int storedOd) {
        this.storedOd = storedOd;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attack;
    }

    public int getBaseOD() {
        return baseOD;
    }

    public int getUpgrLVLattack() {
        return upgrLVLattack;
    }

    public int getUpgrLVLbaseDef() {
        return upgrLVLbaseDef;
    }

    public int getUpgrLVLbuff() {
        return upgrLVLbuff;
    }

    public boolean isBaseDefActivated() {
        return baseDefActivated;
    }

    public boolean isBuffActivated() {
        return buffActivated;
    }

    public boolean isEnergyDefActivated() {
        return energyDefActivated;
    }

    public int getDebuffLVL() {
        return debuffLVL;
    }

    public String  getDebuffType() {
        return debuffType;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setBaseOD(int baseOD) {
        this.baseOD = baseOD;
    }

    public void setUpgrLVLattack(int upgrLVLattack) {
        this.upgrLVLattack = upgrLVLattack;
    }

    public void setUpgrLVLbaseDef(int upgrLVLbaseDef) {
        this.upgrLVLbaseDef = upgrLVLbaseDef;
    }

    public void setUpgrLVLbuff(int upgrLVLbuff) {
        this.upgrLVLbuff = upgrLVLbuff;
    }

    public void setBaseDefActivated(boolean baseDefActivated) {
        this.baseDefActivated = baseDefActivated;
    }

    public void setBuffActivated(boolean buffActivated) {
        this.buffActivated = buffActivated;
    }

    public void setEnergyDefActivated(boolean energyDefActivated) {
        this.energyDefActivated = energyDefActivated;
    }

    public void setDebuffLVL(int debuffLVL) {
        this.debuffLVL = debuffLVL;
    }

    public void setDebuffType(String  debuffType) {
        this.debuffType = debuffType;
    }

    public String getDefElement() {
        return defElement;
    }

    public void setDefElement(String defElement) {
        this.defElement = defElement;
    }

    public int getCriticalChance() {
        return criticalChance;
    }

    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }

    public int getMissChance() {
        return missChance;
    }

    public void setMissChance(int missChance) {
        this.missChance = missChance;
    }

    public String getStrongOpponent() {
        return strongOpponent;
    }

    public void setStrongOpponent(String strongOpponent) {
        this.strongOpponent = strongOpponent;
    }

    public String getWeakOpponent() {
        return weakOpponent;
    }

    public void setWeakOpponent(String weakOpponent) {
        this.weakOpponent = weakOpponent;
    }

    public void feelEffects() {
        String debuffType = this.getDebuffType();
        switch (debuffType) {
            case "Water" : this.setCurrentOD(this.getCurrentOD()-this.getDebuffLVL());
            break;
            case "Wind" : switch (this.getDebuffLVL()) {
                case 1 : this.setMissChance(4);
                break;
                case 2 : this.setMissChance(7);
                break;
                case 3 : this.setMissChance(11);
                break;
            }
            break;
            case "Earth" : break; //ДОПИСАТЬ!!!!!!!!!!!!!!!!!!!!
            default :  this.setMissChance(2);
            break;
        }


        if (this.isBuffActivated()) {
            switch (this.getDefElement()) {
                case "Wind":
                    this.setCurrentOD(this.getCurrentOD() + this.getUpgrLVLbuff());
                    break;
                case "Fire":
                    switch (this.getUpgrLVLbuff()) {
                        case 1 : this.setCriticalChance(4);
                        break;
                        case 2 : this.setCriticalChance(7);
                        break;
                        case 3 : this.setCriticalChance(10);
                        break;
                        default : this.setCriticalChance(2);
                    }
            }
        }
    }
    public void castEnergyDef() {
        this.setCurrentOD(this.getCurrentOD()-1);
        this.setEnergyDefActivated(true);
        System.out.println(this.getName()+" кастует на себя энергетическую защиту ");
    }

    @Override
    public void sharedPattern(Mage mage, Mage enemy) {
        switch (mage.getCurrentOD()) {
            case 1 : mage.turnWith1odShared(enemy);
            break;
            case 2 : mage.turnWith2odShared(enemy);
            break;
            case 3 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 4 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 5 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 6 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 7 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 8 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 9 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 10 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 11 : mage.turnWith3AndMoreOdShared(enemy);
            break;
            case 12 : mage.turnWith3AndMoreOdShared(enemy);
            break;
        }
    }

    @Override
    public void turnWith1odShared(Mage enemy) {
        this.castEnergyDef();
    }

    @Override
    public void turnWith2odShared(Mage enemy) {
        if ((enemy.getDefElement().equals(this.getStrongOpponent()) & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef()))) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef())) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= this.getAttack()) ){
            this.attack(enemy);
        }
        else {
            if (enemy.getDefElement().equals(this.getWeakOpponent())) {
                this.castEnergyDef();
                this.castBaseDef();
            }
            else {
                this.castEnergyDef();
            }
        }
    }

    @Override
    public void turnWith3AndMoreOdShared(Mage enemy) {
        if ((enemy.getDefElement().equals(this.getStrongOpponent()) & enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * (this.getCurrentOD()/2)) |
                (enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack() - enemy.getUpgrLVLbaseDef()) * (this.getCurrentOD()/2)) |
                (!enemy.isBaseDefActivated() & enemy.getHealth() <= (this.getAttack()) * (this.getCurrentOD() / 2))){
            while (this.getCurrentOD() >= 2) {
                this.attack(enemy);
            }
            if (this.getCurrentOD() == 1 & enemy.getDefElement().equals(this.getWeakOpponent()) & this.getHealth() <= enemy.getAttack() * (enemy.getBaseOD() / 2) &
                    ((this.getHealth() > (enemy.getAttack() - Math.pow(2, this.getUpgrLVLbaseDef())) * (enemy.getBaseOD() / 2)) & enemy.getDefElement().equals(this.getWeakOpponent()) |
                    (this.getHealth() > (enemy.getAttack() - this.getUpgrLVLbaseDef()) * (enemy.getBaseOD() / 2)) & !enemy.getDefElement().equals(this.getWeakOpponent()))) {
                this.castBaseDef();
            }
            else if (this.getCurrentOD() == 1) {
                this.castEnergyDef();
            }
        }
        else {
            if ((this.getDefElement().equals(enemy.getStrongOpponent()) & this.getHealth() <= (enemy.getAttack() - Math.pow(2, enemy.getUpgrLVLbaseDef())) * ((enemy.getBaseOD()+enemy.getStoredOd())/2)) |
                    ((!this.getDefElement().equals(enemy.getStrongOpponent()) & this.getHealth() <= enemy.getAttack() * (enemy.getCurrentOD() / 2)))) {
                if (enemy.getDefElement().equals(this.getStrongOpponent()) & this.getCurrentOD() % 2 == 1) {
                    this.castBaseDef();
                }
                else if (this.getCurrentOD() % 2 == 1) {
                    this.castEnergyDef();
                }
                while (this.getCurrentOD() >= 2) {
                    this.attack(enemy);
                }
            }
            else if (!this.isBuffActivated()) {
                this.castBuff();
                if (this.getCurrentOD() >= 1) {
                    this.castEnergyDef();
                    if (this.getCurrentOD() == 1 & enemy.getDefElement().equals(this.getWeakOpponent()) & this.getHealth() <= enemy.getAttack() * (enemy.getBaseOD() / 2) &
                            ((this.getHealth() > (enemy.getAttack() - Math.pow(2, this.getUpgrLVLbaseDef())) * (enemy.getBaseOD() / 2)) & enemy.getDefElement().equals(this.getWeakOpponent()) |
                                    (this.getHealth() > (enemy.getAttack() - this.getUpgrLVLbaseDef()) * (enemy.getBaseOD() / 2)) & !enemy.getDefElement().equals(this.getWeakOpponent()))) {
                        this.castBaseDef();
                    }
                    if (!this.isBaseDefActivated() & this.getCurrentOD() >= 1 & enemy.getDefElement().equals(this.getWeakOpponent())) {
                        this.castBaseDef();
                        while (this.currentOD >= 2) {
                            this.attack(enemy);
                        }
                    }
                    else {
                        while (this.currentOD >= 2) {
                            this.attack(enemy);
                        }
                    }
                }
            }
            else {
                if (this.getCurrentOD() >= 1) {
                    this.castEnergyDef();
                    if (this.getCurrentOD() == 1 & enemy.getDefElement().equals(this.getWeakOpponent()) & this.getHealth() <= enemy.getAttack() * (enemy.getBaseOD() / 2) &
                            ((this.getHealth() > (enemy.getAttack() - Math.pow(2, this.getUpgrLVLbaseDef())) * (enemy.getBaseOD() / 2)) & enemy.getDefElement().equals(this.getWeakOpponent()) |
                                    (this.getHealth() > (enemy.getAttack() - this.getUpgrLVLbaseDef()) * (enemy.getBaseOD() / 2)) & !enemy.getDefElement().equals(this.getWeakOpponent()))) {
                        this.castBaseDef();
                    }
                    if (!this.isBaseDefActivated() & this.getCurrentOD() >= 1 & enemy.getDefElement().equals(this.getWeakOpponent())) {
                        this.castBaseDef();
                        while (this.currentOD >= 2) {
                            this.attack(enemy);
                        }
                    }
                    else {
                        while (this.currentOD >= 2) {
                            this.attack(enemy);
                        }
                    }
                }
            }
        }
    }


    @Override
    public int dealDamage(Mage target, int random) {
        this.setCurrentOD(this.getCurrentOD()-2);
        int damage;
        System.out.println(this.getName()+" атакует и выбрасывает кубик "+random);
        if (random > 20 - this.getCriticalChance()) {
            damage = 2;
        }
        else if (random <= this.getMissChance()) {
            damage = 0;
        }
        else {
            damage = 1;
        }

        if (target.getDefElement().equals(this.getStrongOpponent()) & target.isBaseDefActivated()) {
            damage = damage * this.getAttack() - (int) Math.pow(2, target.getUpgrLVLbaseDef());
            if (damage > 0) {
                target.setHealth(target.getHealth() - damage);
                System.out.println(this.getName()+" наносит "+damage);
            }
        }
        else {
            if (target.isBaseDefActivated() & damage > 0) {
                damage = damage * this.getAttack() - target.getUpgrLVLbaseDef();
                if (damage > 0) {
                    target.setHealth(target.getHealth() - damage);
                    System.out.println(this.getName() + " наносит " + damage);
                }
            }
            else {
                damage = damage * this.getAttack();
                if (damage > 0) {
                    target.setHealth(target.getHealth() - damage);
                    System.out.println(this.getName() + " наносит " + damage);
                }
            }
        }
        if (target.getDefElement().equals("Earth") & target.isBuffActivated()) {
            this.setHealth(this.getHealth() - (int) Math.pow(2, target.getUpgrLVLbuff()-1));
            System.out.println(this.getName() + " получил " + (int) Math.pow(2, target.getUpgrLVLbuff()-1) + " урона от атаки в возвратку");
            System.out.println(this.getName() + " имеет " + this.getHealth() + " ХП");
        }
        return damage;
    }

    @Override
    public void attack(Mage target) {
    }

    @Override
    public void castBaseDef() {
    }

    @Override
    public void castBuff() {
    }

}

interface MageAttacks {
    void attack(Mage target);
    int dealDamage(Mage target, int random);
}

interface CastBaseDef {
    void castBaseDef();
}

interface CastEnergyDef {
    void castEnergyDef();
}

interface MageBuffs {
    void castBuff();
}

interface ActsInFight extends MageAttacks, CastBaseDef, CastEnergyDef, MageBuffs {
}

interface TurnModels {
    void sharedPattern(Mage mage, Mage enemy);

    void turnWith1odShared(Mage enemy);
    void turnWith2odShared(Mage enemy);
    void turnWith3AndMoreOdShared(Mage enemy);


    //void turnWith1od(Mage enemy);
    //void turnWith2od(Mage enemy);
    //void turnWith3od(Mage enemy);
    //void turnWith4od(Mage enemy);
    //void turnWith5od(Mage enemy);
    //void turnWith6od(Mage enemy);
    //void turnWith7od(Mage enemy);
    //void turnWith8od(Mage enemy);
    //void turnWith9od(Mage enemy);
    //void turnWith10od(Mage enemy);
}

interface FeelEffects {
    void feelEffects();
}