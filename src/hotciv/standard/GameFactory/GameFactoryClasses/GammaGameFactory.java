package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class GammaGameFactory implements GameFactory {
    @Override
    public AgeingStrategy ageingStrategy() {
        return null;
    }

    @Override
    public AttackingStrategy attackingStrategy() {
        return null;
    }

    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new AlphaWorldLayoutStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return null;
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return new GammaUnitActionStrategy();
    }
}