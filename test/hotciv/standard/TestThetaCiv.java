package hotciv.standard;

import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.ThetaGameFactory;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.EpsilonAttackingStrategy;
import hotciv.standard.StrategyClasses.ThetaAttackingStrategy;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;


/**
 * Created by csdev on 10/12/17.
 */
public class TestThetaCiv {
    private ThetaAttackingStrategy thetaAttackingStrategy;
    private GameImpl game;
    private Game gameForTestStub;
    private GameFactory thetaMaker;

    @Before
    public void setup() {
        thetaMaker = new ThetaGameFactory();
        game = new GameImpl(thetaMaker);
        gameForTestStub = new GameStubForBattleTesting();
    }

    @Test
    public void shouldHave8AsAttack(){
        assertThat(((ThetaAttackingStrategy)(thetaMaker.attackingStrategy())).getAttackStrength((ExpansionGameConstants.GALLEY)),is(8));
    }

    @Test
    public void shouldHave2AsDefence(){
        assertThat(((ThetaAttackingStrategy)(thetaMaker.attackingStrategy())).getDefendStrength((ExpansionGameConstants.GALLEY)),is(2));
    }

    @Test
    public void shouldLetGalleyMoveOnWater(){
        assertThat(gameForTestStub.getUnitAt(new Position(3,2)).getTypeString(),is(ExpansionGameConstants.GALLEY));
        gameForTestStub.moveUnit(new Position(3,2),new Position(2,2));
        assertThat(gameForTestStub.getUnitAt(new Position(3,2)).getTypeString(),is(ExpansionGameConstants.GALLEY));
    }

    private void advanceRound() {
        game.endOfTurn();
        game.endOfTurn();
    }

    // ================================== TEST STUBS ===
    class StubTile implements Tile {
        private String type;
        public StubTile(String type, int r, int c) { this.type = type; }
        public String getTypeString() { return type; }
    }

    class StubUnit implements Unit {
        private String type; private Player owner;
        public StubUnit(String type, Player owner) {
            this.type = type; this.owner = owner;
        }
        public String getTypeString() { return type; }
        public Player getOwner() { return owner; }
        public int getMoveCount() { return 0; }
        public int getDefensiveStrength() { return 0; }
        public int getAttackingStrength() { return 0; }
    }
    class GameStubForBattleTesting implements Game {
        public Tile getTileAt(Position p) {
            if ( p.getRow() == 1 && p.getColumn() == 1 ||
                    p.getRow() == 8 && p.getColumn() == 8) {
                return new hotciv.standard.StubTile(GameConstants.PLAINS, 1, 1);
            }
            return new hotciv.standard.StubTile(GameConstants.OCEANS, 3, 2);
        }
        public Unit getUnitAt(Position p) {
            if ( p.getRow() == 3 && p.getColumn() == 2){
                return new hotciv.standard.StubUnit(ExpansionGameConstants.GALLEY, Player.RED);
            }
            if ( p.getRow() == 8 && p.getColumn() == 8 ) {
                return new hotciv.standard.StubUnit(GameConstants.ARCHER, Player.RED);
            }
            return null;
        }
        public City getCityAt(Position p) {
            if ( p.getRow() == 1 && p.getColumn() == 1 ) {
                return new City() {
                    public Player getOwner() { return Player.RED; }
                    public int getSize() { return 1; }
                    public String getProduction() {
                        return null;
                    }
                    public String getWorkforceFocus() {
                        return null;
                    }
                };
            }
            return null;
        }

        // the rest is unused test stub methods...
        public void changeProductionInCityAt(Position p, String unitType) {}
        public void changeWorkForceFocusInCityAt(Position p, String balance) {}
        public void endOfTurn() {}
        public Player getPlayerInTurn() {return null;}
        public Player getWinner() {return null;}
        public int getAge() { return 0; }
        public boolean moveUnit(Position from, Position to) {return true;}
        public void performUnitActionAt( Position p ) {}
    }


}
