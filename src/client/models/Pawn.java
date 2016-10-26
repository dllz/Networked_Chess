package client.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Pawn extends ChessPiece
{

    public void moveUp()
    {
        super.setPos(super.getvPos()+1, super.gethPos());
    }

    public void moveUpLeft()
    {
        super.setPos(super.getvPos()+1, super.gethPos()-1);
    }

    public void moveUpRight()
    {
        super.setPos(super.getvPos()+1, super.gethPos()+1);
    }
    @Override
    public void moveUp(int move) {
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

    public Pawn(int hPos, int vPos) {
        super(1, new ArrayList<>(Arrays.asList(direction.up, direction.upleft, direction.upright)), pieces.pawn, hPos, vPos);
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
    public void moveDownLeft(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }

    @Override
    public void moveDownRight(int ver, int hos) {
        throw new UnsupportedOperationException("Not supported for this piece");
    }
}
