package midterm.uno;

/**
 * this class create turning wise for game
 *
 * @author alireza sahragard
 * @since 2020-4-14
 */
public class TurnWise {
    public static final String CLOCKWISE = "CLOCK";
    public static final String ANTICLOCKWISE = "ANTI-CLOCK";

    private String wise;

    /**
     * create a turn wise
     * @param s string turn code;
     */
    public TurnWise(String s) {
        wise = s;
    }

    /**
     * gets turn wise
     * @return turn wise
     */
    public String getWise() {
        return wise;
    }

    /**
     * change turn wise of game
     * @param s turn wise
     */
    public void changeWise(String s) {
        wise = s;
    }
}
