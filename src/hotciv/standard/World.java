package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.util.HashMap;

public class World {

    private HashMap<Position, TileImpl> tileMap = new HashMap<>();
    private HashMap<Position, UnitImpl> unitMap = new HashMap<>();

    private int mapSize = GameConstants.WORLDSIZE;

    public World(){
        setDefaultMap();
        tileMap.put(new Position(1,0),new TileImpl(GameConstants.OCEANS));
        tileMap.put(new Position(0,1),new TileImpl(GameConstants.HILLS));
        tileMap.put(new Position(2,2),new TileImpl(GameConstants.MOUNTAINS));

        unitMap.put(new Position(3,2),new UnitImpl(GameConstants.LEGION, Player.BLUE));
        unitMap.put(new Position(4,3),new UnitImpl(GameConstants.SETTLER,Player.RED));
        unitMap.put(new Position(2,0),new UnitImpl(GameConstants.ARCHER, Player.RED));
    }

    private void setDefaultMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                tileMap.put(new Position(i, j), new TileImpl(GameConstants.PLAINS));
            }
        }
    }

    public TileImpl getTile(Position position){
        tileMap.get(position);
    }
}
