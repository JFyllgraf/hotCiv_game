package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City {
    public int production;
    private Position cityPosition;
    private Player player;
    private int size;

    public CityImpl(Position position, Player player){
        this.player = player;
        this.cityPosition = position;
        this.size = 1;
        production = 0;
    }

    @Override
    public Player getOwner() {
        return this.player;
    }

    public int getRow() { return cityPosition.getRow(); }

    public int getColumn() {
        return cityPosition.getColumn();
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getProduction() {
        return String.valueOf(production);
    }

    @Override
    public String getWorkforceFocus() {
        return GameConstants.ARCHER;
    }

}
