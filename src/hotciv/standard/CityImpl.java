package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City {
    public int production;
    private Position cityPosition;
    private Player player;
    public CityImpl(Position position, Player player){
        this.player = player;
        this.cityPosition = position;
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
        return 0;
    }

    @Override
    public int getProduction() {
        return production;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
