package client;

import dtos.Request;
import dtos.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DeleteCommand implements Command{
    @Override
    public void execute(BufferedReader reader, ObjectOutputStream outputStream) throws IOException {
        System.out.print("Do you want to delete the file by name or by id (1 - name, 2 - id): ");
        int descriptorType = Integer.parseInt(reader.readLine());
        String nextCommand = "Enter filename: ";
        if(descriptorType == 2) {
            nextCommand = nextCommand.replace("filename", "id");
        }
        System.out.print(nextCommand);
        Request request = new Request(3, reader.readLine());
        request.setType(descriptorType);
        outputStream.writeObject(request);
        System.out.println("The request was sent");
    }

    @Override
    public void processResponse(Response response) {
        System.out.println("The response says that " + response.getContent());
    }
}
