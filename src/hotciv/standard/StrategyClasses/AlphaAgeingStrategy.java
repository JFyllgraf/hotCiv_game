package hotciv.standard.StrategyClasses;

import hotciv.standard.StrategyInterfaces.AgeingStrategy;

public class AlphaAgeingStrategy implements AgeingStrategy {

    @Override
    public int incrementAge(int age) {
        return age + 100;
    }
}
