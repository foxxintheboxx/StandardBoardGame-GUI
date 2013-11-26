package jump61;
import java.io.Reader;
import java.io.Writer;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;
import static jump61.Color.*;
import static jump61.GameException.error;

/** Main logic for playing (a) game(s) of Jump61.
 *  @author Ian Fox
 */
class Game {

    /** Name of resource containing help message. */
    private static final String HELP = "jump61/Help.txt";

    /** A new Game that takes command/move input from INPUT, prints
     *  normal output on OUTPUT, prints prompts for input on PROMPTS,
     *  and prints error messages on ERROROUTPUT. The Game now "owns"
     *  INPUT, PROMPTS, OUTPUT, and ERROROUTPUT, and is responsible for
     *  closing them when its play method returns. */
    Game(Reader input, Writer prompts, Writer output, Writer errorOutput) {
        _board = new MutableBoard(Defaults.BOARD_SIZE);
        _readonlyBoard = new ConstantBoard(_board);
        _prompter = new PrintWriter(prompts, true);
        _inp = new Scanner(input);
        _inp.useDelimiter("(?m)$|^|\\p{Blank}");
        _out = new PrintWriter(output, true);
        _err = new PrintWriter(errorOutput, true);
    }
    /** alternate game for gui using N.*/
    Game(Object n) {
        _k = n;
        _board = new MutableBoard(Defaults.BOARD_SIZE);
        _readonlyBoard = new ConstantBoard(_board);
    }

    /** Returns a readonly view of the game board.  This board remains valid
     *  throughout the session. */
    Board getBoard() {
        return _board;
    }

    /** Play a session of Jump61.  This may include multiple games,
     *  and proceeds until the user exits.  Returns an exit code: 0 is
     *  normal; any positive quantity indicates an error.  */
    int play() {
        _out.println("Welcome to " + Defaults.VERSION);

        while (!_quit) {
            int[] move = new int[2];
            if (_playing && getMove(move)) {
                makeMove(_move[0], _move[1]);
            } else if (!_quit && promptForNext()) {
                readExecuteCommand();
            }
        }
        _out.close();
        _inp.close();
        return 0;
    }

    /** Get a move from my input and place its row and column in
     *  MOVE.  Returns true if this is successful, false if game stops
     *  or ends first. */
    boolean getMove(int[] move) {
        while (_playing && !_quit && _move[0] == 0 && promptForNext()) {
            readExecuteCommand();
        }
        if (_move[0] > 0 && !_quit) {
            move[0] = _move[0];
            move[1] = _move[1];
            return true;
        } else {
            return false;
        }
    }

    /** Add a spot to R C, if legal to do so. */
    void makeMove(int r, int c) {
        Color turn = _board.whoseMove();
        if (_red.getColor() == turn) {
            Squaree q = _board.getBoard()[r - 1][c - 1];
            if (q.getColor() == WHITE || q.getColor() == RED) {
                if (_red.human()) {
                    _board.addSpot(RED, r, c);
                    _move[0] = 0;
                    _board.addMove();
                } else {
                    _board.addSpot(RED, r, c);
                    _move[0] = 0;
                    _board.addMove();
                }
            } else {
                _err.println("invalid placement, square is occupied"
                    + " by other color");
                _err.flush();
                _move[0] = 0;
            }
        } else if (_blue.getColor() == turn) {
            Squaree sq = _board.getBoard()[r - 1][c - 1];
            if (sq.getColor() == WHITE || sq.getColor() == BLUE) {
                if (_blue.human()) {
                    _board.addSpot(BLUE, r, c);
                    _move[0] = 0;
                    _board.addMove();
                } else {
                    _board.addSpot(BLUE, r, c);
                    _move[0] = 0;
                    _board.addMove();
                }
            } else {
                _err.println("invalid placement, square is occupied "
                    + "by other color");
                _err.flush();
                _move[0] = 0;
            }
        }
        checkForWin();
    }

    /** Add a spot to square #N, if legal to do so. */
    void makeMove(int n) {
        int r = _board.row(n);
        int c = _board.col(n);
        makeMove(r, c);
    }

    /** Return a random integer in the range [0 .. N), uniformly
     *  distributed.  Requires N > 0. */
    int randInt(int n) {
        return _random.nextInt(n);
    }

    /** Send a message to the user as determined by FORMAT and ARGS, which
     *  are interpreted as for String.format or PrintWriter.printf. */
    void message(String format, Object... args) {
        _out.printf(format, args);
    }

    /** Check whether we are playing and there is an unannounced winner.
     *  If so, announce and stop play. */
    private void checkForWin() {
        if (_board.getWinner() != null) {
            _playing = false;
            announceWinner();
        }
    }

    /** Send announcement of winner to my user output. */
    private void announceWinner() {
        if (_board.getWinner().equals(RED)) {
            _out.println("Red wins.");
        } else {
            _out.println("Blue wins.");
        }

    }

    /** Make player #PLAYER (1 or 2) an AI for subsequent moves. */
    void setAuto(Color player) {
        _playing = false;
        if (player == RED) {
            _red = new AI(this, player);
        } else {
            _blue = new AI(this, player);
        }
    }

    /** Make player #PLAYER (1 or 2) take manual input from the user
     * for subsequent moves. */
    void setManual(Color player) {
        _playing = false;
        if (player == RED) {
            _red = new HumanPlayer(this, player);
        } else {
            _blue = new HumanPlayer(this, player);
        }
    }


    /** Print the current board using standard board-dump format. */
    private void dump() {
        _out.println(_board.toString());
    }

    /** Print a help message. */
    private void help() {
        Main.printHelpResource(HELP, _out);
    }

    /** Stop any current game and set the move number to N. */
    private void setMoveNumber(int n) {
        _board.setMoves(n);
    }

    /** Seed the random-number generator with SEED. */
    private void setSeed(long seed) {
        _random.setSeed(seed);
    }

    /** Place SPOTS spots on square R:C and color the square red or
     *  blue depending on whether COLOR is "r" or "b".  If SPOTS is
     *  0, clears the square, ignoring COLOR.  SPOTS must be less than
     *  the number of neighbors of square R, C. */
    void setSpots(int r, int c, int spots, Color color) {
        _board.set(r, c, spots, color);
    }

    /** Stop any current game and set the board to an empty N x N board
     *  with numMoves() == 0.  */
    void setSize(int n) {
        _playing = false;
        _board.clear(n);
    }

    /** Begin accepting moves for game.  If the game is won,
     *  immediately print a win message and end the game. */
    void restartGame() {
        checkForWin();
        if (_board.getWinner() == null) {
            _playing = true;
        }
    }

    /** Save move R C in _move.  Error if R and C do not indicate an
     *  existing square on the current board. */
    private void saveMove(int r, int c) {
        if (!_board.exists(r, c)) {
            throw error("move %d %d out of bounds", r, c);
        }
        _move[0] = r;
        _move[1] = c;

    }

    /** Read and execute one command.  Leave the input at the start of
     *  a line, if there is more input. */
    private void readExecuteCommand() {
        if (_board.whoseMove() == RED && !_red.human() && _playing) {
            int[] moves = _red.makeMove();
            saveMove(moves[0], moves[1]);
            String r = Integer.toString(moves[0]);
            String c = Integer.toString(moves[1]);
            _out.println("Red moves " + r + " " + c + ".");
        } else if (_board.whoseMove() == BLUE && !_blue.human() && _playing) {
            int[] moves = _blue.makeMove();
            saveMove(moves[0], moves[1]);
            String r = Integer.toString(moves[0]);
            String c = Integer.toString(moves[1]);
            _out.println("Blue moves " + r + " " + c + ".");
        } else {
            String comm = _inp.nextLine();
            line = comm.trim().split("\\s+");
            if (!_playing && line[0].equals("(^[\\d])")) {
                _err.println("Error: no game in progress");
            } else if (_playing) {
                if (line[0].matches("^(^\\d)")) {
                    if (line.length > 1) {
                        int r = Integer.parseInt(line[0]);
                        int c = Integer.parseInt(line[1]);
                        saveMove(r, c);
                    } else {
                        _err.println("Error: needs row & col");
                    }
                } else {
                    if (!line[0].matches("\n")) {
                        executeCommand(line[0]);
                    }
                }
            } else if (!_playing) {
                executeCommand(line[0]);
            }
        }
    }
    /** returns the color from the input next. */
    private Color parseCol() {
        if (line[1].matches("([bB][Ll][uU][eE])")) {
            return Color.parseColor(line[1]);
        } else if (line[1].matches("([Rr][Ee][dD])")) {
            return Color.parseColor(line[1]);
        } else {
            throw error("%s is not a viable color", line[1]);
        }
    }

    /** resize command. clears board to new N. and resets moves. */
    private void resize() {
        _playing = false;
        _board.clear(Integer.parseInt(line[1]));
        _board.resetMoves();
    }

    /** Gather arguments and execute command CMND.  Throws GameException
     *  on errors. */
    void executeCommand(String cmnd) {
        try {
            switch (cmnd) {
            case "\n": case "\r\n":
                break;
            case "":
                break;
            case "#":
                break;
            case "start":
                restartGame();
                break;
            case "quit":
                _quit = true;
                break;
            case "size":
                if (checkLine()) {
                    resize();
                }
                break;
            case "set":
                setter();
                break;
            case "clear":
                clearer();
                break;
            case "auto":
                if (checkLine()) {
                    setAuto(parseCol());
                }
                break;
            case "help":
                help();
                break;
            case "move":
                if (checkLine()) {
                    _playing = false;
                    _board.setMoves(Integer.parseInt(line[1]));
                }
                break;
            case "manual":
                if (checkLine()) {
                    setManual(parseCol());
                }
                break;
            case "dump":
                dump();
                break;
            case "seed":
                if (checkLine()) {
                    setSeed(Long.parseLong(line[1]));
                }
                break;
            default:
                throw error("bad command: '%s'", cmnd);
            }
        } catch (GameException e) {
            _err.println(e);
        }
    }
    /** checks if theres @return BOOLEAN enough args otherwise throws error. */
    private boolean checkLine() {
        if (line.length == 1) {
            throw error("bad command: not enough args");
        }
        return true;
    }
    /** clears the board. */
    private void clearer() {
        _playing = false;
        _board.resetMoves();
        _board.clear(_board.size());
    }
    /** set the board. */
    void setter() {
        _playing = false;
        int necc = 4;
        int count = 1;
        int r = 0, c = 0, n = 0;
        Color col = null;
        while (necc != 0 && count < line.length) {
            if (necc > 1) {

                String nex = line[count];

                if (nex.matches("([0-9]+)")) {
                    if (necc == 4) {
                        r = Integer.parseInt(nex);
                    } else if (necc == 3) {
                        c = Integer.parseInt(nex);
                    } else if (necc == 2) {
                        n = Integer.parseInt(nex);
                    }
                } else {
                    GameException.error("bad set syntax: %s", nex);
                }
                necc -= 1;
                count++;
            } else {
                String nex = line[count];
                if (nex.matches("[r]|[b]")) {
                    necc -= 1;
                    count++;
                    if (nex.equals("r")) {
                        col = RED;
                    } else {
                        col = BLUE;
                    }
                } else {
                    GameException.error("bad set syntax: %s", nex);
                }
            }
        }
        if (necc != 0) {
            GameException.error("bad set syntax for set");
        } else {
            setSpots(r, c, n, col);
        }
    }
    /** Print a prompt and wait for input. Returns true iff there is another
     *  token. */
    private boolean promptForNext() {
        if (_playing) {
            if (_board.whoseMove() == RED) {
                if (_red.human()) {
                    _prompter.print("red>");
                    _prompter.flush();
                } else {
                    return true;
                }
            } else {
                if (_blue.human()) {
                    _prompter.print("blue>");
                    _prompter.flush();
                } else {
                    return true;
                }
            }
        } else {
            _prompter.print(">");
            _prompter.flush();
        }
        return _inp.hasNextLine();
    }


    /** Send an error message to the user formed from arguments FORMAT
     *  and ARGS, whose meanings are as for printf. */
    void reportError(String format, Object... args) {
        _err.print("Error:");
        _err.printf(format, args);
        _err.println();
    }

    /** @return BOOLEAN tells if we are playing.*/
    boolean isPlaying() {
        return _playing;
    }
    /** this sets the next line to STR.*/
    void setLine(String[] str) {
        line = str;
    }

    /** get blue @return PLAYER.*/
    Player getBlue() {
        return _blue;
    }

    /** get Red @return PLAYER.*/
    Player getRed() {
        return _red;
    }



    /** Writer on which to print prompts for input. */
    private PrintWriter _prompter = new PrintWriter(System.out, true);
    /** Scanner from current game input.  Initialized to return
     *  newlines as tokens. */
    private Scanner _inp = new Scanner(System.in);
    /** Outlet for responses to the user. */
    private PrintWriter _out = new PrintWriter(System.out, true);;
    /** Outlet for error responses to the user. */
    private PrintWriter _err = new PrintWriter(System.out, true);;
    /** The board on which I record all moves. */
    private final Board _board;
    /** A readonly view of _board. */
    private final Board _readonlyBoard;
    /** A pseudo-random number generator used by players as needed. */
    private final Random _random = new Random();
    /** True iff a game is currently in progress. */
    private boolean _playing;
    /** the types of the two players. */
    private Player _red = new HumanPlayer(this, RED);
    /** the type of the player.*/
    private Player _blue = new AI(this, BLUE);
    /** Used to return a move entered from the console.  Allocated
     *  here to avoid allocations. */
    private final int[] _move = new int[2];
    /** if we should quit the game. */
    private boolean _quit = false;
    /** this line. */
    private String[] line;
    /** some object.*/
    private Object _k;
}
