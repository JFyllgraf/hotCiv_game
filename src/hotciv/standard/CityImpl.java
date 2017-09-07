package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {
    public int production;

    public CityImpl(){
        production = 0;
    }
    @Override
    public Player getOwner() {
        return Player.RED;
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
