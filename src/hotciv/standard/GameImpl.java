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
    private int mapSize = GameConstants.WORLDSIZE;
    private String plain = GameConstants.PLAINS;
    //mapTile is a map that can contain tileTypes
    private HashMap<Position, TileImpl> mapComponent = new HashMap<>();

    private CityImpl redCity;

    private int age;

    public GameImpl(){
        setDefaultMap();
        currentPlayer=Player.RED;
        this.redCity = new CityImpl();
        this.age = -4000;
    }

    @Override
    public Tile getTileAt(Position p) {
        return mapComponent.get(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return null;
    }

    @Override
    public City getCityAt(Position p) {
        return redCity;
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
        return false;
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

    private void setDefaultMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapComponent.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
    }

}

