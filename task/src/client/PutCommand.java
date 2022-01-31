package client;

import dtos.Request;
import dtos.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PutCommand implements Command {
    @Override
    public void execute(BufferedReader reader, ObjectOutputStream outputStream) throws IOException {
        System.out.print("Enter name of the file: ");
        String filename = reader.readLine();
        Request request = new Request(2, filename);
        byte[] data = new FileManager().readFile(filename).orElse(new byte[]{});
        System.out.print("Enter name of the file to be saved on server: ");
        String content = reader.readLine();
        if(!content.isBlank()) {
            request.setDescriptor(content);
        }
        request.setData(data);
        outputStream.writeObject(request);
        System.out.println("The request was sent");
    }

    @Override
    public void processResponse(Response response) {
        System.out.println("Response says that " + response.getContent());
    }
}
