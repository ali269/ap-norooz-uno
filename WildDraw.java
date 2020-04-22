package midterm.uno;

import java.util.ArrayList;

public class WildDraw extends Card {
    /**
     * create a new card
     *
     */
    protected WildDraw() {
        super("DRAW4+", new Color(Color.ANSI_BG_BLACK), 50, CardSpecies.WILD_DRAW);
    }

    @Override
    public boolean couldAdd(Card card, int cards) {
        return cards == 0;
    }

    public void show() {
        Show show = new ConsoleShow(description, new Color(Color.ANSI_BG_BLACK));
        show.show();
    }
}
