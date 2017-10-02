package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.CityImpl;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.WinnerStrategy;

import java.util.HashMap;

public class AlternatingWinnerStrategy implements WinnerStrategy {

    private WinnerStrategy betaWinnerStrategy, epsilonWinnerStrategy, currentState;


    public AlternatingWinnerStrategy(WinnerStrategy betaWinnerStrategy, WinnerStrategy epsilonWinnerStrategy){
        this.betaWinnerStrategy = betaWinnerStrategy;
        this.epsilonWinnerStrategy = epsilonWinnerStrategy;
        currentState = null;
    }

    @Override
    public Player getWinner(Game game, HashMap<Position, CityImpl> map) {
        if (((GameImpl)game).getGameRounds() <= 20){
            currentState = betaWinnerStrategy;
        } else {
            currentState = epsilonWinnerStrategy;
        }
        return currentState.getWinner(game, map);
    }

}
