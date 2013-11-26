package jump61;


import static jump61.Color.*;
/** An automated Player.
 *  @author Ian Fox
 */
class AI extends Player {

    /** A new player of GAME initially playing COLOR that chooses
     *  moves automatically.
     */
    AI(Game game, Color color) {
        super(game, color);
    }

    @Override
    int[] makeMove() {
        Game game = getGame();
        Board board = getBoard();
        int beta = Integer.MAX_VALUE;
        int alpha = Integer.MIN_VALUE;
        _move = new int[2];
        Color oppC;
        if (getColor() == RED) {
            oppC = BLUE;
        } else {
            oppC = RED;
        }
        for (int row = 1; row <= board.size(); row++) {
            int col = 1;
            for (; col <= board.size(); col++) {
                if (board.isLegal(getColor(), row, col)) {
                    Board b1 = copy(board);
                    b1.addSpot(getColor(), row, col);
                    int score = minmax(oppC, b1, 5, alpha, beta);
                    if (score > alpha) {
                        alpha = score;
                        int[] newM = {row, col};
                        _move = newM;
                    }
                    if (alpha >= beta) {
                        break;
                    }
                }
            }
            if (alpha >= beta) {
                break;
            }
        }
        _move = nullCheck(_move, board);
        return _move;
    }

    /** check if null if so @returns INT[], uses MOVE and BOARD to find the nex
    * move .*/
    private int[] nullCheck(int[] move, Board board) {
        if (move[0] == 0 || move[1] == 0) {
            for (int row = 1; row <= board.size(); row++) {
                int col = 1;
                for (; col <= board.size(); col++) {
                    if (board.isLegal(getColor(), row, col)) {
                        move[0] = row;
                        move[1] = col;
                        return move;
                    }
                }
            }
        }
        return move;
    }

    /** Return the minimum of CUTOFF and the minmax value of board B
     *  (which must be mutable) for player P to a search depth of D
     *  (where D == 0 denotes evaluating just the next move).
     *  If MOVES is not null and ALPHA is not exceeded, set BETA to
     *  a list of all highest-scoring moves for P; clear it if
     *  non-null and CUTOFF is exceeded. the contents of B are
     *  invariant over this call. */
    private int minmax(Color p, Board b, int d, int alpha, int beta) {
        Color oppC;
        if (getColor() == RED) {
            oppC = BLUE;
        } else {
            oppC = RED;
        }
        if (d == 0) {
            return staticEval(p, b);
        } else if (p == getColor()) {
            for (int row = 1; row <= b.size(); row++) {
                int col = 1;
                for (; col <= b.size(); col++) {
                    if (b.isLegal(getColor(), row, col)) {
                        Board b1 = copy(b);
                        b1.addSpot(getColor(), row, col);
                        int score = minmax(oppC, b1, d - 1, alpha, beta);
                        if (score >= alpha) {
                            alpha = score;
                        }
                        if (alpha >= beta) {
                            return alpha;
                        }
                    }
                }
            }
            return alpha;
        } else {
            for (int row = 1; row <= b.size(); row++) {
                int col = 1;
                for (; col <= b.size(); col++) {
                    if (b.isLegal(oppC, row, col)) {
                        Board b0 = copy(b);
                        b0.addSpot(oppC, row, col);
                        int score = minmax(getColor(), b0, d - 1, alpha, beta);
                        if (score < beta) {
                            beta = score;
                        }
                        if (alpha >= beta) {
                            return beta;
                        }
                    }
                }
            }
            return beta;
        }
    }

    /** Returns heuristic value INT of board B for player P.
     *  Higher is better for P. */
    private int staticEval(Color p, Board b) {
        Color oppC;
        if (p == RED) {
            oppC = BLUE;
        } else {
            oppC = RED;
        }
        if (b.numOfColor(p) == b.size() * b.size()) {
            return Integer.MAX_VALUE;
        } else if (b.numOfColor(oppC) == b.size() * b.size()) {
            return Integer.MIN_VALUE;
        } else {
            return b.numOfColor(p);
        }
    }
    @Override
    boolean human() {
        return false;
    }
    /** the make move for the gui using BOARD, @return INT[].*/
    int[] makeGMove(Board board) {
        int beta = Integer.MAX_VALUE;
        int alpha = Integer.MIN_VALUE;
        _move = new int[2];
        Color oppC;
        if (getColor() == RED) {
            oppC = BLUE;
        } else {
            oppC = RED;
        }
        for (int row = 1; row <= board.size(); row++) {
            int col = 1;
            for (; col <= board.size(); col++) {
                if (board.isLegal(getColor(), row, col)) {
                    Board b1 = copy(board);
                    b1.addSpot(getColor(), row, col);
                    int score = minmax(oppC, b1, 5, alpha, beta);
                    if (score > alpha) {
                        alpha = score;
                        int[] newM = {row, col};
                        _move = newM;
                    }
                    if (alpha >= beta) {
                        return _move;
                    }
                }
            }
        }
        _move = nullCheck(_move, board);
        _move[0] = _move[0] - 1;
        _move[1] = _move[1] - 1;
        return _move;
    }
    /** similar to Copy in @return BOARD but resolves pointer issues
     * takes in B.*/
    Board copy(Board b) {
        Board cop = new MutableBoard(b.size());
        int row = 0;
        for (; row < b.size(); row++) {
            for (int col = 0; col < b.size(); col++) {
                Squaree copie = b.getBoard()[row][col].copy();
                cop.getBoard()[row][col] = copie;
            }
        }
        return cop;
    }
    /** the next move. */
    private static int[] _move;

}

