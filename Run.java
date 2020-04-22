package midterm.uno;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        setColor();
        boolean flag = true;
        while (flag) {
            System.out.println("WELCOME TO UNO:");
            System.out.println();
            System.out.println("[1] start a game:");
            System.out.println("[0] exit");
            int processKey;
            Scanner scanner = new Scanner(System.in);
            processKey = scanner.nextInt();
            switch (processKey) {
                case 1: {
                    Game game = new Game();
                    int gamerNum;
                    System.out.println("please enter number of players:");
                    gamerNum = scanner.nextInt();
                    if (!(gamerNum < 8 && gamerNum > 2)) {
                        System.out.println("invalid number!!");
                        break;
                    }
                    System.out.println("please enter number of live gamers:");
                    int playerNum = scanner.nextInt();
                    if (!(playerNum <= gamerNum && playerNum >= 0)) {
                        System.out.println("invalid number");
                        break;
                    }

                    for (int i = 0; i < playerNum; i++) {
                        System.out.println("please enter player name");
                        String s = scanner.next();
                        game.addGamer(new Gamer(s, i + 1));
                    }
                    for (int i = playerNum; i < gamerNum; i++) {
                        game.addGamer(new Player(i + 1));
                    }
                    game.start();
                    game.contributeCards();


                    while (game.checkGame()) {
                        System.out.println("**********start***********");
                        game.printRole();
                        game.print();
                        game.rolePlay();
                        System.out.println("*-------------end---------");
                        //cls();
                        //scanner.nextInt();
                    }

                    game.printFinal();


                    break;
                }
                case 0: {
                    flag = false;
                    break;
                }
                default: {
                    System.out.println("invalid key");
                }
            }
        }
    }


    public static void setColor() {
        try {
            new ProcessBuilder("cmd", "/c" , "color").inheritIO().start().waitFor();
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
