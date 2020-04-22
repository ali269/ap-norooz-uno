package midterm.uno;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

/**
 * this class create a Uno game and control it
 *
 * @author alireza sahragard
 * @since 2020-4-16
 */
public class Game extends Action{
    private ArrayList<Card> treasure;
    private ArrayList<Card> surcharge;
    private Card boardCard;
    private Gamer gamerInRole;
    private TurnWise turnWise;
    private Cycle<Gamer> gamers;
    private ListIterator<Gamer> playing;
    private ArrayList<Color> colors;


    private final String[] cols =  {
            Color.ANSI_BG_BLUE, Color.ANSI_BG_RED,
            Color.ANSI_BRIGHT_BG_YELLOW, Color.ANSI_BG_GREEN
    };

    /**
     * construct a new game
     */
    public Game() {
        treasure = new ArrayList<>();
        surcharge = new ArrayList<>();
        turnWise = new TurnWise(TurnWise.CLOCKWISE);
        gamers = new Cycle<>();
        colors = new ArrayList<>();
        for (String s: cols) {
            colors.add(new Color(s));
        }

        for (Integer i = 0; i < 10; i++) {
            for (Color c: colors) {
                treasure.add(new NumberCard(i.toString(), c));
            }
        }

        for (Integer i = 1; i < 10; i++) {
            for (Color c: colors) {
                treasure.add(new NumberCard(i.toString(), c));
            }
        }

        for (Color c: colors) {
            for (int i = 0; i < 2; i++) {
                treasure.add(new Draw(c));
                treasure.add(new Skip(c));
                treasure.add(new Reverse(c));
            }
        }

        for(int i = 0; i < 4; i++) {
            treasure.add(new Wild());
            treasure.add(new WildDraw());
        }
    }

    /**
     * add a gamer to game
     * @param gamer gamer to add
     */
    public void addGamer(Gamer gamer) {
        gamers.add(gamer);
    }

    /**
     * contribute cards between gamers
     */
    public void contributeCards() {
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            for (Gamer gamer: gamers) {
                int rand = random.nextInt(treasure.size());
                gamer.addCard(treasure.remove(rand));
                if (gamer.equals(gamers.getLast()))
                    break;
            }
        }
    }

    /**
     * show the card in game to gamer
     */
    public void showCard() {
        gamerInRole.setCardInGame(boardCard);
    }

    /**
     * starts the game
     */
    public void start() {
        playing = gamers.iterator();
        Random random = new Random();
        boolean flag = true;
        while (flag) {
            int rand = random.nextInt(treasure.size());
            if (treasure.get(rand).getSpecies() != CardSpecies.WILD_DRAW && treasure.get(rand).getSpecies() != CardSpecies.WILD) {
                boardCard = treasure.remove(rand);
                flag = false;
            }
        }
        cardAction();
        showCard();
    }

    /**
     * plays a role of game
     */
    public void rolePlay() {
        if (gamerInRole.getNumOfAddableCards() != 0) {
            Card card = gamerInRole.getCard();
            if (card == null) {
                addSurcharge(treasure, surcharge, 1);
                draw(gamerInRole, surcharge);
                gamerInRole = next(playing, turnWise);
            }
            else {
                if (card.getSpecies() != CardSpecies.DRAW2)
                    draw(gamerInRole, surcharge);
                boardCard = card;
                cardAction();
            }
        }
        else {
            addSurcharge(treasure, surcharge, 1);
            draw(gamerInRole, surcharge);
            gamerInRole = next(playing, turnWise);
        }
        showCard();
    }

    /**
     * prints the game and it turn wise
     */
    public void printRole() {
        for (Gamer gamer: gamers) {
            if (gamer.equals(gamerInRole))
                System.out.print(Color.ANSI_GREEN + gamer.getName() + Color.ANSI_RESET);
            else
                System.out.print(gamer.getName());
            switch (turnWise.getWise()) {
                case TurnWise.CLOCKWISE:
                    System.out.print(" -->> ");
                    break;
                case TurnWise.ANTICLOCKWISE:
                    System.out.print(" <<-- ");
            }
            if (gamer.equals(gamers.getLast()))
                break;
        }
        System.out.println();
    }

    /**
     * print all users of game
     */
    public void print() {
        for (Gamer gamer: gamers) {
            if (gamer.equals(gamerInRole))
                gamer.showPublic();
            else
                gamer.showPrivate();
            if (gamer.equals(gamers.getLast()))
                break;
        }
        System.out.println();
        printCardOfGame();
        System.out.println();
        boardColor();
    }


    public void printFinal() {
        Gamer[] gamers1 = new Gamer[gamers.size()];
        int i = 0;
        for (Gamer gamer: gamers) {
            gamers1[i++] = gamer;
            if (gamer.equals(gamers.getLast()))
                break;
        }
        for (int i1 = 0; i1 < gamers1.length; i1++) {
            for (int j = 0; j < gamers1.length - 1; j++) {
                if (!gamers1[j].compare(gamers1[j + 1])) {
                    Gamer g = gamers1[j];
                    gamers1[j] = gamers1[j + 1];
                    gamers1[j + 1] = g;
                }
            }
        }

        for (Gamer gamer: gamers1) {
            if (gamer.getScore() == 0) {
                System.out.print(Color.ANSI_GREEN);
                gamer.showPrivate();
                System.out.print(Color.ANSI_RESET);
            }
            else {
                gamer.showPrivate();
            }
        }

    }


    /**
     * play cards action
     */
    private void cardAction() {
        switch (boardCard.getSpecies()) {
            case NUMBER_CARD:
                gamerInRole = next(playing, turnWise);
                break;
            case SKIP:
                gamerInRole = skip(playing, turnWise);
                break;
            case REVERSE:
                reverse(turnWise);
                gamerInRole = next(playing, turnWise);
                break;
            case DRAW2:
                addSurcharge(treasure, surcharge, 2);
                gamerInRole = next(playing, turnWise);
                break;
            case WILD:
                chooseColor(gamerInRole, boardCard);
                gamerInRole = next(playing, turnWise);
                break;
            case WILD_DRAW:
                chooseColor(gamerInRole, boardCard);
                addSurcharge(treasure, surcharge, 4);
                draw(next(playing, turnWise), surcharge);
                gamerInRole = next(playing, turnWise);
                break;
        }
    }

    /**
     * show the game card
     */
    private void printCardOfGame() {
        boardCard.show();
    }

    /**
     * checks the game status
     * @return false if game finish true otherwise
     */
    public boolean checkGame() {
        for (Gamer gamer: gamers) {
            if (gamer.getNumOfCards() == 0)
                return false;
            if (gamer.equals(gamers.getLast()))
                break;
        }
        return true;
    }

    /**
     * prints color of the board
     */
    private void boardColor() {
        String c = boardCard.getColor().getColor();
        System.out.println("BOARD COLOR:");
        for (int i = 0; i < 2; i++) {
            System.out.print(c + "     " + Color.ANSI_RESET);
            System.out.println();
        }
    }

}
