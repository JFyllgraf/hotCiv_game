package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        { TestAlphaCiv.class,
                TestAlphaLinearAgeing.class,
                TestBetaLadderAgeing.class,
                TestAlphaWinnerStrategy.class,
                TestBetaWinnerStrategy.class,
                TestAlphaCivUnitAction.class,
                TestGammaCivUnitAction.class,
                TestAlphaWorldLayoutStrategy.class,
                TestDeltaWorldLayoutStrategy.class,
                TestAlphaAttackingStrategy.class,
                TestEpsilonAttackingStrategy.class,
                TestUtility.class,
                TestEpsilonWinnerStrategy.class,
                TestZetaCivWinnerStrategy.class,
                TestSemiCiv.class,
                TestLogDecorator.class,
                TestGameObserver.class
        }
)

public class TestAll {


}
