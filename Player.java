package midterm.uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * create a virtual player
 *
 * @author alireza sahragard
 * @since 2020-4-16
 */
public class Player extends Gamer {

    private ArrayList<Card> addableCards = new ArrayList<>();

    /**
     * construct a new gamer
     *
     * @param queue string gamer queue
     */
    public Player(int queue) {
        super("PLAYER" + queue, queue);
    }

    /**
     * set the addable cards
     */
    private void setAddableCards() {
        int b = cardsAddable();
        for (Card c: cards) {
            if (c.couldAdd(cardInGame, b))
                addableCards.add(c);
        }
    }
    @Override
    public void showPublic() {
        showPrivate();
    }


    /**
     * play a card if there is one of them
     * @return a card to play
     */
    public Card getCard() {
        emptyAddableCards();
        System.out.println(addableCards);
        setAddableCards();
        Card card = addableCards.get(0);
        for (Card c: addableCards) {
            if (!c.compare(card))
                card = c;
        }
        cards.remove(card);
        return card;
    }

    /**
     * set the game color
     * @return color of game
     */
    public Color setColor() {
        Random random = new Random();
        int r = random.nextInt(4);
        r++;
        return colors.get(r);
    }

    /**
     * empty addable cards list
     */
    private void emptyAddableCards() {
        Iterator<Card> it = addableCards.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}
