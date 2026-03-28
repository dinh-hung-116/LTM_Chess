package com.chess.engine.board;

import com.chess.engine.piece.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {
    protected final int tileCoordinate;


    private static final Map<Integer, emptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    private static Map<Integer, emptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, emptyTile> emptyTileMap = new HashMap<>();
            for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
                emptyTileMap.put(i, new emptyTile(i));
            }
        //Collections.unmodifiableMap(emptyTileMap);
        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile (final int tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    } //Tạo 1 ô --> nếu như Ô đã có quân thì lấy tọa độ ô, quân cờ / Không thì lấy tọa độ ô còn trống

    private Tile (final int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    } //constructor

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class emptyTile extends Tile{
        private emptyTile(final int Coordinate) {
            super(Coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }
        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
