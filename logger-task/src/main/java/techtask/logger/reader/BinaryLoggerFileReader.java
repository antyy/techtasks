package techtask.logger.reader;

import lombok.SneakyThrows;
import techtask.logger.BinaryLoggable;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class BinaryLoggerFileReader<T extends BinaryLoggable> implements BinaryLoggerReader<T> {
    private final CopyOnWriteArrayList<Closeable> resources = new CopyOnWriteArrayList<>();

    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        resources.add(fileInputStream);
        ObjectInputStream[] holder = new ObjectInputStream[1];

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                try {
                    holder[0] = new ObjectInputStream(fileInputStream);
                } catch (IOException e) {
                    return false;
                }
                return true;
            }

            @Override
            @SneakyThrows
            public T next() {
                return clazz.cast(holder[0].readObject());
            }
        };
    }


    public void close() throws IOException {
        for (Closeable closeable : resources) {
            closeable.close();
        }
    }
}
