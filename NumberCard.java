package midterm.uno;

import java.util.ArrayList;

/**
 * this is a class that create number card object
 */
public class NumberCard extends Card {
    /**
     * create a new card
     *
     * @param description string description of card
     * @param color       color of card
     */
    protected NumberCard(String description, Color color) {
        super(description, color, Integer.parseInt(description), CardSpecies.NUMBER_CARD);
    }

    @Override
    public boolean couldAdd(Card card, int cards) {
        return description.equals(card.getDescription()) || color.equals(card.getColor());
    }
}
