package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.StrategyClasses.AlphaAgeingStrategy;
import hotciv.standard.StrategyInterfaces.*;
//H
import java.util.HashMap;
import java.util.Map;

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
    private HashMap<Position, TileImpl> tileMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitMap = new HashMap<>();
    private HashMap<Position, CityImpl> cityMap = new HashMap<>();

    private AgeingStrategy ageingStrategy;
    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private AttackingStrategy attackingStrategy;

    private Position redCityPos;
    private Position blueCityPos;
    private UnitImpl redPlayerProductionFocus;
    private UnitImpl bluePlayerProductionFocus;

    private int age;

    public GameImpl(WinnerStrategy winnerStrategy, UnitActionStrategy unitActionStrategy, WorldLayoutStrategy worldLayoutStrategy, AttackingStrategy attackingStrategy){
        this.ageingStrategy = new AlphaAgeingStrategy();
        this.winnerStrategy = winnerStrategy;
        this.unitActionStrategy = unitActionStrategy;
        this.worldLayoutStrategy = worldLayoutStrategy;

        redCityPos = new Position(1,1);
        blueCityPos = new Position(4,1);

        this.redPlayerProductionFocus = new UnitImpl(GameConstants.ARCHER,Player.RED);
        this.bluePlayerProductionFocus = new UnitImpl(GameConstants.SETTLER,Player.BLUE);

        setDefaultMap();

        currentPlayer=Player.RED;
        this.age = -4000;
    }



    @Override
    public Tile getTileAt(Position p) {
        return tileMap.get(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return unitMap.get(p);
    }

    @Override
    public City getCityAt(Position p) {
        if(cityMap.get(p)!=null){
            return cityMap.get(p);
        }
        return null;

    }

    @Override
    public Player getPlayerInTurn() {
        return currentPlayer;
    }

    @Override
    public Player getWinner() {
        return winnerStrategy.getWinner(this, cityMap);
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if (!isPlayersOwnUnit(from))return false;
        if (!hasMoveCountLeft(from))return false;
        if (!isLegalTile(to))return false;
        if (!distanceIsLegal(from, to))return false;

        unitMap.put(to, new UnitImpl(getUnitAt(from).getTypeString(), getUnitAt(from).getOwner()));
        unitMap.remove(from);
        unitMap.get(to).moveUnit();

        if(movedToEnemyCity(to)){
            cityMap.put(to,new CityImpl(to,currentPlayer));
        }

        return true;
    }

    private boolean movedToEnemyCity(Position to){
        return (getCityAt(to) != null) && (getCityAt(to).getOwner() != currentPlayer) && (getCityAt(to).getOwner() != null);
    }

    private boolean hasMoveCountLeft(Position from){
        return (unitMap.get(from).getMoveCount()>0);
    }

    private boolean distanceIsLegal(Position from, Position to){
        if(to.getRow() <= from.getRow()+1 && to.getRow() >= from.getRow()-1){
            if(to.getColumn() <= from.getColumn()+1 && to.getColumn() >= from.getColumn()-1){
                return true;
            }
        }
        return false;
    }

    private boolean isPlayersOwnUnit(Position from){
        return (unitMap.get(from).getOwner() == currentPlayer);
    }

    private boolean isLegalTile(Position to){
        if((to.getRow() < mapSize && to.getRow() >= 0) && (to.getColumn() < mapSize && to.getColumn() >= 0)) {
            return !((tileMap.get(to).getTypeString().equals(GameConstants.OCEANS)) || (tileMap.get(to).getTypeString().equals(GameConstants.MOUNTAINS)));
        }
        return false;
    }

    @Override
    public void endOfTurn() {
        if(currentPlayer == Player.RED){
            currentPlayer = Player.BLUE;
        }
        else if(currentPlayer == Player.BLUE){
            currentPlayer = Player.RED;

            this.age = ageingStrategy.incrementAge(age);

           increaseAllCitiesProduction();

            produceUnit(redCityPos, redPlayerProductionFocus);
            produceUnit(blueCityPos, bluePlayerProductionFocus);

            resetAllUnitsMovecount();
        }
    }

    private boolean enoughProduction(Position position){
        //returns true if the citys treasury is higher than the cost of the unit.
        return ((CityImpl) getCityAt(position)).getTreasury() >= getCost(getCityAt(position).getProduction());
    }

    public int getCost(String unitType){
        switch (unitType) {
            case GameConstants.ARCHER:
                return 10;
            case GameConstants.LEGION:
                return 15;
            case GameConstants.SETTLER:
                return 30;
        }
        return 0;
    }

    private void resetAllUnitsMovecount(){
        for(Map.Entry<Position, UnitImpl> entry : unitMap.entrySet()){
            if(entry.getValue().getMoveCount() == 0){
                entry.getValue().setMoveCount();
            }
        }
    }

    private void increaseAllCitiesProduction(){
        for(Map.Entry<Position, CityImpl> entry: cityMap.entrySet()){
            entry.getValue().setTreasury(6);
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
        unitActionStrategy.performAction(this, p);
    }

    private void setDefaultMap() {
        this.cityMap = worldLayoutStrategy.setDefaultCities();
        this.tileMap = worldLayoutStrategy.setDefaultTiles();
        this.unitMap = worldLayoutStrategy.setDefaultUnits();
    }

    public void deleteUnit(Position from) {
        if (unitMap.get(from) != null) {
            unitMap.remove(from);
        }
    }

    public void produceUnit(Position position, UnitImpl unit){
        if(isAtCity(position,unit) && enoughProduction(position)){
            unitMap.put(position, unit);
            CityImpl city = (CityImpl)getCityAt(position);
            city.setTreasury(-getCost(city.getProduction()));
        }
    }

    //Checks if position contains the coordinates of blue or red city and if it is owned by the proper owner
    private boolean isAtCity(Position position, UnitImpl unit){
        return (position.getRow() == 1 && position.getColumn() == 1 && unit.getOwner() == Player.RED) || (position.getRow() == 4 && position.getColumn() == 1 && unit.getOwner() == Player.BLUE);
    }

    public void putCityAt(Position position, Player owner){
        cityMap.put(position, new CityImpl(position, owner));
    }


}

