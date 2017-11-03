package hotciv.standard.visual;

import hotciv.view.CivDrawing;
import hotciv.view.GfxConstants;
import minidraw.standard.*;
import minidraw.framework.*;

import hotciv.framework.*;
import hotciv.standard.stub.*;

import java.awt.event.MouseEvent;

import static hotciv.view.GfxConstants.*;

/** Template code for exercise FRS 36.42.

   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class ShowEndOfTurn {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Click top shield to end the turn",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Click to shield to see Game's endOfTurn method being called.");

    // Replace the setting of the tool with your EndOfTurnTool implementation.
    editor.setTool( new EndOfTurnTool(editor,game) );
  }
}

class EndOfTurnTool extends NullTool{
  private Game game;
  private DrawingEditor editor;
  private CivDrawing drawing;
  public EndOfTurnTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
  }

  public void mouseDown(MouseEvent e, int x, int y) {
    if(editor.drawing().findFigure(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y).displayBox().contains(x,y)) {
      game.endOfTurn();
    }
  }

}
