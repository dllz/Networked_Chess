package client.models;

/**
 * Created by Daniel on 2016/10/26.
 */
public class ChessPiece
{
    private enum pieces{
        pawn, rook, knight, bishop, queen, king
    }
    private enum direction {
        up, down, left, right, upleft, upright, downleft, downright
    }
    private int moveLength;
    /**private enum pos
    {
        a1, a2, a3, a4, a5, a6, a7, a8,
        b1, b2, b3, b4, b5, b6, b7, b8,
        c1, c2, c3, c4, c5, c6, c7, c8,
        d1, d2, d3, d4, d5, d6, d7, d8,
        e1, e2, e3, e4, e5, e6, e7, e8,
        f1, f2, f3, f4, f5, f6, f7, f8,
        g1, g2, g3, g4, g5, g6, g7, g8,
        h1, h2, h3, h4, h5, h6, h7, h8
    }**/

    private int hPos;
    private int vPos;

    public void moveUp(int move)
    {
            vPos += move;
    }

    public void moveDown(int move)
    {
        vPos -= move;
    }

    public void moveLeft(int move)
    {
        hPos -= move;
    }

    public void moveRight(int move)
    {
        hPos += move;
    }

    public void moveUpLeft(int ver, int hos)
    {
        vPos += ver;
        hPos -= hos;
    }

    public void moveUpRight(int ver, int hos)
    {
        vPos += ver;
        hPos += hos;
    }

    public void moveDownLeft(int ver, int hos)
    {
        vPos -= ver;
        hPos -= hos;
    }

    public void moveDownRight(int ver, int hos)
    {
        vPos -= ver;
        hPos += hos;
    }


}
