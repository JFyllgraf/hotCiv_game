package hotciv.standard.GameFactory.GameFactoryClasses;

import hotciv.standard.StrategyClasses.BetaAgeingStrategy;
import hotciv.standard.StrategyClasses.ThetaAttackingStrategy;
import hotciv.standard.StrategyInterfaces.AgeingStrategy;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;

/**
 * Created by csdev on 10/12/17.
 */
public class ThetaGameFactory extends DeltaGameFactory {

    //This one is implemented to give the correct attacking and defence from the galley.
    @Override
    public AttackingStrategy attackingStrategy() {
        return new ThetaAttackingStrategy();
    }



}
