package hotciv.standard;

import hotciv.framework.City;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;

public class CityImpl implements City {
    private int treasury;
    private Position cityPosition;
    private Player player;
    private int size;

    private String productionFocus;


    public CityImpl(Position position, Player player){
        this.player = player;
        this.cityPosition = position;
        this.size = 1;
        this.treasury = 0;

        if (player == Player.RED){
            this.productionFocus = GameConstants.ARCHER;
        }
        else {
            this.productionFocus = GameConstants.SETTLER;
        }
    }

    @Override
    public Player getOwner() {
        return this.player;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getProduction() {
        return productionFocus;
    }

    public void setProduction(String newProductionFocus){
        productionFocus = newProductionFocus;
    }

    public int getTreasury(){
        return treasury;
    }

    public void setTreasury(int newTreasuryValue){
        treasury += newTreasuryValue;
    }

    @Override
    public String getWorkforceFocus() {
        return productionFocus;
    }

    public void setWorkforceFocus(String unit){
        this.productionFocus = String.valueOf(unit);
    }

}
