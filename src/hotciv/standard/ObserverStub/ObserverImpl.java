package hotciv.standard.ObserverStub;

import hotciv.framework.Game;
import hotciv.framework.GameObserver;
import hotciv.framework.Player;
import hotciv.framework.Position;

import java.io.PrintStream;

public class ObserverImpl implements GameObserver {

    private Game game;

    public ObserverImpl(Game game){
        game = game;
    }

    @Override
    public void worldChangedAt(Position pos) {
        System.out.println("World has been changed at position: " + pos.toString());
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {
        System.out.println("Turn has ended at age " + age + " and next player is " + nextPlayer);
    }

    @Override
    public void tileFocusChangedAt(Position position) {
        System.out.println("World has been changed at position: " + position.toString());
    }
}
