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
                TestAlphaWorldLayoutStrategy.class
        }
)

public class TestAll {


}
