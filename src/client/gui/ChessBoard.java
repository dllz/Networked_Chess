package client.gui;

import general.models.*;
import server.processing.GameHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

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
    private JLabel time;
    private int player; //white is zero black is one
    boolean waitForUser = true;

    public ChessBoard(Socket client) {
        try
        {
            this.client = client;
            rIn = new BufferedInputStream(client.getInputStream());
            rOut = client.getOutputStream();
            in = new BufferedReader(new InputStreamReader(rIn));
            out = new PrintWriter(client.getOutputStream());
            String line = in.readLine();
            if (line.equals("TYPE WHITE"))
            {
                player = 0;
            } else if(line.equals("TYPE BLACK"))
            {
                player = 1;
            }
            getGame();
            initializeGui();
            gameHandler();
        }catch (SocketException e) {
            JOptionPane.showMessageDialog(this, "Server connection lost");
        } catch(IOException e)
        {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void gameHandler() throws IOException, ClassNotFoundException {
        boolean resign = false;
        String line;

        while(!resign)
        {
            line = in.readLine();
            if (line.equals("YOUR TURN"))
            {
                setUserWait(true);
                while(waitForUser)
                {
                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < chessBoardSquares.length; i++) {
                                for (int j = 0; j < chessBoardSquares[i].length; j++) {
                                    if (e.getSource() == chessBoardSquares[i][j]) {
                                        final int  h = i;
                                        final int v = j;
                                        if(gameBoard.getPiece(h, v) != null)
                                        {
                                            final ChessPiece temp = gameBoard.getPiece(h, v);
                                            ActionListener listener = new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    for (int g = 0; g < chessBoardSquares.length; g++) {
                                                        for (int a = 0; a < chessBoardSquares[g].length; a++) {
                                                            if (e.getSource() == chessBoardSquares[g][a]) {
                                                                int hh = g;
                                                                int vv = a;
                                                                if(gameBoard.getPiece(hh, vv) != null)
                                                                {
                                                                    if (player == 0)
                                                                    {
                                                                        if (temp.getClass().equals(Knight.class) || temp.getClass().equals(Pawn.class) || temp.getClass().equals(King.class))
                                                                        {
                                                                            int res = gameBoard.moveWhiteSolid(getPieceType(temp.getClass()),getDir(h,v,hh,vv),temp.getPieceID());
                                                                            if (res != -1)
                                                                            {
                                                                                setUserWait(false);
                                                                            }else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "Illegal Move");
                                                                            }
                                                                        } else
                                                                        {
                                                                            int res = gameBoard.moveWhite(getPieceType(temp.getClass()),getDir(h,v,hh,vv),temp.getPieceID(), getLength(h,v,hh,vv));
                                                                            if (res != -1)
                                                                            {
                                                                                ChessPiece oveTo = gameBoard.getPiece(hh, vv);
                                                                                gameBoard.killPiece(hh,vv,oveTo.getPieceID());
                                                                                setUserWait(false);
                                                                            }else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "Illegal Move");
                                                                            }
                                                                        }
                                                                    } else if (player == 1)
                                                                    {
                                                                        if (temp.getClass().equals(Knight.class) || temp.getClass().equals(Pawn.class) || temp.getClass().equals(King.class))
                                                                        {
                                                                            int res = gameBoard.moveBlackSolid(getPieceType(temp.getClass()),getDir(h,v,hh,vv),temp.getPieceID());
                                                                            if (res != -1)
                                                                            {
                                                                                setUserWait(false);
                                                                            }else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "Illegal Move");
                                                                            }
                                                                        } else
                                                                        {
                                                                            int res = gameBoard.moveBlack(getPieceType(temp.getClass()),getDir(h,v,hh,vv),temp.getPieceID(), getLength(h,v,hh,vv));
                                                                            if (res != -1)
                                                                            {
                                                                                ChessPiece oveTo = gameBoard.getPiece(hh, vv);
                                                                                gameBoard.killPiece(hh,vv,oveTo.getPieceID());
                                                                                setUserWait(false);
                                                                            }else
                                                                            {
                                                                                JOptionPane.showMessageDialog(null, "Illegal Move");
                                                                            }
                                                                        }
                                                                    }
                                                                } else
                                                                {
                                                                    temp.setPos(vv, hh);
                                                                    setUserWait(false);
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            };
                                        }
                                    }
                                }
                            }
                        }
                    };
                }
            } else if (line.equals("SEND GAME"))
            {
                getGame();
                redrawBoard(player);
            } else if (line.equals("GET GAME"))
            {
                sendGame();
            } else if (line.equals("YOU LOSE"))
            {
                JOptionPane.showMessageDialog(this, "You lost");
                System.exit(1);
            }else if (line.equals("YOU WIN"))
            {
                JOptionPane.showMessageDialog(this, "You Won");
                System.exit(1);
            }
        }
    }

    private int getLength(int h, int v, int hh, int vv) {
        if ( h != hh)
        {
            return Math.abs(h - hh);
        }else
        {
            return Math.abs(v - vv);
        }
    }

    private ChessPiece.direction getDir(int h, int v, int hh, int vv)
    {
        ChessPiece.direction res = ChessPiece.direction.up;
        int changeV = vv - v;
        int changeH = hh - h;
        if (changeV < 0)
        {
            res = ChessPiece.direction.down;
        }
        if (changeV > 0)
        {
            res = ChessPiece.direction.up;
        }
        if (changeH < 0)
        {
            res = ChessPiece.direction.left;
        }
        if (changeH > 0)
        {
            res = ChessPiece.direction.right;
        }
        if (changeH > 0 && changeV > 0)
        {
             res = ChessPiece.direction.upright;
        }
        if (changeH > 0 && changeV < 0)
        {
            res = ChessPiece.direction.downright;
        }
        if (changeH < 0 && changeV < 0)
        {
            res = ChessPiece.direction.downleft;
        }
        if (changeH < 0 && changeV > 0)
        {
            res = ChessPiece.direction.upleft;
        }
        if (changeH > 0 && changeV > 0 && Math.abs(changeH) > Math.abs(changeV))
        {
            res = ChessPiece.direction.rightup;
        }
        if (changeH > 0 && changeV < 0 && Math.abs(changeH) > Math.abs(changeV))
        {
            res = ChessPiece.direction.rightdown;
        }
        if (changeH < 0 && changeV < 0 && Math.abs(changeH) > Math.abs(changeV))
        {
            res = ChessPiece.direction.leftdown;
        }
        if (changeH < 0 && changeV > 0 && Math.abs(changeH) > Math.abs(changeV))
        {
            res = ChessPiece.direction.leftup;
        }
        return res;
    }

    private ChessPiece.pieces getPieceType(Class<? extends ChessPiece> aClass) {
        if (aClass.equals(Bishop.class))
        {
            return ChessPiece.pieces.bishop;
        } else if (aClass.equals(King.class))
        {
            return ChessPiece.pieces.king;
        }else if (aClass.equals(Knight.class))
        {
            return ChessPiece.pieces.knight;
        }else if (aClass.equals(Pawn.class))
        {
            return ChessPiece.pieces.pawn;
        }else if (aClass.equals(Queen.class))
        {
            return ChessPiece.pieces.queen;
        }else if (aClass.equals(Rook.class))
        {
            return ChessPiece.pieces.rook;
        }else
        {
            return null;
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
        time = new JLabel();
        tools.add(time);
        time.setText("Time Left: " + gameClock.getWhiteLeft());

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

    public final void redrawBoard(int player)
    {
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                chessBoardSquares[ii][jj].setIcon(gameBoard.getPiece(ii, jj).getIcon());
            }
        }
        if (player == 0)
        {
            time.setText("Time Left: " + gameClock.getWhiteLeft());
        }else if (player == 1)
        {
            time.setText("Time Left: " + gameClock.getBlackLeft());
        }
    }

    public void getGame() throws IOException, ClassNotFoundException
    {
        Game temp = getObject();
        gameBoard = temp.getBoard();
        gameClock = temp.getClock();
    }


    public void sendGame() throws IOException
    {
        String temp = in.readLine();
        if (temp.equals("READY"))
        {
            sendObject(new Game(gameBoard,gameClock));
        }
    }


    private void setUserWait(boolean v)
    {
        waitForUser = v;
    }

    public void sendObject(Game obj) throws IOException
    {
        Socket temp = new Socket(client.getInetAddress(), client.getPort()+1);
        ObjectOutputStream out = new ObjectOutputStream(temp.getOutputStream());
        out.writeObject(obj);
        out.close();
        temp.close();
    }
    public Game getObject() throws IOException, ClassNotFoundException
    {
        Game res;
        ServerSocket temp = new ServerSocket(client.getLocalPort()+1);
        out.println("READY");
        out.flush();
        Socket cont = temp.accept();

        ObjectInputStream in = new ObjectInputStream(cont.getInputStream());
        res =  (Game) in.readObject();
        in.close();
        cont.close();
        temp.close();
        return res;
    }
}
