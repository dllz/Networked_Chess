package client.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Board
{
    private ChessPiece[] whitePieces;
    private int whiteCount = 0;
    private ChessPiece[] blackPieces;
    private ChessPiece[] deadBlack ;
    private ChessPiece[] deadWhite;
    private static AtomicInteger idCounter = new AtomicInteger();

    
    public Board()
    {
        intialiseWhite();
        intialiseBlack();
    }

    private void intialiseWhite()
    {
        for (int i = 0; i < 8; i++)
        {
            whitePieces[i] = new Pawn(i, 1,createID());
            whiteCount++;
        }
        whitePieces[whiteCount] = new Rook(0,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new Rook(7,0, createID());
        whiteCount++;
        whitePieces[whiteCount] = new Knight(1,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new Knight(6,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new Bishop(2,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new Bishop(5,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new King(4,0,createID());
        whiteCount++;
        whitePieces[whiteCount] = new Queen(3,0,createID());
    }

    private void intialiseBlack()
    {
        for (int i = 0; i < 8; i++)
        {
            blackPieces.add(new Pawn(i, 6,createID()));
        }
        blackPieces.add(new Rook(0,7,createID()));
        blackPieces.add(new Rook(7,7, createID()));
        blackPieces.add(new Knight(1,7,createID()));
        blackPieces.add(new Knight(6,7,createID()));
        blackPieces.add(new Bishop(2,7,createID()));
        blackPieces.add(new Bishop(5,7,createID()));
        blackPieces.add(new King(4,7,createID()));
        blackPieces.add(new Queen(3,7,createID()));
    }

    private void moveWhiteSolid(ChessPiece.pieces piece, ChessPiece.direction dir, int pieceID)
    {
        int res;
        switch (piece)
        {
            case knight:
                switch (dir)
                {

                    case upleft:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.upLeft();
                            whitePieces[res] = temp;
                        }
                    break;
                    case upright:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.upRight();
                            whitePieces[res] = temp;
                        }
                    break;
                    case leftdown:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.leftDown();
                            whitePieces[res] = temp;
                        }
                        break;
                    case leftup:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.leftUp();
                            whitePieces[res] = temp;
                        }
                        break;
                    case rightdown:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.rightDown();
                            whitePieces[res] = temp;
                        }
                        break;
                    case rightup:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.rightUp();
                            whitePieces[res] = temp;
                        }
                        break;
                    case downleft:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.downLeft();
                            whitePieces[res] = temp;
                        }
                        break;
                    case downright:
                        res = searchWhite(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) whitePieces[res];
                            temp.downRight();
                            whitePieces[res] = temp;
                        }
                        break;
                }
                break;
            case pawn:
                break;
            case king:
                break;
        }
    }

    private int searchWhite(int pieceID)
    {
        int pos;
        for (int i = 0; i < whitePieces.length; i++) {
            if(whitePieces[i].getPieceID() == pieceID)
            {
                pos = i;
                return pos;
            }
        }
        return -1;
    }

    private static int createID()
    {
        return idCounter.getAndIncrement();
    }
}
