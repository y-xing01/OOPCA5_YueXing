import java.io.IOException;
import java.util.*;

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
                + "2. Display Map By Key\n"
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
                        System.out.println("Displaying Table for Player List");
                        ArrayList<Player> playerList = playerArrayList();

                        System.out.println("_______________________________________________________________________________________________");
                        System.out.println("|  Player ID  |         Player Name         |  Player Age  |  Player Height  |  Player Weight |");
                        System.out.println("===============================================================================================");
                        for(Player p : playerList){
                            System.out.printf("|      %-6d |\t\t      %-12s\t    | %7d\t   | %10.2f\t     | %10.2f\t  |\n",p.getPlayerID(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getPlayerWeight());
                        }
                        System.out.println("===============================================================================================");
                        break;
                    case TWO:
                        System.out.println("Displaying Table for Player Map");
                        Map<String, ArrayList<Player>> playerHashMap = playerHashMap();

                        //Declaring Key
//                        String key = "Thomas Cup 2022";
                        System.out.print("Enter Map key : ");
                        String key = keyboard.nextLine();
                        playerList = playerHashMap.get(key);

                        if (playerHashMap.containsKey(key)){
                            System.out.println("Players that is participating in " + key + " are : ");
                            for (Player p : playerList){
                                System.out.println(p);
                            }
                        } else {
                            System.out.println("No Map with key " + key + " was found");
                        }

                        break;
                    case THREE:
                        System.out.println("3 option chosen");
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

    public ArrayList<Player> playerArrayList(){
        ArrayList<Player> playerList = new ArrayList<>();

        //Inserting Player in ArrayList
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

        return playerList;
    }

    public Map<String, ArrayList<Player>> playerHashMap(){
        Map<String, ArrayList<Player>> playerMap = new HashMap<>();
        ArrayList<Player> playerList = playerArrayList();

        //Inserting ArrayList in HashMap
        String key = "Thomas Cup 2022";
        playerMap.put(key, playerList);

        return playerMap;
    }

    public Map<String, ArrayList<Player>> playerTreeMap(){
        Map<String, ArrayList<Player>> playerMap = new TreeMap<>();
        ArrayList<Player> playerList = playerArrayList();

        return playerMap;
    }
}
