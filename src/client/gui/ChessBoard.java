package client.gui;

import general.models.Board;
import general.models.Clock;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Daniel on 2016/11/07.
 * initializeGui comes from http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel
 *      Authored by Andrew Thompson on Jan 13 2014 at 16:34 Last edited on Apr 13 2016 at 23:44
 */
public class ChessBoard extends JFrame{
    private Socket client;
    private OutputStream rOut;
    private PrintWriter out;
    private InputStream rIn;
    private BufferedReader in;
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private final JLabel message = new JLabel("Chess Champ is ready to play!");
    private Board gameBoard;
    private Clock gameClock;
    private ObjectOutputStream objectOut;
    private ObjectInputStream objectIn;

    public ChessBoard(Socket client) {
        try
        {
            this.client = client;
            rIn = new BufferedInputStream(client.getInputStream());
            rOut = client.getOutputStream();
            in = new BufferedReader(new InputStreamReader(rIn));
            out = new PrintWriter(client.getOutputStream());
            objectOut = new ObjectOutputStream(rOut);
            objectIn = new ObjectInputStream(rIn);
            getBoard();
            getClock();
            initializeGui();
        }catch (SocketException e) {
            JOptionPane.showMessageDialog(this, "Server connection lost");
        } catch(IOException e)
        {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final void initializeGui() {
        // set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("Resign")); // TODO - add functionality!
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        chessBoard = new JPanel(new GridLayout(0, 9));
        chessBoard.setBorder(new LineBorder(Color.BLACK));
        gui.add(chessBoard);

        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon.
                b.setIcon(gameBoard.getPiece(ii, jj).getIcon());
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        //fill the chess board
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (ii + 1),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    public final void redrawBoard()
    {
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                chessBoardSquares[ii][jj].setIcon(gameBoard.getPiece(ii, jj).getIcon());
            }
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

    public void getClock() throws IOException, ClassNotFoundException
    {
        gameClock = (Clock) objectIn.readObject();
    }

}
