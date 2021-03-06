package hotciv.standard.StrategyClasses;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.standard.StrategyInterfaces.AttackingStrategy;
import hotciv.standard.StrategyInterfaces.DieDecisionStrategy;
import hotciv.standard.Utility;

/**
 * Created by csdev on 9/29/17.
 */
public class EpsilonAttackingStrategy implements AttackingStrategy{
    DieDecisionStrategy dieDecisionStrategy;
    int redAttackWinCounter;
    int blueAttackWinCounter;

    public EpsilonAttackingStrategy(DieDecisionStrategy dieDecisionStrategy){
        this.dieDecisionStrategy = dieDecisionStrategy;
        this.redAttackWinCounter = 0;
        this.blueAttackWinCounter = 0;
    }

    @Override
    public String attackUnit(Game game, Position from, Position to) {
        if(attackingUnitTotalAttackStrength(game,from)>defendingUnitTotalDefendingStrength(game,to)){
            return "Attacker";
        }
        return "Defender";
    }

    public int attackingUnitTotalAttackStrength(Game game, Position position){
        int rawUnitAttackStrength = getAttackStrength(game.getUnitAt(position).getTypeString());
        int boostByTerrain = Utility.getTerrainFactor(game,position);
        int boostByFriendlyUnits = Utility.getFriendlySupport(game, position, game.getUnitAt(position).getOwner());
        int randomDieFactor = dieDecisionStrategy.rollDie();
        return (rawUnitAttackStrength+boostByTerrain+boostByFriendlyUnits)*randomDieFactor;
    }

    public int defendingUnitTotalDefendingStrength(Game game, Position position){
        int rawUnitDefendStrength = getDefendStrength(game.getUnitAt(position).getTypeString());
        int boostByTerrain = Utility.getTerrainFactor(game,position);
        int boostByFriendlyUnits = Utility.getFriendlySupport(game, position, game.getUnitAt(position).getOwner());
        int randomDieFactor = dieDecisionStrategy.rollDie();
        return (rawUnitDefendStrength + boostByTerrain + boostByFriendlyUnits)*randomDieFactor;
    }

    public int getAttackStrength(String unitType){
        switch (unitType) {
            case GameConstants.ARCHER:
                return 2;
            case GameConstants.LEGION:
                return 4;
            case GameConstants.SETTLER:
                return 0;
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
        }
        return 0;
    }

}
