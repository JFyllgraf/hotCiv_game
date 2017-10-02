package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class DeltaGameFactory implements GameFactory {
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
        return new DeltaWorldLayoutStrategy();
    }

    @Override
    public WinnerStrategy winnerStrategy() {
        return null;
    }

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return null;
    }
}