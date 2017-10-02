package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class ZetaGameFactory implements GameFactory {
    @Override
    public AgeingStrategy ageingStrategy() {
        return new AlphaAgeingStrategy();
    }

    @Override
    public AttackingStrategy attackingStrategy() {
        return new AlphaAttackingStrategy();
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return new ZetaCivAlternatingWinnerStrategy(new BetaWinnerStrategy(), new EpsilonWinnerStrategy());
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return null;
    }
}