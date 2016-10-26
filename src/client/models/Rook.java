package client.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Rook extends ChessPiece {
    public Rook(int maxLength, ArrayList<direction> posMovements, pieces type, int hPos, int vPos) {
        super(0, new ArrayList<>(Arrays.asList(direction.up, direction.left, direction.right, direction.down)), pieces.rook, hPos, vPos);
    }
}
