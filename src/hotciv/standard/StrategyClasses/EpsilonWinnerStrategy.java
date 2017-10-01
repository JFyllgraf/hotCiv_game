package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;
import hotciv.standard.StrategyInterfaces.DieDecisionStrategy;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;

/**
 * Created by csdev on 10/1/17.
 */
public class EpsilonWinnerStrategy implements WinnerStrategy {

    public EpsilonWinnerStrategy(){
    }

    @Override
    public Player getWinner(Game game, HashMap<Position, CityImpl> map) {
        return ((GameImpl)game).getAttackWinCounter();
    }

}
