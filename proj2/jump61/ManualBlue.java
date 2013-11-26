package jump61;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static jump61.Color.*;
/** the action of for the manual blue check box.
 * This implements ItemListen interface.
 * @author Ian Fox*/
public class ManualBlue implements ItemListener {
    /** the manual blue constructor takes in DIS.*/
    ManualBlue(Display dis) {
        _dis = dis;
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Player blue = new HumanPlayer(new Game(null), BLUE);
            _dis.setBlue(blue);
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            Player blue = new AI(new Game(null), BLUE);
            _dis.setBlue(blue);
        }
    }
    /** the display of manual blue action.*/
    private Display _dis;
}
