package OOPCA5.Thread;

import OOPCA5.DAOs.MySqlPlayerDao;
import OOPCA5.DAOs.PlayerDaoInterface;
import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.App;
import OOPCA5.Part1.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    MySqlPlayerDao s = new MySqlPlayerDao();

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

    public void start() {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8084);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort());
            System.out.println("Client message: The Client is running and has connected to the server");

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers
            Scanner socketReader = new Scanner(socket.getInputStream());

            final String MENU_ITEMS = "\n*** MAIN MENU OF OPTION FOR OOPCA5 PART 4***\n"
                    + "1. Display Players By ID\n"
                    + "2. Display ALL Players\n"
                    + "3. ADD Player\n"
                    + "4. Delete Player by ID\n"
                    + "5. Exit\n"
                    + "Enter Option [1,5]";

            final int DISPLAYBYID = 1;
            final int DISPLAYALL = 2;
            final int ADDPLAYER = 3;
            final int DELETEPLAYER = 4;
            final int EXIT = 5;

            Scanner keyboard = new Scanner(System.in);
            int option = 0;
            do {
                System.out.println("\n" + MENU_ITEMS);
                  // wait for, and retrieve the reply
                try {
                    String usersInput = keyboard.nextLine();
                    Gson gsonParser = new Gson();
                    Type userListType = new TypeToken<ArrayList<Player>>() {
                    }.getType();
                    ArrayList<Player> userArray;
                    String command;

                    option = Integer.parseInt(usersInput);
                    switch (option) {
                        case DISPLAYBYID:
                            System.out.println("Please enter a command:  (\"diplaybyid INT\" to get Player by ID)");
                            command = in.nextLine();
                            socketWriter.println(command.toLowerCase());
                            String displayById = socketReader.nextLine();
                            System.out.println("Client message: Response from server displayById: ");

                            userArray = gsonParser.fromJson(displayById, userListType);
                            for (Player p : userArray) {
                                System.out.println(p);
                            }

                            break;

                        case DISPLAYALL:
                            System.out.println("Please enter a command:  (\"displayall\" to get All Players)");
                            command = in.nextLine();
                            socketWriter.println(command.toLowerCase());
                            String displayAll = socketReader.nextLine();
                            System.out.println("Client message: Response from server displayAll: ");

                            userArray = gsonParser.fromJson(displayAll, userListType);
                            for (Player p : userArray) {
                                System.out.println(p);
                            }

                            break;
                        case ADDPLAYER:

                            break;
                        case DELETEPLAYER:

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

            socketWriter.close();
            socketReader.close();
            socket.close();
            System.out.println("\nExiting Main Menu.");
//                if (command.startsWith("displaybyid"))   //we expect the server to return a time
//                {
//
//                } else if (command.startsWith("displayall")) {
//
//                } else if (command.startsWith("addplayer")) {
//                    String addPlayer = socketReader.nextLine();
//                    Scanner keyboard = new Scanner(System.in);
//                    Gson gsonParser = new Gson();
//                    Type userListType = new TypeToken<ArrayList<Player>>() {
//                    }.getType();
//
//                    System.out.println("Client message: Response from server addPlayer: ");
//
//                    System.out.println("Please enter player WORLD RANKING : ");
//                    int worldRank = keyboard.nextInt();
//
//                    System.out.println("Please enter player NAME : ");
//                    String playerName = keyboard.next();
//
//                    System.out.println("Please enter player AGE : ");
//                    int playerAge = keyboard.nextInt();
//
//                    System.out.println("Please enter player Height : ");
//                    float playerHeight = keyboard.nextFloat();
//
//                    System.out.println("Please enter player Career Won : ");
//                    int playerCareerWon = keyboard.nextInt();
//
//                    ArrayList<Player> userArray = gsonParser.fromJson(addPlayer, userListType);
//                    for (Player p : userArray) {
//                        System.out.println(p);
//                    }
//                } else // the user has entered the Echo command or an invalid command
//                {
//                    String input = socketReader.nextLine();
//                    System.out.println("Client message: Response from server: \"" + input + "\"");
//                }

//                System.out.println("Enter next command: ");
//                command = in.nextLine();
//                socketWriter.println(command);



        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }
}