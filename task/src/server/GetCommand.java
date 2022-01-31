package server;

import dtos.Request;
import dtos.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class GetCommand implements Command {
    @Override
    public void process(Request request, ObjectOutputStream outputStream, FileManager manager) throws IOException {
        String fileName = request.getDescriptor();
        if(request.getType() == 2) {
            FilesRegistrar filesRegistrar = FilesRegistrar.getInstance();
            String randomVal = UUID.randomUUID().toString();
            fileName = filesRegistrar.getFileName(Integer.parseInt(request.getDescriptor())).orElse(randomVal);
        }
        Optional<byte[]> optionalResponse = manager.readFile(fileName);
        Response response = new Response();
        if(optionalResponse.isPresent()) {
            response.setStatusCode(200);
            System.out.println("Optional has data " +Arrays.toString(optionalResponse.get()));
            response.setData(optionalResponse.get());
            outputStream.reset();
            outputStream.writeObject(response);
            return;
        }
        response.setStatusCode(404);
        response.setContent("this file is not found!");
        outputStream.writeObject(response);
    }
}
