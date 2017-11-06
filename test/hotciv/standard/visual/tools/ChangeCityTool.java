package hotciv.standard.visual.tools;

import hotciv.standard.stub.CityStub;
import hotciv.view.CityFigure;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class ChangeCityTool extends NullTool {
  private CityStub city;
  private CityFigure cityFigure;
  public ChangeCityTool(CityStub c, CityFigure cf) {
    city = c;
    cityFigure = cf;
  }
  public void mouseDown(MouseEvent e, int x, int y) {
    city.makeAChange();
    cityFigure.changed();
  }
}
