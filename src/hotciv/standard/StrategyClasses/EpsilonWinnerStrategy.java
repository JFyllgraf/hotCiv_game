package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;

/**
 * Created by csdev on 10/1/17.
 */
public class EpsilonWinnerStrategy implements WinnerStrategy {
    @Override
    public Player getWinner(Game game, HashMap<Position, CityImpl> map) {
        return null;
    }
}
