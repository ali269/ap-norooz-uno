package midterm.uno;

/**
 * this is a class that create draw card object
 */
public class Draw extends MoveCard {
    /**
     * create a new card
     *
     * @param color       color of card
     */
    protected Draw(Color color) {
        super("DRAW2+", color, CardSpecies.DRAW2);
    }
}
