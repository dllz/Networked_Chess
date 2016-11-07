package server.networking;

import general.models.Board;
import general.models.Clock;

import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/02.
 */
public class GameNetworkHandler
{
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;
    private Socket connect;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private Board gameBoard;
    private Clock gameClock;
    private String client;

    /**
     * Default constructor
     *
     * @param ConnectionToClient Connected server socket
     */
    public GameNetworkHandler(Socket ConnectionToClient, String type) throws IOException {
        // Bind Streams
        out.println("GAME MATCHED");
        out.flush();
        connect = ConnectionToClient;
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        objectOut = new ObjectOutputStream(rOut);
        objectIn = new ObjectInputStream(rIn);
        System.out.println("Streams binded");
        out.println("TYPE " + type);
        out.flush();
    }

    /**
     * The code below is to fix problems with ImageIO.read not clearing all bytes of an image.
     */
    private static void clearInput(InputStream is) throws IOException {
        int extra = is.available();
        if (extra > 0) {
            byte[] buffer = new byte[extra];
            is.read(buffer);
            System.out.println(extra + " " + new String(buffer));
        }
    }

    public void sendBoard() throws IOException
    {
        out.println("SEND BOARD");
        out.flush();
        objectOut.writeObject(gameBoard);
        objectOut.flush();
    }

    public void getBoard() throws IOException, ClassNotFoundException
    {
        out.println("GET BOARD");
        out.flush();
        gameBoard = (Board) objectIn.readObject();
    }

    public void sendClock() throws IOException
    {
        out.println("SEND CLOCK");
        out.flush();
        objectOut.writeObject(gameClock);
        objectOut.flush();
    }

    public void setGameClock(Clock gameClock) {
        this.gameClock = gameClock;
    }

    public Board getGameBoard() {

        return gameBoard;
    }
    public void notifyTurn()
    {
        out.println("YOUR TURN");
        out.flush();
    }


    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }
}
