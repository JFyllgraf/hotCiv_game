package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by csdev on 10/1/17.
 */
public class TestEpsilonWinnerStrategy {
    private WinnerStrategy winnerStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;
    private DieDecisionStrategy dieDecisionStrategy;
    private GameImpl game;

    @Before
    public void setup() {
        dieDecisionStrategy = new FixedDieDecisionStrategy(6);
        winnerStrategy = new EpsilonWinnerStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();
        attackingStrategy = new EpsilonAttackingStrategy(dieDecisionStrategy);
        this.game = new GameImpl(winnerStrategy, null, worldLayoutStrategy, attackingStrategy);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Test
    public void shouldWinTheGameAfter3SuccessfulAttacks() {
        //This test is better known as the blood-thirsty legion that rules the world of hotCiv
        assertThat(game.getUnitAt(new Position(4,3)).getOwner(),is(Player.RED));
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
