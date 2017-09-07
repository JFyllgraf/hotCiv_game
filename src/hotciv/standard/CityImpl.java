package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityImpl implements City {

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
        return 6;
    }

    @Override
    public String getWorkforceFocus() {
        return null;
    }
}
