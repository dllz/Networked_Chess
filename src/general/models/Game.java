package general.models;

import java.io.Serializable;

/**
 * Created by Daniel on 2016/11/07.
 */
public class Game implements Serializable
{
    private Board board;
    private Clock clock;

    public Game(Board board, Clock clock) {
        this.board = board;
        this.clock = clock;
    }

    public Board getBoard() {
        return board;
    }

    public Clock getClock() {
        return clock;
    }
}
