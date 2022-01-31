package server;

import dtos.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Command {
    void process(Request request, ObjectOutputStream outputStream, FileManager manager) throws IOException;
}
