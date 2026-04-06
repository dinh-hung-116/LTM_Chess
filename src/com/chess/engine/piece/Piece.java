package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {

    protected final  PieceType pieceType;
    protected final int piecePosition;
    protected final Alliance pieceAlliance;
    protected final boolean isFirstMove;
    private final int cachedHashCode;
    Piece (PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {
        this.pieceType = pieceType;
        this.pieceAlliance = pieceAlliance;
        this.piecePosition = piecePosition;
        //Con ghi logic cho isFirstMove
        this.isFirstMove = false;
        this.cachedHashCode = computeHashCode();
    }

    //Hàm này tính toán mã băm (hash code) cho một quân cờ dựa trên các thuộc tính của nó.
    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceAlliance.hashCode();
        result = 31 * result + piecePosition;
        result = 31 * result + (isFirstMove ? 1 : 0);
        return result;
    }

    //Hàm này kiểm tra 2 quân cờ có giống nhau không bằng cách so sánh từng thuộc tính.
    @Override
    public boolean equals(final Object other){
        //Kiểm tra nếu 2 đối tượng là cùng một instance thì trả về true
        if(this == other){
            return true;
        }
        //Kiểm tra nếu đối tượng khác null và cùng lớp với Piece thì tiếp tục so sánh
        if(!(other instanceof Piece)){
            return false;
        }
        //Ép kiểu đối tượng khác về Piece để so sánh các thuộc tính
        final Piece otherPiece = (Piece) other;
        return this.pieceType == otherPiece.getPieceType() &&
               this.pieceAlliance == otherPiece.getPieceAlliance() &&
               this.piecePosition == otherPiece.getPiecePosition() &&
               this.isFirstMove == otherPiece.isFirstMove();

    }

    //Hàm này trả về mã băm đã được tính toán trước đó, giúp tăng hiệu suất khi sử dụng trong các cấu trúc dữ liệu như HashMap hoặc HashSet.
    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public int getPiecePosition() {
        return this.piecePosition;
    }
    public Alliance getPieceAlliance() {
        return this.pieceAlliance;
    }

    public boolean isFirstMove() {
        return this.isFirstMove;
    }

    public PieceType getPieceType() {
        return this.pieceType;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
    
    //khai báo một phương thức trừu tượng (abstract method)
    public abstract Piece movPiece(Move move);

    public enum PieceType {
        PAWN("P") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override 
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override 
            public boolean isRook() {
                return true;
            }
        },
        KNIGHT("N") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }

        },
        QUEEN("Q") {
            @Override
            public boolean isKing() {
                return false;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K") {
            @Override
            public boolean isKing() {
                return true;
            }
            @Override
            public boolean isRook() {
                return false;
            }
        };
        private String pieceName;
        PieceType(final String pieceName) {
            this.pieceName = pieceName;
        }

        @Override
        public String toString() {
            return this.pieceName;
        }

        public abstract boolean isKing();
        
        public abstract boolean isRook();
    }
}