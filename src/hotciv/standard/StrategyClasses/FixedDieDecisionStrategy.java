package hotciv.standard.StrategyClasses;

import hotciv.standard.StrategyInterfaces.DieDecisionStrategy;

public class FixedDieDecisionStrategy implements DieDecisionStrategy {

    int dieValue;

    public FixedDieDecisionStrategy(int fixedValue){
        dieValue = fixedValue;
    }

    @Override
    public int rollDie() {
        return dieValue;
    }

}
