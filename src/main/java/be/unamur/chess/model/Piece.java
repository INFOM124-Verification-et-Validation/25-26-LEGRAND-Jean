package be.unamur.chess.model;

import java.awt.*;
import java.util.Set;

/**
 * Abstract class representing a chess piece.
 */
public abstract class Piece {

    protected boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Gets all valid moves for this piece.
     * Valid moves are those that the piece can legally move to,
     * according to its specific movement rules and the current board state.
     * This method ensures that the resulting move does not leave the king in check.
     *
     * @param boardState the current board state
     * @param row        the starting row of the piece
     * @param col        the starting column of the piece
     * @return a set of valid moves as points
     */
    public abstract Set<Point> getValidMoves(Piece[][] boardState, int row, int col);
}

