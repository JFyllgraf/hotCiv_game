package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.AlphaAttackingStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyClasses.BetaAgeingStrategy;
import hotciv.standard.StrategyClasses.BetaWinnerStrategy;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class BetaGameFactory extends AlphaGameFactory {
    @Override
    public AgeingStrategy ageingStrategy() {
        return new BetaAgeingStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new BetaWinnerStrategy();
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return null;
    }
}
