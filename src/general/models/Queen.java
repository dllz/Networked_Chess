package general.models;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Queen extends ChessPiece
{
    public Queen(int hPos, int vPos, int pieceID, ImageIcon icon) {
        super(0, new ArrayList<>(Arrays.asList(direction.up, direction.left, direction.right, direction.down, direction.downright, direction.downleft, direction.upleft, direction.upright)), pieces.queen, hPos, vPos, pieceID);
        super.setIcon(icon);
    }
}
