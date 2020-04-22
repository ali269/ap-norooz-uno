package midterm.uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

/**
 * all actions of uno game
 */
public class Action {
    /**
     * go to next player
     * @param play playing ring
     * @param wise turning wise
     * @return next player
     */
    protected Gamer next(ListIterator<Gamer> play, TurnWise wise) {
        switch (wise.getWise()) {
            case TurnWise.CLOCKWISE:
                return play.next();
            case TurnWise.ANTICLOCKWISE:
                return play.previous();
            default:
                return null;
        }
    }

    /**
     * skip a player
     * @param play playing ring
     * @param wise turning wise
     * @return next player
     */
    protected Gamer skip(ListIterator<Gamer> play, TurnWise wise) {
        switch (wise.getWise()) {
            case TurnWise.CLOCKWISE:
                play.next();
                return play.next();
            case TurnWise.ANTICLOCKWISE:
                play.previous();
                return play.previous();
            default:
                return null;
        }
    }

    /**
     * change game color
     * @param gamer gamer who change
     */
    protected void chooseColor(Gamer gamer, Card board) {
        Color c = gamer.setColor();
        board.setColor(c);
    }

    /**
     * reverse game turning wise
     * @param wise game turn wise
     */
    protected void reverse(TurnWise wise) {
        switch (wise.getWise()) {
            case TurnWise.CLOCKWISE:
                wise.changeWise(TurnWise.ANTICLOCKWISE);
                return;
            case TurnWise.ANTICLOCKWISE:
                wise.changeWise(TurnWise.CLOCKWISE);
        }
    }

    /**
     * assess surcharge cards to gamer
     * @param gamer gamer which is assessed
     * @param cards surcharge cards
     */
    protected void draw(Gamer gamer, ArrayList<Card> cards) {
        Iterator<Card> it = cards.iterator();
        while(it.hasNext()) {
            gamer.addCard(it.next());
            it.remove();
        }
    }

    /**
     * adds a surcharge card
     * @param mine all cards in mine
     * @param sur all surcharge card
     * @param num num of surcharge cards to add
     */
    protected void addSurcharge(ArrayList<Card> mine, ArrayList<Card> sur, int num) {
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            int rand = random.nextInt(mine.size());
            sur.add(mine.remove(rand));
        }
    }


}
