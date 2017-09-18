package hotciv.standard.StrategyClasses;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;
import hotciv.standard.UnitImpl;

public class GammaUnitActionStrategy implements UnitActionStrategy {

    @Override
    public void performAction(GameImpl game, Position position) {
        if (game.getUnitAt(position).getTypeString() == GameConstants.SETTLER){
            game.putCityAt(position, game.getUnitAt(position).getOwner());
            game.deleteUnit(position);
        }
        else if( game.getUnitAt(position).getTypeString() == GameConstants.ARCHER){
            if(game.getUnitAt(position).getMoveCount()==-1){
                ((UnitImpl)game.getUnitAt(position)).unfreezeUnit();
            }
            else {
                ((UnitImpl) game.getUnitAt(position)).doubleDefensiveStrength();
                ((UnitImpl) game.getUnitAt(position)).freezeUnit();
            }
        }

    }

}
