package client.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static client.models.ChessPiece.direction.up;

/**
 * Created by Daniel on 2016/10/26.
 */
public class Board
{
    private ChessPiece[] whitePieces;
    private int whiteCount = 0;
    private ChessPiece[] blackPieces;
    private int blackCount = 0;
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
        whiteCount++;
    }

    private void intialiseBlack()
    {
        for (int i = 0; i < 8; i++)
        {
            blackPieces[blackCount] = new Pawn(i, 6,createID());
            blackCount++;
        }
        blackPieces[blackCount] = new Rook(0,7,createID());
        blackCount++;
        blackPieces[blackCount] = new Rook(7,7, createID());
        blackCount++;
        blackPieces[blackCount] = new Knight(1,7,createID());
        blackCount++;
        blackPieces[blackCount] = new Knight(6,7,createID());
        blackCount++;
        blackPieces[blackCount] = new Bishop(2,7,createID());
        blackCount++;
        blackPieces[blackCount] = new Bishop(5,7,createID());
        blackCount++;
        blackPieces[blackCount] = new King(4,7,createID());
        blackCount++;
        blackPieces[blackCount] = new Queen(3,7,createID());
        blackCount++;
    }

    private int moveWhiteSolid(ChessPiece.pieces piece, ChessPiece.direction dir, int pieceID)
    {
        int res = 0;
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
                switch (dir)
                {
                    case up:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) whitePieces[res];
                            temp.moveUp();
                            whitePieces[res] = temp;
                        }
                    break;
                    case upleft:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) whitePieces[res];
                            temp.moveUpLeft();
                            whitePieces[res] = temp;
                        }
                        break;
                    case upright:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) whitePieces[res];
                            temp.moveUpRight();
                            whitePieces[res] = temp;
                        }
                        break;
                }
                break;
            case king:
                switch (dir)
                {
                    case up:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.moveUp();
                            whitePieces[res] = temp;
                        }
                        break;
                    case upleft:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.upLeft();
                            whitePieces[res] = temp;
                        }
                        break;
                    case upright:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.upRight();
                            whitePieces[res] = temp;
                        }
                        break;
                    case left:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.moveLeft();
                            whitePieces[res] = temp;
                        }
                        break;
                    case right:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.moveRight();
                            whitePieces[res] = temp;
                        }
                        break;
                    case down:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.moveDown();
                            whitePieces[res] = temp;
                        }
                        break;
                    case downleft:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.downLeft();
                            whitePieces[res] = temp;
                        }
                        break;
                    case downright:
                        res = searchWhite(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) whitePieces[res];
                            temp.downRight();
                            whitePieces[res] = temp;
                        }
                        break;
                }
                break;
        }
        return res;
    }
    //TODO Check Solid Legal Move
    private int moveBlackSolid(ChessPiece.pieces piece, ChessPiece.direction dir, int pieceID)//positions inverted
    {
        int res = 0;
        switch (piece)
        {
            case knight:
                switch (dir)
                {

                    case upleft:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.downRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case upright:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.downLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                    case leftdown:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.rightUp();
                            blackPieces[res] = temp;
                        }
                        break;
                    case leftup:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.rightDown();
                            blackPieces[res] = temp;
                        }
                        break;
                    case rightdown:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.leftUp();
                            blackPieces[res] = temp;
                        }
                        break;
                    case rightup:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.leftDown();
                            blackPieces[res] = temp;
                        }
                        break;
                    case downleft:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.upRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case downright:
                        res = searchBlack(pieceID);
                        if (res != -1)
                        {
                            Knight temp = (Knight) blackPieces[res];
                            temp.upLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                }
                break;
            case pawn:
                switch (dir)
                {
                    case up:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) blackPieces[res];
                            temp.moveDown();
                            blackPieces[res] = temp;
                        }
                        break;
                    case upleft:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) blackPieces[res];
                            temp.moveDownRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case upright:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            Pawn temp = (Pawn) blackPieces[res];
                            temp.moveDownLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                }
                break;
            case king:
                switch (dir)
                {
                    case up:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.moveDown();
                            blackPieces[res] = temp;
                        }
                        break;
                    case upleft:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.downRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case upright:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.downLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                    case left:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.moveRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case right:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.moveLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                    case down:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.moveUp();
                            blackPieces[res] = temp;
                        }
                        break;
                    case downleft:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.upRight();
                            blackPieces[res] = temp;
                        }
                        break;
                    case downright:
                        res = searchBlack(pieceID);
                        if(res!= -1)
                        {
                            King temp = (King) blackPieces[res];
                            temp.upLeft();
                            blackPieces[res] = temp;
                        }
                        break;
                }
                break;
        }
        return res;
    }
    //TODO Check Solid Legal Move
    private int moveWhite(ChessPiece.pieces piece, ChessPiece.direction dir, int pieceID, int length)
    {
        int res = 0;
        switch (piece)
        {
            case rook:
                boolean legal = checkWhiteLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case up:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUp(length);
                            }
                            break;
                        case down:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDown(length);
                            }
                            break;
                        case left:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveLeft(length);
                            }
                            break;
                        case right:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveRight(length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
            break;
            case bishop:
                legal = checkWhiteLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case upleft:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUpLeft(length, length);
                            }
                            break;
                        case upright:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUpRight(length, length);
                            }
                            break;
                        case downleft:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDownLeft(length, length);
                            }
                            break;
                        case downright:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDownRight(length, length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
            break;
            case queen:
                legal = checkWhiteLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case upleft:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUpLeft(length, length);
                            }
                            break;
                        case upright:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUpRight(length, length);
                            }
                            break;
                        case downleft:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDownLeft(length, length);
                            }
                            break;
                        case downright:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDownRight(length, length);
                            }
                            break;
                        case up:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveUp(length);
                            }
                            break;
                        case down:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveDown(length);
                            }
                            break;
                        case left:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveLeft(length);
                            }
                            break;
                        case right:
                            res = searchWhite(pieceID);
                            if (res != -1)
                            {
                                whitePieces[res].moveRight(length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
        }
        return res;
    }

    private int moveBlack(ChessPiece.pieces piece, ChessPiece.direction dir, int pieceID, int length)
    {
        int res = 0;
        switch (piece)
        {
            case rook:
                boolean legal = checkBlackLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case up:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDown(length);
                            }
                            break;
                        case down:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUp(length);
                            }
                            break;
                        case left:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveRight(length);
                            }
                            break;
                        case right:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveLeft(length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
                break;
            case bishop:
                legal = checkBlackLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case upleft:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDownRight(length, length);
                            }
                            break;
                        case upright:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDownLeft(length, length);
                            }
                            break;
                        case downleft:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUpRight(length, length);
                            }
                            break;
                        case downright:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUpLeft(length, length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
                break;
            case queen:
                legal = checkBlackLegalMove(dir, res, length);
                if (legal)
                {
                    switch (dir)
                    {
                        case up:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDown(length);
                            }
                            break;
                        case down:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUp(length);
                            }
                            break;
                        case left:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveRight(length);
                            }
                            break;
                        case right:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveLeft(length);
                            }
                            break;
                        case upleft:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDownRight(length, length);
                            }
                            break;
                        case upright:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveDownLeft(length, length);
                            }
                            break;
                        case downleft:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUpRight(length, length);
                            }
                            break;
                        case downright:
                            res = searchBlack(pieceID);
                            if (res != -1)
                            {
                                blackPieces[res].moveUpLeft(length, length);
                            }
                            break;
                    }
                }else
                {
                    throw new UnsupportedOperationException("MoveNotLegal");
                }
        }
        return res;
    }

    private boolean checkBlackLegalMove(ChessPiece.direction dir, int res, int length) {
        boolean failed = false;
        int temp;
        int extraT;
        switch (dir)
        {
            case down:
                temp = blackPieces[res].getvPos();
                if(temp+length < 8)
                {
                    return true;
                }
                break;
            case up:
                temp = blackPieces[res].getvPos();
                if(temp-length > -1)
                {
                    return true;
                }
                break;
            case right:
                temp = blackPieces[res].gethPos();
                if(temp-length > -1)
                {
                    return true;
                }
                break;
            case left:
                temp = blackPieces[res].gethPos();
                if(temp+length < 8)
                {
                    return true;
                }
                break;
            case downright:
                extraT = blackPieces[res].getvPos();
                temp = blackPieces[res].gethPos();
                if(temp-length > -1 && extraT+length < 8)
                {
                    return true;
                }
                break;
            case downleft:
                extraT = blackPieces[res].getvPos();
                temp = blackPieces[res].gethPos();
                if(temp+length < 8 && extraT+length < 8)
                {
                    return true;
                }
                break;
            case upright:
                extraT = blackPieces[res].getvPos();
                temp = blackPieces[res].gethPos();
                if(temp-length > -1 && extraT-length > -1)
                {
                    return true;
                }
                break;
            case upleft:
                extraT = blackPieces[res].getvPos();
                temp = blackPieces[res].gethPos();
                if(temp+length < 8 && extraT-length > -1)
                {
                    return true;
                }
                break;
        }
        return failed;
    }

    private boolean checkWhiteLegalMove(ChessPiece.direction dir, int res, int length) {
        boolean failed = false;
        int temp;
        int extraT;
        switch (dir)
        {
            case up:
                temp = whitePieces[res].getvPos();
                if(temp+length < 8)
                {
                    return true;
                }
                break;
            case down:
                temp = whitePieces[res].getvPos();
                if(temp-length > -1)
                {
                    return true;
                }
                break;
            case left:
                temp = whitePieces[res].gethPos();
                if(temp-length > -1)
                {
                    return true;
                }
                break;
            case right:
                temp = whitePieces[res].gethPos();
                if(temp+length < 8)
                {
                    return true;
                }
                break;
            case upleft:
                extraT = whitePieces[res].getvPos();
                temp = whitePieces[res].gethPos();
                if(temp-length > -1 && extraT+length < 8)
                {
                    return true;
                }
                break;
            case upright:
                extraT = whitePieces[res].getvPos();
                temp = whitePieces[res].gethPos();
                if(temp+length < 8 && extraT+length < 8)
                {
                    return true;
                }
                break;
            case downleft:
                extraT = whitePieces[res].getvPos();
                temp = whitePieces[res].gethPos();
                if(temp-length > -1 && extraT-length > -1)
                {
                    return true;
                }
                break;
            case downright:
                extraT = whitePieces[res].getvPos();
                temp = whitePieces[res].gethPos();
                if(temp+length < 8 && extraT-length > -1)
                {
                    return true;
                }
                break;
        }
        return failed;
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

    private int searchBlack(int pieceID)
    {
        int pos;
        for (int i = 0; i < blackPieces.length; i++) {
            if(blackPieces[i].getPieceID() == pieceID)
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
