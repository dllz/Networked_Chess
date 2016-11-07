package server.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/02.
 */
public class TCPForwarder extends Thread
{
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;
    private Socket connect;

    public TCPForwarder(Socket connect) throws IOException
    {
        this.connect = connect;
        rIn = new BufferedInputStream(connect.getInputStream());
        rOut = connect.getOutputStream();
        in = new BufferedReader(new InputStreamReader(rIn));
        out = new PrintWriter(rOut);
        System.out.println("Streams binded");
        out.println("GET PLAYERS");
        out.flush();
        String line = in.readLine();
        String[] splits = line.split("\\s");
        GameSetUp.addPlayer(connect, splits[0], splits[1]);
        out.println("WAITING");
        out.flush();
    }
}
