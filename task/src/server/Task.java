package server;

import dtos.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Task extends Thread {
    private final Socket socket;
    private final Runnable runnable;
    private final Command[] commands = new Command[] {new GetCommand(), new PutCommand(), new DeleteCommand()};


    public Task(Socket socket, Runnable runnable) {
        this.socket  = socket;
        this.runnable = runnable;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) objectInputStream.readObject();

            if (request.getCommand() == 0) {
                runnable.run();
            } else {
                Command command = commands[request.getCommand() - 1];
                command.process(request, outputStream, new FileManager());
                objectInputStream.close();
                outputStream.close();
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            //something went wrong
        }
    }
}
