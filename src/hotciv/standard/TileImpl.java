package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Tile;

/**
 * Created by csdev on 9/9/17.
 */
public class TileImpl implements Tile {
    private String tileType;

    public TileImpl(String gameconstant) {
        this.tileType = gameconstant;
        }

    @Override
    public String getTypeString() {
        return tileType;
    }
}
