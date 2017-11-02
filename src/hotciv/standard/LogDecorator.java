package hotciv.standard;

import hotciv.framework.*;

import java.io.PrintStream;

public class LogDecorator implements Game {

    private Game game;
    private PrintStream printStream;

    public LogDecorator(Game game, PrintStream printStream){
        this.game = game;
        this.printStream = printStream;
        System.setOut(new PrintStream(printStream));
    }

    @Override
    public Tile getTileAt(Position p) {
        return game.getTileAt(p);
    }

    @Override
    public Unit getUnitAt(Position p) {
        return game.getUnitAt(p);
    }

    @Override
    public City getCityAt(Position p) {
        return game.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public Player getWinner() {
        System.out.println("Winner is player: " + game.getWinner());
        return game.getWinner();
    }

    @Override
    public int getAge() {
        System.out.println("Game age is: " + game.getAge());
        return game.getAge();
    }

    @Override
    public boolean moveUnit(Position from, Position to) {
        System.out.println(game.getPlayerInTurn() + " has moved " + game.getUnitAt(from).getTypeString() + " from " + from.toString() + " to " + to.toString());
        return game.moveUnit(from, to);
    }

    @Override
    public void endOfTurn() {
        System.out.println(game.getPlayerInTurn() + " ends turn.");
        game.endOfTurn();
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance) {
        game.changeWorkForceFocusInCityAt(p, balance);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType) {
        System.out.println(game.getPlayerInTurn() + " changes production in city at " + p.toString() + " to " + unitType);
        game.changeProductionInCityAt(p, unitType);
    }

    @Override
    public void performUnitActionAt(Position p) {
        game.performUnitActionAt(p);
    }

    @Override
    public void addObserver(GameObserver observer) {

    }

    @Override
    public void setTileFocus(Position position) {

    }
}
