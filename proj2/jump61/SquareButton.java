
package jump61;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import static jump61.Color.*;
import java.awt.Color;
/** the SquareButton is essential for the image processing Component.
 * of the GUI it implements it wells and does the job.
 * @author Ian Fox */
public class SquareButton extends JButton implements SquareInt {
    /** the Constructor of a SquareButton takes in a Board B,
     * the ROW and COL and DIS , display of this squarebutton. */
    SquareButton(Board b, int row, int col, Display dis) {
        super();
        _color = WHITE;
        _spots = 0;
        _board = b;
        _row = row;
        _col = col;
        _dis = dis;
        addActionListener(new Push(_dis, _row, _col));
    }

    /** this function adds VAL amount of spots to the curr spots.*/
    public void addSpot(int val) {
        _spots += val;
    }
    /** this is essential the change of image portion of the GUI.
     * takes in a COL*/
    public void setImage(jump61.Color col) {
        int val = getSpots();
        String path = "";
        if (col == WHITE) {
            path = "jump61/images.png";
        } else if (col == RED) {
            if (val == 1) {
                path = "jump61/oneRed.png";
            } else if (val == 2) {
                path = "jump61/twoRed.png";
            } else if (val == 3) {
                path = "jump61/threeRed.png";
            } else {
                path = "jump61/fourRed.png";
            }
        } else {
            if (val == 1) {
                path = "jump61/oneBlue.png";
            } else if (val == 2) {
                path = "jump61/twoBlue.png";
            } else if (val == 3) {
                path = "jump61/threeBlue.png";
            } else {
                path = "jump61/fourBlue.png";
            }
        }
        File file = new File(path);
        try {
            BufferedImage image = ImageIO.read(file);
            _icon = new ImageIcon(path);
            this.setIcon(_icon);
        } catch (IOException t) {
            setText(getColor() + Integer.toString(getSpots()));
            if (getColor() == RED) {
                setBackground(Color.RED);
            } else if (getColor() == BLUE) {
                setBackground(Color.BLUE);
            } else {
                setBackground(Color.WHITE);
            }
        }
    }
    /** this funcion returns the INT number of spots. */
    public int getSpots() {
        return _spots;
    }

    /** substracts the VAL from num of spots.*/
    public void subSpot(int val) {
        if (val > _spots) {
            GameException.error("cant do dis");
        } else {
            _spots -= val;
        }
    }
    /** this function returns the @return COLOR of the square. */
    public jump61.Color getColor() {
        return _color;
    }
    /** this function changes the color to COL. */
    public void changeColor(jump61.Color col) {
        _color = col;
        setImage(_color);
    }
    /** returns the IMAGEICON of this.*/
    public ImageIcon getIcon() {
        return _icon;
    }
    /** the field which maintains the color of this.*/
    private jump61.Color _color;
    /** the number of spots.*/
    private int _spots;
    /** the board repre.*/
    private Board _board;
    /** the row.*/
    private int _row;
    /** the column.*/
    private int _col;
    /** the display for this button.*/
    private Display _dis;
    /** the imageicon of this button.*/
    private ImageIcon _icon;
}
