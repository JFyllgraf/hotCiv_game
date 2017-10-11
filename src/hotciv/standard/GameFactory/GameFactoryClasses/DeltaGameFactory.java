package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class DeltaGameFactory extends AlphaGameFactory {
    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new DeltaWorldLayoutStrategy();
    }

}