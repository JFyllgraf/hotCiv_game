package hotciv.standard.stub;

import hotciv.framework.City;
import hotciv.framework.Player;

public class CityStub implements City {
  boolean redOwns = true;
  // a testing method just to make some
  // state changes
  public void  makeAChange() {
    redOwns = ! redOwns;
  }
  public Player getOwner() {
    return (redOwns ? Player.RED : Player.BLUE);
  }

  public int getSize() {
    return (redOwns ? 4 : 9);
  }
  public String getProduction() {
    return null;
  }
  public String getWorkforceFocus() {
    return null;
  }
}
