package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

/**
 * Created by csdev on 9/29/17.
 */
public interface AttackingStrategy {

    public String attackUnit(Game game, Position from, Position to);

}
