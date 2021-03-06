package hotciv.standard;


import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAlphaWorldLayoutStrategy {

    private WorldLayoutStrategy worldLayoutStrategy;
    private GameImpl game;

    @Before
    public void setup(){
        GameFactory alphaMaker = new AlphaGameFactory();

        game = new GameImpl(alphaMaker);
    }

    @Test
    public void shouldHaveA16x16MapWithPlainsFrom3_3to15_15(){
        for(int i = 3; i<15; i++){
            for(int j = 3; j<15; j++){
                assertThat(game.getTileAt(new Position(i,j)).getTypeString(),is(GameConstants.PLAINS));
            }
        }
    }

}
