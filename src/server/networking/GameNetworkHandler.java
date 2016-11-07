package server.networking;

import general.models.Board;
import general.models.Clock;
import general.models.Game;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
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
        sendObject(new Game(gameBoard, gameClock));
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
        DatagramSocket server = new DatagramSocket();
        byte[] dataOut;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutput oo = new ObjectOutputStream(output);
        oo.writeObject(obj);
        oo.close();
        dataOut = output.toByteArray();
        System.out.println(output.size());
        DatagramPacket packOut = new DatagramPacket(dataOut, dataOut.length, connect.getInetAddress(), connect.getPort());
        server.send(packOut);
    }
    public Game getObject() throws IOException, ClassNotFoundException
    {
        DatagramSocket client = new DatagramSocket(7683);
        byte[] dataIn = new byte[1024];
        DatagramPacket packIn = new DatagramPacket(dataIn, dataIn.length);
        client.receive(packIn);
        try (ObjectInputStream iStream = new ObjectInputStream(new ByteArrayInputStream(packIn.getData()))) {
            return (Game) iStream.readObject();
        }
    }
}
