package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;
import hotciv.framework.Position;

/**
 * Created by csdev on 8/30/17.
 */
public class CityImpl implements City {

    private int population;
    private Position cityPos;
    private Player player;
    private int production;

    public CityImpl(Position position, Player player) {
        this.population = 1;
        this.cityPos = position;
        this.player = player;
    }

    @Override
    public Player getOwner() {
        return this.player;
    }

    public int getRow() {
        return cityPos.getRow();
    }

    public int getColumn() {
        return cityPos.getColumn();
    }

    @Override
    public int getSize() {
        return population;
    }

    @Override
    public int getProduction() {
        return production;
    }

    public void increaseProduction() {
        production += 6;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }

}
