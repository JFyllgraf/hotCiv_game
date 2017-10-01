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

    }
    public void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldDoSomething(){

    }


}
