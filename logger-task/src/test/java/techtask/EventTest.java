package techtask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private Event c;


    @BeforeEach
    public void init() {
        c = new Event(1, 2L, "string value");
    }

    @Test
    void toBytes() throws IOException {
        Event expected = new Event(c);
        c.setStringValue("sdfsdf");
        c.setLongValue(4L);

        c.fromBytes(expected.toBytes());

        assertEquals(expected, c);
    }

}