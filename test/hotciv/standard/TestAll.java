package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        { TestAlphaCiv.class,
                TestAlphaLinearAgeing.class,
                TestBetaLadderAgeing.class,
                TestAlphaWinnerStrategy.class
        }
)

public class TestAll {


}
