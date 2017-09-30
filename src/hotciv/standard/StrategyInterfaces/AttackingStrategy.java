package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Game;
import hotciv.framework.Position;

import java.util.HashMap;

/**
 * Created by csdev on 9/29/17.
 */
public interface AttackingStrategy {
    public boolean moveUnit(Position from, Position to);

    public int getAttackStrength();

    public int getDefensiveStrength();

}
