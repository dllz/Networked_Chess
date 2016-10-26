package client.models;

import java.util.ArrayList;

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
    private int maxLength;
    private ArrayList<direction> posMovements[];
    private pieces type;
    private int hPos;
    private int vPos;

    public int gethPos() {
        return hPos;
    }

    public int getvPos() {
        return vPos;
    }

    public void setPos(int v, int h)
    {
        this.hPos = h;
        this.vPos = v;
    }

    public ChessPiece(int maxLength, ArrayList<direction>[] posMovements, pieces type, int hPos, int vPos) {

        this.maxLength = maxLength;
        this.posMovements = posMovements;
        this.type = type;
        this.hPos = hPos;
        this.vPos = vPos;
    }

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
