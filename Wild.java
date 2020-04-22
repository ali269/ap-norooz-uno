package midterm.uno;

import java.util.ArrayList;

public class Wild extends Card {
    /**
     * create a new card
     *
     */
    protected Wild() {
        super("WILD", new Color(Color.ANSI_BG_BLACK), 50, CardSpecies.WILD);
    }

    @Override
    public boolean couldAdd(Card card, int cards) {
        return true;
    }

    public void show() {
        Show show = new ConsoleShow(description, new Color(Color.ANSI_BG_BLACK));
        show.show();
    }
}
