package techtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import techtask.logger.writer.RollingBinaryFileWriter;
import techtask.logger.writer.RollingBufferedBinaryFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertWithMessage;
import static java.util.stream.Collectors.toList;
import static techtask.utils.TestUtils.cleanUpDirectory;

class RollingBinaryWriterTest {
    private final String fileName = "/home/ivan/Development/Git/techtasks/logger-task/src/test/resources/rolling_logger/binary-logger.log";
    private RollingBinaryFileWriter rollingBinaryFileWrite;
    private final List<Event> events = IntStream.range(0, 10)
            .mapToObj(t -> new Event(t, 55L, "SSSS"))
            .collect(toList());
    private final long rollingSize = 1000L;

    @BeforeEach
    public void init() {
        cleanUpDirectory(fileName);
        rollingBinaryFileWrite = new RollingBufferedBinaryFileWriter(fileName, rollingSize);
    }

    @Test
    @DisplayName("Should create rollsize\fileze number of files")
    public void checkRollingPolicy_numberOfRolledFiles() throws IOException {
        int eventSize = events.get(0).toBytes().length;
        long numberOfFiles = rollingSize % eventSize == 0 ? rollingSize / eventSize : rollingSize / eventSize + 1;

        for (Event event : events) {
            rollingBinaryFileWrite.write(event);
        }

        String logFileName = Path.of(fileName).getFileName().toString();
        List<String> files = Files.walk(Path.of(fileName).getParent(), 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(t -> t.startsWith(logFileName))
                .collect(toList());

        assertWithMessage("Number of rolled files are invalid. Rolling failed")
                .that(files.size())
                .isEqualTo(numberOfFiles);
    }
}