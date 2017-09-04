package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by csdev on 9/1/17.
 */
public class UnitImpl implements Unit {

    private String unitType;
    private Player owner;

    private int moveCount;

    public UnitImpl(String gameconstant, Player owner) {
        this.unitType = gameconstant;
        this.owner = owner;

        this.moveCount = 1;
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

    public void moveUnit() {
        this.moveCount -= 1;
    }

    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void resetMoveCount() {
        this.moveCount = 1;
    }
}
