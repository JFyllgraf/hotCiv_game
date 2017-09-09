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

    @Before
    public void setup(){
        game = new GameImpl();
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
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.RED));
    }

    @Test
    public void shouldBeRedCityAt1_1(){
        assertNotNull(game.getCityAt(new Position(1,1)));
        assertThat(game.getCityAt(new Position(1,1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldStartGameAt4000BC(){
        assertThat(game.getAge(), is(-4000));
    }

    @Test
    public void shouldIncrementGameAgeBy100EachRound(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
        game.endOfTurn();
        game.endOfTurn();
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
    public void shouldBeProduced6productionForCitiesAfter1Round(){
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(new Position(1,1)).getProduction(),is(6));
    }
    @Test
    public void shouldBeProduced6productionsForCitiesAfterEveryRound(){
        for (int i=0; i<4; i++){
            game.endOfTurn();
        }
        assertThat(game.getCityAt(new Position(1,1)).getProduction(),is(12));
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
        assertThat(game.getCityAt(new Position(1,1)).getOwner(),is(Player.RED));
    }
    @Test
    public void shouldReturnColumnWhenUsingGetColumn(){
        assertThat(new Position(1,1).getColumn(),is(1));
    }
    @Test
    public void shouldReturnRowWhenUsingGetRow(){
        assertThat(new Position(1,1).getColumn(),is(1));
    }
    @Test
    public void shouldReturnThePlayerInTurn(){
        assertThat(game.getPlayerInTurn(),is(Player.RED));
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(),is(Player.BLUE));
    }

    @Test
    public void shouldBeBlueAt4_1(){
        assertThat(game.getCityAt(new Position(4,1)).getOwner(),is(Player.BLUE));
    }

    @Test
    public void shouldReturnNullWhenTheresNoCityAtPosition(){
        assertNull(game.getCityAt(new Position(8,8)));
    }

    @Test
    public void shouldAlwaysHaveASizeOf1prCity(){
        assertThat(game.getCityAt(new Position(1,1)).getSize(),is(1));
        assertThat(game.getCityAt(new Position(4,1)).getSize(),is(1));
    }

    @Test
    public void shouldBeBlueLegionAt3_2(){
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.BLUE));
        assertThat(game.getUnitAt(new Position(3,2)).getTypeString(),is(GameConstants.LEGION));
    }

    @Test
    public void shouldBeARedSettlerAt4_3(){
        assertThat(game.getUnitAt(new Position(4,3)).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(new Position(4,3)).getTypeString(),is(GameConstants.SETTLER));
    }

    @Test
    public void shouldbeARedArcherAt2_0(){
        assertThat(game.getUnitAt(new Position(2,0)).getOwner(),is(Player.RED));
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(),is(GameConstants.ARCHER));
    }

    @Test
    public void shouldBePossibleToDeleteAUnit(){
        game.deleteUnit(new Position(2,0));
        assertNull(game.getUnitAt(new Position(2,0)));
    }

    @Test
    public void shouldBePossibleToMoveAUnit(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(new Position(2,0),new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getTypeString(),is(GameConstants.ARCHER));
    }
    @Test
    public void shouldDeleteUnitFromWhenMoving(){
        assertThat(game.getUnitAt(new Position(2,0)).getTypeString(),is(GameConstants.ARCHER));
        game.moveUnit(new Position(2,0),new Position(3,0));
        assertNull(game.getUnitAt(new Position(2,0)));
    }
    @Test
    public void shouldOnlyBePossibleForThePlayerToMoveHisOwnUnits(){
        assertFalse(game.moveUnit(new Position(3,2),new Position(4,2)));
    }
    @Test
    public void shouldHave1MovecountForAllUnits(){
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
        assertThat(game.getUnitAt(new Position(3,2)).getMoveCount(),is(1));
        assertThat(game.getUnitAt(new Position(4,3)).getMoveCount(),is(1));

    }

    @Test
    public void shouldDecrementMovescountForAMove(){
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(2,0),new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
    }


    //Test - Jesper
}
