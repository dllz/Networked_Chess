package client.gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by Daniel on 2016/11/07.
 */
public class GameSetup extends JFrame
{
    private JLabel notice;

    private final String HOST = "localhost";
    private final int PORT = 7683;
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;

    public GameSetup() {
        setTitle("Game Setup");
        notice = new JLabel();
        String user = JOptionPane.showInputDialog("Please enter your username");
        String opp = JOptionPane.showInputDialog("Please enter your opponents username");
        Socket connect = null;
        try {
            connect = new Socket(HOST, PORT);
            notice.setText("Connected to server");
            rIn = new BufferedInputStream(connect.getInputStream());
            rOut = connect.getOutputStream();
            in = new BufferedReader(new InputStreamReader(rIn));
            out = new PrintWriter(connect.getOutputStream());
        } catch (IOException e) {
            notice.setText("Server connection failed");
        }

        boolean waiting = true;
        String line;
        while (waiting)
        {
            try {
                line = in.readLine();
                if(line.equals("GET PLAYERS"))
                {
                    out.println(user + " " + opp);
                    notice.setText("Sending to server");
                    out.flush();
                } else if(line.equals("WAITING"))
                {
                    notice.setText("Waiting for game match");
                } else if(line.equals("GAME MATCHED"))
                {
                    waiting = false;
                    ChessBoard game = new ChessBoard();
                }
            } catch (IOException e) {
                notice.setText("Server connection lost");
            }

        }
        add(notice, BorderLayout.CENTER);
    }

}
