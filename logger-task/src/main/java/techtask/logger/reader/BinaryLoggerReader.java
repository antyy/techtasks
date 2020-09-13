package techtask.logger.reader;

import techtask.logger.BinaryLoggable;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public interface BinaryLoggerReader<T extends BinaryLoggable> extends Closeable {
    Iterator<T> read(File file, Class<T> clazz) throws IOException;
}
