package Server;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents server side of the "client-server" application.
 * Connection between client and the server is made by TCP/IP.
 *
 * Created by james.smith on 7/26/16.
 */
public class Server {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(7000)) {
            System.out.println("Server info:");
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Port: " + serverSocket.getLocalPort());

            Socket socket = serverSocket.accept();
            System.out.println("Received a connection from: " + socket.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Message\n" + in.readUTF());

            out.writeUTF("Thanks for connecting to " + socket.getLocalSocketAddress() + ". Good bye!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
