package hotciv.standard;
import hotciv.framework.*;
import hotciv.standard.GameFactory.GameFactoryClasses.AlphaGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.EpsilonGameFactory;
import hotciv.standard.GameFactory.GameFactoryClasses.EpsilonGameFactoryFixedDice;
import hotciv.standard.GameFactory.GameFactoryInterfaces.GameFactory;
import hotciv.standard.StrategyClasses.*;
import hotciv.standard.StrategyInterfaces.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
/**
 * Created by csdev on 9/29/17.
 */
public class TestEpsilonAttackingStrategy {
    private GameImpl game;
    private Game gameForTestStub;
    private WinnerStrategy winnerStrategy;
    private UnitActionStrategy unitActionStrategy;
    private WorldLayoutStrategy worldLayoutStrategy;
    private EpsilonAttackingStrategy epsilonAttackingStrategy;
    private GameFactory epsilonMaker;
    DieDecisionStrategy dieDecisionStrategy;


    @Before
    public void setup(){
        epsilonMaker = new EpsilonGameFactoryFixedDice();

        game = new GameImpl(epsilonMaker);
        gameForTestStub = new GameStubForBattleTesting();

        dieDecisionStrategy = new FixedDieDecisionStrategy(6);
    }

    private void advanceRound(){
        game.endOfTurn();
        game.endOfTurn();
    }


    @Test
    public void shouldReturn6WithFixedDieValue(){
        assertThat(dieDecisionStrategy.rollDie(), is(6));
    }


    //UNIT TESTING

    @Test
    public void shouldReturn18AsAttackStrengthWhenArcherStandsOnPlainWithNoTeamUnitsAroundWithFixed6Dice(){
        assertThat(((EpsilonAttackingStrategy)(epsilonMaker.attackingStrategy())).attackingUnitTotalAttackStrength(gameForTestStub, new Position(5,5)),is(18));
    }

    @Test
    public void shouldReturn24AsAttackStrengthWhenArcherStandsInForrestWithFixed6Dice(){
        assertThat(((EpsilonAttackingStrategy)(epsilonMaker.attackingStrategy())).attackingUnitTotalAttackStrength(gameForTestStub,new Position(1,2)),is(24));
    }

    @Test
    public void shouldReturn24AsAttackStrengthWhenArcherHasOneTeamUnitAroundWithFixed6Dice(){
        assertThat(((EpsilonAttackingStrategy)(epsilonMaker.attackingStrategy())).attackingUnitTotalAttackStrength(gameForTestStub,new Position(3,2)),is(24));
    }

    @Test
    public void shouldReturn24AsDefenseStrengthWhenArcherOnPlainWithNoTeamUnitsAroundWithFixed6Dice(){
        assertThat(((EpsilonAttackingStrategy)(epsilonMaker.attackingStrategy())).defendingUnitTotalDefendingStrength(gameForTestStub, new Position(5,5)),is(24));
    }
    @Test
    public void shouldReturn36AsAttackStrengthWhenArcherOnForrestWith1TeamUnitAroundWithFixed6Dice(){
        assertThat(((EpsilonAttackingStrategy)(epsilonMaker.attackingStrategy())).defendingUnitTotalDefendingStrength(gameForTestStub, new Position(8,8)),is(36));
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
        public int getDefensiveStrength() {
            if (type == GameConstants.ARCHER){
                return 3;
            } if (type == GameConstants.LEGION){
                return 2;
            } if (type == GameConstants.SETTLER){
                return 3;
            } return 0;
        }
        public int getAttackingStrength() {
            if (type == GameConstants.ARCHER){
                return 2;
            } if (type == GameConstants.SETTLER){
                return 4;
            } return 0;
        }

        public String getTypeString() { return type; }
        public Player getOwner() { return owner; }
        public int getMoveCount() { return 0; }

    }
    class GameStubForBattleTesting implements Game {
        public Tile getTileAt(Position p) {
            if ( p.getRow() == 1 && p.getColumn() == 2 ||
                 p.getRow() == 8 && p.getColumn() == 8) {
                return new StubTile(GameConstants.FOREST, 1, 2);
            }
            return new StubTile(GameConstants.PLAINS, 3, 2);
        }
        public Unit getUnitAt(Position p) {
            if ( p.getRow() == 3 && p.getColumn() == 2 ||
                    p.getRow() == 2 && p.getColumn() == 2 ||
                    p.getRow() == 5 && p.getColumn() == 5  ||
                    p.getRow() == 8 && p.getColumn() == 8 ||
                    p.getRow() == 8 && p.getColumn() == 9){
                return new StubUnit(GameConstants.ARCHER, Player.BLUE);
            }
            if ( p.getRow() == 1 && p.getColumn() == 2 ) {
                return new StubUnit(GameConstants.ARCHER, Player.RED);
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
        public boolean moveUnit(Position from, Position to) {return false;}
        public void performUnitActionAt( Position p ) {}
    }

}


