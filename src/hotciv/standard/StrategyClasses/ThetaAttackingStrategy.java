package hotciv.standard.StrategyClasses;

import hotciv.framework.ExpansionGameConstants;
import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;

/**
 * Created by csdev on 10/12/17.
 */
public class ThetaAttackingStrategy implements AttackingStrategy {
    @Override
    public String attackUnit(Game game, Position from, Position to) {
        return null;
    }

    public int getAttackStrength(String unitType){
        switch (unitType) {
            case GameConstants.ARCHER:
                return 2;
            case GameConstants.LEGION:
                return 4;
            case GameConstants.SETTLER:
                return 0;
            case ExpansionGameConstants.GALLEY:
                return 8;
        }
        return 0;
    }

    public int getDefendStrength(String unitType){
        switch (unitType) {
            case GameConstants.ARCHER:
                return 3;
            case GameConstants.LEGION:
                return 2;
            case GameConstants.SETTLER:
                return 3;
            case ExpansionGameConstants.GALLEY:
                return 2;
        }
        return 0;
    }
}
