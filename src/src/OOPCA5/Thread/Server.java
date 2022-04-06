package OOPCA5.Thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start(){
        try {
            ServerSocket ss = new ServerSocket(8081);

            System.out.println("Server is starting. Listening for connections on port " + ss.getLocalPort());

            //a number for client that the server allocates as client connects
            int clienNum =  0;

            while(true){
                //Listen and wait for connection, then accept connection
                Socket socket = ss.accept();
                //Open a new socket to connect/communicate with client
                clienNum++;

                System.out.println("Server: ");
                System.out.println("Client " + clienNum + " is connected");
                System.out.println("Port of remote client: " + socket.getPort());
                System.out.println("Port of this server: " + socket.getLocalPort());


            }
        } catch (IOException e){
            System.out.println("Server IOException : " + e);
        }
        System.out.println("Exiting the server, Goodbye!");
    }
}
