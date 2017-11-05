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
        System.out.println("World has been changed at postition: " + pos.toString());
    }

    @Override
    public void turnEnds(Player nextPlayer, int age) {

    }

    @Override
    public void tileFocusChangedAt(Position position) {

    }
}
