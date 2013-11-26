package jump61;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static jump61.Color.*;
/** the ation listener for auto checkbox.
 * @author Ian Fox */
public class AutoBlue implements ItemListener {
    /** the way to construct an new auto check box listerner.
     * by taking in DIS */
    AutoBlue(Display dis) {
        _dis = dis;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            _dis.getAutoR().setEnabled(false);
            _dis.getAutoR().setSelected(false);
            Player blue = new AI(new Game(null), BLUE);
            _dis.setBlue(blue);
            if (!_dis.getRed().human()) {
                while (_dis.getWinner() == null) {
                    if (_dis.getB().whoseMove() == RED) {
                        _dis.getB().addMove();
                        Board copy = _dis.copyGboard();
                        int[] move = ((AI) _dis.getRed()).makeGMove(copy);
                        _dis.addSpot(RED, move[0], move[1]);
                        _dis.update();
                        try {
                            Thread.sleep(ONE_THOU);
                        } catch (InterruptedException x) {
                            x.printStackTrace();
                        }
                    } else {
                        _dis.getB().addMove();
                        Board copy = _dis.copyGboard();
                        int[] move = ((AI) _dis.getBlue()).makeGMove(copy);
                        _dis.addSpot(BLUE, move[0], move[1]);
                        try {
                            Thread.sleep(ONE_THOU);
                        } catch (InterruptedException x) {
                            x.printStackTrace();
                        }
                    }
                    try {
                        System.out.println("here");
                        Thread.sleep(ONE_THOU);
                    } catch (InterruptedException x) {
                        x.printStackTrace();
                    }
                }
            }
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            _dis.getAutoR().setEnabled(true);
            Player blue = new HumanPlayer(new Game(null), BLUE);
            _dis.setBlue(blue);
        }
    }
    /** the display of this. */
    private Display _dis;
    /** time to delay screen update.*/
    private static final int ONE_THOU = 1000;
}
