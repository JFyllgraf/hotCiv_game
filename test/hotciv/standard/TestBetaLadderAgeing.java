package hotciv.standard;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestBetaLadderAgeing {
    AgeingStrategy as;

    @Before
    public  void setup(){
        as = new BetaAgeingStrategy();
    }

    @Test
    public void shouldIncrement100YearsBetween4000And100BC(){
        assertThat(-4000 + 100 /*Starting age plus 100 for one round */, is(as.incrementAge(-4000)/*4000 BC*/));
    }

    @Test
    public void shouldIncrement99YearsBetween100And1BC(){
        assertThat(-100 + 99 /*Ageing 99 years at 100 BC*/, is(as.incrementAge(-100)/*100 BC*/));
    }

    @Test
    public void shouldIncrement2YearsBetween1BCand1AD(){
        assertThat(-1 + 2 /*Ageing 2 years at 1 BC*/, is(as.incrementAge(-1)/*1 BC*/));
    }

    @Test
    public void shouldIncrement49YearsBetween1and50AD(){
        assertThat(1+49 /*Ageing 49 years at 1 AD*/, is(as.incrementAge(1)/*1 AD*/));
    }

    @Test
    public void shouldIncrement50YearsBetween50and1750AD(){
        assertThat(50+50 /*Ageing 50 years at 50 AD*/, is(as.incrementAge(50)/*50 AD*/));
    }

    @Test
    public void shouldIncrement25YearsBetween1750and1900AD(){
        assertThat(1750+25 /*Ageing 25 years at 1750 AD*/, is(as.incrementAge(1750)/*1750 AD*/));
    }

    @Test
    public void shouldIncrement5YearsBetween1900and1970AD(){
        assertThat(1900+5 /*Ageing 5 years at 1900 AD*/, is(as.incrementAge(1900)/*1900 AD*/));
    }

    @Test
    public void shouldIncrement1YearFrom1970AndBeyond(){
        assertThat(1970+1 /* Ageing 1 year at 1970 AD*/, is(as.incrementAge(1970)/*1970 AD*/));
    }


}
