package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class represents client side of the "client-server" application.
 * Connection between client and the server is made by TCP/IP.
 *
 * Created by james.smith on 7/26/16.
 */
public class Client {

    public static void main(String[] args) {
        String str1 = "When you walk through a storm, hold your head up high\n" +
                "And don’t be afraid of the dark\n" +
                "At the end of the storm, there’s a golden sky\n" +
                "And the sweet, silver song of a lark";
        /*
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter IP Address of the server: ");
        String ipAddress = reader.nextLine();

        System.out.print("Enter port of the server: ");
        int port = reader.nextInt();
        */

        String ipAddress = "localhost";
        int port = 7000;

        System.out.println("Connecting to: \n" + ipAddress + "\n" + port);
        try (Socket socket = new Socket(ipAddress, port)) {
            System.out.println("Connected to: " + socket.getRemoteSocketAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String message = "Jeff Kepner, the first American to receive a double hand transplant seven years ago, now says the hands have never functioned at all. \"From day one I have never been able to use my hands,\" he said in a recent interview with Time.\n" +
                    "\n" +
                    "Kepner was the first American to undergo the experimental surgery, which, while risky, had the potential to greatly improve his quality of life. Kepner lost both his hands due to sepsis in 1999, according to Time. Up until the hand transplants, he had been using prosthetics, which enabled him to drive and remain employed — now he can do neither.\n" +
                    "\n" +
                    "THIS COMPLETE FAILURE MAY BE AN EXCEPTION\n" +
                    "\n" +
                    "While disheartening, the complete failure of Kepner's transplant may be an exception — at least according to his doctors at the University of Pittsburgh Medical Center. \"The other three patients have had significant functional return in their hands and have been able to resume completely independent living, including driving, working, and going to school,\" Dr. W.P. Andrew Lee, Kepner's lead surgeon, told Time.\n" +
                    "\n" +
                    "When it comes to removing the transplants, Kepner's options are limited. If doctors were to completely remove the transplants, there would be no guarantee he'd be able to go back to using prosthetics. And a partial removal could cause Kepner's body to reject the hands.\n" +
                    "\n" +
                    "For now, Kepner doesn't seem interested in either option: \"I am not going through all those operations again,\" he told Time.";

            out.writeUTF(message);
            System.out.println("Server says: " + in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
