package ClientSide;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientTCP {
    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 6969)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String command;
            do {
                System.out.println("Enter your command: ");
                BufferedReader inClavier = new BufferedReader(new InputStreamReader(System.in));
                command = inClavier.readLine();

                writer.println(command);

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);

            } while (!command.equals("closeClientSocket"));

            System.out.println("Server Socket Closed");
            socket.close();

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}
