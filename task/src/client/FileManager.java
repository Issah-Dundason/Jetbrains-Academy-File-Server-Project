package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class FileManager {
    private static final String DATA_DIR;

    static {
        DATA_DIR =  System.getProperty("user.dir")  +
                File.separator + "src" +
                File.separator + "client" +
                File.separator + "data" +
                File.separator;
    }

    public Optional<byte[]> readFile(String fileName) {
        try {
            byte[] content = Files.readAllBytes(Paths.get(getPath(fileName)));
            return Optional.of(content);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public boolean save(String nameForFile, byte[] content) {
        boolean fileCreated = createFile(nameForFile);
        if(fileCreated) {
            try {
                FileOutputStream fos = new FileOutputStream(getPath(nameForFile));
                fos.write(content);
                fos.flush();
                fos.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private boolean createFile(String filename) {
        File file = new File(getPath(filename));
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getPath(String filename) {
        return DATA_DIR + File.separator + filename;
    }

}
