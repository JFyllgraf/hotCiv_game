package hotciv.standard;

import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameFactory.GameFactoryClasses.BetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.DeltaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.GammaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDeltaWorldLayoutStrategy {

    private GameImpl game;
    private WorldLayoutStrategy worldLayoutStrategy;

    @Before
    public void setup(){
        GameFactory deltaMaker = new DeltaGameFactory();

        game = new GameImpl(deltaMaker);
    }

    @Test
    public void shouldHaveRedCityAt8_12(){
        assertThat(game.getCityAt(new Position(8,12)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldHaveBlueCityAt4_5(){
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveDeltaTileWorldLayout(){
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), is("hills"));
        assertThat(game.getTileAt(new Position(9,3)).getTypeString(), is("forest"));
        assertThat(game.getTileAt(new Position(8,6)).getTypeString(), is("ocean"));
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), is("ocean"));
        assertThat(game.getTileAt(new Position(4,8)).getTypeString(), is("hills"));
        assertThat(game.getTileAt(new Position(7,2)).getTypeString(), is("plains"));
        assertThat(game.getTileAt(new Position(13,5)).getTypeString(), is("plains"));
        assertThat(game.getTileAt(new Position(3,3)).getTypeString(), is("mountain"));
    }

}
