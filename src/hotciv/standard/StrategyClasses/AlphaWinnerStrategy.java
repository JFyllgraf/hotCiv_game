package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;

public class AlphaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(GameImpl game, HashMap<Position, CityImpl> map) {
        if (game.getAge() == -3000){
            return Player.RED;
        }
        return null;
    }

    @Override
    public void updateUnitAttackCounter(GameImpl game, Player player){

    }

}
