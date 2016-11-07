package general.models;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Bishop extends ChessPiece
{
    public Bishop(int hPos, int vPos, int pieceID, ImageIcon icon) {
        super(0, new ArrayList<>(Arrays.asList(direction.downleft, direction.upleft, direction.upright, direction.downright)), pieces.bishop, hPos, vPos, pieceID);
        super.setIcon(icon);
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
}
