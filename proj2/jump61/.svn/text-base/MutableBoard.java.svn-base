package jump61;

import static jump61.Color.*;
import static jump61.GameException.error;

import java.util.ArrayList;
/** A Jump61 board state.
 *  @author Ian Fox
 */
class MutableBoard extends Board {

    /** An N x N board in initial configuration. */
    MutableBoard(int N) {
        _N = N;
        _board = new Squaree[_N][_N];
        _history = new ArrayList<Board>();
        int row = 0;
        for (; row < _N; row++) {
            for (int col = 0; col < _N; col++) {
                _board[row][col] = new Squaree();
            }
        }
    }

    /** A board whose initial contents are copied from BOARD0. Clears the
     *  undo history. */
    MutableBoard(Board board0) {
        _history = new ArrayList<Board>();
    }
    @Override
    Squaree[][] getBoard() {
        return _board;
    }
    @Override
    void clear(int N) {
        _N = N;
        _history = new ArrayList<Board>();
        _board = new Squaree[_N][_N];
        for (int i = 0; i < N; i++) {
            for (int s = 0; s < N; s++) {
                _board[i][s] = new Squaree();
            }
        }
    }

    @Override
    void copy() {
        Board cop = new MutableBoard(_N);
        int row = 0;
        for (; row < _N; row++) {
            for (int col = 0; col < _N; col++) {
                Squaree copie = _board[row][col].copy();
                cop.getBoard()[row][col] = copie;
            }
        }
        _history.add(cop);
    }

    @Override
    int size() {
        return _N;
    }

    @Override
    int spots(int r, int c) {
        Squaree sq = _board[r - 1][c - 1];
        return sq.getSpots();
    }

    @Override
    int spots(int n) {
        int r = row(n);
        int c = col(n);
        return spots(r, c);
    }

    @Override
    Color color(int r, int c) {
        Squaree sq = _board[r - 1][c - 1];
        return sq.getColor();
    }

    @Override
    Color color(int n) {
        int r = row(n);
        int c = col(n);
        return color(r, c);
    }

    @Override
    int numMoves() {
        return _moves;
    }

    @Override
    int numOfColor(Color color) {
        int total = 0;
        for (int row = 0; row < _N; row++) {
            for (int col = 0; col < _N; col++) {
                Squaree sq = _board[row][col];
                if (sq.getColor() == color) {
                    total++;
                }
            }
        }
        return total;
    }

    @Override
    void addSpot(Color player, int r, int c) {
        Squaree sq = _board[r - 1][c - 1];
        if (getWinner() == null) {
            if (!_jump) {
                copy();
            }
            if (sq.getColor() != player) {
                sq.changeColor(player);
            }
            sq.addSpot(1);
            if (neighbors(r, c) < sq.getSpots()) {
                jump(r, c, player);
            }
        }
    }

    @Override
    void addSpot(Color player, int n) {
        int r = row(n);
        int c = col(n);
        addSpot(player, r, c);
    }

    @Override
    void set(int r, int c, int num, Color player) {
        r = r - 1;
        c = c - 1;
        if (num != 0) {
            _board[r][c] = new Squaree();
            if (neighbors(r + 1, c + 1) < num) {
                throw error("'%d' is too many spots for "
                    + "row: '%d' ; col : '%d'", num, r, c);
            } else {
                _board[r][c].addSpot(num);
                _board[r][c].changeColor(player);
            }
        } else {
            _board[r][c] = new Squaree();
        }
    }

    @Override
    void set(int n, int num, Color player) {
        int r = row(n);
        int c = col(n);
        set(r, c, num, player);
    }

    @Override
    void setMoves(int num) {
        assert num > 0;
        _moves = num;
    }
    @Override
    void undo() {
        if (_history.size() > 1) {
            Board board = _history.remove(_history.size() - 1);
            _board = board.getBoard();
        } else if (_history.size() == 1) {
            Board board = _history.get(0);
            _board = board.getBoard();
        }
    }
    /** Do all jumping on this board, assuming that initially, S is the only
     *  square that might be over-full use R C and PLAYER. */
    private void jump(int r, int c, Color player) {
        _jump = true;
        Squaree sq = _board[r - 1][c - 1];
        sq.subSpot(neighbors(r, c));
        if ((r - 1) >= 1) {
            addSpot(player, r - 1, c);
        }
        if ((r + 1) <= size()) {
            addSpot(player, r + 1, c);
        }
        if ((c - 1) >= 1) {
            addSpot(player, r , c - 1);
        }
        if ((c + 1) <= size()) {
            addSpot(player, r, c + 1);
        }
        _jump = false;
    }

    /** resets moves to zero.*/
    @Override
    void resetMoves() {
        _moves = 1;
    }
    @Override
    /** makes a move.*/
    void addMove() {
        _moves += 1;
    }
    /** Total combined number of moves by both sides. */
    protected int _moves = 1;
    /** the next Player to move. */
    private Color _nextPlayer;
    /** the winner of the game. */
    private Color _winner;
    /** the size N of the current board. */
    private int _N;
    /** jump mode. */
    private boolean _jump;
    /** a 2d array String Rep of the Board.*/
    private Squaree[][] _board;
    /** a history of the board. */
    private ArrayList<Board> _history;
}
