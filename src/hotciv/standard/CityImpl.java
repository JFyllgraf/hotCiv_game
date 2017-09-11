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

    private String workforceFocus;


    public CityImpl(Position position, Player player){
        this.player = player;
        this.cityPosition = position;
        this.size = 1;
        production = 0;

        if (player == Player.RED){
            this.workforceFocus = GameConstants.ARCHER;
        }
        else {
            this.workforceFocus = GameConstants.SETTLER;
        }
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

    public void setProduction(int newValue){
        production=newValue;
    }

    @Override
    public String getWorkforceFocus() {
        return workforceFocus;
    }

    public void setWorkforceFocus(String unit){
        this.workforceFocus = String.valueOf(unit);
    }

}
