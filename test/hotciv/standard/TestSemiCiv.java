package hotciv.standard;

import hotciv.framework.Player;
import hotciv.standard.GameFactory.GameFactoryClasses.GammaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.SemiGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by csdev on 10/11/17.
 */



public class TestSemiCiv {
    private GameImpl game;

    @Before
    public void setup(){
        GameFactory gammaMaker = new SemiGameFactory();
        game = new GameImpl(gammaMaker);
    }

    @Test
    public void shouldBeASmokeTest(){
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
        //Everything has been tested in the different strategies, therefore this is just a smoke test.
    }
}

