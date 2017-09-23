package hotciv.standard.StrategyClasses;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class AlphaWorldLayoutStrategy implements WorldLayoutStrategy {
    private int mapSize;

    private HashMap<Position, TileImpl> tileMap;
    private HashMap<Position, CityImpl> cityMap;
    private HashMap<Position, UnitImpl> unitMap;
    private Position redCityPos;
    private Position blueCityPos;

    public AlphaWorldLayoutStrategy(){
        this.mapSize = GameConstants.WORLDSIZE;
        tileMap = new HashMap<>();
        cityMap  = new HashMap<>();
        unitMap = new HashMap<>();
        redCityPos = new Position(1,1);
        blueCityPos = new Position(4,1);
    }

    @Override
    public HashMap setDefaultTiles() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
        tileMap.put(new Position(1,0),new TileImpl(GameConstants.OCEANS));
        tileMap.put(new Position(0,1),new TileImpl(GameConstants.HILLS));
        tileMap.put(new Position(2,2),new TileImpl(GameConstants.MOUNTAINS));
        return tileMap;
    }

    public HashMap setDefaultCities() {
        cityMap.put(redCityPos, new CityImpl(redCityPos, Player.RED));
        cityMap.put(blueCityPos, new CityImpl(blueCityPos,Player.BLUE));
        return cityMap;
    }

    public HashMap setDefaultUnits(){
        unitMap.put(new Position(3,2),new UnitImpl(GameConstants.LEGION,Player.BLUE));
        unitMap.put(new Position(4,3),new UnitImpl(GameConstants.SETTLER,Player.RED));
        unitMap.put(new Position(2,0),new UnitImpl(GameConstants.ARCHER, Player.RED));
        return unitMap;
    }

}
