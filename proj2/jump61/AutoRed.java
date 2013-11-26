package jump61;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static jump61.Color.*;
/** the ation listener for auto checkbox.
 * @author Ian Fox */
public class AutoRed implements ItemListener {
    /** the way to construct an new auto check box listerner.
     * by taking in DIS */
    AutoRed(Display dis) {
        super();
        _dis = dis;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            boolean t = _dis.getBlue().human();
            _dis.getAutoB().setEnabled(false);
            _dis.getAutoB().setSelected(false);
            Player red = new AI(new Game(null), RED);
            if (!t) {
                Player blue = new AI(new Game(null), BLUE);
                _dis.setBlue(blue);
            }
            _dis.setRed(red);
            final int size = _dis.gttBsize();
            if (!_dis.getBlue().human()) {
                new Thread((Runnable) new AIvAI(_dis)).start();

            }
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            _dis.getAutoB().setEnabled(true);
            Player red = new HumanPlayer(new Game(null), RED);
            _dis.setRed(red);
        }
    }
    /** the special handling of AIvAI.*/
    private class AIvAI implements Runnable {
        /** creates a new AI game with the display DIS.*/
        AIvAI(Display dis) {
            _d = dis;
        }
        @Override
        public void run() {
            try {
                while (_d.getWinner() == null) {
                    if (_d.getB().whoseMove() == RED) {
                        _d.getB().addMove();
                        Board copy = _d.copyGboard();
                        int[] move = ((AI)
                            _d.getRed()).makeGMove(copy);
                        _move = checkMove(move, RED);
                        _d.addSpot(RED, move[0], move[1]);
                        try {
                            Thread.sleep(WAIT);
                        } catch (InterruptedException x) {
                            x.printStackTrace();
                        }
                    } else {
                        _d.getB().addMove();
                        Board copy = _d.copyGboard();
                        int[] move = ((AI)
                            _d.getBlue()).makeGMove(copy);
                        move = checkMove(move, BLUE);
                        _d.addSpot(BLUE, move[0], move[1]);

                    }
                }
            } catch (ClassCastException x) {
                System.out.println("change of players during AIvAI");
            }

        }
        /** the display of this field.*/
        private Display _d;
    }
    /** the way we check if a certain MOVE is within the params of the game.
     * and the particular COL. this @return a INT[]*/
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
    /** the display of this. */
    private Display _dis;
    /** the current move.*/
    private int[] _move;
    /** wait time .*/
    private static final int WAIT = 1500;
}

