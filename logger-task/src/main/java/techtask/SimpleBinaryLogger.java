package techtask;

import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;


public class SimpleBinaryLogger<T extends BinaryLoggable> implements BinaryLogger<T> {
    private final File file;
    private final RollingBinaryFileWriter  rollingBinaryFileWriter;

    public SimpleBinaryLogger(String fileName, long fileSize) {
        file = new File(fileName);
        rollingBinaryFileWriter = new RollingBinaryFileWriter(fileName, fileSize);
    }

    @Override
    public void write(T loggable) throws IOException {
        rollingBinaryFileWriter.write(loggable);
    }

    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
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

    @Override
    public void close() throws IOException {

    }
}
