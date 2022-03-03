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
                + "2. Display Hash Map By Key\n"
                + "3. Display Tree Map By Key\n"
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
                        System.out.println("\nDisplaying Table for Player List :");
                        ArrayList<Player> playerList = playerArrayList();

                        System.out.println("_____________________________________________________________________________________________________");
                        System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  |  Player Weight |");
                        System.out.println("=====================================================================================================");
                        for(Player p : playerList){
                            System.out.printf("|         %-6d    |\t\t    %-12s\t  | %7d\t     | %10.2f\t   | %10.2f\t    |\n",p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getPlayerWeight());
                        }
                        System.out.println("=====================================================================================================");
                        break;
                    case TWO:
                        System.out.println("\nDisplaying Table for Player Hash Map");
                        playerHashMap();

                        break;
                    case THREE:
                        System.out.println("\nDisplaying Table for Player Tree Map : ");
                        playerTreeMap();

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

    public void playerHashMap(){
        Scanner keyboard = new Scanner(System.in);
        Map<String, Player> playerHashMap = new HashMap<>();
        ArrayList<Player> playerList = playerArrayList();

        //Inserting ArrayList in HashMap nad creating key
        String compName = "Thomas Cup 2022";
        for (Player p : playerList){
            playerHashMap.put(compName, p);
            System.out.println(p);
        }


        //Inputting Key
        System.out.print("Enter Map key : ");
        String key = keyboard.nextLine();

        //Check IF key exists ELSE error message
        if (playerHashMap.containsKey(key)){
            //Display Map
            System.out.println("\nPlayers that is participating in " + key + " are : ");
            System.out.println("_______________________________________________________________________________________________");
            System.out.println("|  Player ID  |         Player Name         |  Player Age  |  Player Height  |  Player Weight |");
            System.out.println("===============================================================================================");
            for (Player p2 : playerList){
                System.out.printf("|      %-6d |\t\t      %-12s\t    | %7d\t   | %10.2f\t     | %10.2f\t  |\n",p2.getPlayerWRank(), p2.getPlayerName(), p2.getPlayerAge(), p2.getPlayerHeight(), p2.getPlayerWeight());
            }
            System.out.println("===============================================================================================");
        } else {
            //Error Message
            System.out.println("No Map with key " + key + " was found");
        }
    }

    public void playerTreeMap(){
        Map<Integer, Player> playerTreeMap = new TreeMap<>();
        ArrayList<Player> playerList = playerArrayList();

        //Inserting ArrayList in TreeMap and creating key
        for (int playerId = 0; playerId < playerList.size(); playerId++){
            playerTreeMap.put(playerId, playerList.get(playerId));
        }

        //Setting key
        Set<Integer> keySet = playerTreeMap.keySet();

        for (Map.Entry<Integer, Player> entry : playerTreeMap.entrySet()){
            System.out.println("Player ID : " + entry.getKey() + ",  " + entry.getValue());
        }
    }
}
