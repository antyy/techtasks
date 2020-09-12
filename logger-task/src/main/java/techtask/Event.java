package techtask;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.IOException;
import java.io.Serializable;


@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable, BinaryLoggable {

    public Event(Event other) {
        this.intValue = other.intValue;
        this.longValue = other.longValue;
        this.stringValue = new String(other.stringValue);
    }

    private Integer intValue = 6;
    private Long longValue = 12L;
    private String stringValue = "Some String";

    @Override
    public byte[] toBytes() throws IOException {
//        byte[] stringBytes = stringValue.getBytes();
//        int buffSize = 4 + stringBytes.length + 4 + 8;
//
//        return ByteBuffer.allocate(buffSize)
//                .putInt(stringBytes.length)
//                .putInt(intValue)
//                .putLong(longValue)
//                .put(stringBytes)
//                .array();
        return SerializationUtils.toByteArray(this);
    }

    @Override
    public void fromBytes(byte[] rawBytes) throws IOException {
        try {
            Event other = SerializationUtils.fromByteArray(rawBytes, this.getClass());
            this.setIntValue(other.intValue);
            this.setLongValue(other.getLongValue());
            this.setStringValue(other.getStringValue());

        } catch (ClassNotFoundException c) {
            throw new RuntimeException(c);
        }

    }
}
