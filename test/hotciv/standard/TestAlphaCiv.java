package hotciv.standard;

import hotciv.framework.*;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

/**
 * Skeleton class for AlphaCiv test cases
 * <p>
 * Updated Oct 2015 for using Hamcrest matchers
 * <p>
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Department of Computer Science
 * Aarhus University
 * <p>
 * Please visit http://www.baerbak.com/ for further information.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class TestAlphaCiv {
    private GameImpl game;
    
    private Position redCity;
    private Position blueCity;
    private Position redArcher;
    private Position redSettler;
    private Position blueLegion;

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }

    @Before
    public void setup(){
        game = new GameImpl();

        redCity = new Position(1,1);
        blueCity = new Position(4,1);
        redArcher = new Position(2,0);
        redSettler = new Position(4,3);
        blueLegion = new Position(3,2);
    }

    @Test
    public void shouldBeRedAsTheStartingPlayer(){
        assertThat(game.getPlayerInTurn(),is(Player.RED));
    }
    @Test
    public void shouldBeBluesTurnAfterRed(){
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
    }
    @Test
    public void shouldBeRedsTurnAfterBlues(){
        advanceRound();
        assertThat(game.getPlayerInTurn(),is(Player.RED));
    }

    @Test
    public void shouldBeRedCityAt1_1(){
        assertNotNull(game.getCityAt(redCity));
        assertThat(game.getCityAt(redCity).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldStartGameAt4000BC(){
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldIncrementGameAgeBy100EachRound(){
        advanceRound();
        assertThat(game.getAge(), is(-3900));
        advanceRound();
        assertThat(game.getAge(), is (-3800));
    }

    @Test
    public void shouldBeRedWinnerAtYear3000BC(){
        //simulates 10 rounds - or 1000 years.
        for (int i=0; i<20; i++){
            game.endOfTurn();
        }
        assertThat(game.getWinner(), is(Player.RED));
    }

    @Test
    public void shouldReturnNullAsWinnerOfWinnerMethodIsCalledWhenItsNotYear3000BCYet(){
        assertThat(game.getWinner(),is(nullValue()));
    }

    @Test
    public void shouldBeProduced6productionForCitiesAfter1Round(){
        advanceRound();
        assertThat(Integer.valueOf(game.getCityAt(redCity).getProduction()),is(6));
        assertThat(Integer.valueOf(game.getCityAt(blueCity).getProduction()),is(6));
    }

    @Test
    public void shouldBeProduced6productionsForCitiesAfterEveryRound(){
        for (int i=0; i<2; i++){
            advanceRound();
        }
        assertThat(Integer.valueOf(game.getCityAt(redCity).getProduction()),is(12));
        assertThat(Integer.valueOf(game.getCityAt(blueCity).getProduction()),is(12));
    }

    @Test
    public void shouldHaveA16x16MapWithPlainsFrom3_3to15_15(){
        for(int i = 3; i<15; i++){
            for(int j = 3; j<15; j++){
                assertThat(game.getTileAt(new Position(i,j)).getTypeString(),is(GameConstants.PLAINS));
                //This has to be changed to all different kind of gameconstants. Could not find solution for that.
            }
        }
    }

    @Test
    public void shouldBeOceanAt1_0(){
        assertThat(game.getTileAt(new Position(1,0)).getTypeString(),is(GameConstants.OCEANS));
    }

    @Test
    public void shouldBeHillsAt0_1(){
        assertThat(game.getTileAt(new Position(0,1)).getTypeString(),is(GameConstants.HILLS));
    }

    @Test
    public void shouldBeMountainsAt2_2(){
        assertThat(game.getTileAt(new Position(2,2)).getTypeString(),is(GameConstants.MOUNTAINS));
    }
    @Test
    public void shouldReturnOwnerWhenUsingGetOwner(){
        assertThat(game.getCityAt(redCity).getOwner(),is(Player.RED));
    }
    @Test
    public void shouldReturnColumnWhenUsingGetColumn(){
        assertThat(redCity.getColumn(),is(1));
    }
    @Test
    public void shouldReturnRowWhenUsingGetRow(){
        assertThat(redCity.getColumn(),is(1));
    }
    @Test
    public void shouldReturnThePlayerInTurn(){
        assertThat(game.getPlayerInTurn(),is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
    }

    @Test
    public void shouldBeBlueAt4_1(){
        assertThat(game.getCityAt(blueCity).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldReturnNullWhenTheresNoCityAtPosition(){
        assertNull(game.getCityAt(new Position(8,8)));
    }

    @Test
    public void shouldAlwaysHaveASizeOf1prCity(){
        assertThat(game.getCityAt(redCity).getSize(),is(1));
        assertThat(game.getCityAt(blueCity).getSize(),is(1));
    }

    @Test
    public void shouldBeBlueLegionAt3_2(){
        assertThat(game.getUnitAt(blueLegion).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(blueLegion).getTypeString(),is(GameConstants.LEGION));
    }

    @Test
    public void shouldBeARedSettlerAt4_3(){
        assertThat(game.getUnitAt(redSettler).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(redSettler).getTypeString(),is(GameConstants.SETTLER));
    }

    @Test
    public void shouldbeARedArcherAt2_0(){
        assertThat(game.getUnitAt(redArcher).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(redArcher).getTypeString(),is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBePossibleToDeleteAUnit(){
        game.deleteUnit(redArcher);
        assertNull(game.getUnitAt(redArcher));
    }

    @Test
    public void shouldBePossibleToMoveAUnit(){
        assertThat(game.getUnitAt(redArcher).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(redArcher,new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getTypeString(),is(GameConstants.ARCHER));
    }
    @Test
    public void shouldDeleteUnitFromWhenMoving(){
        assertThat(game.getUnitAt(redArcher).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(redArcher,new Position(3,0));
        assertNull(game.getUnitAt(redArcher));
    }
    @Test
    public void shouldOnlyBePossibleForThePlayerToMoveHisOwnUnits(){
        assertFalse(game.moveUnit(blueLegion,new Position(4,2)));
    }
    @Test
    public void shouldHave1MovecountForAllUnits(){
        assertThat(game.getUnitAt(redArcher).getMoveCount(),is(1));
        assertThat(game.getUnitAt(blueLegion).getMoveCount(),is(1));
        assertThat(game.getUnitAt(redSettler).getMoveCount(),is(1));

    }

    @Test
    public void shouldDecrementMovescountForAMove(){
        assertThat(game.getUnitAt(redArcher).getMoveCount(),is(1));
        game.moveUnit(redArcher,new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
    }

    @Test
    public void shouldOnlyBeAbleToMoveMovecountMovesEachRound(){
        game.moveUnit(redArcher,new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
        game.moveUnit(new Position(3,0),new Position(4,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
    }

    @Test
    public void shouldResetUnitsMovescountAfterEndedRound(){
        assertThat(game.getUnitAt(redArcher).getMoveCount(),is(1));
        game.moveUnit(redArcher,new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
        advanceRound();
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(1));
    }

    @Test
    public void shouldBeIllegalToMoveUnitsOverOceans(){
        assertThat(game.moveUnit(redArcher,new Position(1, 0)), is(false));
    }

    @Test
    public void shouldBeIllegalToMoveUnitsOverMountains(){
        game.endOfTurn();
        assertThat(game.moveUnit(blueLegion, new Position(2,2)), is(false));
    }

    @Test
    public void shouldBeIllegalToMoveOutsideTheMap(){
        assertThat(game.moveUnit(redArcher, new Position(2,-1)), is(false));
    }

    @Test
    public void shouldOnlyBePossibleToMoveUnitsAsFarAsTheirMoveCount(){
        assertThat(game.moveUnit(redArcher, new Position(4, 0)), is(false));
        assertThat(game.moveUnit(redArcher, new Position(3,1)), is(true));
        game.endOfTurn();
        assertThat(game.moveUnit(blueLegion, new Position(2,3)), is(true));
    }
    
    @Test
    public void shouldBePossibleToProduceNewUnits(){
        assertThat(game.produceUnit(redCity, new UnitImpl(GameConstants.ARCHER, Player.RED)), is(true));
        assertThat(game.getUnitAt(redCity).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldOnlyBePossibleToProduceUnitsAtCities(){
        assertThat(game.produceUnit(new Position(10, 10), new UnitImpl(GameConstants.LEGION, Player.RED)), is(false));
        assertThat(game.produceUnit(redCity, new UnitImpl(GameConstants.LEGION, Player.RED)), is(true));
    }

    @Test
    public void shouldOnlyBePossibleToProduceUnitsAtOwnCity(){
        assertThat(game.produceUnit(blueCity, new UnitImpl(GameConstants.LEGION, Player.RED)), is(false));
        assertThat(game.produceUnit(redCity, new UnitImpl(GameConstants.LEGION, Player.RED)), is(true));
    }

    @Test
    public void shouldGiveRedCityWorkforceFocusArcher(){
        assertThat(game.getCityAt(redCity).getWorkforceFocus(), is(GameConstants.ARCHER)); //returns the workforceFocus unit for the city.
    }

    @Test
    public void shouldGiveBlueCityWorkforceFocusSettler(){
        assertThat(game.getCityAt(blueCity).getWorkforceFocus(), is(GameConstants.SETTLER)); //returns the workforceFocus unit for the city.
    }

    @Test
    public void shouldBePossibleToChangeWorkforceFocus(){
        ((CityImpl)game.getCityAt(redCity)).setWorkforceFocus(GameConstants.LEGION);
        assertThat(game.getCityAt(redCity).getWorkforceFocus(), is(GameConstants.LEGION));
    }

    @Test
    public void shouldLetRedAttackAndDestroyBluesUnit(){
        game.moveUnit(new Position(2,0),new Position(3,1));
        advanceRound();
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
        game.moveUnit(new Position(3,1),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.RED));
    }

}