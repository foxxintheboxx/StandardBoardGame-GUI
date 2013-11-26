package jump61;


import java.util.Formatter;

import static jump61.Color.*;

/** Represents the state of a Jump61 game.  Squares are indexed either by
 *  row and column (between 1 and size()), or by square number, numbering
 *  squares by rows, with squares in row 1 numbered 0 - size()-1, in
 *  row 2 numbered size() - 2*size() - 1, etc.
 *  @author Ian Fox
 */
abstract class Board {

    /** (Re)initialize me to a cleared board with N squares on a side. Clears
     *  the undo history and sets the number of moves to 0. */
    void clear(int N) {
        unsupported("clear");
    }

    /** Copy the contents of BOARD into me. */
    void copy() {
        unsupported("copy");
    }
    /** makes a move.*/
    abstract void addMove();
    /** Return the number of rows and of columns of THIS. */
    abstract int size();

    /** Returns the number of spots in the square at row R, column C,
     *  1 <= R, C <= size (). */
    abstract int spots(int r, int c);

    /** Returns the number of spots in square #N. */
    abstract int spots(int n);

    /** Returns the color of square #N, numbering squares by rows, with
     *  squares in row 1 number 0 - size()-1, in row 2 numbered
     *  size() - 2*size() - 1, etc. */
    abstract Color color(int n);

    /** Returns the color of the square at row R, column C,
     *  1 <= R, C <= size(). */
    abstract Color color(int r, int c);

    /** Returns the total number of moves made (red makes the odd moves,
     *  blue the even ones). */
    abstract int numMoves();

    /** Returns the Color of the player who would be next to move.  If the
     *  game is won, this will return the loser (assuming legal position). */
    Color whoseMove() {
        if (numMoves() % 2 == 0) {
            return BLUE;
        } else {
            return RED;
        }
    }

    /** Return true iff row R and column C denotes a valid square. */
    final boolean exists(int r, int c) {
        return 1 <= r && r <= size() && 1 <= c && c <= size();
    }

    /** Return true iff S is a valid square number. */
    final boolean exists(int s) {
        int N = size();
        return 0 <= s && s < N * N;
    }

    /** Return the row number for square #N.
     * this is one greater than the actual array.
     * rep because array runs from 0 - r-1. */
    final int row(int n) {
        if (n > size() * size()) {
            GameException.error("out of index");
        }
        if (n <= size()) {
            return 1;
        } else {
            int r = 1;
            while (r * size() < n) {
                r += 1;
            }
            return r;
        }
    }

    /** Return the column number for square #N. */
    final int col(int n) {
        if (n > size() * size()) {
            GameException.error("out of index");
        }
        int col = n % size();
        if (col == 0) {
            return size();
        } else {
            return col;
        }
    }

    /** Return the square number of row R, column C.
        Note sqNum, n is represented through the algoritihim,
        this assumes r and c are from 0 to _N - 1. */
    final int sqNum(int r, int c) {
        if (c > size() || r > size()) {
            GameException.error("out of index");
        }
        return (size() * r) + (c + 1);
    }


    /** Returns true iff it would currently be legal for PLAYER to add a spot
        to square at row R, column C.
        this is actual R and C, so it r - 1 and c - 1. */
    boolean isLegal(Color player, int r, int c) {
        if (player.equals(WHITE)) {
            GameException.error("Cannot run this with white color you stupid");
        }
        Squaree sq = getBoard()[r - 1][c - 1];
        if (sq.getColor() == WHITE || sq.getColor() == player) {
            if (sq.getSpots() < neighbors(r, c) + 1) {
                return true;
            }
        }
        return false;
    }

    /** Returns true iff it would currently be legal for PLAYER to add a spot
     *  to square #N. */
    boolean isLegal(Color player, int n) {
        int column = col(n);
        int ro = row(n);
        return isLegal(player, ro, column);
    }

    /** Returns true iff PLAYER is allowed to move at this point. */
    boolean isLegal(Color player) {
        if (player.equals(whoseMove())) {
            return true;
        }
        return false;

    }

    /** Returns the winner of the current position, if the game is over,
     *  and otherwise null. */
    final Color getWinner() {
        int board = size() * size();
        if (numOfColor(RED) == board) {
            return RED;
        } else if (numOfColor(BLUE) == board) {
            return BLUE;
        } else {
            return null;
        }
    }

    /** Return the number of squares of given COLOR. */
    abstract int numOfColor(Color color);

    /** Add a spot from PLAYER at row R, column C.  Assumes
     *  isLegal(PLAYER, R, C). */
    void addSpot(Color player, int r, int c) {
        unsupported("addSpot");
    }

    /** Add a spot from PLAYER at square #N.  Assumes isLegal(PLAYER, N). */
    void addSpot(Color player, int n) {
        unsupported("addSpot");
    }

    /** Set the square at row R, column C to NUM spots (0 <= NUM), and give
     *  it color PLAYER if NUM > 0 (otherwise, white).  Clear the undo
     *  history. */
    void set(int r, int c, int num, Color player) {
        unsupported("set");
    }

    /** Set the square #N to NUM spots (0 <= NUM), and give it color PLAYER
     *  if NUM > 0 (otherwise, white).  Clear the undo history. */
    void set(int n, int num, Color player) {
        unsupported("set");
    }

    /** Set the current number of moves to N.  Clear the undo history. */
    void setMoves(int n) {
        unsupported("setMoves");
    }

    /** Undo the effects one move (that is, one addSpot command).  One
     *  can only undo back to the last point at which the undo history
     *  was cleared, or the construction of this Board. */
    void undo() {
        unsupported("undo");
    }
    /** returns the board of this. */
    abstract Squaree[][] getBoard();

    /** Returns my dumped representation. */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("===%n");
        int row = 0;
        while (row != size()) {
            int c = 0;
            String col = "";
            while (c != size()) {
                Squaree sq = getBoard()[row][c];
                if ((c + 1) == size()) {
                    col += sq.repr();
                } else {
                    col += sq.repr() + " ";
                }
                c++;
            }
            String len = "%"
                + Integer.toString(col.length() + 4) + "s" + "%n";
            out.format(len, col);
            row++;
        }
        out.format("===");
        return out.toString();
    }

    /** Returns an external rendition of me, suitable for
     *  human-readable textual display.  This is distinct from the dumped
     *  representation (returned by toString). */
    public String toDisplayString() {
        Formatter out = new Formatter();
        int col = 0;
        while (col != size()) {
            int r = 0;
            String row = "";
            while (r != size()) {
                Squaree sq = getBoard()[r][col];
                if ((r + 1) == size()) {
                    row += sq.repr();
                } else {
                    row += sq.repr() + " ";
                }
                r++;
            }
            String len = "%"
                + Integer.toString(row.length() + 4) + "s" + "%n";
            out.format(len, row);
            col++;

        }
        return out.toString();
    }

    /** Returns the number of neighbors of the square at row R, column C. */
    int neighbors(int r, int c) {
        r -= 1;
        c -= 1;
        if (r == 0 || r == (size() - 1)) {
            if (c == 0 || c == (size() - 1)) {
                return 2;
            } else {
                return 3;
            }
        } else if (c == 0 || c == (size() - 1)) {
            return 3;
        } else {
            return 4;
        }
    }

    /** Returns the number of neighbors of square #N. */
    int neighbors(int n) {
        int c = col(n);
        int r = row(n);
        return neighbors(r, c);
    }

    /** Indicate fatal error: OP is unsupported operation. */
    private void unsupported(String op) {
        String msg = String.format("'%s' operation not supported", op);
        throw new UnsupportedOperationException(msg);
    }
    /** resets moves to zero.*/
    abstract void resetMoves();

    /** The length of an end of line on this system. */
    private static final int NL_LENGTH =
        System.getProperty("line.separator").length();

    /** the next Player to move. */
    private Color _nextPlayer;

    /** the winner of the game. */
    private Color _winner = null;

    /** the size N of the current board. */
    private int _N;

    /** Total combined number of moves by both sides. */
    protected int _moves;
}
