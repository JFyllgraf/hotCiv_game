package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/11/17.
 */
public class SemiGameFactory implements GameFactory {
    @Override
    public AgeingStrategy ageingStrategy() {
        return new BetaAgeingStrategy();
    }

    @Override
    public AttackingStrategy attackingStrategy() {
        return new EpsilonAttackingStrategy(new FixedDieDecisionStrategy(6));
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new EpsilonWinnerStrategy();
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return new GammaUnitActionStrategy();
    }
}
