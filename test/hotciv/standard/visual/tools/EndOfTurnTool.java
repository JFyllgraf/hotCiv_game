package hotciv.standard.visual.tools;

import hotciv.framework.Game;
import hotciv.view.CivDrawing;
import hotciv.view.GfxConstants;
import minidraw.framework.DrawingEditor;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class EndOfTurnTool extends NullTool {
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
