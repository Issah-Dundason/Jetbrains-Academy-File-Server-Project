package client;

import dtos.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;


public interface Command {
    void execute(BufferedReader reader, ObjectOutputStream outputStream) throws IOException;
    void processResponse(Response response);
}
