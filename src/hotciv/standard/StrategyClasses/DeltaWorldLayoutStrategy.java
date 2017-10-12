package hotciv.standard.StrategyClasses;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import hotciv.standard.TileImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public class DeltaWorldLayoutStrategy implements WorldLayoutStrategy {

    private int mapSize;

    private HashMap<Position, TileImpl> tileMap;
    private HashMap<Position, CityImpl> cityMap;
    private HashMap<Position, UnitImpl> unitMap;
    private Position redCityPos;
    private Position blueCityPos;

    public DeltaWorldLayoutStrategy(){
        this.mapSize = GameConstants.WORLDSIZE;
        tileMap = new HashMap<>();
        cityMap  = new HashMap<>();
        unitMap = new HashMap<>();
        redCityPos = new Position(8,12);
        blueCityPos = new Position(4,5);
    }

    @Override
    public HashMap setDefaultTiles() {
        String[] layout =
                new String[] {
                        "...ooMooooo.....",
                        "..ohhoooofffoo..",
                        ".oooooMooo...oo.",
                        ".ooMMMoooo..oooo",
                        "...ofooohhoooo..",
                        ".ofoofooooohhoo.",
                        "...ooo..........",
                        ".ooooo.ooohooM..",
                        ".ooooo.oohooof..",
                        "offfoooo.offoooo",
                        "oooooooo...ooooo",
                        ".ooMMMoooo......",
                        "..ooooooffoooo..",
                        "....ooooooooo...",
                        "..ooohhoo.......",
                        ".....ooooooooo..",
                };
        // Conversion...
        String line;
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            line = layout[r];
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = line.charAt(c);
                String type = "error";
                if ( tileChar == '.' ) { type = GameConstants.OCEANS; }
                if ( tileChar == 'o' ) { type = GameConstants.PLAINS; }
                if ( tileChar == 'M' ) { type = GameConstants.MOUNTAINS; }
                if ( tileChar == 'f' ) { type = GameConstants.FOREST; }
                if ( tileChar == 'h' ) { type = GameConstants.HILLS; }
                Position p = new Position(r,c);
                tileMap.put( p, new TileImpl(type));
            }
        }
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
