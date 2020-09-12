package techtask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Some {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        String fileName = "/home/ivan/IdeaProjects/logger-task/src/test/resources/binary-logger-object.log";
        String fileName1 = "/home/ivan/IdeaProjects/logger-task/src/test/resources/binary-logger2.log";
        Path directory = Path.of(fileName).getParent();
        System.out.println(directory);
//        File f = new File(fileName);
//        f.list()
        long amount = Files.walk(directory, 1)
                .map(Path::getFileName)
                .map(Path::toString)
                .filter(t-> t.startsWith("binary-logger")).count();
        System.out.println(amount);

//
//        Event expectedLoggable = new Event(33, 55L, "AAAA");
//        Event expectedLoggable2 = new Event(44, 755L, "BBBB");
//
//        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(expectedLoggable);
//        objectOutputStream.reset();
////        objectOutputStream.writeUnshared();
//        objectOutputStream.writeObject(expectedLoggable2);
//
//        objectOutputStream.close();
//        fileOutputStream.close();
//
//        fileOutputStream = new FileOutputStream(fileName1);
//        fileOutputStream.write(expectedLoggable.toBytes());
//        fileOutputStream.write(expectedLoggable2.toBytes());
//
//        fileOutputStream.close();
//
//
//
//
//        FileInputStream fileInputStream = new FileInputStream(fileName1);
//        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//        System.out.println("From object 1" + objectInputStream.readObject());
//
//        objectInputStream = new ObjectInputStream(fileInputStream);
//        System.out.println("From object 2" + objectInputStream.readObject());
//
//        objectInputStream = new ObjectInputStream(fileInputStream);
//        System.out.println("From object 2" + objectInputStream.readObject());

//        System.out.println("From object 1" + objectInputStream.readObject());
//        System.out.println("From object 2" + objectInputStream.readObject());
//
//
//        FileInputStream fileInputStream = new FileInputStream(fileName1);//AC ED 00 05
//        for (int i = 0; i < 538; i++) {
//            System.out.print(String.format("%02X ", fileInputStream.read()));
//        }
//        System.out.println();
//         fileInputStream = new FileInputStream(fileName);//79
//        for (int i = 0; i < 535; i++) {
//            System.out.print(String.format("%02X ", fileInputStream.read()));
//        }

//
//        Color c = new Color();
//        c.fromBytes(fileInputStream.readAllBytes());
//        System.out.println("From bytes 1" + c);
//        c.fromBytes(fileInputStream.readAllBytes());
//        System.out.println("From bytes 2" + c);

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ObjectOutputStream os = new ObjectOutputStream(out);
//        os.writeObject(c);
//        byte[] result = out.toByteArray();
//
//        for (int i = 0; i < result.length; i++) {
//            if (i % 10 == 0) {
//                System.out.println();
//            }
//            System.out.print(result[i] + " ");
//        }
    }
}
