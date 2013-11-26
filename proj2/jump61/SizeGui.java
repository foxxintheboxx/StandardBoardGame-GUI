package jump61;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/** the Action Listener which allows for resizing.
 * @author Ian Fox */
public class SizeGui implements ActionListener {
    /** the instance of SizeGui that takes in a DIS play.*/
    SizeGui(Display dis) {
        super();
        _s = "";
        _dis = dis;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField sizer = _dis.gttSize();
        String s = sizer.getText();
        _dis.setSize();
        if (s.matches("([1-9])")) {
            _dis.resize(_dis.getFrame(), Integer.parseInt(s));
            _dis.saveSize(Integer.parseInt(s));
        } else {
            Runnable r = new Runner();
            SwingUtilities.invokeLater(r);
        }
    }
    /** @return STRING of the new size.*/
    public String getS() {
        return _s;
    }
    /** the string holding the new size.*/
    private String _s;
    /** the display for this image.*/
    private Display _dis;
    /** a runnable class to output html.*/
    private class Runner implements Runnable {
        /** the final in which we output the html.*/
        public void run() {
            String pt1 = "<html><body width='";
            String pt2 = "'><h1>Error</h1>"
                + "please enter digit";
            JPanel p = new JPanel(new BorderLayout());
            int width = WID;
            String s = pt1 + width + pt2;
            JOptionPane.showMessageDialog(null, s);
        }
    }
    /** WID.*/
    private static final int WID = 150;
}
