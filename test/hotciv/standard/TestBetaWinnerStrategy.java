package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.StrategyClasses.BetaWinnerStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestBetaWinnerStrategy {

    private WinnerStrategy winnerStrategy;
    private GameImpl game;
    private Position blueCityPos;

    @Before
    public void setup(){
        this.blueCityPos = new Position(4,1);
        winnerStrategy = new BetaWinnerStrategy();
        game = new GameImpl(winnerStrategy,null);
    }

    @Test
    public void shouldMakePlayerWinnerIfHeConquresAllCities(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        //advances the game 5 rounds, to spawn a settler at Blue City.
        for (int i = 0; i < 10; i++){
            game.endOfTurn();
        }
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(3,1), blueCityPos);
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }

}
