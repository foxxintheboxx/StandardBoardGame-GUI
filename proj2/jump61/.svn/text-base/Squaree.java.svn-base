package jump61;
import static jump61.Color.*;
/** the square class which represents a unique square withing Jump61.
 * @author Ian Fox
 */
public class Squaree implements SquareInt {
    /** the constructor of square initially makes everything whit.
     * and zero spots. */
    Squaree() {
        _color = WHITE;
        _spots = 0;
    }
    /** this function adds VAL amount of spots to the curr spots.*/
    public void addSpot(int val) {
        _spots += val;
    }
    /** this function changes the color to COL. */
    public void changeColor(Color col) {
        _color = col;
    }
    /** this function returns the @return COLOR of the square. */
    public Color getColor() {
        return _color;
    }
    /** this funcion returns the INT number of spots. */
    public int getSpots() {
        return _spots;
    }
    /** this function returns the STRING representation. */
    public String repr() {
        if (_color == WHITE && _spots == 0) {
            return "--";
        } else {
            String spots = Integer.toString(_spots);
            String color;
            if (_color == RED) {
                color = "r";
            } else {
                color = "b";
            }
            return spots + color;
        }
    }
    /** @return SQUARE identical to this. */
    public Squaree copy() {
        Squaree cop = new Squaree();
        int spot = getSpots();
        cop.addSpot(spot);
        Color col = getColor();
        cop.changeColor(col);
        return cop;
    }
    /** substracts the VAL from num of spots.*/
    @Override
    public void subSpot(int val) {
        if (val > _spots) {
            GameException.error("cant do dis");
        } else {
            _spots -= val;
        }
    }
    /** the field which maintains the color of this.*/
    private Color _color;
    /** the number of spots.*/
    private int _spots;
    /** the number of something.*/
    private int _mon;
}

