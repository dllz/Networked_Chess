package server.networking;

import general.models.Board;
import general.models.Clock;
import general.models.Game;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
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
    private Board gameBoard;
    private Clock gameClock;
    private ServerSocket data;
    private ObjectInputStream dataIn;
    private Socket cont;
    private ObjectOutputStream dataOut;

    /**
     * Default constructor
     *
     * @param ConnectionToClient Connected server socket
     */
    public GameNetworkHandler(Socket ConnectionToClient, String type) throws IOException {
        // Bind Streams
        System.out.println("Attempting to bind strams");
        connect = ConnectionToClient;
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        System.out.println("Streams binded");
        out.println("GAME MATCHED");
        out.flush();
        data = new ServerSocket(7684);
        System.out.println("Second socket made");
        cont = data.accept();
        System.out.println("Connection made");
        dataIn = new ObjectInputStream(cont.getInputStream());
        dataOut = new ObjectOutputStream(cont.getOutputStream());
        System.out.println("Second Streams binded");
        System.out.println("First command sent");
        out.println("TYPE " + type);
        out.flush();
        System.out.println("Second command sent");
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


    public Game getGame() throws IOException, ClassNotFoundException
    {
        out.println("GET GAME");
        out.flush();
        Game temp = getObject();
        return temp;
    }

    public void getBoard() throws IOException, ClassNotFoundException
    {
        gameBoard = getGame().getBoard();
    }

    public void sendGame() throws IOException
    {
        out.println("SEND GAME");
        out.flush();
        String temp = in.readLine();
        if (temp.equals("READY"))
        {
            sendObject(new Game(gameBoard,gameClock));
        }

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

    public void sendObject(Game obj) throws IOException
    {
        System.out.println("Sending data" + " " + System.currentTimeMillis());

        dataOut.writeObject(obj);
        dataOut.flush();
    }
    public Game getObject() throws IOException, ClassNotFoundException
    {
        Game res;
        res =  (Game) dataIn.readObject();
        return res;
    }
}
