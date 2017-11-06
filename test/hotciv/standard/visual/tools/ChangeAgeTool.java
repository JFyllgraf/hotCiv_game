package hotciv.standard.visual.tools;

import hotciv.view.TextFigure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ChangeAgeTool extends NullTool {
  private TextFigure textFigure;
  public ChangeAgeTool(TextFigure tf) {
    textFigure = tf;
  }
  int count = 0;
  public void mouseDown(MouseEvent e, int x, int y) {
    count++;
    textFigure.setText( ""+(4000-count*100)+" BC" );
  }
}
