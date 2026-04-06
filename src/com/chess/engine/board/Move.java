package com.chess.engine.board;

import com.chess.engine.board.Move.PawnJump.NullMove;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.Rook;
import com.chess.engine.piece.King;
import com.chess.engine.board.Board.Builder;
import java.util.Objects;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;

    public static final Move NULL_MOVE = new NullMove(null, null, -1);
    public Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    @Override 
     public int hashCode() {
        int result = 1;
        result = 31 * result + this.destinationCoordinate;
        result = 31 * result + this.movedPiece.hashCode();
        result = result + (isFirstMove ? 1 : 0);
        return result;
    }

       
    @Override 
     public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Move)) {
            return false;
        }
        final Move otherMove = (Move) other;
        return
                getDestinationCoordinate() == otherMove.getDestinationCoordinate() &&
                getMovedPiece().equals(otherMove.getMovedPiece());
    }
    public int getCurrentCoordinate() {
        return this.getMovPiece()Piece.getPiecePosition();
    }
    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }
    // lấy dữ liệu 
    public Piece getMovPiece(){
        return this.movedPiece;
    }

    public boolean isAttack() {
        return false;
    }

    public boolean isCastlingMove() {
        return false;
    }
    
    public Piece getAttackedPiece() {
        return null;
    }
    public abstract Board execute();

    public static final class NormalMove extends Move {
        public NormalMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
        //nhiệm vụ tạo ra một bàn cờ mới sau khi một quân cờ di chuyển
        @Override
        public Board execute() {

            @SuppressWarnings("unused")
            //Tạo builder để dựng bàn cờ mới
            final Board.Builder builder = new Board.Builder();
            // giữ lại quân của current player (trừ quân di chuyển)
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            //duyệt quân đối thủ
            for(final Piece piece: this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movPiece(this));
            // đổi lượt chơi
            builder.setMoveMaker(this.board.currentPlayer().getAlliance());

            return builder.build();
        }
    }

    public static final class AttackMove extends Move {
        final Piece attackedPiece;
        public AttackMove(final Board board, final Piece movedPiece, final int destinationCoordinate, final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }

        @Override
        public int hashCode() {
            return this.attackedPiece.hashCode() + super.hashCode();
        }

         @Override 
         public boolean equals(final Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AttackMove)) {
                return false;
            }
            final AttackMove otherAttackMove = (AttackMove) other;
            return super.equals(otherAttackMove) && getAttackedPiece().equals(otherAttackMove.getAttackedPiece());
        }

        @Override
        public Board execute() {
            return null;
        }

        @Override
        public boolean isAttack() {
            return true;
        }

        @Override
        public Piece getAttackedPiece() {
            return this.attackedPiece;
        }
    }

    
    public static final class PawnMove extends Move {
        public PawnMove(final Board board, 
                        final Piece movedPiece, 
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class PawnAttackMove extends AttackMove {
        public PawnAttackMove(final Board board, 
                              final Piece movedPiece, 
                              final int destinationCoordinate, 
                              final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }
    
    
    public static final class PawnEnPassantAttackMove extends PawnAttackMove {
        public PawnEnPassantAttackMove(final Board board, 
                                      final Piece movedPiece, 
                                      final int destinationCoordinate, 
                                      final Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate, attackedPiece);
        }
    }

    public static final class PawnJump extends Move {
        public PawnJump(final Board board, 
                        final Piece movedPiece, 
                        final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {
            final Builder builder = new Builder();
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece: this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            final Pawn movedPawn = (Pawn) this.movedPiece.movPiece(this);
            builder.setPiece(movedPawn);
            builder.setEnPassantPawn(movedPawn);
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
            return builder.build();
    }

    static abstract class CastleMove extends Move {
        protected final Rook castleRook;
        protected final int castleRookStart;
        protected final int castleRookDestination;


        public CastleMove(final Board board, 
                          final Piece movedPiece, 
                          final int destinationCoordinate,
                          final Rook castleRook,
                          final int castleRookStart,
                          final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate);
            this.castleRook = castleRook;
            this.castleRookStart = castleRookStart;
            this.castleRookDestination = castleRookDestination;
        }
    }

    public Rook getCastleRook() {
        return this.castleRook;
    }

    @Override 
    public boolean isCastlingMove() {
        return true;
    }

    @Override
    public Board execute() {
        final Builder builder = new Builder();
         for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece) && !this.castleRook.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            for(final Piece piece: this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(this.movedPiece.movPiece(this));
            //todo look into the first move on normal piece
            builder.setPiece(new Rook(this.castleRook.getPieceAlliance(), this.castleRookDestination));
            builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());

    }
}


    

    public static final class KingSideCastleMove extends CastleMove {
        public KingSideCastleMove(final Board board, 
                                  final Piece movedPiece, 
                                  final int destinationCoordinate,
                                  final Rook castleRook,
                                  final int castleRookStart,
                                  final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public String toString() {
            return "0-0";
        }
    }

    public static final class QueenSideCastleMove extends CastleMove {
        public QueenSideCastleMove(final Board board, 
                                   final Piece movedPiece, 
                                   final int destinationCoordinate,
                                   final Rook castleRook,
                                   final int castleRookStart,
                                   final int castleRookDestination) {
            super(board, movedPiece, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
        }

        @Override
        public String toString() {
            return "0-0-0";
        }
    }

    public static final class NullMove extends Move {
        public NullMove() {
            super(null, null, -1);
        }

        @Override
        public Board execute() {
            throw new RuntimeException("Cannot execute null move");
        }

    }
    
    public static class MoveFactory {
        private MoveFactory() {
            throw new RuntimeException("Not instantiable!");
        }
        public static Move createMove(final Board board,
                                      final int currentCoordinate,
                                      final int destinationCoordinate) {
            for(final Move move : board.getAllLegalMoves()) {
                if(move.getCurrentCoordinate() == currentCoordinate &&
                   move.getDestinationCoordinate() == destinationCoordinate) {
                    return move;
                }
            }
            return new NullMove(board, null, -1);
        }
    }
}