package techtask;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static techtask.TestUtils.cleanFile;
import static techtask.TestUtils.readObject;

class SimpleBinaryLoggerTest {
    private BinaryLogger<Event> logger;
    private final String fileName = "/home/ivan/IdeaProjects/logger-task/src/test/resources/binary-logger.log";
    private final Event expectedLoggable = new Event(33, 55L, "SSSS");
    private final List<Event> loggableList = Stream.of("sfdsfsf", "dfsdf", "we32e23", "dd23e", "kkjd9dssf")
            .map(t -> new Event(t.length(), t.length() + 4L, t))
            .collect(Collectors.toList());

    @BeforeEach
    public void init() {
        cleanFile(fileName);
        logger = new SimpleBinaryLogger<>(fileName, 5 * 1024);
    }

    @Test
    @DisplayName("Logger should write single object in file correct")
    void write() throws IOException, ClassNotFoundException {
        logger.write(expectedLoggable);
        Event actual = readObject(fileName, Event.class);

        assertEquals(expectedLoggable, actual);
    }

    @Test
    @SneakyThrows
    void read() {
        TestUtils.bulkSaveToFile(loggableList, fileName);
        List<Event> actual = new ArrayList<>();

        logger.read(new File(fileName), Event.class)
                .forEachRemaining(actual::add);

        assertThat(actual)
                .containsExactlyElementsIn(loggableList);

    }

    @Test
    void close() {
    }


}