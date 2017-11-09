package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.EpsilonGameFactoryFixedDice;
import hotciv.standard.GameFactory.GameFactoryClasses.ZetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.ObserverStub.ObserverImpl;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestGameObserver {
    private Game game;
    private GameObserver observer;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup(){
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        game = new GameImpl(new AlphaGameFactory());
        observer = new ObserverImpl(game);

        game.addObserver(observer);
    }

    @Test
    public void shouldNotifyObserverWhenUnitsMove(){
        game.moveUnit(new Position(2,0), new Position(2,1));
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("World has been changed at position: [2,1]"));
    }

    @Test
    public void shouldNotifyObserverAfterEndOfTurn(){
        game.changeWorkForceFocusInCityAt(new Position(1,1), GameConstants.productionFocus);
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("World has been changed at position: [1,1]"));
    }

    @Test
    public void shouldNotifyObserverWhenChangingCityProduction(){
        game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("World has been changed at position: [1,1]"));
    }

    @Test
    public void shouldNotifyObserverWhenPerformingUnitAction(){
        game.performUnitActionAt(new Position(2,0));
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("World has been changed at position: [2,0]"));
    }

    @Test
    public void shouldNotifyObserverWhenProducingANewUnit(){
        for(int i = 0; i < 4; i++){
            game.endOfTurn();
        }
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(3), is("World has been changed at position: [1,1]"));
    }

    @Test
    public void shouldNotifyWhenTurnEnds(){
        game.endOfTurn();
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("Turn has ended at age -4000 and next player is BLUE"));
    }

    @Test
    public void shouldNotifyWhenTileFocusChanges(){
        game.setTileFocus(new Position(1,1));
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("World has been changed at position: [1,1]"));
    }

    private ArrayList<String> getStringArray(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(outputStream.toByteArray())));
        ArrayList<String> stringList = new ArrayList<>();
        String line;
        try {
            while ((line = reader.readLine()) != null)
                stringList.add(line);
        } catch (IOException e){
            return stringList;
        }
        return stringList;
    }


}
