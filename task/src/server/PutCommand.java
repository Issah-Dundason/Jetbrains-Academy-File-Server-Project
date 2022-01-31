package server;

import dtos.Request;
import dtos.Response;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PutCommand implements Command{
    @Override
    public void process(Request request, ObjectOutputStream outputStream, FileManager fileManager) throws IOException {
        boolean saved = fileManager.save(request.getDescriptor(), request.getData());
        Response response = new Response();
        if(saved) {
            FilesRegistrar filesRegistrar = FilesRegistrar.getInstance();
            int generatedId = filesRegistrar.getLastIndex() + 1;
            filesRegistrar.saveIndex(generatedId, request.getDescriptor());
            response.setStatusCode(200);
            response.setContent("file is saved! ID = " + generatedId);
            outputStream.writeObject(response);
            outputStream.flush();
            return;
        }
        response.setStatusCode(403);
        response.setContent("creating the file was forbidden!");
        outputStream.writeObject(response);
    }
}
