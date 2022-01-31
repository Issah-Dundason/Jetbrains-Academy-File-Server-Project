package server;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

public class FileManager {
    private static final String DATA_DIR;

    static {
        DATA_DIR =  System.getProperty("user.dir") +
                File.separator + "src" +
                File.separator + "server" +
                File.separator + "data" +
                File.separator;
    }

    public boolean deleteFile(String filename) {
        try {
            return Files.deleteIfExists(Paths.get(getPath(filename)));
        } catch (IOException e) {
            return false;
        }
    }

    public Optional<byte[]> readFile(String fileName) {
        try {
            byte[] content = Files.readAllBytes(Paths.get(getPath(fileName)));
            System.out.println(Arrays.toString(content));
            return Optional.of(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean save(String nameForFile, byte[] content) {
        try {
            Files.write(Path.of(getPath(nameForFile)), content);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getPath(String filename) {
        return DATA_DIR + File.separator + filename;
    }

}
