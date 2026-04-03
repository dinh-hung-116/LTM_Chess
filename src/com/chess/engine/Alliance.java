package com.chess.engine;

import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;

public enum Alliance {
    WHITE {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public boolean isWhite() {
            return true;
        }

        @Override
        public boolean isBlack() {
            return false;
        }
        //move 1 
        @Override
        protected Player chossePlayer(final WhitePlayer whitePlayer,
                                      final BlackPlayer blackPlayer) {
            return whitePlayer;
        }
    },
    BLACK {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public boolean isWhite() {
            return false;
        }

        @Override
        public boolean isBlack() {
            return true;
        }
        //move 1
        @Override
        protected Player chossePlayer(final WhitePlayer whitePlayer,
                                      final BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

   public abstract int getDirection();
    public abstract boolean isWhite();
    public abstract boolean isBlack();
    //move 1
    protected abstract Player chossePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
}
