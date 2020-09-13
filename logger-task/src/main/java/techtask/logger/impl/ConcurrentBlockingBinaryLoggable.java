package techtask.logger.impl;

import techtask.logger.BinaryLoggable;

import java.io.IOException;

public class ConcurrentBlockingBinaryLoggable<T extends BinaryLoggable> extends SimpleBinaryLogger<T> {


    public ConcurrentBlockingBinaryLoggable(String fileName, long fileSize) {
        super(fileName, fileSize);
    }

    @Override
     public void write(T loggable) throws IOException {
        synchronized (this){
            super.write(loggable);
        }
    }
}
