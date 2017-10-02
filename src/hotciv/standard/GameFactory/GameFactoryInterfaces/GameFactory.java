package hotciv.standard.GameFactory.GameFactoryInterfaces;

import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public interface GameFactory {
    public AgeingStrategy ageingStrategy();
    public AttackingStrategy attackingStrategy();
    public WorldLayoutStrategy worldLayoutStrategy();
    public WinnerStrategy winnerStrategy();
    public UnitActionStrategy unitActionStrategy();
}
