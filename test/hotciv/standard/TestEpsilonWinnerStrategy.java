package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyClasses.EpsilonWinnerStrategy;
import hotciv.standard.StrategyClasses.GammaUnitActionStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by csdev on 10/1/17.
 */
public class TestEpsilonWinnerStrategy {
    private WinnerStrategy winnerStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private GameImpl game;

    @Before
    public void setup() {
        winnerStrategy = new EpsilonWinnerStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();

        this.game = new GameImpl(winnerStrategy, null, worldLayoutStrategy, null);
    }

    @Test
    public void shouldWinTheGameAfter3SuccessfulAttacks() {

    }
}
