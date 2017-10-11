package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class EpsilonGameFactory extends AlphaGameFactory {

    @Override
    public AttackingStrategy attackingStrategy() {
        return new EpsilonAttackingStrategy(new RandomDieDecisionStrategy());
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new EpsilonWinnerStrategy();
    }
}
