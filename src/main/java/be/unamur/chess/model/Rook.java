package be.unamur.chess.model;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Rook piece class.
 */
public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public Set<Point> getValidMoves(Piece[][] boardState, int row, int col) {
        Set<Point> moves = new HashSet<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] dir : directions) {
            int r = row + dir[0];
            int c = col + dir[1];

            while (r >= 0 && r < 8 && c >= 0 && c < 8) {
                Piece target = boardState[r][c];
                if (target == null) {
                    moves.add(new Point(r, c));
                } else {
                    if (target.isWhite() != this.isWhite()) {
                        moves.add(new Point(r, c)); // capture
                    }
                    break;
                }
                r += dir[0];
                c += dir[1];
            }
        }
        return moves;
    }

    @Override
    public String toString() {
        return isWhite() ? "WRook" : "BRook";
    }
}