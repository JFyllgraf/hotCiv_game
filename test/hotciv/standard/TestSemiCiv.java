package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.SemiGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.BetaAgeingStrategy;
import hotciv.standard.StrategyClasses.EpsilonAttackingStrategy;

import hotciv.standard.StrategyInterfaces.AgeingStrategy;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by csdev on 10/11/17.
 */



public class TestSemiCiv {
    private GameImpl game;
    AgeingStrategy as;
    private GameFactory semiMaker;
    private Game gameForTestStub;

    @Before
    public void setup(){
        GameFactory semiMaker = new SemiGameFactory();
        game = new GameImpl(semiMaker);
        as = new BetaAgeingStrategy();
        gameForTestStub = new GameStubForBattleTesting();
    }



    @Test
    public void shouldDoubleStrenthForArcherWhenActionIsPerformed(){
        //Smoke test for Unitaction
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(), CoreMatchers.is(6));
    }

    @Test
    public void shouldFreezeArcherAfterActionIsPerformed(){
        //Smoke test for Unitaction
        game.performUnitActionAt(new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(), CoreMatchers.is(-1));
        assertThat(game.moveUnit(new Position(2,0),new Position(3,1)), CoreMatchers.is(false));
    }

    @Test
    public void shouldIncrement2YearsBetween1BCand1AD(){
        //Smoke test for Beta ageing
        assertThat(-1 + 2 /*Ageing 2 years at 1 BC*/, CoreMatchers.is(as.incrementAge(-1)/*1 BC*/));
    }

    @Test
    public void shouldIncrement49YearsBetween1and50AD(){
        //Smoke test for Beta ageing
        assertThat(1+49 /*Ageing 49 years at 1 AD*/, CoreMatchers.is(as.incrementAge(1)/*1 AD*/));
    }

    @Test
    public void shouldHaveBlueCityAt4_5(){
        //Smoke test for Delta programmable world layout
        assertThat(game.getCityAt(new Position(4,5)).getOwner(), CoreMatchers.is(Player.BLUE));
    }

    @Test
    public void shouldHaveDeltaTileWorldLayout(){
        //Smoke test for Delta programmable world layout
        assertThat(game.getTileAt(new Position(1,3)).getTypeString(), CoreMatchers.is("hills"));
        assertThat(game.getTileAt(new Position(9,3)).getTypeString(), CoreMatchers.is("forest"));
        assertThat(game.getTileAt(new Position(8,6)).getTypeString(), CoreMatchers.is("ocean"));
        assertThat(game.getTileAt(new Position(15,15)).getTypeString(), CoreMatchers.is("ocean"));
        assertThat(game.getTileAt(new Position(4,8)).getTypeString(), CoreMatchers.is("hills"));
        assertThat(game.getTileAt(new Position(7,2)).getTypeString(), CoreMatchers.is("plains"));
        assertThat(game.getTileAt(new Position(13,5)).getTypeString(), CoreMatchers.is("plains"));
        assertThat(game.getTileAt(new Position(3,3)).getTypeString(), CoreMatchers.is("mountain"));
    }

}

