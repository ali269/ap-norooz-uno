package midterm.uno;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * this class creates a new game
 */
public class Gamer {
    protected String name;
    protected Card cardInGame;
    protected ArrayList<Card> cards;
    protected int queue;
    protected final HashMap<Integer, Color> colors;



    /**
     * construct a new gamer
     * @param name string gamer name
     * @param queue string gamer queue
     */
    public Gamer(String name, int queue) {
        this.queue = queue;
        this.name = name;
        cards = new ArrayList<>();
        colors = new HashMap<>();
        colors.put(1, new Color(Color.ANSI_BG_BLUE));
        colors.put(2, new Color(Color.ANSI_BG_RED));
        colors.put(3, new Color(Color.ANSI_BG_GREEN));
        colors.put(4, new Color(Color.ANSI_BRIGHT_BG_YELLOW));
    }

    /**
     * gets the name of gamer
     * @return string name
     */
    public String getName() {
        return name;
    }

    /**
     * add a card to cards
     * @param c card to add
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    /**
     * gets card's number
     * @return int number of cards
     */
    public int getNumOfCards() {
        return cards.size();
    }

    /**
     * set's the card in game
     * @param card card in the board
     */
    public void setCardInGame(Card card) {
        cardInGame = card;
    }

    /**
     * gets the number of cards in the hand of gamer and could play them
     * @return int number of cards
     */
    public int getNumOfAddableCards() {
        int num = 0;
        int b = cardsAddable();
        for (Card c: cards) {
            if (c.couldAdd(cardInGame, b))
                num++;
        }
        return num;
    }

    /**
     * gets the total score of gamer
     * @return int total score
     */
    public int getScore() {
        int score = 0;
        for (Card card: cards) {
            score += card.getScore();
        }
        return score;
    }

    /**
     * show gamer's info in a private way
     */
    public void showPrivate() {
        System.out.println("NAME: " + name + " |SCORE: " + getScore() + " |CARDS-NUM: " + getNumOfCards());
    }

    /**
     * show gamer's info in a public way
     */
    public void showPublic() {
        showPrivate();
        int i = 0;
        int b = cardsAddable();
        for (Card c: cards) {
            if (c.couldAdd(cardInGame, b))
                System.out.println(Color.ANSI_GREEN + "[" + (i + 1) + "]" + Color.ANSI_RESET);
            else {
                System.out.println("[" + (i + 1) + "]");
            }
            c.show();
            i++;
        }
    }

    /**
     * gets a cards of user
     * @return card of user to play
     */
    public Card getCard() {
        int cardIndex;
        System.out.println("please enter the number of card you want to add:");
        Scanner scanner = new Scanner(System.in);
        cardIndex = scanner.nextInt();
        cardIndex--;
        int b = cardsAddable();
        if (cardIndex < getNumOfCards() && cards.get(cardIndex).couldAdd(cardInGame, b))
            return cards.remove(cardIndex);
        else
            return null;
    }

    /**
     * gets num of addable cards those are not wild draw card
     * @return num of common cards
     */
    protected int cardsAddable () {
        int num = 0;
        for(Card c: cards) {
            if (c.getSpecies() != CardSpecies.WILD_DRAW) {
                if (c.couldAdd(cardInGame, 0))
                    num++;
            }
        }
        return num;
    }


    /**
     * sets the color of the game
     * @return color to set color of game
     */
    public Color setColor() {
        System.out.println("please choose one of colors");
        System.out.print("{");
        int i = 1;
        for (Color c: colors.values()) {
            System.out.print(c.getColor() + "  " + i + "  " + Color.ANSI_RESET + " | ");
            i++;
        }
        System.out.println("}");
        int a;
        Scanner scanner = new Scanner(System.in);
        a = scanner.nextInt();
        return colors.get(a);
    }

    /**
     * compare two gamers
     * @param gamer gamer to compare
     * @return true if better than the other false other wise
     */
    public boolean compare(Gamer gamer) {
        return getScore() < gamer.getScore();
    }

    /**
     * check the equality of two gamer
     * @param gamer gamer to check
     * @return true if they are same false otherwise
     */
    public boolean equals(Gamer gamer) {
        return name.equals(gamer.getName());
    }
}
