package client.networking;

import general.models.Board;
import general.models.Clock;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/02.
 */
public class BlackPlayer {
    private final String HOST = "localhost";
    private final int PORT = 7683;
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private Board gameBoard;
    private Clock gameClock;

    public BlackPlayer(Socket connetion)  throws IOException, ClassNotFoundException
    {
        Socket connect = connetion;
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        objectOut = new ObjectOutputStream(rOut);
        objectIn = new ObjectInputStream(rIn);
    }

    public void waitForOpp()
    {

    }

    public void updateBoard() throws IOException, ClassNotFoundException
    {
        sendMessage("GET#BOARD");
        gameBoard = (Board) objectIn.readObject();
        updateClock();
    }

    public void sendMove() throws IOException
    {
        sendMessage("Move#Black");
        objectOut.writeObject(gameBoard);
        objectOut.flush();
    }

    public void sendMessage(String message) {
        // Send text message
        System.out.printf("Sending" + message);
        out.println(message);
        out.flush();
    }

    public void updateClock() throws IOException, ClassNotFoundException
    {
        sendMessage("Clock#Update");
        gameClock = (Clock) objectIn.readObject();
    }

    public String getReply() throws IOException
    {
        String res = in.readLine();
        return res;
    }
}
