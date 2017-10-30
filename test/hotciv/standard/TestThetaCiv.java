package hotciv.standard;
import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.SemiGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.ThetaGameFactory;
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
 * Created by csdev on 10/23/17.
 */
public class TestThetaCiv {
    private GameFactory thetaMaker;
    private Game game;

    @Before
    public void setup(){
        thetaMaker = new ThetaGameFactory();
        game = new GameImpl(thetaMaker);
    }
    @Test
    public void shouldBeAbleToProduceGalley(){
        //game.changeProductionInCityAt(new Position(8,12),GameConstants.GALLEY);
        //for(int i = 0; i<5; i++){
        //    advanceRound();
        //}
        //assertThat(game.getUnitAt(new Position(8,12)).getTypeString(),is(GameConstants.GALLEY));
    }



    private void advanceRound() {
        game.endOfTurn();
        game.endOfTurn();
    }
}
