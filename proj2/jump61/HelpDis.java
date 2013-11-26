package jump61;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** this class represents the action listener for the help button.
 * outputs an html frame telling what you should do.
 * @author Ian Fox */
public class HelpDis implements ActionListener {
    /** the helpdis constructor which takes in DIS.*/
    HelpDis(Display dis) {
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() != null) {
            Runnable r = new Runner();
            SwingUtilities.invokeLater(r);
        }
    }
    /** the class runner is runnable file which out puts the help.
     * message .*/
    private class Runner implements Runnable {
        @Override
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>Help</h1>"
                + "<h2>#:</h2>"
                + "A comment; this has no effect"
                + "<h2>start:</h2>"
                + "Start playing the game from the"
                + "current position. This has no effect if already."
                + "playing."
                + "<h2>quit:</h2>"
                + "exits the program."
                + "<h2>auto:</h2>"
                + "Stops the current game until the"
                + "next START command and causes player P to be"
                + "played by an automated player (an AI) on subsequent"
                + "moves. The value P must be \"red\" or blue. Initially,"
                + "Blue is an automated player."
                + "<h2>manual</h2>"
                + "Stops the current game until the next start"
                + "command and cause player P to take moves from the terminal"
                + "<h2>size</h2>"
                + "stops any current game and clears the current configuration"
                + " and sets the size of the board to N squares. Initially,"
                + "N = 6."
                + "<h2>move</h2>"
                + "stop any current game, set the number of the next move you"
                + " usually use this command after setting up an initial posn"
                + "Initially the number of the next move is 1"
                + "<h2>set</h2>"
                + "R C N P: sets the current board and the square R,"
                + "C with N spots and the color P."
                + "<p>The body width in this text is set to ";

            JPanel p = new JPanel(new BorderLayout());
            int width = WID;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }

    /** the field that holds the display.*/
    private Display _dis;
    /** the WID.*/
    private static final int WID = 450;
}
