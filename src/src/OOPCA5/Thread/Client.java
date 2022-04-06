package OOPCA5.Thread;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8084);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command:  (\"Time\" to get time, or \"Echo message\" to get echo or \"Triple INT\" to get triple number) " +
                    "\n\t\t\t\t\t\t\tor \"Add INT INT \" to get add up number)  or \"MULTIPLY INT INT\" to get multiplication number)  or \"SUBTRACT INT INT\" to get subtract number)  " +
                    "\n\t\t\t\t\t\t\tor \"DIVIDE INT INT\" to get divide number)>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

            socketWriter.println(command.toLowerCase());

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            boolean keep_looping = true;
            while (keep_looping){
                if(command.startsWith("displaybyid"))   //we expect the server to return a time
                {
                    String displayById = socketReader.nextLine();
                    System.out.println("Client message: Response from server displayById: " + displayById);
                }
                else if(command.startsWith("displayall")){
                    String displayAll = socketReader.nextLine();
                    System.out.println("Client message: Response from server displayAll: " + displayAll);
                }
                else                            // the user has entered the Echo command or an invalid command
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\"");
                }

                System.out.println("Enter next command: ");
                command = in.nextLine();
                socketWriter.println(command);
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required