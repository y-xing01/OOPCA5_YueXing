package OOPCA5.Thread;

import OOPCA5.DAOs.MySqlPlayerDao;
import OOPCA5.Exceptions.DaoException;
import OOPCA5.Part1.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

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
            try {
                while ((msg = socketReader.readLine()) != null) {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + msg);

                    msg.toLowerCase();
                    if (msg.startsWith("displaybyid")) {
                        try {
                            Gson gson = new Gson();
                            String token[] = msg.split(" ");
                            int num = Integer.parseInt(token[1]);
                            ArrayList<Player> playerList = s.findPlayerById(num);

                            String gsonParsed = gson.toJson(playerList);
                            socketWriter.println(gsonParsed);
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("displayall")) {
                        try {
                            Gson gson = new Gson();
                            ArrayList<Player> playerList = s.findAllPlayers();
                            System.out.println("Server run" + playerList);

                            String gsonParsed = gson.toJson(playerList);
                            socketWriter.println(gsonParsed);
                        } catch (DaoException e) {
                            e.printStackTrace();
                        }
                    } else if (msg.startsWith("addplayer")) {
//                        try {
//
//
//
//
//                            socketWriter.println(player);
//                        } catch (DaoException e) {
//                            e.printStackTrace();
//                        }
                    } else {
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
