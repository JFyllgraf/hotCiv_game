package hotciv.standard;
import hotciv.standard.StrategyClasses.FixedDieDecisionStrategy;
import hotciv.standard.StrategyInterfaces.DieDecisionStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by csdev on 9/29/17.
 */
public class TestEpsilonAttackingStrategy {

    DieDecisionStrategy dieDecisionStrategy;

    @Before
    public void setup(){
        dieDecisionStrategy = new FixedDieDecisionStrategy(6);
    }

    @Test
    public void shouldReturn6WithFixedDieValue(){
        assertThat(dieDecisionStrategy.rollDie(), is(6));
    }

}
