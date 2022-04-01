package OOPCA5.Part1;

import OOPCA5.DAOs.MySqlPlayerDao;
import OOPCA5.DAOs.PlayerDaoInterface;
import OOPCA5.Exceptions.DaoException;
import java.io.IOException;
import java.util.*;

public class AppMenu {
    public static void main(String[] args) {

        AppMenu app = new AppMenu();
        app.start();
    }

    public void start() {
        try {
            displayMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /******************************************************************************************************************************
     ****************************************************ALL DISPLAY MENU BELOW****************************************************
     ******************************************************************************************************************************/

    //MAIN MENU
    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
                + "1. Display Menu for OOPCA5 PART ONE\n"
                + "2. Display Menu for OOPCA5 PART TWO\n"
                + "3. Exit\n"
                + "Enter Option [1,3]";

        final int PART1 = 1;
        final int PART2 = 2;
        final int EXIT = 3;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PART1:
                        System.out.println("\nDisplaying Menu for OOPCA5 Part 1 :");
                        displayerPart1Menu();

                        break;
                    case PART2:
                        System.out.println("\nDisplaying Menu for OOPCA5 Part 2 : ");
                        displayPart2Menu();

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

    //PART ONE MENU
    private void displayerPart1Menu() throws IOException {

        final String MENU_ITEMS = "*** MAIN MENU OF OPTIONS FOR OOPCA5 PART 1***\n"
                + "1. Display All Players\n"
                + "2. (HashMap) Display Player Table and Career Win By Country\n"
                + "3. (TreeMap) Display Player List by Tournament ID\n"
                + "4. (PriorityQueue) Display Player by Age priority\n"
                + "5. (PriorityQueue TwoFields) Display Player by Age within Name priority\n"
                + "6. Exit\n"
                + "Enter Option [1,6]";

        final int PLAYERS = 1;
        final int TWO = 2;
        final int THREE = 3;
        final int FOUR = 4;
        final int FIVE = 5;
        final int EXIT = 6;

        Scanner keyboard = new Scanner(System.in);
        App app = new App();
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PLAYERS:
                        System.out.println("\nDisplaying Table for Player :");
                        app.displayArrayList();

                        break;
                    case TWO:
                        System.out.println("\nDisplaying Player Table List by Country : ");
                        app.playerHashMap();

                        break;
                    case THREE:
                        System.out.println("\nDisplaying Player List by Tournament ID : ");
                        app.playerTreeMap();

                        break;
                    case FOUR:
                        System.out.println("\nDisplaying Player List by AGE priority : ");
                        app.playerPriorityQueue();

                        break;
                    case FIVE:
                        System.out.println("\nDisplaying Player List by AGE within NAME priority : ");
                        app.playerPriorityQueueTwoField();

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

        System.out.println("\nExiting to Main Menu.");
    }

    //PART 2 MENU
    private void displayPart2Menu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTION FOR OOPCA5 PART 2***\n"
                + "1. Find ALL Players in Database\n"
                + "2. Find Players by WORLD RANKING\n"
                + "3. Delete Player by ID\n"
                + "4. ADD Player \n"
                + "5. Find ALL Players by FILTER\n"
                + "6. Return ALL Players by JSON\n"
                + "7. Return Players by ID JSON\n"
                + "8. Exit\n"
                + "Enter Option [1,8]";

        final int DISPLAYALL = 1;
        final int DISPLAYWORLDRANK = 2;
        final int DELETEPLAYER = 3;
        final int ADDPLAYER = 4;
        final int FILTERPLAYER = 5;
        final int DISPLAYJSON = 6;
        final int DISPLAYIDJSON = 7;
        final int EXIT = 8;

        Scanner keyboard = new Scanner(System.in);
        App app = new App();
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAYALL:
                        app.findAllPlayer();

                        break;
                    case DISPLAYWORLDRANK:
                        app.findPlayerByWorldRank();

                        break;
                    case DELETEPLAYER:
                        app.deletePlayerById();

                        break;
                    case ADDPLAYER:
                        app.addPlayer();

                        break;
                    case FILTERPLAYER:
                        app.findPlayerByFilter();

                        break;
                    case DISPLAYJSON:
                        app.findAllPlayersJson();

                        break;
                    case DISPLAYIDJSON:
                        app.findPlayerByIdJson();

                        break;
                    case EXIT:
                        System.out.println("Exit Menu option chosen");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

            } catch (InputMismatchException |
                    NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu.");
    }
}


