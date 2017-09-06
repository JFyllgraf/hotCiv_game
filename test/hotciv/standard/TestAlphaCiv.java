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

    /**
     * Fixture for alphaciv testing.
     */
    @Before
    public void setUp() {
        game = new GameImpl();
    }

    // FRS p. 455 states that 'Red is the first player to take a turn'.
    @Test
    public void shouldBeRedAsStartingPlayer() {
        assertThat(game, is(notNullValue()));

        // TODO: reenable the assert below to get started...
        assertThat(game.getPlayerInTurn(), is(Player.RED));
    }

    @Test
    public void shouldBeBluesTurnAfterRed() {
        game.endOfTurn();
        assertThat(game.getPlayerInTurn(), is(Player.BLUE));
    }

    @Test
    public void shouldHave1PopulationPerCity() {
        assertThat(game.getCityAt(new Position(1, 1)).getSize(), is(1));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getCityAt(new Position(4, 1)).getSize(), is(1));
    }

    @Test
    public void shouldMakeRedWinnerAt3000BC() {
        if (game.getAge() == 3000) {
            assertThat(game.getWinner(), is(Player.RED));
        }
    }

    @Test
    public void shouldBeRedCityAt1_1() {
        assertThat(game.getCityAt(new Position(1, 1)).getOwner(), is(Player.RED));
    }

    @Test
    public void shouldBeBlueCityAt4_1() {
        assertThat(game.getCityAt(new Position(4, 1)).getOwner(), is(Player.BLUE));
    }

    @Test
    public void shouldHaveMap() {
        assertNotNull(game.getMap());
    }

    @Test
    public void shouldHaveOceanAt1_0() {
        assertThat(game.getMap().getGameConstant(new Position(1, 0)), is("ocean"));
    }

    @Test
    public void shouldHaveMountainAt2_2() {
        assertThat(game.getMap().getGameConstant(new Position(2, 2)), is("mountain"));
    }

    @Test
    public void shouldGiveAllCitites6ProductionAfterEndedRound() {
        //end of turn
        game.endOfTurn();
        game.endOfTurn();
        //asserts that red city has 6 production after 1 round
        assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(6));
        game.endOfTurn();
        game.endOfTurn();
        //asserts that red city has 12 production after 2 rounds
        assertThat(game.getCityAt(new Position(1, 1)).getProduction(), is(12));
        //asserts that blue city has 12 production after 2 rounds
        assertThat(game.getCityAt(new Position(4, 1)).getProduction(), is(12));
    }

    @Test
    public void shouldHaveOneArcherOwnedByRedAt2_0() {
        assertThat(game.getMap().getGameUnit(new Position(2, 0)).getOwner(), is(Player.RED));
        assertThat(game.getMap().getGameUnit(new Position(2, 0)).getTypeString(), is("archer"));
    }

    @Test
    public void shouldHaveOneLegionOwnedByRedAt3_2() {
        assertThat(game.getMap().getGameUnit(new Position(3, 2)).getOwner(), is(Player.BLUE));
        assertThat(game.getMap().getGameUnit(new Position(3, 2)).getTypeString(), is("legion"));
    }

    @Test
    public void shouldHaveOneSettlerOwnedByRedAt4_3() {
        assertThat(game.getMap().getGameUnit(new Position(4, 3)).getOwner(), is(Player.BLUE));
        assertThat(game.getMap().getGameUnit(new Position(4, 3)).getTypeString(), is("settler"));
    }

    @Test
    public void shouldHaveHillAt0_1() {
        assertThat(game.getMap().getGameConstant(new Position(0, 1)), is("hills"));
    }

    @Test
    public void shouldLetUserMoveItsUnitsOnItsTurn() {
        assertThat(game.getUnitAt(new Position(2, 0)).getOwner(), is(game.getPlayerInTurn()));
        game.moveUnit(new Position(2, 0), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is("archer"));
    }
    @Test
    public void shouldBeIllegalForPlayersToMoveOtherPlayersUnits(){
        assertFalse(game.moveUnit(new Position(3,2),new Position(4,3)));
    }

    @Test
    public void shouldOnlyAllowUnitsToMoveAsFarAsMovecount() {
        assertThat(game.getUnitAt(new Position(2, 0)).getMoveCount(), is(1));
        game.moveUnit(new Position(2, 0), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(0));
        assertFalse(game.moveUnit(new Position(2, 1), new Position(2, 2)));
    }

    @Test
    public void shouldOnlyAllowPlayersToChooseALegalMovement() {
        assertTrue(game.moveUnit(new Position(2, 0), new Position(2, 1)));
        assertFalse(game.moveUnit(new Position(2, 1), new Position(7, 2)));

    }

    @Test
    public void shouldResetUnitsMovesAfterRoundEnded() {
        game.moveUnit(new Position(2, 0), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(2, 1)).getMoveCount(), is(1));
    }

    @Test
    public void shouldMakeTheAttackingUnitWinner() {
        game.moveUnit(new Position(2, 0), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is("archer"));
        game.endOfTurn();
        game.moveUnit(new Position(3, 2), new Position(2, 1));
        assertThat(game.getUnitAt(new Position(2, 1)).getTypeString(), is("legion"));
    }

    @Test
    public void shouldStartTheGameAt4000BC(){
        assertThat(game.getAge(),is(-4000));
    }

    @Test
    public void shouldAge100YearsEachRound() {
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(), is(-3900));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getAge(),is(-3800));
    }

    @Test
    public void shouldCostOneMovementForMovingUnit1TileRightOrLeft(){
        //This test takes the red unit at 2,0 and moves it to 3,0. Checks if that costs 1 movecount
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(2,0),new Position(3,0));
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(0));
        //Ends turn two times to shift turn back to red again
        game.endOfTurn();
        game.endOfTurn();
        //Tests the left direction instead of right.
        assertThat(game.getUnitAt(new Position(3,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(3,0),new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(0));
    }

    @Test
    public void shouldCostOneMovementForMovingUnit1TileUpOrDown(){
        //This test does the same as shouldCostOneMovementForMovingUnit1TileRightOrLeft(), but up and down instead.
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(2,0),new Position(2,1));
        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(1));
        game.moveUnit(new Position(2,1),new Position(2,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(0));
    }
    @Test
    public void shouldCostOneMovementForMovingUnit1TileCross(){
        //This test does the same as shouldCostOneMovementForMovingUnit1TileRightOrLeft(), but up and down instead.
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(2,0),new Position(3,1));
        assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(),is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(),is(1));
        game.moveUnit(new Position(3,1),new Position(4,2));
        assertThat(game.getUnitAt(new Position(4,2)).getMoveCount(),is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(4,2)).getMoveCount(),is(1));
        game.moveUnit(new Position(4,2),new Position(5,1));
        assertThat(game.getUnitAt(new Position(5,1)).getMoveCount(),is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(5,1)).getMoveCount(),is(1));
        game.moveUnit(new Position(5,1),new Position(4,0));
        assertThat(game.getUnitAt(new Position(4,0)).getMoveCount(),is(0));
        game.endOfTurn();
        game.endOfTurn();
        assertThat(game.getUnitAt(new Position(4,0)).getMoveCount(),is(1));
        game.moveUnit(new Position(4,0),new Position(3,1));
        assertThat(game.getUnitAt(new Position(3,1)).getMoveCount(),is(0));
    }
    @Test
    public void shouldNotBePossibleToMoveUnitOutsideOfMap(){
        //We start out by move outside the map and afterwards check if the unit at the start-position is still there
        //By checking the amount of moves left. If it is 1, then there is a unit and it has not been moved.
        game.moveUnit(new Position(2,0),new Position(2,-1));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
    }
    @Test
    public void shouldNotBePossibleToMoveOverAMountain(){
        //This tests tries to move the red archer to the mountain. If movecount is still 1 at the starting position
        //(the move moving from beside to onto mountain), then the archer has not moved, and the test succeed.
        game.moveUnit(new Position(2,0),new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1),new Position(2,2));
        assertThat(game.getUnitAt(new Position(2,1)).getMoveCount(),is(1));
    }
    @Test
    public void shouldNotBePossibleToMoveOverAnOcean(){
        //This tests tries to move the red archer into the ocean. If movecount is still 1 at the starting position
        //then the archer has not moved, and the test succeed.
        game.moveUnit(new Position(2,0),new Position(1,0));
        assertThat(game.getUnitAt(new Position(2,0)).getMoveCount(),is(1));
    }
    @Test
    public void shouldLetRedAttackAndDestroyBlue(){
        // in this example, red moves towards blues unit and when red comes close enough, the archer kills blue's unit.
        game.moveUnit(new Position(2,0),new Position(3,1));
        game.endOfTurn();
        game.endOfTurn();
        game.attackUnit(new Position(3,1),new Position(3,2));
        assertThat(game.getUnitAt(new Position(3,2)).getOwner(),is(Player.RED));
    }

}
