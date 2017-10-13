package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.DeltaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.ThetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
import hotciv.standard.StrategyClasses.EpsilonAttackingStrategy;
import hotciv.standard.StrategyClasses.ThetaAttackingStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by csdev on 10/12/17.
 */
public class TestThetaCiv {
    private ThetaAttackingStrategy thetaAttackingStrategy;
    private DeltaWorldLayoutStrategy deltaWorldLayoutStrategy;
    private Game game;
    private GameFactory thetaMaker;

    @Before
    public void setup() {
        thetaMaker = new ThetaGameFactory();
        game = new GameImpl(thetaMaker);
    }



    @Test
    public void shouldHave8AsAttack() {
        assertThat(((ThetaAttackingStrategy) (thetaMaker.attackingStrategy())).getAttackStrength((ExpansionGameConstants.GALLEY)), is(8));
    }

    @Test
    public void shouldHave2AsDefence() {
        assertThat(((ThetaAttackingStrategy) (thetaMaker.attackingStrategy())).getDefendStrength((ExpansionGameConstants.GALLEY)), is(2));
    }

    @Test
    public void shouldLetGalleyMoveOnWater() {
        assertThat(game.getUnitAt(new Position(6, 6)).getTypeString(), is(ExpansionGameConstants.GALLEY));
        game.moveUnit(new Position(6, 6), new Position(6, 7));
        assertThat(game.getUnitAt(new Position(6, 7)).getTypeString(), is(ExpansionGameConstants.GALLEY));
    }
    @Test
    public void shouldNotLetGalleyMoveOnPlain(){
        assertThat(game.getUnitAt(new Position(6, 6)).getTypeString(), is(ExpansionGameConstants.GALLEY));
        game.moveUnit(new Position(6, 6), new Position(5, 5));
        assertThat(game.getUnitAt(new Position(6, 6)).getTypeString(), is(ExpansionGameConstants.GALLEY));
    }
    @Test
    public void shouldLetGalleyMoveTwoMovesInOneRound(){
        assertThat(game.getUnitAt(new Position(6,6)).getTypeString(),is(ExpansionGameConstants.GALLEY));
        game.moveUnit(new Position(6,6),new Position(7,6));
        game.moveUnit(new Position(7,6),new Position(8,6));
        assertThat(game.getUnitAt(new Position(8,6)).getTypeString(),is(ExpansionGameConstants.GALLEY));
    }
    @Test
    public void shouldNotLetGalleyMoveMoreThanTwoMovesInOneRound(){
        assertThat(game.getUnitAt(new Position(6,6)).getTypeString(),is(ExpansionGameConstants.GALLEY));
        game.moveUnit(new Position(6,6),new Position(7,6));
        game.moveUnit(new Position(7,6),new Position(8,6));
        game.moveUnit(new Position(8,6),new Position(9,6));
        assertThat(game.getUnitAt(new Position(8,6)).getTypeString(),is(ExpansionGameConstants.GALLEY));
    }

    private void advanceRound() {
        game.endOfTurn();
        game.endOfTurn();
    }

}
