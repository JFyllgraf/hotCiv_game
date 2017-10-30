package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.Adapters.FractalMapAdapterStrategy;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;

/**
 * Created by csdev on 10/30/17.
 */
public class FractalGameFactory extends AlphaGameFactory {
    @Override
    public WorldLayoutStrategy worldLayoutStrategy() {
        return new FractalMapAdapterStrategy();
    }

}