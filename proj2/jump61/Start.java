package jump61;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static jump61.Color.*;
/** the Start class is an implementation of an Action Listener.
 * it used to interact with users using the START button.
 * @author Ian Fox */
public class Start implements ActionListener {
    /** the Start instance constructor takes in DIS display.*/
    Start(Display dis) {
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            if (_dis.whoseMove() == RED) {
                _dis.setDirections("Red's move");
                onButtons(RED);
                offButtons(BLUE);
            } else {
                _dis.setDirections("Blue's move");
                onButtons(BLUE);
                offButtons(RED);
            }
        }
    }
    /** options function which takes in a COL and turns off these buttons.*/
    private void offButtons(jump61.Color col) {
        SquareButton[][] b = _dis.getGBoard();
        if (col == RED) {
            for (int r = 0; r < _dis.gttBsize(); r++) {
                for (int c = 0; c < _dis.gttBsize(); c++) {
                    SquareButton here = b[r][c];
                    if (here.getColor() == RED) {
                        here.setIcon(here.getIcon());
                        here.setDisabledIcon(here.getIcon());
                        here.setEnabled(false);
                    }
                }
            }
        } else if (col == BLUE) {
            for (int r = 0; r < _dis.gttBsize(); r++) {
                for (int c = 0; c < _dis.gttBsize(); c++) {
                    SquareButton here = b[r][c];
                    if (here.getColor() == RED) {
                        here.setIcon(here.getIcon());
                        here.setDisabledIcon(here.getIcon());
                        here.setEnabled(false);
                    }
                }
            }
        }
    }
    /** another option fucntion which turns all the buttons off in a the gui.
     * acorrding to COL*/
    private void onButtons(jump61.Color col) {
        SquareButton[][] b = _dis.getGBoard();
        if (col == RED) {
            for (int r = 0; r < _dis.gttBsize(); r++) {
                for (int c = 0; c < _dis.gttBsize(); c++) {
                    SquareButton here = b[r][c];
                    if (here.getColor() == RED) {
                        here.setEnabled(true);
                    }
                }
            }
        } else if (col == BLUE) {
            for (int r = 0; r < _dis.gttBsize(); r++) {
                for (int c = 0; c < _dis.gttBsize(); c++) {
                    SquareButton here = b[r][c];
                    if (here.getColor() == BLUE) {
                        here.setEnabled(true);
                    }
                }
            }
        }
    }
    /** the field that stores the Display.*/
    private Display _dis;
}
