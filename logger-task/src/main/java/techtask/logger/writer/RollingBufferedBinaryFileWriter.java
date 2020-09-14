package techtask.logger.writer;

import lombok.SneakyThrows;
import techtask.logger.BinaryLoggable;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RollingBufferedBinaryFileWriter implements RollingBinaryFileWriter {

    private File file;
    private final String fileName;
    private final long rollingSize;
    private long currentSize = 0;
    private BufferedOutputStream fileOutputStream;

    @SneakyThrows
    public RollingBufferedBinaryFileWriter(String fileName, long rollingSize) {
        file = new File(fileName);
        this.rollingSize = rollingSize;
        this.fileName = fileName;
        fileOutputStream = new BufferedOutputStream(new FileOutputStream(file));
    }


    public void write(BinaryLoggable loggable) throws IOException {
        write(loggable.toBytes());
    }


    public void write(byte[] data) throws IOException {

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



        file = new File(fileName);
        fileOutputStream.close();

        if (!file.renameTo(newFile)) {
            throw new IOException("Could not roll over file");
        }
        fileOutputStream = new BufferedOutputStream(new FileOutputStream(file));
    }

    private File findNewRollOverFileName() throws IOException {

        Path directory = Path.of(fileName).getParent();
        String name = Path.of(fileName).getFileName().toString();
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
