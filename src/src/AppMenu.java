import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppMenu {
    public static void main(String[] args) {

        AppMenu app = new AppMenu();
        app.start();
    }

    public void start(){
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display All Players\n"
                + "2. \n"
                + "3. \n"
                + "4. Exit\n"
                + "Enter Option [1,4]";

        final int PLAYERS = 1;
        final int TWO = 2;
        final int THREE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PLAYERS:
                        ArrayList<Player> playerList = new ArrayList<>();

                        playerList.add(new Player(1, "ZJ Lee", 25, 185.50f, 85.20f));
                        playerList.add(new Player(2, "V.Axelson", 25, 185.50f, 85.20f));
                        playerList.add(new Player(3, "K.Momota", 25, 185.50f, 85.20f));
                        playerList.add(new Player(4, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(5, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(6, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(7, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(8, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(9, "LZJ", 25, 185.50f, 85.20f));
                        playerList.add(new Player(10, "LZJ", 25, 185.50f, 85.20f));

                        System.out.println("_______________________________________________________________________________________________");
                        System.out.println("|  Player ID  |         Player Name         |  Player Age  |  Player Height  |  Player Weight |");
                        System.out.println("===============================================================================================");
                        for(Player p : playerList){
                            System.out.printf("|      %-6d |\t\t      %-12s\t    | %7d\t   | %10.2f\t     | %10.2f\t  |\n",p.getPlayerID(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getPlayerWeight());
                        }
                        System.out.println("===============================================================================================");
                        break;
                    case TWO:
                        System.out.println("Vehicles option chosen");
                        break;
                    case THREE:
                        System.out.println("Bookings option chosen");
                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }
}
