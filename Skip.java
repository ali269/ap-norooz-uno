package midterm.uno;

/**
 * this is a class that create a Skip card object
 */
public class Skip extends MoveCard {
    /**
     * create a new card
     *
     * @param color       color of card
     */
    protected Skip(Color color) {
        super("SKIP", color, CardSpecies.SKIP);
    }
}
