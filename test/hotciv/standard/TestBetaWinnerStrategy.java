package hotciv.standard;

import hotciv.framework.Player;
import hotciv.standard.StrategyClasses.BetaWinnerStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestBetaWinnerStrategy {

    private WinnerStrategy winnerStrategy;
    private GameImpl game;

    @Before
    public void setup(){
        winnerStrategy = new BetaWinnerStrategy();
        game = new GameImpl(winnerStrategy);
    }

    @Test
    public void shouldMakePlayerWinnerIfHeConquresAllCities(){

        assertThat(game.getWinner(), is(Player.RED));
    }

}
