package techtask.logger.impl;

import techtask.logger.BinaryLoggable;
import techtask.logger.BinaryLogger;
import techtask.logger.reader.BinaryLoggerFileReader;
import techtask.logger.reader.BinaryLoggerReader;
import techtask.logger.writer.RollingBinaryFileWriter;
import techtask.logger.writer.RollingBufferedBinaryFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;


public class SimpleBinaryLogger<T extends BinaryLoggable> implements BinaryLogger<T> {
    private final RollingBinaryFileWriter rollingBinaryFileWriter;
    private final BinaryLoggerReader<T> binaryLoggerFileReader;

    public SimpleBinaryLogger(String fileName, long fileSize) {
        rollingBinaryFileWriter = new RollingBufferedBinaryFileWriter(fileName, fileSize);
        binaryLoggerFileReader = new BinaryLoggerFileReader<>();
    }

    public SimpleBinaryLogger(RollingBinaryFileWriter rollingBinaryFileWriter, BinaryLoggerReader<T> binaryLoggerFileReader) {
        this.rollingBinaryFileWriter = rollingBinaryFileWriter;
        this.binaryLoggerFileReader = binaryLoggerFileReader;
    }


    @Override
    public void write(T loggable) throws IOException {
        rollingBinaryFileWriter.write(loggable);
    }

    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        return binaryLoggerFileReader.read(file, clazz);
    }

    @Override
    public void close() throws IOException {
        binaryLoggerFileReader.close();
        rollingBinaryFileWriter.close();
    }
}
