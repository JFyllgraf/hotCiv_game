package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;

public class ZetaCivAlternatingWinnerStrategy implements WinnerStrategy {

    private WinnerStrategy betaWinnerStrategy, epsilonWinnerStrategy, currentState;


    public ZetaCivAlternatingWinnerStrategy(WinnerStrategy betaWinnerStrategy, WinnerStrategy epsilonWinnerStrategy){
        this.betaWinnerStrategy = betaWinnerStrategy;
        this.epsilonWinnerStrategy = epsilonWinnerStrategy;
        currentState = null;
    }

    @Override
    public Player getWinner(GameImpl game, HashMap<Position, CityImpl> map) {
        return calculateStrategy(game).getWinner(game, map);
    }

    public WinnerStrategy calculateStrategy(GameImpl game){
        if (game.getGameRounds() <= 20){
            currentState = betaWinnerStrategy;
        } else {
            currentState = epsilonWinnerStrategy;
        }
        return currentState;
    }

    @Override
    public void updateUnitAttackCounter(GameImpl game, Player player) {
        calculateStrategy(game).updateUnitAttackCounter(game, player);
    }


}
