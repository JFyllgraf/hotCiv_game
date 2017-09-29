package hotciv.standard;

import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyClasses.GammaUnitActionStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class TestGammaCivUnitAction {

    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private GameImpl game;

    @Before
    public void setup(){
        unitActionStrategy = new GammaUnitActionStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();
        this.game = new GameImpl(null, unitActionStrategy, worldLayoutStrategy,null);
    }

    @Test
    public void shouldSpawnTownAtSettlersPositionAfterItHasPerformedItsAction(){
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getCityAt(new Position(4,3)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldRemoveSettlerWhenTheirActionIsPerformed(){
        game.performUnitActionAt(new Position(4,3));
        assertThat(game.getUnitAt(new Position(4,3)), is(nullValue()));
    }

    @Test
    public void shouldDoubleStrenthForArcherWhenActionIsPerformed(){
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(),is(6));
    }

    @Test
    public void shouldFreezeArcherAfterActionIsPerformed(){
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(-1));
        assertThat(game.moveUnit(new Position(2,0),new Position(3,1)),is(false));
    }

    @Test
    public void shouldUnFreezeArcherAfterItIsFrozenByUsingAction(){
        game.performUnitActionAt(new Position(2,0)); //Freeze
        game.performUnitActionAt(new Position(2,0)); //and then unfreeze
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(0));
    }




}
