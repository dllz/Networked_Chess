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
    public GameNetworkHandler(Socket ConnectionToClient) throws IOException {
        // Bind Streams

        connect = ConnectionToClient;
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        objectOut = new ObjectOutputStream(rOut);
        objectIn = new ObjectInputStream(rIn);
        System.out.println("Streams binded");

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
        objectOut.writeObject(gameBoard);
        objectOut.flush();
    }

    public void getBoard() throws IOException, ClassNotFoundException
    {
        gameBoard = (Board) objectIn.readObject();
    }

    public void sendClock() throws IOException, ClassNotFoundException
    {
        objectOut.writeObject(gameClock);
        objectOut.flush();
    }
}
