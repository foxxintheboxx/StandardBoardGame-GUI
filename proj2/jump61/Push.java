
package jump61;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static jump61.Color.*;
import java.awt.BorderLayout;
/** the most fundamental way working the buttons within the game.
 * this is basically how users interact with the board and how
 * they interact with AIs.
 * @author Ian Fox */
public class Push implements ActionListener {
    /** the constructor for a push action.
     * takes in a DIS, ROW, and COL .*/

    Push(Display dis, int row, int col) {
        _dis = dis;
        _row = row;
        _col = col;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SquareButton sq = _dis.getGBoard()[_row][_col];
        if (e.getActionCommand() != null && _dis.getB().getWinner() == null
            && (_dis.getBlue().human() || _dis.getRed().human())) {
            if (_dis.whoseMove() == RED
                || (_dis.whoseMove() == BLUE && !_dis.getBlue().human())) {
                if (sq.getColor() == RED || sq.getColor() == WHITE) {
                    boolean opp = _dis.getBlue().human();
                    if (opp) {
                        _dis.setDirections("Blue's move");
                    }
                    _dis.getB().addMove();
                    _dis.addSpot(RED, _row, _col);
                    if (!opp) {
                        _dis.getB().addMove();
                        Board copy = _dis.copyGboard();
                        int[] move = ((AI) _dis.getBlue()).makeGMove(copy);
                        move = checkMove(move, BLUE);
                        _dis.addSpot(BLUE, move[0], move[1]);
                        _dis.setDirections("Red's move");
                    }
                    if (_dis.getWinner() != null) {
                        _dis.addSpot(RED, 0, 0);
                    }
                } else {
                    Runnable r = new Runner();
                    SwingUtilities.invokeLater(r);
                }
            } else {
                if (sq.getColor() == BLUE || sq.getColor() == WHITE) {
                    boolean opp = _dis.getRed().human();
                    if (opp) {
                        _dis.setDirections("Red's move");
                    }
                    _dis.getB().addMove();
                    _dis.addSpot(BLUE, _row, _col);
                    if (_dis.getWinner() != null) {
                        _dis.addSpot(RED, 0, 0);
                    }
                    if (!opp) {
                        _dis.getB().addMove();
                        Board copy = _dis.copyGboard();
                        int[] move = ((AI) _dis.getRed()).makeGMove(copy);
                        move = checkMove(move, BLUE);
                        _dis.addSpot(RED, move[0], move[1]);
                        _dis.setDirections("Blue's move");
                    }
                    if (_dis.getWinner() != null) {
                        _dis.addSpot(RED, 0, 0);
                    }
                } else {
                    Runnable r = new Runner();
                    SwingUtilities.invokeLater(r);
                }
            }
        }
    }
    /** the runnable class which outputs erroneous pushes.*/
    private class Runner implements Runnable {
        @Override
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>Error</h1>"
                + "Square is occupied by other Color";
            JPanel p = new JPanel(new BorderLayout());
            int width = WID;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }
    /** a method to make sure the AI Gui does not return a move.
     * that is not Viable. It @returns INT[] and takes in a COL
     * and the initial MOVE.*/
    private int[] checkMove(int[] move, jump61.Color col) {
        if (move[0] >= _dis.gttBsize() || move[1] >= _dis.gttBsize()) {
            for (int i = 0; i < _dis.gttBsize(); i++) {
                for (int c = 0; c < _dis.gttBsize(); c++) {
                    if (_dis.getGBoard()[i][c].getColor() == col
                        || _dis.getGBoard()[i][c].getColor() == WHITE) {
                        move[0] = i;
                        move[1] = c;
                    }
                }
            }
            return move;
        }
        return move;
    }
    /** the row of this push.*/
    private int _row;
    /** the column of this button.*/
    private int _col;
    /** the display this buttoon interacts with.*/
    private Display _dis;
    /** Width the error message.*/
    private static final int WID = 150;
}
