package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.StrategyClasses.AlphaAgeingStrategy;
import hotciv.standard.StrategyInterfaces.AgeingStrategy;
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
    //mapTile is a map that can contain tileTypes
    private HashMap<Position, TileImpl> tileMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitMap = new HashMap<>();
    private HashMap<Position, CityImpl> cityMap = new HashMap<>();

    private AgeingStrategy ageingStrategy;

    private CityImpl redCity;
    private CityImpl blueCity;
    private Position redCityPos;
    private Position blueCityPos;
    private UnitImpl redPlayerWorkForce;
    private UnitImpl bluePlayerWorkForce;

    private int age;

    public GameImpl(){
        ageingStrategy = new AlphaAgeingStrategy();

        redCityPos = new Position(1,1);
        blueCityPos = new Position(4,1);

        this.redPlayerWorkForce = new UnitImpl(GameConstants.ARCHER,Player.RED);
        this.bluePlayerWorkForce = new UnitImpl(GameConstants.SETTLER,Player.BLUE);

        cityMap.put(redCityPos, new CityImpl(redCityPos,Player.RED));
        cityMap.put(blueCityPos, new CityImpl(blueCityPos,Player.BLUE));

        setDefaultMap();

        tileMap.put(new Position(1,0),new TileImpl(GameConstants.OCEANS));
        tileMap.put(new Position(0,1),new TileImpl(GameConstants.HILLS));
        tileMap.put(new Position(2,2),new TileImpl(GameConstants.MOUNTAINS));

        unitMap.put(new Position(3,2),new UnitImpl(GameConstants.LEGION,Player.BLUE));
        unitMap.put(new Position(4,3),new UnitImpl(GameConstants.SETTLER,Player.RED));
        unitMap.put(new Position(2,0),new UnitImpl(GameConstants.ARCHER, Player.RED));


        currentPlayer=Player.RED;
        this.redCity = new CityImpl(redCityPos, Player.RED);
        this.blueCity = new CityImpl(blueCityPos, Player.BLUE);
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
        if(age==-3000) {
            return Player.RED;
        }
        else {
            return null;
        }
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        if((to.getRow() < 16 && to.getRow() >= 0) && (to.getColumn() < 16 && to.getColumn() >= 0)){
            if(onlyMovePlayersOwnUnits(from) && onlyMoveIfMoveCountisgreaterthan0(from) && onlyMoveToLegalTiles(to) && MoveDistanceIsLegal(from, to)) {
                unitMap.put(to, new UnitImpl(getUnitAt(from).getTypeString(), getUnitAt(from).getOwner()));
                deleteUnit(from);
                unitMap.get(to).moveUnit();
                return true;
            }
        }
        return false;
    }

    private boolean onlyMoveIfMoveCountisgreaterthan0(Position from){
        return (unitMap.get(from).getMoveCount()>0);
    }

    private boolean MoveDistanceIsLegal(Position from, Position to){
        if(to.getRow() <= from.getRow()+1 && to.getRow() >= from.getRow()-1){
            if(to.getColumn() <= from.getColumn()+1 && to.getColumn() >= from.getColumn()-1){
                return true;
            }
        }
        return false;
    }

    private boolean onlyMovePlayersOwnUnits(Position from){
        return (unitMap.get(from).getOwner() == currentPlayer);
    }

    private boolean onlyMoveToLegalTiles(Position to){
        return !((tileMap.get(to).getTypeString().equals(GameConstants.OCEANS)) || (tileMap.get(to).getTypeString().equals(GameConstants.MOUNTAINS)));
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

            produceUnit(redCityPos,redPlayerWorkForce);
            produceUnit(blueCityPos,bluePlayerWorkForce);

            resetAllUnitsMovecount();
        }
    }

    private void produceUnitIfEnoughProduction(){
        if(Integer.valueOf(this.redCity.getProduction())>=getCost(redCity.getWorkforceFocus())){
            produceUnit(new Position(1,1),new UnitImpl(GameConstants.ARCHER,Player.RED));
            redCity.setProduction(Integer.valueOf(redCity.getProduction())-(getCost(redCity.getWorkforceFocus())));
        }
        if(Integer.valueOf(this.blueCity.getProduction())>=getCost(blueCity.getWorkforceFocus())){
            produceUnit(new Position(1,1),new UnitImpl(GameConstants.SETTLER,Player.BLUE));
            blueCity.setProduction(Integer.valueOf(blueCity.getProduction())-(getCost(blueCity.getWorkforceFocus())));
        }
    }

    private boolean enoughProduction(City city){
        if(Integer.valueOf(city.getProduction())>=getCost(city.getWorkforceFocus())){
            return true;
        }
        return false;

    }

    int getCost(String unitType){
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
            entry.getValue().resetMoveCount();
        }
    }

    private void increaseAllCitiesProduction(){
        for(Map.Entry<Position, CityImpl> entry: cityMap.entrySet()){
            entry.getValue().setProduction(Integer.valueOf(entry.getValue().getProduction())+6);
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
                tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
    }

    boolean deleteUnit(Position from) {
        if (unitMap.get(from) != null) {
            unitMap.remove(from);
            return true;
        } else {
            return false;
        }
    }

    boolean produceUnit(Position position, UnitImpl unit){
        if(hasBlueOrRedCity(position,unit) && enoughProduction(getCityAt(position))){
            unitMap.put(position, unit);
            ((CityImpl)getCityAt(position)).setProduction(Integer.valueOf(getCityAt(position).getProduction())-(getCost(getCityAt(position).getWorkforceFocus())));
            return true;
        }
        return false;
    }

    //Checks if position contains the coordinates of blue or red city and if it is owned by the proper owner
    boolean hasBlueOrRedCity(Position position, UnitImpl unit){
        if((position.getRow() == 1 && position.getColumn() == 1 && unit.getOwner()==Player.RED) || (position.getRow() == 4 && position.getColumn() == 1 && unit.getOwner()==Player.BLUE)){
            return true;
        }
        return false;
    }




}

