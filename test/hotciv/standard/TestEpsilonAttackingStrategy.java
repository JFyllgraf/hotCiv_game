package hotciv.standard;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by csdev on 9/29/17.
 */
public class TestEpsilonAttackingStrategy {
    private GameImpl game;

    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;
    DieDecisionStrategy dieDecisionStrategy;

    @Before
    public void setup(){
        winnerStrategy = new AlphaWinnerStrategy();
        unitActionStrategy = new AlphaUnitActionStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();
        dieDecisionStrategy = new FixedDieDecisionStrategy(6);
        attackingStrategy = new EpsilonAttackingStrategy(dieDecisionStrategy);
        game = new GameImpl(winnerStrategy,unitActionStrategy, worldLayoutStrategy, attackingStrategy);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }


    @Test
    public void shouldReturn6WithFixedDieValue(){
        assertThat(dieDecisionStrategy.rollDie(), is(6));
    }

    @Test
    public void shouldLetBlueLegionKillRedArcher(){
        game.moveUnit(new Position(2,0),new Position(3,1));
        game.endOfTurn();
        game.moveUnit(new Position(3,2),new Position(3,1));
        assertThat(game.getUnitAt(new Position(3,1)).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldLetLegionWinAgainstArcherWhenArcherAttacks(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        advanceRound();
        game.moveUnit(new Position(3,1),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldNotLetSettlerWinAgainstLegion(){
        game.moveUnit(new Position(4,3), new Position(3,3));
        advanceRound();
        game.moveUnit(new Position(3,3),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
    }
}
