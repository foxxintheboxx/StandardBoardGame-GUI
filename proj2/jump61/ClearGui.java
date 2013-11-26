package jump61;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** the actionlistner which implements the Clear method on the GUI.
 * @author Ian Fox*/
public class ClearGui implements ActionListener {
    /** the constructor which takes in DIS. */
    ClearGui(Display dis) {
        super();
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            _dis.resize(_dis.getFrame(), _dis.gttBsize());
            _dis.getB().setMoves(1);
            _dis.setDirections("");
        }
    }
    /** the display of this cleargui.*/
    private Display _dis;
}
