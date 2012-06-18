package org.ks.viergewinnt;

import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.ApplicationScoped;
import org.ks.viergewinnt.interfaces.Game;

/**
 *
 * @author Kevin Sapper 2012
 */
@ApplicationScoped
public class GameManager implements Game<VierGewinntMove> {

    private Collection<VierGewinntMove> moves;

    private byte board[][] = new byte[GameConstants.COLUMNS][GameConstants.ROWS];

    private byte turn;

    private boolean isWon;

    public void startGame() {
        resetBoard();
        turn = GameConstants.FIRST_PLAYER;
        isWon = false;
    }

    public void resumeGame() {
    }

    public void endGame() {
    }

    private void resetBoard() {
        moves = new ArrayList<>();
        for (byte i = 0; i < GameConstants.COLUMNS; i++) {
            moves.add(new VierGewinntMove(i));
            for (byte j = 0; j < GameConstants.ROWS; j++) {
                board[i][j] = GameConstants.NO_PLAYER;
            }
        }
    }

    @Override
    public Collection<VierGewinntMove> moves() {
        Collection<VierGewinntMove> copyMoves = new ArrayList<>();
        copyMoves.addAll(moves);
        return copyMoves;
    }

    @Override
    public Game<VierGewinntMove> doMove(VierGewinntMove move) {
        System.out.println(move.getColumn());
        for (byte i = GameConstants.ROWS - 1; i >= 0; i--) {
            if (isNoPlayer(move.getColumn(), i)) {
                placeChip(move.getColumn(), i);
                // remove move if row is filled
                if (i == 0) {
                    moves.remove(move);
                }
                break;
            }
        }
        if (!isWon) {
            turn = isFirstPLayer() ? GameConstants.SECOND_PLAYER : GameConstants.FIRST_PLAYER;
        }
        return this;
    }

    @Override
    public boolean isFirstPLayer() {
        return turn == GameConstants.FIRST_PLAYER;
    }

    @Override
    public boolean lastHasWon() {
        return isWon;
    }

    private void checkWinner(byte column, byte row) {
        // minus 1 because the placed chip is counted twice
        int horizontal = countChips(column, row, -1, 0) + countChips(column, row, 1, 0) - 1;
        int vertical = countChips(column, row, 0, -1) + countChips(column, row, 0, 1) - 1;
        int diagonalLeftToRight = countChips(column, row, 1, 1) + countChips(column, row, -1, -1) - 1;
        int diagonalRightToLeft = countChips(column, row, 1, -1) + countChips(column, row, -1, 1) - 1;

        isWon = horizontal >= 4 || vertical >= 4 || diagonalLeftToRight >= 4 || diagonalRightToLeft >= 4;
    }

    private int countChips(int column, int row, int x, int y) {
        if (column < 0 || row < 0 || column >= GameConstants.COLUMNS || row >= GameConstants.ROWS || board[column][row] != turn) {
            return 0;
        } else {
            return 1 + countChips(column + x, row + y, x, y);
        }
    }

    private void placeChip(byte column, byte row) {
        board[column][row] = turn;
        checkWinner(column, row);
    }

    private boolean isNoPlayer(byte column, byte row) {
        return board[column][row] == GameConstants.NO_PLAYER;
    }

    public byte[][] getBoard() {
        return board;
    }
}
