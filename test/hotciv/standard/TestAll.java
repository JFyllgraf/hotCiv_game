package hotciv.standard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        { TestAlphaCiv.class,
            TestAlphaLinearAgeing.class,
            TestBetaCiv.class
        }
)

public class TestAll {


}
