package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class EpsilonGameFactoryFixedDice implements GameFactory {
    @Override
    public AgeingStrategy ageingStrategy() {
        return new AlphaAgeingStrategy();
    }

    @Override
    public AttackingStrategy attackingStrategy() {
        return new EpsilonAttackingStrategy(new FixedDieDecisionStrategy(6));
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return new AlphaUnitActionStrategy();
    }
}
