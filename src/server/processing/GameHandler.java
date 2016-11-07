package server.processing;

import general.models.Board;
import general.models.Clock;
import server.networking.BlackNetworkHandler;
import server.networking.WhiteNetworkHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;

/**
 * Created by Daniel on 2016/11/02.
 */
public class GameHandler implements Runnable
{
    private BlackNetworkHandler blackPlayer;
    private WhiteNetworkHandler whitePlayer;
    private int currentTurn = 0;
    private Board game;
    private Clock gameClock;
    private long whiteMove;
    private long blackMove;

    public GameHandler(Socket white, Socket black) throws IOException
    {
        System.out.println("Joining matches");
        this.whitePlayer = new WhiteNetworkHandler(white);
        this.blackPlayer = new BlackNetworkHandler(black);
        System.out.println("Sockets assigned");
        game = new Board();
        whitePlayer.setGameBoard(game);
        blackPlayer.setGameBoard(game);
        long seconds = System.currentTimeMillis() / 1000l;
        whiteMove = seconds;
        gameClock = new Clock(seconds, 20);
        System.out.println("Everything is intialised");
        whitePlayer.setGameClock(gameClock);
        blackPlayer.setGameClock(gameClock);
        whitePlayer.sendGame();
        blackPlayer.sendGame();
        System.out.println("Everything sent");
    }


    @Override
    public void run()
    {
        boolean running = true;
        while (running)
        {
            System.out.println("Game control");
            if (currentTurn == 0)
            {
                try {
                    whitePlayer.getBoard();
                    blackPlayer.setGameBoard(whitePlayer.getGameBoard());
                    long seconds = System.currentTimeMillis() / 1000l;
                    long time = seconds - whiteMove;
                    gameClock.setWhiteTime(gameClock.getWhiteTime() - time);
                    whitePlayer.setGameClock(gameClock);
                    blackPlayer.setGameClock(gameClock);
                    whitePlayer.sendGame();
                    blackPlayer.sendGame();
                    blackPlayer.notifyTurn();
                    blackMove = System.currentTimeMillis() / 1000l;
                    currentTurn = 1;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else if (currentTurn == 1)
            {
                try {
                    blackPlayer.getBoard();
                    whitePlayer.setGameBoard(blackPlayer.getGameBoard());
                    long seconds = System.currentTimeMillis() / 1000l;
                    long time = seconds - whiteMove;
                    gameClock.setWhiteTime(gameClock.getWhiteTime() - time);
                    whitePlayer.setGameClock(gameClock);
                    blackPlayer.setGameClock(gameClock);
                    whitePlayer.sendGame();
                    blackPlayer.sendGame();
                    whitePlayer.notifyTurn();
                    whiteMove = System.currentTimeMillis() / 1000l;
                    currentTurn = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
