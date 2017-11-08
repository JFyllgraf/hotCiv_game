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

import java.awt.*;
import java.awt.event.MouseEvent;

public class ShowCompositionTool extends NullTool {
  private Game game;
  private Drawing drawing;
  private DrawingEditor editor;
  private int y;
  private int x;
  private int startingPosX, startingPosY;
  private Figure unitOnMove;
  private ChangeAgeTool changeAgeTool;
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
    changeAgeTool = new ChangeAgeTool(textFigure);
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
    boolean doesFigureExist = editor.drawing().findFigure(x, y) != null;
    if(doesFigureExist && editor.drawing().findFigure(x, y).displayBox().contains(GfxConstants.TURN_SHIELD_X, GfxConstants.TURN_SHIELD_Y)) {
      endOfTurnTool.mouseDown(e, x, y);
    }
    else if(doesFigureExist && editor.drawing().findFigure(x, y).displayBox().contains(GfxConstants.AGE_TEXT_X, GfxConstants.AGE_TEXT_Y)){
      changeAgeTool.mouseDown(e,x,y);
    }
    else {
      setFocusTool.mouseDown(e, x, y);
      moveTool.mouseDown(e, x, y);
      showActionTool.mouseDown(e, x, y);
      System.out.println(game.getPlayerInTurn());
      System.out.println(game.getUnitAt(new Position(x,y)));
    }

    if(e.isShiftDown() && game.getCityAt(coordinateToPos(x,y)) != null){
      CityStub city = (CityStub) game.getCityAt(coordinateToPos(x,y));
      cityFigure = (CityFigure) drawing.findFigure(x,y);
      changeCityTool = new ChangeCityTool(city,cityFigure);
      changeCityTool.mouseDown(e,x,y);
      }
    }

  @Override public void mouseDrag(MouseEvent e, int x, int y) {
    moveTool.mouseDrag(e,x,y);
  }

  @Override public void mouseUp(MouseEvent e, int x, int y) {
    moveTool.mouseUp(e,x,y);
  }
}
