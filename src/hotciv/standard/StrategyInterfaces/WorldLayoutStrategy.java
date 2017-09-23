package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.TileImpl;

import java.util.HashMap;

public interface WorldLayoutStrategy {

    public HashMap setDefaultTiles();
    public HashMap setDefaultCities();
    public HashMap setDefaultUnits();

}
