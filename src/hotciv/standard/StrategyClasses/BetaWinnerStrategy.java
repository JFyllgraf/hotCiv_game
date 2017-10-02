package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;
import java.util.Map;

public class BetaWinnerStrategy implements WinnerStrategy {

    private int redCityCounter;
    private int blueCityCounter;

    @Override
    public Player getWinner(GameImpl game, HashMap<Position, CityImpl> map) {
        for(Map.Entry<Position, CityImpl> entry: map.entrySet()){
            if (entry.getValue().getOwner() == Player.RED){
              redCityCounter++;
            }if (entry.getValue().getOwner() == Player.BLUE){
                blueCityCounter++;
            }
        }
        if (redCityCounter == 0 && blueCityCounter != 0){
            return Player.BLUE;
        }if (blueCityCounter == 0 && redCityCounter != 0){
            return Player.RED;
        }
        return null;
    }

    @Override
    public void updateUnitAttackCounter(GameImpl game, Player player) {

    }
}
