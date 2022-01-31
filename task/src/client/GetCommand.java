package client;

import dtos.Request;
import dtos.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class GetCommand implements Command {
    @Override
    public void execute(BufferedReader reader, ObjectOutputStream outputStream) throws IOException {
        System.out.print("Do you want to get the file by name or by id (1 - name, 2 - id): ");
        int descriptorType = Integer.parseInt(reader.readLine());
        String nextCommand = "Enter filename: ";
        if(descriptorType == 2) {
            nextCommand = nextCommand.replace("filename", "id");
        }
        System.out.print(nextCommand);
        Request request = new Request(1, reader.readLine());
        request.setType(descriptorType);
        outputStream.writeObject(request);
        System.out.println("The request was sent");
    }

    @Override
    public void processResponse(Response response) {
        if(response.getStatusCode() == 200) {
            System.out.println("The file was downloaded! specify a name for it: ");
            Scanner scanner = new Scanner(System.in);
            String fileName = scanner.nextLine();
            scanner.close();
            new FileManager().save(fileName, response.getData());
            System.out.println("File saved on the hard drive!");
        } else {
            System.out.println("The response says that " + response.getContent());
        }
    }
}
