package hotciv.standard.visual.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.view.GfxConstants;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ShowActionTool extends NullTool {
  private Game game;
  private Drawing drawing;
  private DrawingEditor editor;
  private int y;
  private int x;
  private int startingPosX, startingPosY;
  private Figure unitOnMove;

  public ShowActionTool(DrawingEditor editor, Game game) {
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
    if(e.isShiftDown()) {
      if (game.getUnitAt(coordinateToPos(x, y)) == null)
        return;
      game.performUnitActionAt(coordinateToPos(x,y));
    }
  }

  @Override public void mouseUp(MouseEvent e, int x, int y) {
    Position startingPos = coordinateToPos(startingPosX, startingPosY);
    Position unitPos = coordinateToPos(x, y);

  }
}
