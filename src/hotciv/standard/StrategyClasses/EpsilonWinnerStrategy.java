package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Unit;
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

    private int redAttackWinCounter;
    private int blueAttackWinCounter;

    public EpsilonWinnerStrategy(){
        redAttackWinCounter = 0;
        blueAttackWinCounter = 0;
    }

    @Override
    public Player getWinner(GameImpl game, HashMap<Position, CityImpl> map) {
        if (redAttackWinCounter >= 3){
            return Player.RED;
        } if (blueAttackWinCounter >= 3){
            return Player.BLUE;
        }
        System.out.println("Red: " + redAttackWinCounter + "Blue: " + blueAttackWinCounter);
        return null;
    }

    @Override
    public void updateUnitAttackCounter(GameImpl game, Player player) {
        if(player.equals(Player.RED)){
            redAttackWinCounter++;
        }if(player.equals(Player.BLUE)){
            blueAttackWinCounter++;
        }
    }

}
