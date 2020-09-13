package techtask.logger.writer;

import techtask.logger.BinaryLoggable;

import java.io.Closeable;
import java.io.IOException;

public interface RollingBinaryFileWriter extends Closeable {
    void write(byte[] data) throws IOException;

    void write(BinaryLoggable loggable) throws IOException;
}
