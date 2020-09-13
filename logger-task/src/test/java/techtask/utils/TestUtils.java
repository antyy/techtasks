package techtask.utils;

import lombok.SneakyThrows;
import techtask.Event;
import techtask.logger.BinaryLoggable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUtils {

    @SneakyThrows
    public static void saveToFile(BinaryLoggable loggable, String file) {
        saveToFile(loggable, new File(file));
    }

    @SneakyThrows
    public static void saveToFile(BinaryLoggable loggable, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(loggable.toBytes());
        }
    }


    @SneakyThrows
    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, File file) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            loggables.stream()
                    .map(TestUtils::toBytes)
                    .forEach(data -> write(data, fileOutputStream));
        }
    }


    @SneakyThrows
    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, String file) {
        bulkSaveToFile(loggables, new File(file));
    }

    @SneakyThrows
    private static byte[] toBytes(BinaryLoggable loggable) {
        return loggable.toBytes();
    }

    @SneakyThrows
    private static void write(byte[] data, FileOutputStream fileOutputStream) {
        fileOutputStream.write(data);
    }

    @SneakyThrows
    public static <T> T readObject(File file, Class<T> tClass) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return tClass.cast(objectInputStream.readObject());
    }

    @SneakyThrows
    public static <T> T readObject(String file, Class<T> tClass) {
        return readObject(new File(file), tClass);
    }


    public static void cleanFile(String fileName) {
        File f = new File(fileName);
        if (f.exists() && !f.delete()) {
            throw new RuntimeException("Failed to delete file" + fileName);
        }
    }

    @SneakyThrows

    public static void cleanUpDirectory(String fileName) {

        String file = Path.of(fileName).getFileName().toString();
        Files.walk(Path.of(fileName).getParent())
                .map(Path::toFile)
                .filter(File::isFile)
                .forEach(File::delete);

    }

    public static List<Event> createEvents(int numberOfEvents) {
        return IntStream.range(0, numberOfEvents)
                .mapToObj(t -> new Event(t, t + 11L, "sdom"))
                .collect(Collectors.toList());
    }

    public static List<Event> createLargeEvents(int numberOfEvents) {
        return IntStream.range(0, numberOfEvents)
                .mapToObj(t -> new Event(t, t + 11L, "sdpackage techtask.utils;\n" +
                        "\n" +
                        "import lombok.SneakyThrows;\n" +
                        "import techtask.logger.BinaryLoggable;\n" +
                        "import techtask.Event;\n" +
                        "\n" +
                        "import java.io.File;\n" +
                        "import java.io.FileInputStream;\n" +
                        "import java.io.FileOutputStream;\n" +
                        "import java.io.ObjectInputStream;\n" +
                        "import java.nio.file.Files;\n" +
                        "import java.nio.file.Path;\n" +
                        "import java.util.List;\n" +
                        "import java.util.stream.Collectors;\n" +
                        "import java.util.stream.IntStream;\n" +
                        "\n" +
                        "public class TestUtils {\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, String file) {\n" +
                        "        saveToFile(loggable, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            fileOutputStream.write(loggable.toBytes());\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            loggables.stream()\n" +
                        "                    .map(TestUtils::toBytes)\n" +
                        "                    .forEach(data -> write(data, fileOutputStream));\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, String file) {\n" +
                        "        bulkSaveToFile(loggables, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static byte[] toBytes(BinaryLoggable loggable) {\n" +
                        "        return loggable.toBytes();\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static void write(byte[] data, FileOutputStream fileOutputStream) {\n" +
                        "        fileOutputStream.write(data);\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(File file, Class<T> tClass) {\n" +
                        "        FileInputStream fileInputStream = new FileInputStream(file);\n" +
                        "        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);\n" +
                        "        return tClass.cast(objectInputStream.readObject());\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(String file, Class<T> tClass) {\n" +
                        "        return readObject(new File(file), tClass);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    public static void cleanFile(String fileName) {\n" +
                        "        File f = new File(fileName);\n" +
                        "        if (f.exists() && !f.delete()) {\n" +
                        "            throw new RuntimeException(\"Failed to delete file\" + fileName);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "\n" +
                        "    public static void cleanUpDirectory(String fileName) {\n" +
                        "\n" +
                        "        String file = Path.of(fileName).getFileName().toString();\n" +
                        "        Files.walk(Path.of(fileName).getParent())\n" +
                        "                .map(Path::toFile)\n" +
                        "                .filter(File::isFile)\n" +
                        "                .forEach(File::delete);\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createEvents(int numberOfEvents){\n" +
                        "       return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createLargeEvents(int numberOfEvents){\n" +
                        "        return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "}\npackage techtask.utils;\n" +
                        "\n" +
                        "import lombok.SneakyThrows;\n" +
                        "import techtask.logger.BinaryLoggable;\n" +
                        "import techtask.Event;\n" +
                        "\n" +
                        "import java.io.File;\n" +
                        "import java.io.FileInputStream;\n" +
                        "import java.io.FileOutputStream;\n" +
                        "import java.io.ObjectInputStream;\n" +
                        "import java.nio.file.Files;\n" +
                        "import java.nio.file.Path;\n" +
                        "import java.util.List;\n" +
                        "import java.util.stream.Collectors;\n" +
                        "import java.util.stream.IntStream;\n" +
                        "\n" +
                        "public class TestUtils {\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, String file) {\n" +
                        "        saveToFile(loggable, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            fileOutputStream.write(loggable.toBytes());\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            loggables.stream()\n" +
                        "                    .map(TestUtils::toBytes)\n" +
                        "                    .forEach(data -> write(data, fileOutputStream));\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, String file) {\n" +
                        "        bulkSaveToFile(loggables, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static byte[] toBytes(BinaryLoggable loggable) {\n" +
                        "        return loggable.toBytes();\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static void write(byte[] data, FileOutputStream fileOutputStream) {\n" +
                        "        fileOutputStream.write(data);\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(File file, Class<T> tClass) {\n" +
                        "        FileInputStream fileInputStream = new FileInputStream(file);\n" +
                        "        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);\n" +
                        "        return tClass.cast(objectInputStream.readObject());\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(String file, Class<T> tClass) {\n" +
                        "        return readObject(new File(file), tClass);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    public static void cleanFile(String fileName) {\n" +
                        "        File f = new File(fileName);\n" +
                        "        if (f.exists() && !f.delete()) {\n" +
                        "            throw new RuntimeException(\"Failed to delete file\" + fileName);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "\n" +
                        "    public static void cleanUpDirectory(String fileName) {\n" +
                        "\n" +
                        "        String file = Path.of(fileName).getFileName().toString();\n" +
                        "        Files.walk(Path.of(fileName).getParent())\n" +
                        "                .map(Path::toFile)\n" +
                        "                .filter(File::isFile)\n" +
                        "                .forEach(File::delete);\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createEvents(int numberOfEvents){\n" +
                        "       return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createLargeEvents(int numberOfEvents){\n" +
                        "        return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "}\npackage techtask.utils;\n" +
                        "\n" +
                        "import lombok.SneakyThrows;\n" +
                        "import techtask.logger.BinaryLoggable;\n" +
                        "import techtask.Event;\n" +
                        "\n" +
                        "import java.io.File;\n" +
                        "import java.io.FileInputStream;\n" +
                        "import java.io.FileOutputStream;\n" +
                        "import java.io.ObjectInputStream;\n" +
                        "import java.nio.file.Files;\n" +
                        "import java.nio.file.Path;\n" +
                        "import java.util.List;\n" +
                        "import java.util.stream.Collectors;\n" +
                        "import java.util.stream.IntStream;\n" +
                        "\n" +
                        "public class TestUtils {\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, String file) {\n" +
                        "        saveToFile(loggable, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void saveToFile(BinaryLoggable loggable, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            fileOutputStream.write(loggable.toBytes());\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, File file) {\n" +
                        "        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {\n" +
                        "            loggables.stream()\n" +
                        "                    .map(TestUtils::toBytes)\n" +
                        "                    .forEach(data -> write(data, fileOutputStream));\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static void bulkSaveToFile(List<? extends BinaryLoggable> loggables, String file) {\n" +
                        "        bulkSaveToFile(loggables, new File(file));\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static byte[] toBytes(BinaryLoggable loggable) {\n" +
                        "        return loggable.toBytes();\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    private static void write(byte[] data, FileOutputStream fileOutputStream) {\n" +
                        "        fileOutputStream.write(data);\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(File file, Class<T> tClass) {\n" +
                        "        FileInputStream fileInputStream = new FileInputStream(file);\n" +
                        "        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);\n" +
                        "        return tClass.cast(objectInputStream.readObject());\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "    public static <T> T readObject(String file, Class<T> tClass) {\n" +
                        "        return readObject(new File(file), tClass);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    public static void cleanFile(String fileName) {\n" +
                        "        File f = new File(fileName);\n" +
                        "        if (f.exists() && !f.delete()) {\n" +
                        "            throw new RuntimeException(\"Failed to delete file\" + fileName);\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    @SneakyThrows\n" +
                        "\n" +
                        "    public static void cleanUpDirectory(String fileName) {\n" +
                        "\n" +
                        "        String file = Path.of(fileName).getFileName().toString();\n" +
                        "        Files.walk(Path.of(fileName).getParent())\n" +
                        "                .map(Path::toFile)\n" +
                        "                .filter(File::isFile)\n" +
                        "                .forEach(File::delete);\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createEvents(int numberOfEvents){\n" +
                        "       return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "\n" +
                        "    public static List<Event> createLargeEvents(int numberOfEvents){\n" +
                        "        return IntStream.range(0, numberOfEvents)\n" +
                        "                .mapToObj(t -> new Event(t, t + 11L, \"sdom\"))\n" +
                        "                .collect(Collectors.toList());\n" +
                        "    }\n" +
                        "}\nom"))
                .collect(Collectors.toList());
    }
}
