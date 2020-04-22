package midterm.uno;

/**
 * this class make a visible work for user
 */
public class Show {
    protected String fgColor = Color.ANSI_PURPLE;
    protected String description;
    protected String bgColor;

    /**
     * create show fields
     * @param description show object description
     * @param color color of object
     */
    public Show(String description, Color color) {
        this.description = description;
        bgColor = color.getColor();
    }

    public void show() {
        System.out.println(description + bgColor + Color.ANSI_RESET);
    }
}
