package techtask;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RollingBinaryFileWriter implements Closeable {

    private File file;
    private final String fileName;
    private final long rollingSize;
    private long currentSize = 0;
    private FileOutputStream fileOutputStream;

    @SneakyThrows
    public RollingBinaryFileWriter(String fileName, long rollingSize) {
        file = new File(fileName);
        this.rollingSize = rollingSize;
        this.fileName = fileName;
        fileOutputStream = new FileOutputStream(file);
    }


    public void write(BinaryLoggable loggable) throws IOException {
        byte[] data = loggable.toBytes();
        currentSize += data.length;
        if (currentSize > rollingSize) {
            rollOver();
            currentSize = data.length;
        }

        writeByteData(data);
    }

    private void writeByteData(byte[] data) throws IOException {
        fileOutputStream.write(data);
    }

    private void rollOver() throws IOException {
        File newFile = findNewRollOverFileName();

        if (!file.renameTo(newFile)) {
            throw new IOException("Could not roll over file");
        }

        file = new File(fileName);
        fileOutputStream.close();
        fileOutputStream = new FileOutputStream(file);//TODO: check what happens if proceed without reassigning output stream
    }

    private File findNewRollOverFileName() throws IOException {

        Path directory = Path.of(fileName).getParent();
        String name = directory.getFileName().toString();
        long index = Files.walk(directory, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(t -> t.startsWith(name)).count();

        return new File(directory.resolve(name + index).toString());
    }

    @Override
    public void close() throws IOException {
        fileOutputStream.close();
    }
}
