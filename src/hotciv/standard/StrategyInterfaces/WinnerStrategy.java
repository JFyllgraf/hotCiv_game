package hotciv.standard.StrategyInterfaces;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.UnitImpl;

import java.util.HashMap;

public interface WinnerStrategy {

    public Player getWinner(GameImpl game, HashMap<Position, CityImpl> map);

    public void updateUnitAttackCounter(GameImpl game, Player player);

}
