package midterm.uno;

public class ConsoleShow extends Show {

    /**
     * create show fields
     *
     * @param description show object description
     * @param color       color of object
     */
    public ConsoleShow(String description, Color color) {
        super(description, color);
    }

    public void show() {
        for (int i = 0; i < 5; i++) {
            switch (i) {
                case 0:
                case 4:
                    System.out.print(fgColor + bgColor + "***************" + Color.ANSI_RESET);
                    break;
                case 2:
                    System.out.printf(fgColor + bgColor + "***%7s  ***" , description);
                    break;
                default:
                    System.out.print(fgColor + bgColor + "**           **" + Color.ANSI_RESET);
            }
            System.out.println(Color.ANSI_RESET);
        }
    }
}
