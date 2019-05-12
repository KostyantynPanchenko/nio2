import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("d:/TomcatRemoteDebug.jpg");
        System.out.println("Path = " + path.toString());
        File file = new File(path.toString());
        System.out.println("File exists = " + file.exists());
        Path dirPath = Paths.get(".");
        File dir = dirPath.toFile();
        System.out.println("\n");
        System.out.println("Dir '.' exists          = " + dir.exists());
        System.out.println("DirPath getFileName()   = " + dirPath.getFileName());
        System.out.println("DirPath normalized      = " + dirPath.normalize().toAbsolutePath());
        System.out.println("Dir info                = " + dir.getName() + ", " + dir.getAbsolutePath());
        System.out.println("DirPath for '/' info    = " + Paths.get("/").normalize().toAbsolutePath());
        System.out.println("DirPath for '..' info   = " + Paths.get("../").normalize().toAbsolutePath());
        System.out.println("DirPath for 'nio2' info = " + Paths.get("../nio2").normalize().toAbsolutePath());

        File nio = Paths.get(".").toFile();
        System.out.println("nio.exists() = " + nio.exists());
        System.out.println(nio.getAbsolutePath());

        File read = Paths.get("resources/read.txt").toFile();
        System.out.println("read.txt exists = " + read.exists());

        int count;
        try (SeekableByteChannel fileChannel = Files.newByteChannel(Paths.get("resources/read.txt"))) {
            ByteBuffer buffer = ByteBuffer.allocate(128);
            do {
                count = fileChannel.read(buffer);
                if (count != -1) {
//                    buffer.rewind();
//                    IntStream.range(0, count).forEach(b -> System.out.print((char) buffer.get()));
                    buffer.flip();
                    byte[] dest = new byte[count];
                    buffer.get(dest);
                    System.out.println(new String(dest));
                }
            } while(count != -1);
        } catch (InvalidPathException pe) {
            System.out.println("Path exception");
        } catch (IOException ioe) {
            System.out.println("IO exception");
        }

    }
}
