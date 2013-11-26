package jump61;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** the action Listener which respons the to the Quit button
 * on the GUI.
 * @author Ian Fox */
public class QuitDis implements ActionListener {
    /** the constructor, which takens in the DIS for reference. */
    QuitDis(Display dis) {
    super();
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            _dis.getFrame().setVisible(false);
            System.exit(0);
        }
    }
    /**the display of this quit button.*/
    private Display _dis;
}
