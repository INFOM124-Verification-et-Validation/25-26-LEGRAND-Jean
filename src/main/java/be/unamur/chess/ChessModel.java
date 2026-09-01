package be.unamur.chess;

import be.unamur.chess.model.*;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The model class that represents the game logic and state of the chess game.
 */
class ChessModel {
    private final int ROWS = 8;
    private final int COLS = 8;
    private Piece[][] boardState;

    /**
     * Constructs a ChessModel and initializes the board state.
     */
    public ChessModel() {
        boardState = new Piece[8][8];
        initializeBoard();
    }

    /**
     * Initializes the chess board with the starting positions of pieces.
     */
    private void initializeBoard() {
        // Black Pawns
        boardState[1][0] = new Pawn(false);
        boardState[1][1] = new Pawn(false);
        boardState[1][2] = new Pawn(false);
        boardState[1][3] = new Pawn(false);
        boardState[1][4] = new Pawn(false);
        boardState[1][5] = new Pawn(false);
        boardState[1][6] = new Pawn(false);
        boardState[1][7] = new Pawn(false);

        // White Pawns
        boardState[6][0] = new Pawn(true);
        boardState[6][1] = new Pawn(true);
        boardState[6][2] = new Pawn(true);
        boardState[6][3] = new Pawn(true);
        boardState[6][4] = new Pawn(true);
        boardState[6][5] = new Pawn(true);
        boardState[6][6] = new Pawn(true);
        boardState[6][7] = new Pawn(true);

        // Black pieces
        boardState[0][0] = new Rook(false);
        boardState[0][7] = new Rook(false);
        boardState[0][1] = new Knight(false);
        boardState[0][6] = new Knight(false);
        boardState[0][2] = new Bishop(false);
        boardState[0][5] = new Bishop(false);
        boardState[0][3] = new Queen(false);
        boardState[0][4] = new King(false);

        // White pieces
        boardState[7][0] = new Rook(true);
        boardState[7][7] = new Rook(true);
        boardState[7][1] = new Knight(true);
        boardState[7][6] = new Knight(true);
        boardState[7][2] = new Bishop(true);
        boardState[7][5] = new Bishop(true);
        boardState[7][3] = new Queen(true);
        boardState[7][4] = new King(true);
    }

    /**
     * Gets the board state.
     *
     * @return the current state of the board.
     */
    public Piece[][] getBoardState() {
        return boardState;
    }
}
