package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.GammaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyClasses.GammaUnitActionStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestLogDecorator {

    private LogDecorator game;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        GameFactory factory = new AlphaGameFactory();
        game = new LogDecorator(new GameImpl(factory));
    }

    @After
    public  void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void test(){
        game.endOfTurn();
        assertThat(outContent.toString(), is("RED ends turn"));
    }


}
