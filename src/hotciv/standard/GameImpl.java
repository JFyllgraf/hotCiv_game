package hotciv.standard;

import hotciv.framework.*;

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

    //Players
    private Player redPlayer;
    private Player bluePlayer;
    private Player currentPlayer;
    private int age;

    private CityImpl redCity;
    private CityImpl blueCity;
    private MapCreator map;


    public GameImpl() {
        //We start the game out with two players. red and blue with the corresponding enum types.
        //We also set a currentPlayer object as red, because red always start.
        this.map = new MapCreator();

        //Units
        //puts all the starting units and constants into the map
        map.putGameUnit(new Position(2, 0), new UnitImpl(GameConstants.ARCHER, Player.RED));
        map.putGameUnit(new Position(3, 2), new UnitImpl(GameConstants.LEGION, Player.BLUE));
        map.putGameUnit(new Position(4, 3), new UnitImpl(GameConstants.SETTLER, Player.BLUE));
        //Constants
        map.putGameConstant(new Position(1, 0), GameConstants.OCEANS);
        map.putGameConstant(new Position(2, 2), GameConstants.MOUNTAINS);
        map.putGameConstant(new Position(0, 1), GameConstants.HILLS);

        this.age = -4000;

        this.redPlayer = Player.RED;
        this.bluePlayer = Player.BLUE;
        this.currentPlayer = this.redPlayer;

        this.redCity = new CityImpl(new Position(1, 1), redPlayer);
        this.blueCity = new CityImpl(new Position(4, 1), bluePlayer);
    }

    public MapCreator getMap() {
        return map;
    }

    public Tile getTileAt(Position p) {
        return null;
    }

    public Unit getUnitAt(Position p) {
        return map.getGameUnit(p);
    }

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

    public Player getPlayerInTurn() {
        return this.currentPlayer;
    }
    //Winner is always Player.RED at -3000BC
    public Player getWinner() {
        if (age == -3000) {
            return Player.RED;
        }
        return null;
    }

    public int getAge() {
        return age;
    }

    public boolean moveUnit(Position from, Position to) {
        //Moves unit from position from to position to.
        if ((map.getGameUnit(from) != null) && (map.getGameUnit(from).getOwner() == currentPlayer) && (map.getGameUnit(from).getMoveCount() > 0)) {
            if(isValidMove(from, to)){
                map.putGameUnit(to, map.getGameUnit(from));
                map.getGameUnit(to).moveUnit();
                map.deleteGameUnit(from);
                return true;
            }
        }
        return false;
    }

    public boolean isValidMove(Position from, Position to){
        if(isInsideMap(to)) {
            if (from.getRow() <= to.getRow() + 1 && from.getRow() >= to.getRow() - 1) {
                if (from.getColumn() <= to.getColumn() + 1 && from.getColumn() >= to.getColumn() - 1)
                    return true;
            }
        }
        return false;
    }

    //Checks wheather the player is about to move the object outside the map or not.
    public boolean isInsideMap(Position to){
        return(to.getColumn()<16 && to.getRow()<16 && to.getColumn()>=0 && to.getRow()>=0);
    }

    public void endOfTurn() {
        //Changes the player in turn and after ended round (All players turn) production is increased by 6, movecount is
        //reset and age is incremented by 100
        if (currentPlayer == Player.RED) {
            currentPlayer = this.bluePlayer;
        } else {
            //We increase production of all cities by 6 after last players turn.
            redCity.increaseProduction();
            blueCity.increaseProduction();
            map.resetAllMovesCount();
            currentPlayer = this.redPlayer;
            age+=100;
        }

    }

    public void changeWorkForceFocusInCityAt(Position p, String balance) {
    }

    public void changeProductionInCityAt(Position p, String unitType) {

    }

    public void performUnitActionAt(Position p) {
    }

    public int getPopulationAtCity(CityImpl city) {
        return city.getSize();
    }


}

