package client;

import dtos.Request;
import dtos.Response;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    private static final String ADDRESS = "127.0.0.1";
    private static final int PORT = 23456;
    private static final Command[] commands = new Command[] {new GetCommand(), new PutCommand(), new DeleteCommand()};

    public static void main(String[] args) {

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            printMenu();
            Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            String input = reader.readLine();

            if(input.equals("exit")) {
                Request request = new Request(0, "exit");
                System.out.println("The request was sent");
                outputStream.writeObject(request);
                System.exit(0);
            }

            int action = Integer.parseInt(input);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Command command = commands[action - 1];
            command.execute(reader, outputStream);
            Response response = (Response) inputStream.readObject();
            command.processResponse(response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printMenu() {
        System.out.println("Enter action (1 - get a file, 2 - create a file, 3 - delete a file):");
    }

}
