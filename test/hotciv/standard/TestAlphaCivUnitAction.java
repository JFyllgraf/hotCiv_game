package hotciv.standard;

import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by csdev on 9/18/17.
 */
public class TestAlphaCivUnitAction {
    private UnitActionStrategy unitActionStrategy;

    @Before
    public void setup(){
        unitActionStrategy = new AlphaUnitActionStrategy();
    }

    @Test
    public void shouldDoNothingWhenUnitWantsToPerformUnitAction(){
        unitActionStrategy.performAction();
        //It does nothing, so this is the testCase
    }
}
