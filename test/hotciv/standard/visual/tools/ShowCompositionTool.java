package hotciv.standard.visual.tools;

import hotciv.framework.Game;
import hotciv.framework.GameConstants;
import hotciv.framework.Position;
import hotciv.standard.stub.CityStub;
import hotciv.view.CityFigure;
import hotciv.view.GfxConstants;
import hotciv.view.TextFigure;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ShowCompositionTool extends NullTool {
  private Game game;
  private Drawing drawing;
  private DrawingEditor editor;
  private int y;
  private int x;
  private int startingPosX, startingPosY;
  private Figure unitOnMove;
  private EndOfTurnTool endOfTurnTool;
  private SetFocusTool setFocusTool;
  private MoveTool moveTool;
  private ShowActionTool showActionTool;
  private ChangeCityTool changeCityTool;
  private TextFigure textFigure;
  private CityFigure cityFigure;

  public ShowCompositionTool(DrawingEditor editor, Game game) {
    endOfTurnTool = new EndOfTurnTool(editor,game);
    setFocusTool = new SetFocusTool(editor,game);
    moveTool = new MoveTool(editor,game);
    showActionTool = new ShowActionTool(editor,game);
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
    endOfTurnTool.mouseDown(e,x,y);
    setFocusTool.mouseDown(e,x,y);
    moveTool.mouseDown(e,x,y);
    showActionTool.mouseDown(e,x,y);

    if(e.isShiftDown() && game.getCityAt(coordinateToPos(x,y)) != null){
      CityStub city = (CityStub) game.getCityAt(coordinateToPos(x,y));
      cityFigure = (CityFigure) drawing.findFigure(x,y);
      changeCityTool = new ChangeCityTool(city,cityFigure);
      changeCityTool.mouseDown(e,x,y);
      }
    }

  @Override public void mouseDrag(MouseEvent e, int x, int y) {
    endOfTurnTool.mouseDrag(e,x,y);
    setFocusTool.mouseDrag(e,x,y);
    moveTool.mouseDrag(e,x,y);
    showActionTool.mouseDrag(e,x,y);
  }

  @Override public void mouseUp(MouseEvent e, int x, int y) {
    endOfTurnTool.mouseUp(e,x,y);
    setFocusTool.mouseUp(e,x,y);
    moveTool.mouseUp(e,x,y);
    showActionTool.mouseUp(e,x,y);
  }
}
