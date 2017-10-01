package hotciv.standard;

import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import hotciv.framework.*;

import hotciv.standard.StrategyClasses.AlphaAttackingStrategy;
import hotciv.standard.StrategyClasses.AlphaUnitActionStrategy;
import hotciv.standard.StrategyClasses.AlphaWinnerStrategy;
import hotciv.standard.StrategyClasses.AlphaWorldLayoutStrategy;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
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

//Hot fix af game Age
public class TestAlphaCiv {
    private GameImpl game;
    
    private Position redCity;
    private Position blueCity;
    private Position redArcher;
    private Position redSettler;
    private Position blueLegion;

    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }


    @Before
    public void setup(){
        winnerStrategy = new AlphaWinnerStrategy();
        unitActionStrategy = new AlphaUnitActionStrategy();
        worldLayoutStrategy = new AlphaWorldLayoutStrategy();
        attackingStrategy = new AlphaAttackingStrategy();

        game = new GameImpl(winnerStrategy,unitActionStrategy, worldLayoutStrategy, attackingStrategy);

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
    public void shouldReturnNullAsWinnerOfWinnerMethodIsCalledWhenItsNotYear3000BCYet(){
        assertThat(game.getWinner(),is(nullValue()));
    }

    @Test
    public void shouldBeProduced6TreasuriesForCitiesAfter1Round(){
        advanceRound();
        assertThat(((CityImpl) game.getCityAt(redCity)).getTreasury(),is(6));
        assertThat(((CityImpl)game.getCityAt(redCity)).getTreasury(),is(6));
    }

    @Test
    public void shouldBeProduced6TreasuriesForCitiesAfterEveryRound(){
        for (int i=0; i<2; i++){
            advanceRound();
        }
        assertThat(((CityImpl)game.getCityAt(redCity)).getTreasury(),is(2));
        assertThat(((CityImpl)game.getCityAt(blueCity)).getTreasury(),is(12));
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
        advanceRound();
        advanceRound();
        assertThat(game.getUnitAt(redCity).getTypeString(), is(GameConstants.ARCHER));
    }

    @Test
    public void shouldOnlyBePossibleToProduceUnitsAtCities(){
        game.produceUnit(new Position(1,1 ),new UnitImpl(GameConstants.ARCHER,Player.RED));
        assertThat(game.getUnitAt(new Position(1,1)),is(nullValue()));
    }

    @Test
    public void shouldOnlyBePossibleToProduceUnitsAtOwnCity(){
        for(int i = 0; i<5; i++){
            advanceRound();
        }
        assertThat(game.getUnitAt(blueCity).getOwner(), is(Player.BLUE));
        assertThat(game.getUnitAt(redCity).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldGiveRedCityProductionFocusArcher(){
        assertThat(game.getCityAt(redCity).getProduction(), is(GameConstants.ARCHER)); //returns the ProductionFocus unit for the city.
    }

    @Test
    public void shouldGiveBlueCityProductionFocusSettler(){
        assertThat(game.getCityAt(blueCity).getProduction(), is(GameConstants.SETTLER)); //returns the productionFocus unit for the city.
    }

    @Test
    public void shouldBePossibleToChangeProductionFocus(){
        ((CityImpl)game.getCityAt(redCity)).setProduction(GameConstants.LEGION);
        assertThat(game.getCityAt(redCity).getProduction(), is(GameConstants.LEGION));
    }


    @Test
    public void shouldAutomaticallyBuyUnitWhenTreasuryIsHighEnough(){
        advanceRound();
        advanceRound();
        assertThat(game.getUnitAt(redCity).getTypeString(),is(GameConstants.ARCHER)); //CHECKS if red automatically spawns an archer
        //When enough treasury is gained.

    }

    @Test
    public void shouldCost10TreasuriesForAnArcher(){
        advanceRound();
        advanceRound();
        assertThat(((CityImpl)game.getCityAt(redCity)).getTreasury(),is(2)); //Assumed that redcity buys archer and reduces treasuries by 10
    }

    @Test
    public void shouldReturnTheCorrectCostValueOfAUnit(){
        assertThat(game.getCost(GameConstants.ARCHER),is(10));
        assertThat(game.getCost(GameConstants.SETTLER),is(30));
        assertThat(game.getCost(GameConstants.LEGION),is(15));
    }

    @Test
    public void shouldCost30TreasuriesForASettler(){
        advanceRound();
        advanceRound();
        advanceRound();
        advanceRound();
        advanceRound();
        assertThat(((CityImpl)game.getCityAt(blueCity)).getTreasury(),is(0)); //Assumed that bluecity produces legion and reduces treasure by 15
    }

    @Test
    public void shouldChangeCityOwnerWhenAttackingUnitKillsACitysDefendingUnit(){
        game.moveUnit(new Position(2,0), new Position(3,1));
        //advances the game 5 rounds, to spawn a settler at Blue City.
        for (int i = 0; i < 5; i++){
            advanceRound();
        }
        assertThat(game.getCityAt(blueCity).getOwner(), is(Player.BLUE));
        game.moveUnit(new Position(3,1), new Position(4,1));
        assertThat(game.getCityAt(blueCity).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldLetArchersStartOutWith3InDefence(){
        assertThat(game.getUnitAt(new Position(2,0)).getDefensiveStrength(),is(3));
    }


}