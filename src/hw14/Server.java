package hw14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static class HandlerServer implements Runnable {
        private final Socket socket;

        public HandlerServer(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                Socket localSocket = socket;
                PrintWriter pw = new PrintWriter(localSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(localSocket.getInputStream()));

                String str;
                while ((str = br.readLine()) != null) {
                    if (str.equals("exit")) {
                        pw.println("bye");
                        break;
                    }
                    str = LocalDateTime.now() + " " + str;
                    pw.println(str);
                    System.out.println("The message: " + str);

                }
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8989;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен на порту : " + port);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        //noinspection InfiniteLoopStatement
        while (true) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new HandlerServer(clientSocket));
        }
    }
}