package org.ks.viergewinnt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import org.ks.viergewinnt.interfaces.Game;

/**
 *
 * @author Kevin Sapper 2012
 */
@ApplicationScoped
public class GameManager implements Game<VierGewinntMove> {

    @Inject
    private Event<String> finishEvent;

    private byte board[][] = new byte[GameConstants.COLUMNS][GameConstants.ROWS];

    private byte turn;

    private boolean isWon;

    public void startGame() {
        resetBoard();
    }

    public void resumeGame(byte[][] board, byte turn) {
        resetBoard();
        this.board = board;
        this.turn = turn;
    }

    private void resetBoard() {
        for (byte i = 0; i < GameConstants.COLUMNS; i++) {
            for (byte j = 0; j < GameConstants.ROWS; j++) {
                board[i][j] = GameConstants.NO_PLAYER;
            }
        }
        
        turn = GameConstants.FIRST_PLAYER;
        isWon = false;
    }

    @Override
    public List<VierGewinntMove> moves() {
        List<VierGewinntMove> moves = new ArrayList<>();
        for (byte i = 0; i < GameConstants.COLUMNS; i++) {
            if(board[i][0] == GameConstants.NO_PLAYER) {
                moves.add(new VierGewinntMove(i));
            }
        }
        return moves;
    }

    @Override
    public Game<VierGewinntMove> doMove(VierGewinntMove move) {
        System.out.println(move.getColumn());
        for (byte i = GameConstants.ROWS - 1; i >= 0; i--) {
            if (isNoPlayer(move.getColumn(), i)) {
                placeChip(move.getColumn(), i);
                break;
            }
        }
        if (otherPlayerHasWon()) {
            finishEvent.fire(isFirstPLayer() ? "Player 1" : "Player 2");
        } else {
            turn = isFirstPLayer() ? GameConstants.SECOND_PLAYER : GameConstants.FIRST_PLAYER;
        }
        return this;
    }

    @Override
    public boolean isFirstPLayer() {
        return turn == GameConstants.FIRST_PLAYER;
    }

    @Override
    public boolean otherPlayerHasWon() {
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