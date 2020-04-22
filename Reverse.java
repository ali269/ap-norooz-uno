package midterm.uno;

/**
 * this is a class that create reverse cards object
 */
public class Reverse extends MoveCard {
    /**
     * create a new card
     *
     * @param color       color of card
     */
    protected Reverse(Color color) {
        super("REVERSE", color, CardSpecies.REVERSE);
    }
}
