package techtask.logger.impl;

import lombok.SneakyThrows;
import techtask.logger.BinaryLoggable;
import techtask.logger.BinaryLogger;
import techtask.logger.reader.BinaryLoggerFileReader;
import techtask.logger.reader.BinaryLoggerReader;
import techtask.logger.writer.RollingBinaryFileWriter;
import techtask.logger.writer.RollingBufferedBinaryFileWriter;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import static java.util.Objects.nonNull;

public class ConcurrentNotBlockingBinaryLogger<T extends BinaryLoggable> implements BinaryLogger<T> {
    private static final int DEFAULT_QUEUE_SIZE = 1000000;
    private volatile boolean isWorking = true;
    private final RollingBinaryFileWriter rollingBinaryFileWriter;
    private final BinaryLoggerReader<T> binaryLoggerFileReader;
    private final BlockingQueue<BinaryLoggable> linkedBlockingDeque;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ConcurrentNotBlockingBinaryLogger(String fileName, long fileSize) {
        this(fileName, fileSize, DEFAULT_QUEUE_SIZE);

    }

    public ConcurrentNotBlockingBinaryLogger(String fileName, long fileSize, int queueSize) {
        linkedBlockingDeque = new LinkedBlockingDeque<>(queueSize);
        rollingBinaryFileWriter = new RollingBufferedBinaryFileWriter(fileName, fileSize);
        executorService.submit((Runnable) this::write);
        binaryLoggerFileReader = new BinaryLoggerFileReader<>();
    }


    public ConcurrentNotBlockingBinaryLogger(RollingBinaryFileWriter rollingBinaryFileWriter, BinaryLoggerReader<T> binaryLoggerFileReader) {
        this.rollingBinaryFileWriter = rollingBinaryFileWriter;
        this.binaryLoggerFileReader = binaryLoggerFileReader;
        linkedBlockingDeque = new LinkedBlockingDeque<>(DEFAULT_QUEUE_SIZE);
        executorService.submit((Runnable) this::write);
    }


    @Override
    public void write(T loggable) throws IOException {
        if (isWorking) {
            try {
                linkedBlockingDeque.put(loggable);
            } catch (InterruptedException e) {
                throw new RuntimeException("queue was interrupted", e);
            }
        }
    }

    @Override
    public Iterator<T> read(File file, Class<T> clazz) throws IOException {
        return binaryLoggerFileReader.read(file, clazz);
    }

    @SneakyThrows
    private void write() {
        while (isWorking || (linkedBlockingDeque.size() > 0)) {
            BinaryLoggable loggable = linkedBlockingDeque.poll();
            if (nonNull(loggable)) {
                rollingBinaryFileWriter.write(loggable.toBytes());
            }
        }
    }

    @Override
    public void close() throws IOException {
        System.out.println("queue size before = " + linkedBlockingDeque.size());
        isWorking = false;
        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.println("Closing ConcurrentNotBlockingBinaryLogger...");
            System.out.println("queue size " + linkedBlockingDeque.size());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to close service");
            }
        }
        System.out.println("queue size after = " + linkedBlockingDeque.size());

        rollingBinaryFileWriter.close();
        binaryLoggerFileReader.close();
    }
}
