package hotciv.standard;

import hotciv.framework.*;

import java.util.HashMap;

/**
 * Skeleton implementation of HotCiv.
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

public class GameImpl implements Game {

    private Player currentPlayer;

    private CityImpl redCity;
    private CityImpl blueCity;

    private int age;

    private World world;

    public GameImpl(){
        this.world = new World();

        currentPlayer=Player.RED;

        this.redCity = new CityImpl(new Position(1, 1), Player.RED);
        this.blueCity = new CityImpl(new Position(4, 1), Player.BLUE);

        this.age = -4000;
    }

    @Override
    public Tile getTileAt(Position p) {
        return world.getTile(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return world.getUnit(p);
    }

    @Override
    public City getCityAt(Position p) {
        if (p.getRow() == redCity.getRow() && p.getColumn() == redCity.getColumn()) {
            return redCity;
        }
        if (p.getRow() == blueCity.getRow() && p.getColumn() == blueCity.getColumn()) {
            return blueCity;
        } else {
            return null;
        }
    }

    @Override
    public Player getPlayerInTurn() {
        return currentPlayer;
    }

    @Override
    public Player getWinner() {
        return Player.RED;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if(onlyMovePlayersOwnUnits(from)) {
            unitMap.put(to, new UnitImpl(getUnitAt(from).getTypeString(), getUnitAt(from).getOwner()));
            deleteUnit(from);
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public void endOfTurn() {
        if(currentPlayer == Player.RED){
            currentPlayer = Player.BLUE;
        }
        else if(currentPlayer == Player.BLUE){
            currentPlayer = Player.RED;
            this.age += 100;
            this.redCity.production+=6;
        }
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {

    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {

    }

    @Override
    public void performUnitActionAt(Position p) {

    }


    public boolean deleteUnit(Position from) {
        if (unitMap.get(from) != null) {
            unitMap.remove(from);
            return true;
        } else {
            return false;
        }
    }

}

