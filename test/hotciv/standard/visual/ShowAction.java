package hotciv.standard.visual;

import hotciv.view.GfxConstants;
import minidraw.standard.*;
import minidraw.framework.*;

import hotciv.framework.*;
import hotciv.standard.stub.*;

import java.awt.event.MouseEvent;

/** Template code for exercise FRS 36.43.

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
public class ShowAction {
  
  public static void main(String[] args) {
    Game game = new StubGame2();

    DrawingEditor editor = 
      new MiniDrawApplication( "Shift-Click unit to invoke its action",  
                               new HotCivFactory4(game) );
    editor.open();
    editor.showStatus("Shift-Click on unit to see Game's performAction method being called.");

    // Replace the setting of the tool with your ActionTool implementation.
    editor.setTool( new ShowActionTool(editor,game) );
  }
}

class ShowActionTool extends NullTool{
  private Game game;
  private Drawing drawing;
  private DrawingEditor editor;
  private int y;
  private int x;
  private int startingPosX, startingPosY;
  private Figure unitOnMove;

  ShowActionTool(DrawingEditor editor, Game game) {
    this.game = game;
    this.editor = editor;
    drawing = editor.drawing();
  }

  //Helping methods//
  private boolean isInsideMapBorders(int x, int y) {
    Position position = coordinateToPos(x, y);
    return !(position.getRow() < 0 || position.getRow() >= GameConstants.WORLDSIZE
            || position.getColumn() < 0 || position.getColumn() >= GameConstants.WORLDSIZE);
  }

  private Position coordinateToPos(int x, int y) {
    int r = (y - GfxConstants.MAP_OFFSET_Y) / GfxConstants.TILESIZE;
    int c = (x - GfxConstants.MAP_OFFSET_X) / GfxConstants.TILESIZE;
    return new Position(r,c);
  }

  //behaviour methods//
  @Override public void mouseDown(MouseEvent e, int x, int y) {
    if (game.getUnitAt(coordinateToPos(x,y)) == null)
      return;
    unitOnMove = drawing.findFigure(x, y);
    this.y = y;
    startingPosY = y;
    this.x = x;
    startingPosX = x;
  }

  @Override public void mouseUp(MouseEvent e, int x, int y) {
    Position startingPos = coordinateToPos(startingPosX, startingPosY);
    Position unitPos = coordinateToPos(x, y);

  }
}
