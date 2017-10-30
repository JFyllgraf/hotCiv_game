package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import hotciv.framework.Position;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/** Created by Simon
 */
public class TestLogDecorator {
    private Game game, decoratee;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setup(){
        decoratee = new GameImpl(new AlphaGameFactory());
        outputStream = new ByteArrayOutputStream();
        game = new LogDecorator(decoratee, new PrintStream(outputStream));
    }

    @Test
    public void shouldPrintWhichPlayerThatEndsTurn(){
        assertThat(game.getPlayerInTurn(), is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
        game.endOfTurn();
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("RED ends turn."));
        assertThat(sl.get(1), is("BLUE ends turn."));
    }

    @Test
    public void shouldPrintWhenPlayerMovesUnits(){
        game.moveUnit(new Position(2,0), new Position(3,1)); //Moves red archer from 2,0 to 3,1
        ArrayList<String> sl = getStringArray();
        assertThat(game.getUnitAt(new Position(3,1)).getTypeString(), is("archer"));
        assertThat(sl.get(0), is("RED has moved archer from [2,0] to [3,1]"));
    }

    @Test
    public void shouldPrintWorldAge(){
        game.getAge();
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("Game age is: -4000"));
    }

    @Test
    public void shouldPrintWhenProductionIsChangedInACity(){
        game.changeProductionInCityAt(new Position(1,1), GameConstants.LEGION);
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is( "RED changes production in city at [1,1] to legion"));
    }

    @Test
    public void shouldToggleTranscriptingWhenLocalMethodIsCalled(){
        game.getAge();
        ArrayList<String> sl = getStringArray();
        assertThat(sl.get(0), is("Game age is: -4000"));
        transcriptToggle(); //Toggles transcript off

        game.endOfTurn();
        sl = getStringArray();
        assertThat(sl.size(), is(1)); //Did not print to the array
        transcriptToggle(); //Toggles transcript on again

        game.endOfTurn();
        sl = getStringArray();
        assertThat(sl.get(1), is("RED ends turn."));  //Another line is added to the array
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

    private void transcriptToggle(){
        if (game == decoratee){
            decoratee = new GameImpl(new AlphaGameFactory());
            game = new LogDecorator(decoratee, new PrintStream(outputStream));
        } else {
            game = decoratee;
        }
    }
}
