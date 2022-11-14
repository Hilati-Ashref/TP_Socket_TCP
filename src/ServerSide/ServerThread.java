package ServerSide;

import Handler.Gestion;
import Model.Account;

import java.io.*;
import java.lang.management.GarbageCollectorMXBean;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;
    private Gestion G = new Gestion();

    public ServerThread ( Socket socket ) {
        this.socket = socket;
    }

    public void run(){
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String in = "", out;

            String nom = null;
            while (!in.equals("closeServerSocket")) {
                in = reader.readLine();
                if (in.startsWith("CREATION") && (in.length() > 9)) {
                    nom = in.substring(10);
                }
                out = G.commandHandler(in, nom);
                writer.println("Server: " + out);
            }
            System.out.println("Server Socket Closed");
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
