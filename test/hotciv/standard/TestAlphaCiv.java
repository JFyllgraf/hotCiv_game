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





}
