package hotciv.standard.Adapters;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.StrategyInterfaces.WorldLayoutStrategy;
import hotciv.standard.TileImpl;
import thirdparty.ThirdPartyFractalGenerator;

import java.util.HashMap;

/**
 * Created by csdev on 10/30/17.
 */
public class FractalMapAdapterStrategy implements WorldLayoutStrategy {
    private ThirdPartyFractalGenerator thirdparty;
    private HashMap<Position, TileImpl> tileMap;

    public FractalMapAdapterStrategy(){
        thirdparty = new ThirdPartyFractalGenerator();
        tileMap = new HashMap<>();

    }
    @Override
    public HashMap setDefaultTiles() {
        for (int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                char tileChar = thirdparty.getLandscapeAt(r,c);
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

    @Override
    public HashMap setDefaultCities() {
        return null;
    }

    @Override
    public HashMap setDefaultUnits() {
        return null;
    }
}
