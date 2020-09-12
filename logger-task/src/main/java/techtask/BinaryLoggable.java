package techtask;

import java.io.IOException;
import java.io.Serializable;

/**
 * BinaryLoggable represents an entity that can be logged by {@link
 * BinaryLogger}.
 */
public interface BinaryLoggable  {
    /**
     * Serialize the fields of this object into a byte array.
     */
    byte[] toBytes() throws IOException;

    /**
     * Deserialize the fields of this object from given byte array.
     */
    void fromBytes(byte[] rawBytes) throws IOException;
}