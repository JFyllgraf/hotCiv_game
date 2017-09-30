package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;
import hotciv.standard.StrategyClasses.AlphaAttackingStrategy;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;

/**
 * Created by csdev on 9/9/17.
 */
public class UnitImpl implements Unit {

    private final String unitType;
    private final Player owner;
    private int moveCount;
    private int defensiveStrength;
    private AttackingStrategy attackingStrategy;
    public UnitImpl(String gameconstant, Player owner) {
        this.unitType = gameconstant;
        this.owner = owner;
        this.moveCount = 1;
        this.defensiveStrength = 3;
        this.attackingStrategy = new AlphaAttackingStrategy();
    }

    @Override
    public String getTypeString() {
        return unitType;
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public int getMoveCount() {
        return moveCount;
    }


    @Override
    public int getDefensiveStrength() {
        return attackingStrategy.getDefensiveStrength();
    }

    public void doubleDefensiveStrength(){
        defensiveStrength +=3;
    }

    @Override
    public int getAttackingStrength() {
        return attackingStrategy.getAttackStrength();
    }

    public void moveUnit() {
        this.moveCount -= 1;
    }


    public void setMoveCount() {
        if(this.moveCount>=0) {
            this.moveCount = 1;
        }
    }

    public void freezeUnit(){
        this.moveCount = -1;
    }

    public void unfreezeUnit(){
        this.moveCount = 0;
    }

}