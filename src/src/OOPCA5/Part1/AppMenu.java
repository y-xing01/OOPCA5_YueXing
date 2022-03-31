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
        final int DISPLAYAGE = 2;
        final int DELETEPLAYER = 3;
        final int ADDPLAYER = 4;
        final int FILTERPLAYER = 5;
        final int DISPLAYJSON = 6;
        final int DISPLAYIDJSON = 7;
        final int EXIT = 8;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                PlayerDaoInterface PlayerDao = new MySqlPlayerDao();
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case DISPLAYALL:
                        System.out.println("\n");
                        try {
                            System.out.println("\nFind ALL Players : ");
                            ArrayList<Player> playerList = PlayerDao.findAllPlayers();

                            if (playerList.isEmpty())
                                System.out.println("There are no Players");
                            else {
                                System.out.println("________________________________________________________________________________________________________");
                                System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  | Player Career Win |");
                                System.out.println("========================================================================================================");
                                for (Player p : playerList)
                                    System.out.printf("|         %-6d    |\t    %-12s\t\t  | %7d\t     | %10.2f\t   | \t%7d\t       |\n", p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getCareerWin());
                                System.out.println("========================================================================================================");
                            }
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        break;
                    case DISPLAYAGE:
                        try {
                            System.out.println("\nFind Players by WORLD RANKING");
                            System.out.println("Please enter WORLD RANKING : ");
                            int wRank = keyboard.nextInt();

                            ArrayList<Player> playerList = PlayerDao.findPlayerByWorldRanking(wRank);

                            if (playerList.isEmpty()) {
                                System.out.println("There are no Players that is ranked " + wRank);
                            } else {
                                System.out.println("\nPlayer Age has rank of " + wRank + " : ");
                                System.out.println("________________________________________________________________________________________________________");
                                System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  | Player Career Win |");
                                System.out.println("========================================================================================================");
                                for (Player p : playerList)
                                    System.out.printf("|         %-6d    |\t    %-12s\t\t  | %7d\t     | %10.2f\t   | \t%7d\t       |\n", p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getCareerWin());
                                System.out.println("========================================================================================================");
                            }
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        keyboard.nextLine();
                        break;
                    case DELETEPLAYER:
                        try {
                            System.out.println("\nDELETE Player by ID");
                            System.out.println("Please enter player ID to delete (1-10) : ");
                            int player_id = keyboard.nextInt();
                            if (PlayerDao.deletePlayerById(player_id) == true)
                                System.out.println("Player ID : " + player_id + " is deleted.");
                            else
                                System.out.println("Player with that ID is not found.");
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        keyboard.nextLine();
                        break;
                    case ADDPLAYER:
                        try {
                            System.out.println("\nADD Player");
                            System.out.println("Please enter player WORLD RANKING : ");
                            int worldRank = keyboard.nextInt();

                            System.out.println("Please enter player NAME : ");
                            String playerName = keyboard.next();

                            System.out.println("Please enter player AGE : ");
                            int playerAge = keyboard.nextInt();

                            System.out.println("Please enter player Height : ");
                            float playerHeight = keyboard.nextFloat();

                            System.out.println("Please enter player Career Won : ");
                            int playerCareerWon = keyboard.nextInt();

                            Player player = PlayerDao.addPlayer(new Player(worldRank, playerName, playerAge, playerHeight, playerCareerWon));

                            if (player != null)
                                System.out.println(player + " is ADDED.");
                            else {
                                System.out.println("Player is NOT ADDED");
                            }

                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        keyboard.nextLine();
                        break;
                    case FILTERPLAYER:
                        try {
                            System.out.println("\nDisplay ALL Players filtered by AGE COMPARATOR : ");
                            ArrayList<Player> playerList = PlayerDao.findPlayerByFilter(new ageComparator(SortType.Ascending));

                            if (playerList.isEmpty()) {
                                System.out.println("There are no Players");
                            } else {
                                System.out.println("________________________________________________________________________________________________________");
                                System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  | Player Career Win |");
                                System.out.println("========================================================================================================");
                                for (Player p : playerList)
                                    System.out.printf("|         %-6d    |\t    %-12s\t\t  | %7d\t     | %10.2f\t   | \t%7d\t       |\n", p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getCareerWin());
                                System.out.println("========================================================================================================");
                            }
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        break;
                    case DISPLAYJSON:
                        try {
                            System.out.println("\nReturn ALL Players by JSON : ");
                            System.out.println(PlayerDao.findAllPlayersJson());
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                        break;
                    case DISPLAYIDJSON:
                        try {
                            System.out.println("\nReturn Player by ID JSON");
                            System.out.println("Please enter a player ID : ");
                            int playerId = keyboard.nextInt();

                            if (PlayerDao.findPlayerByIdJson(playerId).length() > 0)
                                System.out.println(PlayerDao.findPlayerByIdJson(playerId));
                            else
                                System.out.println("Player with ID " + playerId + " is not found.");
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
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


