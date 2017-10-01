package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;

/**
 * Created by csdev on 9/29/17.
 */
public class AlphaAttackingStrategy implements AttackingStrategy {


    @Override
    public String attackUnit(Game game, Position from, Position to) {
        return "Attacker";
    }

}
