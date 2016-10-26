package client.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class King extends ChessPiece {
    public King(int maxLength, ArrayList<direction> posMovements, pieces type, int hPos, int vPos, int pieceID) {
        super(1, new ArrayList<>(Arrays.asList(direction.up, direction.left, direction.right, direction.down, direction.downright, direction.downleft, direction.upleft, direction.upright)), pieces.king, hPos, vPos, pieceID);
    }

    public void upRight()
    {
        super.setPos(super.getvPos()+1, super.gethPos()+1);
    }
    public void downLeft()
    {
        super.setPos(super.getvPos()-1, super.gethPos()-1);
    }
    public void downRight()
    {
        super.setPos(super.getvPos()-1, super.gethPos()+1);
    }
    public void upLeft()
    {
        super.setPos(super.getvPos()+1, super.gethPos()-1);
    }
    public void moveUp()
    {
        super.setPos(super.getvPos()+1, super.gethPos());
    }
    public void moveDown()
    {
        super.setPos(super.getvPos()-1, super.gethPos());
    }
    public void moveLeft()
    {
        super.setPos(super.getvPos(), super.gethPos()-1);
    }
    public void moveRight()
    {
        super.setPos(super.getvPos(), super.gethPos()+1);
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
