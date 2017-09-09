package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Unit;

/**
 * Created by csdev on 9/9/17.
 */
public class UnitImpl implements Unit {

    private final String unitType;
    private final Player owner;
    private int movecount;

    public UnitImpl(String gameconstant, Player owner) {
        this.unitType = gameconstant;
        this.owner = owner;
        this.movecount = 1;
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
        return movecount;
    }


    @Override
    public int getDefensiveStrength() {
        return 0;
    }

    @Override
    public int getAttackingStrength() {
        return 0;
    }

    public void moveUnit() {
        this.movecount -= 1;
    }


    public void resetMoveCount() {
        this.movecount = 1;
    }
}