package techtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static techtask.TestUtils.cleanFile;

class RollingBinaryFileWriterTest {
    private final String fileName = "/home/ivan/IdeaProjects/logger-task/src/test/resources/binary-logger.log";
    private final Event expectedLoggable = new Event(33, 55L, "SSSS");
    private  RollingBinaryFileWriter rollingBinaryFileWrite;


    @BeforeEach
    public void init(){
        cleanFile(fileName);
        rollingBinaryFileWrite = new RollingBinaryFileWriter(fileName, 500);
    }

    @Test
    public void testRolling() throws IOException {
        for (int i = 0; i < 10; i++) {
            rollingBinaryFileWrite.write(expectedLoggable);
        }
    }
}