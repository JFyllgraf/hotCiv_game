package hotciv.standard;

import hotciv.framework.ExpansionGameConstants;
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

    public UnitImpl(String gameconstant, Player owner) {
        this.unitType = gameconstant;
        this.owner = owner;
        if(gameconstant== ExpansionGameConstants.GALLEY){
            this.moveCount = 2;
        }
        else {
            this.moveCount = 1;
        }
        this.defensiveStrength = 3;
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
        return defensiveStrength;
    }

    public void doubleDefensiveStrength(){
        defensiveStrength +=3;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void moveUnit() {
        this.moveCount -= 1;
    }

    public void setMoveCount() {
        if(this.moveCount>=0) {
            this.moveCount = 1;
        }
    }
    public void setGalleyMoveCount(){
        if(this.moveCount>=0){
            this.moveCount = 2;
        }
    }

    public void freezeUnit(){
        this.moveCount = -1;
    }

    public void unfreezeUnit(){
        this.moveCount = 0;
    }

}