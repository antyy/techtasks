package techtask;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import techtask.logger.BinaryLogger;
import techtask.logger.impl.ConcurrentNotBlockingBinaryLogger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static techtask.utils.TestUtils.cleanUpDirectory;

public class ConcurrentTest {
    private final String fileName = Paths.get("src/test/resources/concurrent/binary-logger.log").toAbsolutePath().toString();
    private static final long FILE_SIZE = 1000 * 1024 * 1024;
    private static final long SMALL_FILE = 10240;

    private BinaryLogger<Event> logger;

    @BeforeEach
    public void init() {
        cleanUpDirectory(fileName);
        logger = new ConcurrentNotBlockingBinaryLogger<>(fileName, SMALL_FILE);
    }

    @Test
    @SneakyThrows
    public void performConcurrentTest3() {
        int numberOfEvents = 1000;
        int numberOfThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            service.submit(() -> submitNumberOfEvents(numberOfEvents, logger));
        }
        System.out.println("Task submitted");

        service.shutdown();
        while (!service.isTerminated()) {
            System.out.println("Working...");
            Thread.sleep(1000L);
        }

        logger.close();
        System.out.println("Done writing");
        verifyNumberOfRecords(numberOfThreads * numberOfEvents);
    }


    @SneakyThrows
    public void submitNumberOfEvents(int numberOfEvents, BinaryLogger<Event> logger) {
        System.out.println(Thread.currentThread().getId() + " started");

        for (int i = 0; i < numberOfEvents; i++) {
            logger.write(new Event(i, i + 1l, "SDFD"));
        }

        System.out.println(Thread.currentThread().getId() + " done");
    }

    @SneakyThrows
    public void verifyNumberOfRecords(int numberOfRecords) {
        String logFileName = Path.of(fileName).getFileName().toString();
        List<Path> files = Files.walk(Path.of(fileName).getParent(), 1)
                .filter(t -> t.getFileName().toString().startsWith(logFileName))
                .collect(toList());

        int sum = files.stream()
                .mapToInt(this::getNumberOfRecords)
                .sum();

        assertEquals(numberOfRecords, sum);

    }

    @SneakyThrows
    public int getNumberOfRecords(Path path) {
        AtomicInteger sum = new AtomicInteger(0);
        logger.read(path.toFile(), Event.class).forEachRemaining(t -> sum.incrementAndGet());
        return sum.get();
    }
}
