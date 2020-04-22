package midterm.uno;

import java.util.ArrayList;

/**
 * this class create the cards of game
 */
public abstract class Card {
    protected String description;
    protected Color color;
    protected int score;
    protected CardSpecies species;

    /**
     * create a new card
     * @param description string description of card
     * @param color color of card
     * @param score score of card
     * @param species species of card
     */
    protected Card(String description, Color color, int score, CardSpecies species) {
        this.description = description;
        this.color = color;
        this.score = score;
        this.species = species;
    }

    /**
     * set the color of card
     * @param color color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * gets description of card
     * @return string card description
     */
    public String getDescription() {
        return description;
    }

    /**
     * gets the color of card
     * @return color of card
     */
    public Color getColor() {
        return color;
    }

    /**
     * gets score of card
     * @return score of card
     */
    public int getScore() {
        return score;
    }

    /**
     * gets card species
     * @return card species
     */
    public CardSpecies getSpecies() {
        return species;
    }

    /**
     * compare two cards
     * @param card card to compare
     * @return true if they are same false otherwise
     */
    public boolean equals(Card card) {
        return description.equals(card.getDescription());
    }

    /**
     * check if player could add this card or not
     * @param card the card in game
     * @param cards cards in player hand and could add them
     * @return true if could add false otherwise
     */
    public abstract boolean couldAdd(Card card, int cards);

    /**
     * show the card in console
     */
    public void show() {
        Show show = new ConsoleShow(description, color);
        show.show();
    }

    /**
     * compare to cards
     * @param card card to compare
     * @return true if this is less dangerous false otherwise
     */
    public boolean compare(Card card) {
        return score < card.getScore();
    }
}
