package hw14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        String serverIp = "localhost";
        int serverPort = 8989;
        System.out.println("Соединение с сервером " + serverIp + ":" + serverPort);

        Socket server = new Socket(serverIp, serverPort);
        BufferedReader inServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
        PrintWriter outServer = new PrintWriter(server.getOutputStream(), true);
        BufferedReader inConsole = new BufferedReader(new InputStreamReader(System.in));

        String dataFromUser, dataFromServer;

        do {
            dataFromUser = inConsole.readLine();
            outServer.println(dataFromUser);
            dataFromServer = inServer.readLine();
            System.out.println(dataFromServer);
        } while (!"exit".equalsIgnoreCase(dataFromUser));
        outServer.close();
        inServer.close();
        outServer.close();
        server.close();
    }
}
