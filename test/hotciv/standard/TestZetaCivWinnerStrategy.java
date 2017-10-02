package hotciv.standard;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.ZetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestZetaCivWinnerStrategy {

    Game game;

    private WinnerStrategy betaWinnerStrategy;
    private WinnerStrategy epsilonWinnerStrategy;
    private WinnerStrategy winnerStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;

    private Position blueCityPos;

    @Before
    public void setup(){
        GameFactory zetaMaker = new ZetaGameFactory();

        game = new GameImpl(zetaMaker);

        blueCityPos = new Position(4,1);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldWinWhenConqueringAllCitiesBefore20RoundsPassed(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        //advances the game 5 rounds, to spawn a settler at Blue City.
        for (int i = 0; i < 10; i++){
            game.endOfTurn();
        }
        game.moveUnit(new Position(3,1), blueCityPos);
        assertThat(game.getCityAt(blueCityPos).getOwner(), is(Player.RED));
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldWinAfter3SuccesfulAttacksAfter20RoundsPassed(){
        for(int i = 0; i < 22; i++){
            advanceRound();
        }

        game.moveUnit(new Position(2,0),new Position(3,1));
        game.endOfTurn();
        game.moveUnit(new Position(3,2),new Position(4,3));
        assertThat(game.getUnitAt(new Position(4,3)).getOwner(),is(Player.BLUE));
        game.endOfTurn();
        game.moveUnit(new Position(3,1), new Position(3,2));
        game.endOfTurn();
        game.moveUnit(new Position(4,3),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
        game.endOfTurn();
        advanceRound();
        assertThat(game.getUnitAt(new Position(1,1)).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(new Position(1,1), new Position(2,1));
        advanceRound();
        game.moveUnit(new Position(2,1), new Position(3,1));
        game.endOfTurn();
        game.moveUnit(new Position(3,2),new Position(3,1));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(),is(Player.BLUE));
        assertThat(game.getWinner(),is(Player.BLUE));
    }
}
