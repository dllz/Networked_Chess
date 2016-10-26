package client.models;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Pawn extends ChessPiece
{
    public Pawn(int hPos, int vPos) {
        super(1, new ArrayList<>(Arrays.asList(direction.up, direction.upleft, direction.upright)), pieces.pawn, hPos, vPos);
    }
}
