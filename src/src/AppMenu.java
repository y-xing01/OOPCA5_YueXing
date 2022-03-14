import java.io.IOException;
import java.util.*;

public class AppMenu {
    public static void main(String[] args) {

        AppMenu app = new AppMenu();
        app.start();
    }

    public void start() {
        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMainMenu() throws IOException {

        final String MENU_ITEMS = "\n*** MAIN MENU OF OPTIONS ***\n"
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
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case PLAYERS:
                        System.out.println("\nDisplaying Table for Player :");
                        displayArrayList();

                        break;
                    case TWO:
                        System.out.println("\nDisplaying Player Table List by Country : ");
                        playerHashMap();

                        break;
                    case THREE:
                        System.out.println("\nDisplaying Player List by Tournament ID : ");
                        playerTreeMap();

                        break;
                    case FOUR:
                        System.out.println("\nDisplaying Player List by AGE priority : ");
                        playerPriorityQueue();

                        break;
                    case FIVE:
                        System.out.println("\nDisplaying Player List by AGE within NAME priority : ");
                        playerPriorityQueueTwoField();

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

    public static ArrayList<Player> playerArrayList() {
        ArrayList<Player> playerList = new ArrayList<>();

        //Inserting Player in ArrayList
        playerList.add(new Player(1, "Viktor Axelsen", 28, 194.50f, 421));
        playerList.add(new Player(2, "Kento Momota", 27, 175.00f, 361));
        playerList.add(new Player(3, "Anders Antonsen", 24, 183.00f, 238));
        playerList.add(new Player(4, "Chou Tien-chen", 32, 180.00f, 391));
        playerList.add(new Player(5, "Anthony Ginting", 25, 171.40f, 201));
        playerList.add(new Player(6, "Chen Long", 33, 187.00f, 446));
        playerList.add(new Player(7, "Lee Zii Jia", 23, 186.00f, 188));
        playerList.add(new Player(8, "J.Christie", 24, 179.50f, 228));
        playerList.add(new Player(9, "Loh Kean Yew", 24, 175.00f, 163));
        playerList.add(new Player(10, "Angus Ng", 27, 181.00f, 298));

        return playerList;
    }

    public static void displayArrayList() {
        ArrayList<Player> playerList = playerArrayList();
        System.out.println("____________________________________________________________________________________");
        System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  |");
        System.out.println("====================================================================================");
        for (Player p : playerList) {
            System.out.printf("|         %-6d    |\t    %-12s\t\t  | %7d\t     | %10.2f\t   |\n", p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight());
        }
        System.out.println("====================================================================================");
    }

    public static void playerHashMap() {
        Scanner keyboard = new Scanner(System.in);
        Map<String, ArrayList<Player>> playerHashMap = new HashMap<>();

        //Inserting Key And Object
        //Country (Denmark)
        ArrayList<Player> countryList = new ArrayList<>();
        String country = "Denmark";
        countryList.add(new Player(1, "Viktor Axelsen", 28, 194.50f, 421));
        countryList.add(new Player(3, "Anders Antonsen", 24, 183.00f, 238));
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (Japan)
        countryList = new ArrayList<>();
        countryList.add(new Player(2, "Kento Momota", 27, 175.00f, 361));
        country = "Japan";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (Taiwan)
        countryList = new ArrayList<>();
        countryList.add(new Player(4, "Chou Tien-chen", 32, 180.00f, 391));
        country = "Taiwan";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (Indonesia)
        countryList = new ArrayList<>();
        countryList.add(new Player(5, "Anthony Ginting", 25, 171.40f, 201));
        countryList.add(new Player(8, "J.Christie", 24, 179.50f, 228));
        country = "Indonesia";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (China)
        countryList = new ArrayList<>();
        countryList.add(new Player(6, "Chen Long", 33, 187.00f, 446));
        country = "China";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (Malaysia)
        countryList = new ArrayList<>();
        countryList.add(new Player(7, "Lee Zii Jia", 23, 186.00f, 188));
        country = "Malaysia";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (Singapore)
        countryList = new ArrayList<>();
        countryList.add(new Player(9, "Loh Kean Yew", 24, 175.00f, 163));
        country = "Singapore";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Country (HongKong)
        countryList = new ArrayList<>();
        countryList.add(new Player(10, "Angus Ng", 27, 181.00f, 298));
        country = "HongKong";
        playerHashMap.put(country.toLowerCase(), countryList);

        //Inputting Key and retrieving object
        System.out.print("Enter Country Name (Denmark, Japan, Taiwan, Indonesia, China, Malaysia, Singapore, HongKong): ");
        country = keyboard.nextLine();
        countryList = playerHashMap.get(country.toLowerCase());

        //Check IF key exists ELSE error message
        if (playerHashMap.containsKey(country.toLowerCase())) {
            //Display Map
            System.out.println("\nPlayers in country " + country + " : ");
            System.out.println("________________________________________________________________________________________________________");
            System.out.println("| Player World Rank |         Player Name         |  Player Age  |  Player Height  | Player Career Win |");
            System.out.println("========================================================================================================");
            for (Player p : countryList) {
                System.out.printf("|         %-6d    |\t    %-12s\t\t  | %7d\t     | %10.2f\t   | \t%7d\t       |\n", p.getPlayerWRank(), p.getPlayerName(), p.getPlayerAge(), p.getPlayerHeight(), p.getCareerWin());
            }
            System.out.println("========================================================================================================");
        } else {
            //Error Message
            System.out.println("No Country of " + country + " was found");
        }
    }

    public void playerTreeMap() {
        Map<Integer, Player> playerTreeMap = new TreeMap<>();
        ArrayList<Player> playerList = playerArrayList();

        //Inserting ArrayList in TreeMap and creating key
        for (int tPlayerId = 0; tPlayerId < playerList.size(); tPlayerId++) {
            playerTreeMap.put(tPlayerId + 202201, playerList.get(tPlayerId));
        }

        //Find entry key and displaying map
        for (Map.Entry<Integer, Player> entry : playerTreeMap.entrySet()) {
            //Tournament Player ID From 202201 - 202202
//            if (entry.getKey() == 202202)
            System.out.println("Player Tournament ID : " + entry.getKey() + ",  " + entry.getValue() + "\n");

        }
    }

    public void playerPriorityQueue() {
        PriorityQueue<Player> ageQueue = new PriorityQueue<>(new ageComparator(SortType.Ascending));

        //Third priority
        ageQueue.add(new Player(10, "Angus Ng", 27, 181.00f, 298));
        ageQueue.add(new Player(2, "Kento Momota", 27, 175.00f, 361));

        //Second priority
        ageQueue.add(new Player(9, "Loh Kean Yew", 24, 175.00f, 163));
        ageQueue.add(new Player(8, "J.Christie", 24, 179.50f, 228));


        System.out.println("\nRemove and display one element");
        while (!ageQueue.isEmpty()){
            ageQueue.remove();
        }
        System.out.println(ageQueue.peek());

        //Third priority
        ageQueue.add(new Player(10, "Angus Ng", 27, 181.00f, 298));
        ageQueue.add(new Player(2, "Kento Momota", 27, 175.00f, 361));

        //Second priority
        ageQueue.add(new Player(9, "Loh Kean Yew", 24, 175.00f, 163));
        ageQueue.add(new Player(8, "J.Christie", 24, 179.50f, 228));

        //First priority
        ageQueue.add(new Player(7, "Lee Zii Jia", 23, 186.00f, 188));
        System.out.println("\nRemove and display all element : ");
        while (!ageQueue.isEmpty()) {
            System.out.println(ageQueue.remove() + "\n");
        }
    }


    public void playerPriorityQueueTwoField() {

        PriorityQueue<Player> nameQueue = new PriorityQueue<>();
        ArrayList<Player> playerList = playerArrayList();

        for (Player p : playerList) {
            nameQueue.add(p);
        }

        while (!nameQueue.isEmpty()) {
            System.out.println(nameQueue.remove() + "\n");
        }
    }
}

//            System.out.println("\nPlayers country and career win in " + country + " : ");
//            System.out.println("___________________________________________________");
//            System.out.println("|         Player Name         | Player Career Win |");
//            System.out.println("===================================================");
//            for(Player p : countryList){
//               System.out.printf("|\t    %-12s\t\t  |\t   %7d\t      |\n", p.getPlayerName(), p.getCareerWin());
//            }
//            System.out.println("===================================================");

