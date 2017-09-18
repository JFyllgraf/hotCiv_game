package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;

import java.util.HashMap;

public interface WinnerStrategy {

    public Player getWinner(Game game, HashMap<Position, CityImpl> map);

}
