package hotciv.standard.visual;

import hotciv.framework.Game;
import hotciv.standard.GameFactory.GameFactoryClasses.DeltaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.SemiGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.GameImpl;
import hotciv.standard.stub.StubGame2;
import hotciv.standard.visual.tools.SetFocusTool;
import hotciv.standard.visual.tools.ShowCompositionTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;

/**
 * Created by csdev on 11/6/17.
 */
public class ShowSemi {
    public static void main(String[] args) {
        GameFactory semiMaker = new SemiGameFactory();
        Game game = new GameImpl(semiMaker);

        DrawingEditor editor =
                new MiniDrawApplication( "Play the game - Red starts",
                        new HotCivFactory4(game) );
        editor.open();

        editor.showStatus("SemiCiv gamemode has started");
        editor.setTool( new ShowCompositionTool(editor,game) );
    }
}
