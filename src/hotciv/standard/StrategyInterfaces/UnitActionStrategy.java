package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Position;
import hotciv.standard.GameImpl;

/**
 * Created by csdev on 9/18/17.
 */
public interface UnitActionStrategy {

    public void performAction(GameImpl game, Position position);
}
