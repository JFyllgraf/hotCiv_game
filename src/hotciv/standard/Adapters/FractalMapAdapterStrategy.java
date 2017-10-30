package hotciv.standard.Adapters;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.standard.StrategyClasses.DeltaWorldLayoutStrategy;
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
    private DeltaWorldLayoutStrategy deltaWorldLayoutStrategy = new DeltaWorldLayoutStrategy();


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
                type = deltaWorldLayoutStrategy.tileChecker(tileChar, type);
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
