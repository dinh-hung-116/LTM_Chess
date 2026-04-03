package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece{
    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinates = this.piecePosition;
            while(BoardUtils.isValidTile(candidateDestinationCoordinates)) {

                if(isFirstColumnExclusion(candidateDestinationCoordinates, candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinates,candidateCoordinateOffset)) {
                    break;
                }

                candidateDestinationCoordinates += candidateCoordinateOffset;
                if(BoardUtils.isValidTile(candidateDestinationCoordinates)) { //check loai nuoc di nhu the nao
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);
                    if(!candidateDestinationTile.isTileOccupied()) { //nuoc di binh thuong
                        legalMoves.add(new Move.NormalMove(board, this, candidateDestinationCoordinates));
                    } else { //nuoc di an quan
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance =pieceAtDestination.getPieceAlliance();
                        if(this.pieceAlliance != pieceAlliance) {
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                        }
                    }
                    break;
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
        @Override
    public Queen movPiece(final Move move) {
        return new Queen(move.getDestinationCoordinate(), move.getMovPiece().getPieceAlliance());
    }
    @Override
    public String toString() {
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -9 || candidateOffset == 7 || candidateOffset == -1);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9 || candidateOffset == 1);
    }


}
