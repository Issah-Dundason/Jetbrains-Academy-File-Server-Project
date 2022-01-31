package server;

import java.io.*;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class FilesRegistrar {
    private Map<Integer, String> fileIndex = new ConcurrentHashMap<>();
    private String FILE = "fileIndex";
    private static volatile FilesRegistrar INSTANCE;
    private static final ReentrantLock reLock = new ReentrantLock(true);

    private FilesRegistrar() {}

    public static FilesRegistrar getInstance() {
        reLock.lock();
        if(INSTANCE == null) {
            INSTANCE = new FilesRegistrar();
            INSTANCE.getData();
        }
        reLock.unlock();
        return INSTANCE;
    }

    private void getData() {
        File file = new File(FILE);
        if(file.exists()) {
            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
                fileIndex = (ConcurrentHashMap<Integer, String>) inputStream.readObject();
                inputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Couldn't read index file");
            }
        }
    }

    public Optional<String> getFileName(int id) {
        if(!fileIndex.containsKey(id))
            return Optional.empty();
        return Optional.of(fileIndex.get(id));
    }

    public void saveIndex(int id, String filename) {
        fileIndex.put(id, filename);
        reLock.lock();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(fileIndex);
        } catch (IOException exception) {
            System.out.println("Couldn't be saved");
        }
        reLock.unlock();
    }

    public int getLastIndex() {
        return fileIndex.size();
    }

}
