package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.AlphaAttackingStrategy;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWinnerStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
/**
 * Created by csdev on 9/29/17.
 */
public class TestAlphaAttackingStrategy {
    private GameImpl game;

    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;

    @Before
    public void setup(){

        GameFactory alphaMaker = new AlphaGameFactory();

        game = new GameImpl(alphaMaker);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldLetRedAttackAndDestroyBluesUnit(){
        game.moveUnit(new Position(2,0),new Position(3,1));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
        advanceRound();
        game.moveUnit(new Position(3,1),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.RED));
    }

}
