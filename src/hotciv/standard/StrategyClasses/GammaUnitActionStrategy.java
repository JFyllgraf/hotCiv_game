package hotciv.standard.StrategyClasses;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.GameImpl;
import hotciv.standard.StrategyInterfaces.UnitActionStrategy;

public class GammaUnitActionStrategy implements UnitActionStrategy {

    @Override
    public void performAction(GameImpl game, Position position) {
        if (game.getUnitAt(position).getTypeString() == GameConstants.SETTLER){
            game.putCityAt(position, game.getUnitAt(position).getOwner());
        }
    }

}
