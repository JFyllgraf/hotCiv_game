package hotciv.standard;

import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.EpsilonGameFactoryFixedDice;
import hotciv.standard.GameFactory.GameFactoryClasses.FractalGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.ZetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
/**
 * Created by csdev on 10/30/17.
 */
public class ManualFractalTest {

    private static GameImpl game;


    public static void main(String[] args){
        GameFactory fractalGameMaker = new FractalGameFactory();
        String[] layout = new String[]{};
        String layoutString = "";
        game = new GameImpl(fractalGameMaker);
        for ( int r = 0; r < GameConstants.WORLDSIZE; r++ ) {
            System.out.println(layoutString);
            layoutString = "";
            for ( int c = 0; c < GameConstants.WORLDSIZE; c++ ) {
                if (game.getTileAt(new Position(r,c)).getTypeString().equals(GameConstants.OCEANS)) {
                        layoutString = layoutString + ".";
                }
                if (game.getTileAt(new Position(r,c)).getTypeString().equals(GameConstants.PLAINS)) {
                        layoutString = layoutString + "o";

                }
                if (game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.MOUNTAINS) {
                        layoutString = layoutString + "M";
                }
                if (game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.HILLS) {
                        layoutString = layoutString + "h";
                }
                if (game.getTileAt(new Position(r,c)).getTypeString() == GameConstants.FOREST) {
                        layoutString = layoutString + "f";
                }

            }
        }
    }
}
