package techtask;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class TestUtils {

    @SneakyThrows
    public static void saveToFile(BinaryLoggable loggable, String file) {
        saveToFile(loggable, new File(file));
    }

    @SneakyThrows
    public static void saveToFile(BinaryLoggable loggable, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(loggable.toBytes());
        }
    }


    @SneakyThrows
    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            loggables.stream()
                    .map(TestUtils::toBytes)
                    .forEach(data -> write(data, fileOutputStream));
        }
    }


    @SneakyThrows
    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, String file) {
        bulkSaveToFile(loggables, new File(file));
    }

    @SneakyThrows
    private static byte[] toBytes(BinaryLoggable loggable) {
        return loggable.toBytes();
    }

    @SneakyThrows
    private static void write(byte[] data, FileOutputStream fileOutputStream) {
        fileOutputStream.write(data);
    }

    @SneakyThrows
    public static <T> T readObject(File file, Class<T> tClass) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return tClass.cast(objectInputStream.readObject());
    }

    @SneakyThrows
    public static <T> T readObject(String file, Class<T> tClass) {
        return readObject(new File(file), tClass);
    }


    public static void cleanFile(String fileName) {
        File f = new File(fileName);
        if (f.exists() &&  !f.delete()) {
           throw new RuntimeException("Failed to delete file" + fileName);
        }
    }

}
