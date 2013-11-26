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
 *@author Ian Fox.*/
public class SetGui implements ActionListener {
    /**the instance variable in which resize DIS.*/
    SetGui(Display dis) {
        super();
        _s = "";
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField sizer = _dis.getSet();
        String s = sizer.getText();
        sizer.setText("");
        String[] actions = s.trim().split("\\s+");
        int r = 0, c = 0, n = 0;
        jump61.Color col = null;
        if (actions.length >= 4 && actions[0].matches("[1-9]")
            && actions[1].matches("[1-9]") && actions[2].matches("[0-9]")) {
            if (Integer.parseInt(actions[0]) <= _dis.gttBsize()) {
                r = Integer.parseInt(actions[0]);
            }
            if (Integer.parseInt(actions[1]) <= _dis.gttBsize()) {
                c = Integer.parseInt(actions[1]);
            }
            if (Integer.parseInt(actions[2]) <= _dis.neighbors(r - 1, c - 1)) {
                n = Integer.parseInt(actions[2]);
            }
            if (actions[3].equals("r")) {
                col = RED;
            }
            if (actions[3].equals("b")) {
                col = BLUE;
            }
            if (n == 0) {
                col = WHITE;
            }
            if (r == 0 || c == 0 || col == null) {
                Runner x = new Runner();
                SwingUtilities.invokeLater(x);
            } else {
                if (col == WHITE) {
                    _dis.getGBoard()[r - 1][c - 1] =
                        new SquareButton(_dis.getB(), r - 1, c - 1, _dis);
                    _dis.getGBoard()[r - 1][c - 1].changeColor(WHITE);
                    _dis.update();
                } else {
                    while (n > 0) {
                        _dis.addSpot(col, r - 1, c - 1);
                        n -= 1;
                    }
                }
            }
        } else {
            Runner x = new Runner();
            SwingUtilities.invokeLater(x);
        }
    }
    /** the @return STRING in which we will set to the new size.*/
    public String getS() {
        return _s;
    }
    /** the string which we will set.*/
    private String _s;
    /** the display in which we will modify.*/
    private Display _dis;
    /** the Runner class which will output erroneous inputs.*/
    private class Runner implements Runnable {
        /** the method in which we invoke.*/
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>Error</h1>"
                + "Bad Set Syntax";
            JPanel p = new JPanel(new BorderLayout());
            int width = WID;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }
    /** WID.*/
    private static final int WID = 150;
}
