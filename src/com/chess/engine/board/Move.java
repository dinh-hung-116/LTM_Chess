package com.chess.engine.board;

import com.chess.engine.piece.Piece;

public abstract class Move {
    final Board board;
    final Piece movedPiece;
    final int destinationCoordinate;
    public Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public int getDestinationCoordinate() {
        return this.destinationCoordinate;
    }

    public abstract Board execute();

    public static final class NormalMove extends Move {
        public NormalMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }

        @Override
        public Board execute() {

            @SuppressWarnings("unused")
            //Tạo builder để dựng bàn cờ mới
            final Board.Builder builder = new Board.Builder();
            //duyệt quân của người chơi hiện tại
            for(final Piece piece : this.board.currentPlayer().getActivePieces()){
                if(!this.movedPiece.equals(piece)){
                    builder.setPiece(piece);
                }
            }
            //duyệt quân đối thủ
            for(final Piece piece: this.board.currentPlayer().getOpponent().getActivePieces()){
                builder.setPiece(piece);
            }
            builder.setPiece(null);
            //set lượt chơi
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
        public Board execute() {
            return null;
        }
    }
}
