package hotciv.standard.StrategyClasses;

import hotciv.framework.Position;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;

/**
 * Created by csdev on 9/29/17.
 */
public class AlphaAttackingStrategy implements AttackingStrategy {

    @Override
    public boolean moveUnit(Position from, Position to) {
        return false;
    }

    @Override
    public int getAttackStrength() {
        return 0;
    }

    @Override
    public int getDefensiveStrength() {
        return 3;
    }
}
