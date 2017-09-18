package hotciv.standard;

import hotciv.standard.StrategyClasses.AlphaAgeingStrategy;
import hotciv.standard.StrategyInterfaces.AgeingStrategy;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TestAlphaLinearAgeing {
    AgeingStrategy as;

    @Before
    public void setup(){
        as = new AlphaAgeingStrategy();
    }

    @Test
    public void shouldIncrementGameAgeBy100EachRound(){
        assertThat(-4000 + 100 /*Starting age plus 100 for one round */, is(as.incrementAge(-4000)/*4000 BC*/));
        assertThat(-4000 + 100 + 100/*Starting age plus 200 for two rounds*/, is(as.incrementAge(-3900)/* 3900 BC*/));
    }



}
