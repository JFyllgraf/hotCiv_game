package hotciv.standard.StrategyClasses;

import hotciv.framework.Player;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

public class AlphaWinnerStrategy implements WinnerStrategy {

    @Override
    public Player getWinner(int age) {
        if (age == -3000){
            return Player.RED;
        }
        return null;
    }
}
