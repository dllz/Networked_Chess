package general.models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Rook extends ChessPiece {
    public Rook(int hPos, int vPos, int pieceID, ImageIcon icon) {
        super(0, new ArrayList<>(Arrays.asList(direction.up, direction.left, direction.right, direction.down)), pieces.rook, hPos, vPos, pieceID);
        super.setIcon(icon);
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
