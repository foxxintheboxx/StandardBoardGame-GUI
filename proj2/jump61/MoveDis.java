package jump61;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static jump61.Color.*;
/** this sets the gui to a specific representation.
 * @author Ian Fox */
public class MoveDis implements ActionListener {
    /** Creates a Move action which takes in the text of Move.
     * also takes in DIS.*/
    MoveDis(Display dis) {
        super();
        _s = "";
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField sizer = _dis.getMove();
        String s = sizer.getText();
        sizer.setText("");
        if (s.matches("[0-9]+")) {
            _dis.getB().setMoves(Integer.parseInt(s));
        } else {
            Runner x = new Runner();
            SwingUtilities.invokeLater(x);
        }
    }
    /** returns STRING of move jtextfield.*/
    public String getS() {
        return _s;
    }
    /** returns the string of textfield.*/
    private String _s;
    /** the display dis.*/
    private Display _dis;
    /** a Runner which returns the Error message for a bad move.*/
    private class Runner implements Runnable {
        @Override
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>Error</h1>"
                + "Bad Move Syntax";
            JPanel p = new JPanel(new BorderLayout());
            int width = ONEFIFTY;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }
    /** WIDTH.*/
    private static final int ONEFIFTY = 150;
}
