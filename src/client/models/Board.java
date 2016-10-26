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
    private ArrayList<ChessPiece> whitePieces = new ArrayList<>();
    private ArrayList<ChessPiece> blackPieces = new ArrayList<>();
    private ArrayList<ChessPiece> deadBlack = new ArrayList<>();
    private ArrayList<ChessPiece> deadWhite = new ArrayList<>();
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
            whitePieces.add(new Pawn(i, 1,createID()));
        }
        whitePieces.add(new Rook(0,0,createID()));
        whitePieces.add(new Rook(7,0, createID()));
        whitePieces.add(new Knight(1,0,createID()));
        whitePieces.add(new Knight(6,0,createID()));
        whitePieces.add(new Bishop(2,0,createID()));
        whitePieces.add(new Bishop(5,0,createID()));
        whitePieces.add(new King(4,0,createID()));
        whitePieces.add(new Queen(3,0,createID()));
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



    private static int createID()
    {
        return idCounter.getAndIncrement();
    }
}
