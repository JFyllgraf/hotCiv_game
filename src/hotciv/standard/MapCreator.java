package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by csdev on 8/31/17.
 */
public class MapCreator {
    private int mapSize = GameConstants.WORLDSIZE;
    private String plain = GameConstants.PLAINS;
    //map is a map that can contain units
    HashMap<Position, UnitImpl> mapUnit = new HashMap<Position, UnitImpl>();
    //mapTile is a map that can contain tileTypes
    HashMap<Position, String> mapComponent = new HashMap<Position, String>();

    public MapCreator() {
        setDefaultMap();
    }

    //This method is the method for creating the default map. It is a hashmap that is 16x16 and every position is
    //filled with the GameComponent: plain.
    public void setDefaultMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                mapComponent.put(new Position(i, j), plain);
            }
        }
    }

    public void putGameUnit(Position position, UnitImpl unit) {
        mapUnit.put(position, unit);
    }

    public void putGameConstant(Position position, String component) {
        mapComponent.put(position, component);
    }

    public UnitImpl getGameUnit(Position position) {
        return mapUnit.get(position);
    }

    public String getGameConstant(Position position) {
        return mapComponent.get(position);
    }

    public boolean deleteGameUnit(Position from) {
        if (mapUnit.get(from) != null) {
            mapUnit.remove(from);
            return true;
        } else {
            return false;
        }
    }

    public void resetAllMovesCount() {
        for (Map.Entry<Position, UnitImpl> entry : mapUnit.entrySet()) {
            entry.getValue().resetMoveCount();
        }
    }
}
