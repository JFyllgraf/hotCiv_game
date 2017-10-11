package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;

/**
 * Created by csdev on 10/2/17.
 */
public class GammaGameFactory extends AlphaGameFactory {

    @Override
    public UnitActionStrategy unitActionStrategy() {
        return new GammaUnitActionStrategy();
    }
}