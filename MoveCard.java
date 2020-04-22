package midterm.uno;

import java.util.ArrayList;

/**
 * this is a class that implement common methods of move cards
 */
public abstract class MoveCard extends Card {

    /**
     * create a new card
     *
     * @param description string description of card
     * @param color       color of card
     * @param species     species of card
     */
    protected MoveCard(String description, Color color, CardSpecies species) {
        super(description, color, 20, species);
    }

    @Override
    public boolean couldAdd(Card card, int cards) {
        return description.equals(card.getDescription()) || color.equals(card.getColor());
    }
}
