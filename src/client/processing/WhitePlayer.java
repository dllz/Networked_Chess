package client.processing;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import general.models.Board;
import general.models.Clock;

import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/02.
 */
public class WhitePlayer {

    private final String HOST = "localhost";
    private final int PORT = 2015;
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;
    private Board gameBoard;
    private Clock gameClock;

    public WhitePlayer(String user, String opponent)  throws IOException, ClassNotFoundException
    {
        Socket connect = new Socket(HOST, PORT);
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        objectOut = new ObjectOutputStream(rOut);
        objectIn = new ObjectInputStream(rIn);
        sendMessage("NewGame#White#" + user + "#Black#" + opponent);
        updateBoard();
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
        sendMessage("Move#White");
        objectOut.writeObject(gameBoard);
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
