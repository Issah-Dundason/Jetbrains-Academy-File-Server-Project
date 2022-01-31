package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 23456;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started!");
            while(true) {
                Socket socket = serverSocket.accept();
                Runnable runnable = () -> {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        //do nothing
                    }
                    System.exit(0);
                };
                Task task = new Task(socket, runnable);
                task.start();
            }
        } catch (IOException exception) {
            //do nothing
        }
    }
}
