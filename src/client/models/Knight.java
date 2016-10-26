package client.models;

import java.util.ArrayList;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Knight extends ChessPiece
{
    public Knight(int maxLength, ArrayList<direction> posMovements, pieces type, int hPos, int vPos) {
        super(0, null, pieces.knight, hPos, vPos);
    }

    public void upLeft()
    {
        super.setPos(super.getvPos()+2, super.gethPos()-1);
    }

    public void leftUp()
    {
        super.setPos(super.getvPos()+1, super.gethPos()-2);
    }

    public void leftDown()
    {
        super.setPos(super.getvPos()-1, super.gethPos()-2);
    }

    public void downLeft()
    {
        super.setPos(super.getvPos()-2, super.gethPos()-1);
    }
    public void downRight()
    {
        super.setPos(super.getvPos()-2, super.gethPos()+1);
    }
    public void rightDown()
    {
        super.setPos(super.getvPos()-1, super.gethPos()+2);
    }
    public void upRight()
    {
        super.setPos(super.getvPos()+2, super.gethPos()+1);
    }
    public void rightUp()
    {
        super.setPos(super.getvPos()+1, super.gethPos()+2);
    }


    @Override
    public void moveUp(int move) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveDown(int move) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveLeft(int move) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveRight(int move) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveUpLeft(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveUpRight(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveDownLeft(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveDownRight(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

}
