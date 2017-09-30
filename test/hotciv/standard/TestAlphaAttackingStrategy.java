package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
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

    private Position redCity;
    private Position blueCity;
    private Position redArcher;
    private Position redSettler;
    private Position blueLegion;

    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;

    @Before
    public void setup(){
        winnerStrategy = new AlphaWinnerStrategy();
        unitActionStrategy = new AlphaUnitActionStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();
        attackingStrategy = new AlphaAttackingStrategy();

        game = new GameImpl(winnerStrategy,unitActionStrategy, worldLayoutStrategy, attackingStrategy);

        redCity = new Position(1,1);
        blueCity = new Position(4,1);
        redArcher = new Position(2,0);
        redSettler = new Position(4,3);
        blueLegion = new Position(3,2);
    }
    public void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldReturn0AsAttackingStrength(){
        assertThat(game.getUnitAt(redArcher).getAttackingStrength(),is(0));
    }

    @Test
    public void shouldReturn3AsDefensiveStrength(){
        assertThat(game.getUnitAt(redArcher).getDefensiveStrength(),is(3));
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
