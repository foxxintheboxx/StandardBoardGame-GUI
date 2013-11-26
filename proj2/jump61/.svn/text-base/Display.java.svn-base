package jump61;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Dimension;
import static jump61.Color.*;

/** the display which is literally the GUI with a JUMP61 game integrated.
 * within the gui. This is all full functional.
 * @author Ian Fox*/
public class Display extends JPanel {
    /** creates a new Display.*/
    Display() {
        _frame = new JFrame();
        _pan = null;
        _panel = new JPanel();
        _manB = new JCheckBox("Manual", false);
        Font font = new Font("Courier", Font.BOLD, TWENTY);
        _manB.setFont(font);
        _title = new JPanel();
        _directions = new JTextField(FIFTEEN);
        _directions.setPreferredSize(new Dimension(FIFTY, FIFTY));
        _directions.setMaximumSize(_directions.getPreferredSize());
        _directions.setMinimumSize(_directions.getPreferredSize());
        _directions.setEditable(false);
        font = new Font("Courier", Font.BOLD, FIFTY_FIVE);
        _directions.setFont(font);
        JLabel name = new JLabel("Welcome to Jump61 2.0");
        Font nFont = name.getFont();
        name.setFont(new Font("Papyrus", Font.ITALIC
            + Font.BOLD, TWENTY_FIVE));
        name.setPreferredSize(new Dimension(FOUR_HUN, THIRTY));
        name.setMaximumSize(_panel.getPreferredSize());
        name.setMinimumSize(_panel.getPreferredSize());
        _title.setBackground(Color.CYAN);
        _title.add(name);
        _help = new JButton("help");
        _clear = new JButton("clear");
        _quit = new JButton("quit");
        _start = new JButton("start");
        _size = new JTextField(FIFTEEN);
        _board = new MutableBoard(Defaults.BOARD_SIZE);
        _red = new HumanPlayer(new Game(null), RED);
        _blue = new AI(new Game(null) , BLUE);
        _autoR = new JCheckBox("Auto", false);
        _set = new JTextField(FIFTEEN);
        _panel.setBackground(Color.gray);
        _autoB = new JCheckBox("Manual", true);
        _help.addActionListener(new HelpDis(this));
        _quit.addActionListener(new QuitDis(this));
        _start.addActionListener(new Start(this));
        _autoB.addItemListener(new AutoBlue(this));
        _autoR.addItemListener(new AutoRed(this));
    }
    /** the button grid of the GUI.*/
    private SquareButton[][] _gBoard;
    /** the jframe in which this acts on .*/
    private JFrame _frame;
    /** the Jpanel on which we make a gride.*/
    private JPanel _pan;
    /** the panel in which all of the jtextfield.*/
    private JPanel _panel;
    /** the checkbox for manual blue.*/
    private JCheckBox _manB;
    /** the checkbox for auto blue.*/
    private JCheckBox _autoB;
    /** chechbox for auto red .*/
    private JCheckBox _autoR;
    /** the jlabel of hilfinger.*/
    private JLabel hilf;
    /** the japenl of the title. */
    private JPanel _title;
    /** button to use help.*/
    private JButton _help;
    /** the clear button.*/
    private JButton _clear;
    /** the quit button of the gui.*/
    private JButton _quit;
    /** the start button of the gui.*/
    private JButton _start;
    /** the textfield for the size.*/
    private JTextField _size;
    /** the textfield for the set.*/
    private JTextField _set;
    /** the Board the goes with it .*/
    private Board _board;
    /** the size of the gui board. */
    private int _bSize = 6;
    /** the boolean to say if its playini.*/
    private boolean _playing = false;
    /** returns player red.*/
    private Player _red;
    /** returns player blue.*/
    private Player _blue;
    /** returns the JTextField that directs the user.*/
    private JTextField _directions;
    /** returns the JTextfield which modifies the amount of moves.*/
    private JTextField _move;
    /**  the standard usage of out to output the display and config.*/
    void out() {
        JLabel move = new JLabel("Move");
        Font labelFont = move.getFont();
        move.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel size = new JLabel("Size");
        Font labelFont0 = move.getFont();
        size.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel blue = new JLabel("< Blue >");
        Font labelFont1 = move.getFont();
        blue.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel red = new JLabel("< Red >");
        Font labelFont2 = move.getFont();
        red.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel set = new JLabel("Set");
        Font labelFont3 = move.getFont();
        set.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel hashtag = new JLabel("Comment");
        Font labelFont4 = move.getFont();
        hashtag.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, TWENTY_FIVE));
        _panel.add(size);
        opField(_size);
        _size.addActionListener(new SizeGui(this));
        _clear.addActionListener(new ClearGui(this));
        _panel.add(set);
        opField2(_set);
        _set.addActionListener(new SetGui(this));
        _panel.add(blue);
        _manB.addItemListener(new ManualBlue(this));
        _panel.add(_manB);
        Font font = new Font("Courier", Font.BOLD, TWENTY);
        _autoB.setFont(font);
        _autoR.setFont(font);
        _panel.add(red);
        _panel.add(_autoB);
        _panel.add(_autoR);
        _move = new JTextField(FIFTEEN);
        _panel.add(BorderLayout.NORTH, move);
        opField4(_move);
        _move.addActionListener(new MoveDis(this));
        _panel.add(BorderLayout.CENTER, _help);
        _panel.add(_clear);
        _panel.add(_quit);
        _panel.add(_start);
        _frame.getContentPane().add(BorderLayout.EAST, _panel);
        _frame.getContentPane().add(BorderLayout.NORTH, _title);
        _frame.getContentPane().add(BorderLayout.SOUTH, _directions);
        _panel.setPreferredSize(new Dimension(TWO_HUN, TWO_HUN));
        _panel.setMaximumSize(_panel.getPreferredSize());
        _panel.setMinimumSize(_panel.getPreferredSize());
        _frame.setSize(EIGHT_HUN,
            SEVEN_H);
        _pan = new JPanel(new GridLayout(2, 2));
        _frame.getContentPane().add(_pan);
        _frame.setVisible(false);
        resize(_frame, _board.size());
        _autoB.addItemListener(new AutoBlue(this));
        _autoR.addItemListener(new AutoRed(this));
        Usage u = new Usage();
        SwingUtilities.invokeLater(u);
    }
    /** Specific AI vs. AI out display.*/
    void outAI() {
        JLabel move = new JLabel("Move");
        Font labelFont = move.getFont();
        move.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel size = new JLabel("Size");
        Font labelFont0 = move.getFont();
        size.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel blue = new JLabel("< Blue >");
        Font labelFont1 = move.getFont();
        blue.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel red = new JLabel("< Red >");
        Font labelFont2 = move.getFont();
        red.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel set = new JLabel("Set");
        Font labelFont3 = move.getFont();
        set.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, THIRTY));
        JLabel hashtag = new JLabel("Comment");
        Font labelFont4 = move.getFont();
        hashtag.setFont(new Font("Papyrus", Font.ROMAN_BASELINE, TWENTY_FIVE));
        _help.addActionListener(new HelpDis(this));
        _quit.addActionListener(new QuitDis(this));
        _start.addActionListener(new Start(this));
        _panel.add(size);
        opField(_size);
        _size.addActionListener(new SizeGui(this));
        _clear.addActionListener(new ClearGui(this));
        _panel.add(set);
        opField2(_set);
        _set.addActionListener(new SetGui(this));
        _panel.add(blue);
        _manB.addItemListener(new ManualBlue(this));
        _panel.add(_manB);
        Font font = new Font("Courier", Font.BOLD, TWENTY);
        _autoB.setFont(font);
        _autoR.setFont(font);
        _panel.add(red);
        _panel.add(_autoB);
        _panel.add(_autoR);
        _move = new JTextField(FIFTEEN);
        _panel.add(BorderLayout.NORTH, move);
        opField4(_move);
        _move.addActionListener(new MoveDis(this));
        _panel.add(BorderLayout.CENTER, _help);
        _panel.add(_clear);
        _panel.add(_quit);
        _panel.add(_start);
        _frame.getContentPane().add(BorderLayout.EAST, _panel);
        _frame.getContentPane().add(BorderLayout.NORTH, _title);
        _frame.getContentPane().add(BorderLayout.SOUTH, _directions);
        _panel.setPreferredSize(new Dimension(TWO_HUN, TWO_HUN));
        _panel.setMaximumSize(_panel.getPreferredSize());
        _panel.setMinimumSize(_panel.getPreferredSize());
        _frame.setSize(EIGHT_HUN,
            SEVEN_H);
        _pan = new JPanel(new GridLayout(2, 2));
        _frame.getContentPane().add(_pan);
        _frame.setBackground(Color.WHITE);
        _frame.setVisible(false);
        resize(_frame, _board.size());
    }
    /** returns JFRAME of this.*/
    JFrame getFrame() {
        return _frame;
    }
    /** returns the panel which hold the squarebutton array.*/
    JPanel getPan() {
        return _pan;
    }
    /**  sets the FIELD2 to arbitrary pararmeters.*/
    void opField2(JTextField field2) {
        field2.setPreferredSize(new Dimension(FIFTY, TWENTY_FIVE));
        field2.setMaximumSize(field2.getPreferredSize());
        field2.setMinimumSize(field2.getPreferredSize());
        _panel.add(field2);
    }
    /**  sets the FIELD to arbitrary pararmeters.*/
    void opField(JTextField field) {
        field.setPreferredSize(new Dimension(EIGHTY, TWENTY_FIVE));
        field.setMaximumSize(field.getPreferredSize());
        field.setMinimumSize(field.getPreferredSize());
        _panel.add(BorderLayout.WEST, field);
    }
    /** sets the FIELD4 to arbitrary  parameters.*/
    void opField4(JTextField field4) {
        field4.setPreferredSize(new Dimension(FIFTY, TWENTY_FIVE));
        field4.setMaximumSize(field4.getPreferredSize());
        field4.setMinimumSize(field4.getPreferredSize());
        _panel.add(BorderLayout.CENTER, field4);
        field4.setSize(10, 10);
    }
    /** sent the textfield FIELD5 to particular parameters.*/
    void opField5(JTextField field5) {
        field5.setPreferredSize(new Dimension(FIFTY, FIFTY));
        field5.setMaximumSize(field5.getPreferredSize());
        field5.setMinimumSize(field5.getPreferredSize());
        field5.setEnabled(true);
        field5.setEditable(true);
        _panel.add(BorderLayout.CENTER, field5);
    }
    /** the auto blue functionality && then @returns JCHECKBOX.*/
    JCheckBox getAutoB() {
        return _autoB;
    }
    /** returns the manual blue field.*/
    JCheckBox getManB() {
        return _manB;
    }
    /** returns the autoR checkbox.*/
    JCheckBox getAutoR() {
        return _autoR;
    }
    /** take in PON and sets it to PON.*/
    void setPan(JPanel pon) {
        _pan = pon;
    }
    /** @return the SQUAREBUTTON[][] gui.*/
    SquareButton[][] getGBoard() {
        return _gBoard;
    }
    /** @return BOARD which relates to the gui.*/
    Board getB() {
        return _board;
    }
    /** returns a square representation of the board.*/
    Squaree[][] getBoard() {
        return _board.getBoard();
    }
    /** returns the JTEXTFIELD the size.*/
    JTextField gttSize() {
        return _size;
    }
    /** the size of the of the text field .*/
    void setSize() {
        _size.setText("");
    }
    /** the save size of the int S.*/
    void saveSize(int s) {
        _bSize = s;
    }
    /** @return the INT of gui board size.*/
    int gttBsize() {
        return _bSize;
    }

    /** taking in F a frame and N we resize the grid.*/
    void resize(JFrame f, int n) {
        _frame.remove(_pan);
        JPanel pon = new JPanel(new GridLayout(n, n));
        int n1 = 0;
        _board.clear(n);
        SquareButton[][] buttons = new SquareButton[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                SquareButton here = new SquareButton(getB(), r, c, this);
                here.setImage(here.getColor());
                pon.add(here);
                buttons[r][c] = here;
            }
        }
        _gBoard = buttons;
        setPan(pon);
        _frame.getContentPane().add(_pan);
        _frame.setVisible(true);
    }

    /** @return JUMP61.COLOR which whose move it is.*/
    jump61.Color whoseMove() {
        return _board.whoseMove();
    }
    /** using COL we set the jtextfield's text.*/
    void setDirections(String col) {
        _directions.setText(col);
    }
    /** @returns JTEXTFIELD in which we get moves.*/
    JTextField getMove() {
        return _move;
    }
    /** @returns JTEXTFIELD in which we _set.*/
    JTextField getSet() {
        return _set;
    }
    /** Returns the number of neighbors of the square at row R, column C. */
    int neighbors(int r, int c) {
        if (r == 0 || r == (gttBsize() - 1)) {
            if (c == 0 || c == (gttBsize() - 1)) {
                return 2;
            } else {
                return 3;
            }
        } else if (c == 0 || c == (gttBsize() - 1)) {
            return 3;
        } else {
            return 4;
        }
    }

    /** addspot of to the SquareButton takes in R and C of the button.
    * and PLAYER */
    void addSpot(jump61.Color player, int r, int c) {
        if (getWinner() == null) {
            SquareButton sq = _gBoard[r][c];
            sq.addSpot(1);
            sq.changeColor(player);
            update();
            if (neighbors(r, c) < sq.getSpots()) {
                jump(r, c, player);
                update();
            }
        } else {
            String path = "Jump61/hilfinger.jpg";
            File file = new File(path);
            jump61.Color winner = getWinner();
            try {
                BufferedImage image = ImageIO.read(file);
                int i = 0;
                _frame.remove(_pan);
                ImageIcon hil = new ImageIcon(image);
                JPanel pon = new JPanel(new GridLayout(_bSize, _bSize));
                Font font = new Font("Courier", Font.BOLD, TWENTY_FIVE);
                while (i < gttBsize() * gttBsize()) {
                    i++;
                    JButton hi;
                    if (i % 2 != 0) {
                        hi = new JButton(winner + " wins.");
                        hi.setFont(font);
                    } else {
                        hi = new JButton("RTFM", hil);
                    }
                    pon.add(hi);
                }
                setPan(pon);
                _frame.getContentPane().add(_pan);
                _frame.setVisible(true);
            } catch (IOException t) {
                _frame.remove(_pan);
                JPanel pon = new JPanel(new GridLayout(_bSize, _bSize));
                int i = 0;
                Font font = new Font("Courier", Font.BOLD, TWENTY_FIVE);
                while (i < gttBsize() * gttBsize()) {
                    i++;
                    JButton hi;
                    if (i % 2 != 0) {
                        hi = new JButton(winner + " wins.");
                        hi.setFont(font);
                    } else {
                        hi = new JButton("RTFM");
                    }
                    _directions.setText(getWinner() + " wins");
                    pon.add(hi);
                }
            }
        }
    }
    /** updates the gui's representation .*/
    void update() {
        _frame.remove(_pan);
        JPanel pon = new JPanel(new GridLayout(_bSize, _bSize));
        for (int r = 0; r < _bSize; r++) {
            for (int c = 0; c < _bSize; c++) {
                SquareButton here = _gBoard[r][c];
                pon.add(here);
            }
        }
        setPan(pon);
        _frame.getContentPane().add(_pan);
        _frame.setVisible(true);
    }
    /** updates the code relative to the gbutton square.
     * takes in BOARD. */
    void update(SquareButton[][] board) {
        _frame.remove(_pan);
        JPanel pon = new JPanel(new GridLayout(_bSize, _bSize));
        for (int r = 0; r < _bSize; r++) {
            for (int c = 0; c < _bSize; c++) {
                SquareButton here = board[r][c];
                pon.add(here);
            }
        }
        setPan(pon);
        _frame.getContentPane().add(_pan);
        _frame.setVisible(true);
    }
    /** the gui's get winner @return jump61.Color.*/
    final jump61.Color getWinner() {
        int board = _bSize * _bSize;
        if (numOfColor(RED) == board) {
            return RED;
        } else if (numOfColor(BLUE) == board) {
            return BLUE;
        } else {
            return null;
        }
    }
    /** the num of the COLOR.
     * returns an INT. */
    int numOfColor(jump61.Color color) {
        int total = 0;
        for (int row = 0; row < _bSize; row++) {
            for (int col = 0; col < _bSize; col++) {
                SquareButton sq = _gBoard[row][col];
                if (sq.getColor() == color) {
                    total++;
                }
            }
        }
        return total;
    }
    /** @return BOARD which is copied of gui board.*/
    Board copyGboard() {
        Board b = new MutableBoard(_bSize);
        for (int row = 0; row < _bSize; row++) {
            for (int col = 0; col < _bSize; col++) {
                Squaree sqB = b.getBoard()[row][col];
                SquareButton sq = _gBoard[row][col];
                sqB.changeColor(sq.getColor());
                sqB.addSpot(sq.getSpots());

            }
        }
        return b;

    }
    /** Do all jumping on this board, assuming that initially, S is the only
     *  square that might be over-full use R C and PLAYER. */
    private void jump(int r, int c, jump61.Color player) {
        SquareButton sq = _gBoard[r][c];
        sq.subSpot(neighbors(r, c));
        sq.changeColor(player);
        if ((r - 1) >= 0) {
            addSpot(player, r - 1, c);
        }
        if ((r + 1) < _bSize) {
            addSpot(player, r + 1, c);
        }
        if ((c - 1) >= 0) {
            addSpot(player, r , c - 1);
        }
        if ((c + 1) < _bSize) {
            addSpot(player, r, c + 1);
        }
        if (getWinner() != null) {
            addSpot(RED, 1, 1);
        }
    }

    /** returns red @return PLAYER.*/
    Player getRed() {
        return _red;
    }

    /** returns blue @return PLAYER.*/
    Player getBlue() {
        return _blue;
    }

    /** set blue player with B. */
    void setBlue(Player b) {
        _blue = b;
    }
    /** set Red player with B. */
    void setRed(Player b) {
        _red = b;
    }
    /** @return JTEXTFIELD that gives directions.*/
    JTextField getDirections() {
        return _directions;
    }
    /** the usage class is Runnable html doc.*/
    private class Usage implements Runnable {
        @Override
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>USAGE</h1>"
                + "<h2>The Basics</h2>"
                + "The Game is initially set to red as a manaual player"
                + " and blue as an AI."
                + "<h2>Changing Blue  & Red</h2>"
                + "To do this cleanly, first clear the current board."
                + " Then, select the configuration. On rare cases, if you"
                + " are constantly switching between players types, then"
                + " you may have to press a button twice."
                + "<h2>Auto V. Auto</h2>"
                + "deselect Blue's manual box & both of Red boxes, clear the "
                + "board and then select Red. WORKS BETTER WITH SMALL BOARDS";
            JPanel p = new JPanel(new BorderLayout());
            int width = FOUR_FIF;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }
    /** a twenty.*/
    private static final int TWENTY = 20;
    /** fifteen.*/
    private static final int FIFTEEN = 15;
    /** thirty.*/
    private static final int THIRTY = 30;
    /** twenty-five.*/
    private static final int TWENTY_FIVE = 25;
    /** fifty.*/
    private static final int FIFTY = 50;
    /** two hundred.*/
    private static final int TWO_HUN = 200;
    /** eight hundred.*/
    private static final int EIGHT_HUN = 800;
    /** FIFTY_FIVE.*/
    private static final int FIFTY_FIVE = 55;
    /** four hundred.*/
    private static final int FOUR_HUN = 400;
    /** seven hundred.*/
    private static final int SEVEN_H = 700;
    /** four fity.*/
    private static final int FOUR_FIF = 450;
    /** 80.*/
    private static final int EIGHTY = 80;

}

