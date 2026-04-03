package com.chess.engine.piece;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.NormalMove;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.Move.*;

public class Knight extends Piece{
    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(PieceType.KNIGHT, piecePosition, pieceAlliance);
    }

    //duyet nuoc di cua Knight
    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();


        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) { //duyệt vòng for để tìm đường đi thích hợp
            final int candidateDestinationCoordinates = this.piecePosition + currentCandidateOffset; //Điểm đến quân mã hiện tại = vị trí hiện tại của quân mã + vị trí có thể đi được {-17, -15, -10, -6, 6, 10, 15, 17}
            if(BoardUtils.isValidTile(candidateDestinationCoordinates)) {
                //gioi han quan Knight di ra khoi ban co
                if(isFirstColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSecondColumnExclusion(this.piecePosition, currentCandidateOffset) ||
                        isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)||
                        isEighthColumnExclusion(this.piecePosition, currentCandidateOffset))  {
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);
                if(!candidateDestinationTile.isTileOccupied()) { //nuoc di binh thuong
                    legalMoves.add(new NormalMove(board, this, candidateDestinationCoordinates));
                } else { //nuoc di an quan
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance =pieceAtDestination.getPieceAlliance();

                    if(this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new AttackMove(board, this, candidateDestinationCoordinates, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

        @Override
    public Knight movPiece(final Move move) {
        return new Knight(move.getDestinationCoordinate(), move.getMovPiece().getPieceAlliance());
    }

    @Override
    public String toString() {
        return PieceType.KNIGHT.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == - 17 ||
                candidateOffset == -10 || candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == -6 || candidateOffset == 15);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == - 15 ||
                candidateOffset == -6 || candidateOffset == 10 || candidateOffset == 17);
    }
}
