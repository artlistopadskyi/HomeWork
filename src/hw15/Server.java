package hw15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

    static class Handler implements Runnable {
        private static final AtomicInteger INDEX = new AtomicInteger(0);

        private final Socket socket;
        private final BufferedReader reader;
        private final PrintWriter writer;
        private final List<Handler> clientHandlers;
        private final int currentIndex;

        public Handler(Socket socket, List<Handler> clientHandlers) {
            this.currentIndex = INDEX.incrementAndGet();
            this.socket = socket;
            this.clientHandlers = clientHandlers;
            try {
                this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void run() {
            try {
                String message;
                while((message = reader.readLine()) != null) {
                    for(Handler handler: clientHandlers) {
                        handler.sendMessage(message, currentIndex);
                    }
                }
                clientHandlers.remove(this);
                socket.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void sendMessage(String message, int clientIndex) {
            try {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
                LocalDateTime dateTimeNow = LocalDateTime.now();

                writer.println(dateTimeNow.format(dateTimeFormatter) + " - Client " + clientIndex + ": " + message);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8989;
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Handler> handlers = Collections.synchronizedList(new ArrayList<>());

        System.out.println("The server is running on the port " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            Handler handler = new Handler(socket, handlers);
            handlers.add(handler);
            System.out.println("Client " + handler.currentIndex + " add");
            pool.submit(handler);
        }
    }
}