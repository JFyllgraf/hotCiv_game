package hotciv.standard.StrategyClasses;

import hotciv.standard.StrategyInterfaces.DieDecisionStrategy;

public class RandomDieDecisionStrategy implements DieDecisionStrategy {

    @Override
    public int rollDie() {
        return (int) (Math.random() * 6)+1;
    }
}
