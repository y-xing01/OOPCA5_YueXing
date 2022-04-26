package OOPCA5.Thread;

import OOPCA5.DAOs.MySqlPlayerDao;
import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    MySqlPlayerDao s = new MySqlPlayerDao();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            ServerSocket ss = new ServerSocket(8084);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNum = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNum++;

                System.out.println("Server: Client " + clientNum + " has connected.");

                System.out.println("Server: Port of remote client: " + socket.getPort());
                System.out.println("Server: Port of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNum)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNum);
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber) {
            try {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String msg;
            String response = null;
            Gson gson = new Gson();
            try {
                while ((msg = socketReader.readLine()) != null) {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + msg);

                    msg.toLowerCase();
                    if (msg.startsWith("displaybyid")) {
                        try {
                            int num = 0;

                            String token[] = msg.split(" ");
                            // any positive or negetive integer or not!
//                            check input is int
                            if (token[1].matches("-?\\d+")) {
                                if (Integer.parseInt(token[1]) > 0) {
                                    num = Integer.parseInt(token[1]);
                                    ArrayList<Player> playerList = s.findPlayerById(num);
                                    response = gson.toJson(playerList);
                                } else {
                                    response = "ID must bigger than 0";
                                }
                            } else {
                                response = "Input is not an integer";
                            }
                            socketWriter.println(response);
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("displayall")) {
                        try {
                            ArrayList<Player> playerList = s.findAllPlayers();
                            System.out.println("Server run" + playerList);

                            String gsonParsed = gson.toJson(playerList);
                            socketWriter.println(gsonParsed);
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("addplayer")) {
                        try {
                            int playerWRank = 0;
                            String playerName = "";
                            int playerAge = 0;
                            float playerHeight = 0;
                            int playerCareerWon = 0;
                            String token[] = msg.split(" ");

                            //Input error checking (If Integer is number // If String is not number // If Float has decimals)
                            if (token[1].matches("-?\\d+") && token[3].matches("-?\\d+") && token[4].matches("^([+-]?\\d*\\.?\\d*)$") && token[5].matches("-?\\d+")) {
                                if(!token[2].matches("-?\\d+")){
                                    if (Integer.parseInt(token[1]) > 0 && Integer.parseInt(token[3]) > 0 && Float.parseFloat(token[4]) > 0) {
                                        playerWRank = Integer.parseInt(token[1]);
                                        playerName = token[2];
                                        playerAge = Integer.parseInt(token[3]);
                                        playerHeight = Float.parseFloat(token[4]);
                                        playerCareerWon = Integer.parseInt(token[5]);
                                        Player p = s.addPlayer(new Player(playerWRank, playerName, playerAge, playerHeight, playerCareerWon));
                                        response = "Player is ADDED";
                                    } else {
                                        response = "Input must be bigger than 0";
                                    }
                                }else{
                                    response = "Player Name must be a string";
                                }
                            } else {
                                response = "Input is not an integer";
                            }
                            socketWriter.println(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("deletebyid")) {
                        try {
                            int num = 0;

                            String token[] = msg.split(" ");
                            // any positive or negetive integer or not!
                            // check input is int
                            if (token[1].matches("-?\\d+")) {
                                if (Integer.parseInt(token[1]) > 0) {
                                    num = Integer.parseInt(token[1]);
                                    if (s.deletePlayerById(num) == true)
                                        response = "Player ID : " + num + " is deleted.";
                                    else
                                        response = "Player with ID of " + num + " is not found.";
                                } else {
                                    response = "ID must bigger than 0";
                                }
                            } else {
                                response = "Input is not an integer";
                            }
                            socketWriter.println(response);
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("editplayer")) {
                        System.out.println("Test");
                        try {
                            int playerId = 0;
                            int playerWRank = 0;
                            String playerName = "";
                            int playerAge = 0;
                            float playerHeight = 0;
                            int playerCareerWon = 0;
                            String token[] = msg.split(" ");
                            //Input error checking (If Integer is number // If String is not number // If Float has decimals)
                            if (token[2].matches("-?\\d+") && token[4].matches("-?\\d+") && token[5].matches("^([+-]?\\d*\\.?\\d*)$") && token[6].matches("-?\\d+")) {
                                if(!token[3].matches("-?\\d+")){
                                    if (Integer.parseInt(token[1]) > 0 && Integer.parseInt(token[2]) > 0 && Integer.parseInt(token[4]) > 0 && Float.parseFloat(token[5]) > 0) {
                                        playerId = Integer.parseInt(token[1]);
                                        playerWRank = Integer.parseInt(token[2]);
                                        playerName = token[3];
                                        playerAge = Integer.parseInt(token[4]);
                                        playerHeight = Float.parseFloat(token[5]);
                                        playerCareerWon = Integer.parseInt(token[6]);
                                        boolean p = s.editPlayer(playerId, new Player(playerWRank, playerName, playerAge, playerHeight, playerCareerWon));
                                        if (p)
                                            response = "Player ID : " + playerId + " is edited.";
                                        else
                                            response = "Player with ID of " + playerId + " is not found.";
                                    } else {
                                        response = "Input must be bigger than 0";
                                    }
                                }else{
                                    response = "Player Name must be a string";
                                }
                            } else {
                                response = "Input is not an integer";
                            }
                            socketWriter.println(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
