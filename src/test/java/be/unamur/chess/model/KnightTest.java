package be.unamur.chess.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {

    @Test
    void testKnightValidMoves() {
        Piece[][] board = new Piece[8][8];
        Knight knight = new Knight(true);

        // Place knight in the center
        board[4][4] = knight;
        knight.getValidMoves(board, 4, 4);
    }


}