package hotciv.standard;

import hotciv.framework.Player;
import hotciv.standard.StrategyClasses.AlphaAgeingStrategy;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWinnerStrategy;
import hotciv.standard.StrategyInterfaces.AgeingStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAlphaWinnerStrategy {
    private GameImpl game;
    private  WinnerStrategy winnerStrategy;

    @Before
    public void setup(){
        winnerStrategy = new AlphaWinnerStrategy();
        game = new GameImpl(winnerStrategy, null);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldBeRedWinnerAtYear3000BC(){
        for (int i = 0; i < 10; i++){
            advanceRound();
        }
        assertThat(game.getWinner(), is(Player.RED));
    }

}
