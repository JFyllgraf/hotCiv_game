package hotciv.standard.stub;

import hotciv.framework.*;
import hotciv.standard.CityImpl;

import java.util.*;


/** Test hotciv.standard.stub for game for hotciv.standard.visual testing of
 * minidraw based graphics.
 *
 * SWEA support code.
 *
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Department of Computer Science
     Aarhus University
   
   Please visit http://www.baerbak.com/ for further information.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

public class StubGame2 implements Game {

  // === Unit handling ===
  private Position pos_archer_red;
  private Position pos_legion_blue;
  private Position pos_settler_red;
  private Position pos_galley_red;
  private Position red_city_pos;
  private Unit red_archer;
  private City redCity;
  private boolean settlerPressed = false;
  private int year;
  public Unit getUnitAt(Position p) {
    if ( p.equals(pos_archer_red) ) {
      return red_archer;
    }
    if ( p.equals(pos_settler_red) && !settlerPressed) {
      return new StubUnit(GameConstants.SETTLER, Player.RED);
    }
    if ( p.equals(pos_legion_blue) ) {
      return new StubUnit( GameConstants.LEGION, Player.BLUE );
    }
    if ( p.equals(pos_galley_red) ) {
      return new StubUnit( ThetaConstants.GALLEY, Player.RED );
    }
    return null;
  }

  // Stub only allows moving red archer
  public boolean moveUnit( Position from, Position to ) { 
    System.out.println( "-- StubGame2 / moveUnit called: "+from+"->"+to );
    if(to.getRow() <= from.getRow()+1 && to.getRow() >= from.getRow()-1){
      if(to.getColumn() <= from.getColumn()+1 && to.getColumn() >= from.getColumn()-1){
        if ( from.equals(pos_archer_red) ) {
          pos_archer_red = to;
        }
      }
    }
    // notify our observer(s) about the changes on the tiles
    gameObserver.worldChangedAt(from);
    gameObserver.worldChangedAt(to);
    return true;
  }

  // === Turn handling ===
  private Player inTurn;
  public void endOfTurn() {
    System.out.println( "-- StubGame2 / endOfTurn called." );
    inTurn = (getPlayerInTurn() == Player.RED ?
              Player.BLUE : 
              Player.RED );
    // no age increments
    if(inTurn==Player.RED){
      year = year + 100;
    }
    gameObserver.turnEnds(inTurn, year);
    System.out.println(year);
  }
  public Player getPlayerInTurn() { return inTurn; }


  // === Observer handling ===
  protected GameObserver gameObserver;
  // observer list is only a single one...
  public void addObserver(GameObserver observer) {
    gameObserver = observer;
  } 

  public StubGame2() { 
    defineWorld(1); 
    // AlphaCiv configuration
    pos_archer_red = new Position( 2, 0);
    pos_legion_blue = new Position( 3, 2);
    pos_settler_red = new Position( 4, 3);
    pos_galley_red = new Position( 6, 4);
    red_city_pos = new Position(7,7);
    year = -4000;
    // the only one I need to store for this hotciv.standard.stub
    red_archer = new StubUnit( GameConstants.ARCHER, Player.RED );
    redCity = new CityStub();
    inTurn = Player.RED;
  }

  // A simple implementation to draw the map of DeltaCiv
  protected Map<Position,Tile> world;
  protected Map<Position, City> cityWorld;
  public Tile getTileAt( Position p ) { return world.get(p); }


  /** define the world.
   * @param worldType 1 gives one layout while all other
   * values provide a second world layout.
   */
  protected void defineWorld(int worldType) {
    world = new HashMap<Position, Tile>();
    for (int r = 0; r < GameConstants.WORLDSIZE; r++) {
      for (int c = 0; c < GameConstants.WORLDSIZE; c++) {
        Position p = new Position(r, c);
        world.put(p, new StubTile(GameConstants.PLAINS));
      }
    }
  }

  public City getCityAt( Position p ) {
    if(p.equals(red_city_pos)) {
      return redCity;
    }
    if(p.equals(pos_settler_red) && settlerPressed) {
      return new CityStub();
    }
    return null;
  }
  public Player getWinner() { return null; }
  public int getAge() { return 0; }  
  public void changeWorkForceFocusInCityAt( Position p, String balance ) {}
  public void changeProductionInCityAt( Position p, String unitType ) {}
  public void performUnitActionAt( Position p ) {
    System.out.println("Unit action performed at: " + p);
    if(getUnitAt(p).getTypeString() == GameConstants.SETTLER){
      settlerPressed = true;
      gameObserver.worldChangedAt(p);
    }
  }

  public void createCityAt(Position position){

  }

  public void setTileFocus(Position position) {
    System.out.println("-- StubGame2 / setTileFocus called.");
    gameObserver.tileFocusChangedAt(position);
  }

}
