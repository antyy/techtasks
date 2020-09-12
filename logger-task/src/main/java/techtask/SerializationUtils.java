package techtask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {

    public static byte[] toByteArray(Object o) throws IOException {
        byte[] output;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ObjectOutputStream os = new ObjectOutputStream(out)) {
            os.writeObject(o);
            output = out.toByteArray();
        }
        return output;

    }

    public static <T> T fromByteArray(byte[] arr, Class<T> tClass) throws IOException, ClassNotFoundException {
        T obj;
        try (ByteArrayInputStream in = new ByteArrayInputStream(arr);
             ObjectInputStream is = new ObjectInputStream(in);) {
            obj = tClass.cast(is.readObject());
        }
        return obj;
    }
}
