package server;

import dtos.Request;
import dtos.Response;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.UUID;

public class DeleteCommand implements Command {
    @Override
    public void process(Request request, ObjectOutputStream outputStream, FileManager manager) throws IOException {
        String fileName = request.getDescriptor();
        if(request.getType() == 2) {
            FilesRegistrar filesRegistrar = FilesRegistrar.getInstance();
            String randomVal = UUID.randomUUID().toString();
            fileName = filesRegistrar.getFileName(Integer.parseInt(request.getDescriptor())).orElse(randomVal);
        }
        boolean deleted = manager.deleteFile(fileName);
        Response response = new Response();
        if(deleted) {
            response.setStatusCode(200);
            response.setContent("this file was deleted successfully!");
            outputStream.writeObject(response);
            outputStream.flush();
            return;
        }
        response.setStatusCode(404);
        response.setContent("this file is not found!");
        outputStream.writeObject(response);
        outputStream.flush();
    }
}
