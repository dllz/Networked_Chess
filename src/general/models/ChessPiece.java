package general.models;

import java.util.ArrayList;

/**
 * Created by Daniel on 2016/10/26.
 */
public class ChessPiece
{
    public enum pieces{
        pawn, rook, knight, bishop, queen, king
    }
    public enum direction {
        up, down, left, right, upleft, upright, downleft, downright,
        leftup, leftdown,
        rightup, rightdown
    }
    private int maxLength;
    private ArrayList<direction> posMovements;
    private pieces type;
    private int hPos;
    private int vPos;
    private int pieceID;

    public int gethPos() {
        return hPos;
    }

    public int getPieceID() {
        return pieceID;
    }

    public int getvPos() {

        return vPos;
    }

    public ChessPiece() {
    }

    public void setPos(int v, int h)
    {
        this.hPos = h;
        this.vPos = v;
    }

    public ChessPiece(int maxLength, ArrayList<direction> posMovements, pieces type, int hPos, int vPos, int pieceID) {

        this.maxLength = maxLength;
        this.posMovements = posMovements;
        this.type = type;
        this.hPos = hPos;
        this.vPos = vPos;
        this.pieceID = pieceID;
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
